package ir.irancell.application;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;

import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModemSelectorDialog extends Dialog {
    private Activity activity;
    private FastItemAdapter fastAdapter;
    private Retrofit retrofit;
    private ApiInterface apiService;
    private Dialog dialog;

    public ModemSelectorDialog(Activity activity) {
        super(activity);
        this.activity = activity;
        this.dialog = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_custom);


        RecyclerView recyclerView = findViewById(R.id.modem_selector_recylcerview);
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(llm); // set LayoutManager to RecyclerView

        //create our FastAdapter which will manage everything
        fastAdapter = new FastItemAdapter();
        //set our adapters to the RecyclerView
        //we wrap our FastAdapter inside the ItemAdapter -> This allows us to chain adapters for more complex useCases
        recyclerView.setAdapter(fastAdapter);

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();
        apiService = retrofit.create(ApiInterface.class);
    }

    @Override
    public void show() {
        super.show();
        getModems();
        Log.d("SKings", "salam2");
    }

    @Override
    public void dismiss() {
        super.dismiss();
        fastAdapter.clear();
    }

    private void getModems() {
        apiService.get("/modems").enqueue(new AsyncRetrofitCallback<ResponseBody>(activity) {
            JSONArray result = null;

            @Override
            public void onPreResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onBackgroundResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("SKings", "salam");
                try {
                    result = new JSONArray(response.body().string());
                    Log.d("SKings", result.toString());
                } catch (Exception e) {
                    Log.d("SKings", "getModesm@onbackground");
                }
            }

            @Override
            public void onPostResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject modem = result.getJSONObject(i);
                        fastAdapter.add(new ModemItem(modem.getString("business_name"), activity,
                                modem.getString("_id"), dialog));
                    }
                } catch (Exception e) {
                    Log.d("SKings", "getModems@onPostResponse");
                }
            }

            @Override
            public void onPreFailure(Call<ResponseBody> call, Throwable throwable) {

            }

            @Override
            public void onBackgroundFailure(Call<ResponseBody> call, Throwable throwable) {

            }

            @Override
            public void onPostFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });


    }

}