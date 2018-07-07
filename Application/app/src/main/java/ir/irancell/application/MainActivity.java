package ir.irancell.application;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FastItemAdapter fastAdapter;
    private Retrofit retrofit;
    private ApiInterface apiService;
    private int state = 0;
    private ModemSelectorDialog modemSelectorDialog;
    private Typeface tf;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        tf = Typeface.createFromAsset(getAssets(), "font.ttf");
//        ((TextView) findViewById(R.id.business_name_textview)).setTypeface(tf);

        // recycler view
        recyclerView = findViewById(R.id.recyclerview);
        DividerItemDecoration divider = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.addItemDecoration(divider);
        recyclerView.setScrollbarFadingEnabled(false);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm); // set LayoutManager to RecyclerView

        //create our FastAdapter which will manage everything
        fastAdapter = new FastItemAdapter();

        //set our adapters to the RecyclerView
        //we wrap our FastAdapter inside the ItemAdapter -> This allows us to chain adapters for more complex useCases
        recyclerView.setAdapter(fastAdapter);

        // webview
        webView = findViewById(R.id.speed_test_webview);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView.loadUrl(Constant.SPEED_TEST_URL);

        // building retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();
        apiService = retrofit.create(ApiInterface.class);


        //listen to onclick listeners
        ((ImageView) findViewById(R.id.modem_spec_imageview)).setColorFilter(Color.parseColor("#FFBE00"));
        findViewById(R.id.modem_spec_imageview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state != 0) {
                    state = 0;
                    findViewById(R.id.modem_spec_layout).setVisibility(View.VISIBLE);
                    findViewById(R.id.webview_layout).setVisibility(View.GONE);

                    findViewById(R.id.modem_spec_imageview).setBackgroundColor(getResources().getColor(R.color.shadow));
                    ((ImageView) findViewById(R.id.modem_spec_imageview)).setColorFilter(Color.parseColor("#FFBE00"));

                    findViewById(R.id.test_speed_imageview).setBackgroundColor(getResources().getColor(R.color.transparent));
                    ((ImageView) findViewById(R.id.test_speed_imageview)).setColorFilter(Color.parseColor("#000000"));


                }
            }
        });

        findViewById(R.id.test_speed_imageview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state != 1) {
                    state = 1;
                    findViewById(R.id.modem_spec_layout).setVisibility(View.GONE);
                    findViewById(R.id.webview_layout).setVisibility(View.VISIBLE);
                    findViewById(R.id.modem_spec_imageview).setBackgroundColor(getResources().getColor(R.color.transparent));
                    ((ImageView) findViewById(R.id.modem_spec_imageview)).setColorFilter(Color.parseColor("#000000"));

                    findViewById(R.id.test_speed_imageview).setBackgroundColor(getResources().getColor(R.color.shadow));
                    ((ImageView) findViewById(R.id.test_speed_imageview)).setColorFilter(Color.parseColor("#FFBE00"));

                    webView.loadUrl(Constant.SPEED_TEST_URL);
                }
            }
        });

        // modem selector
        modemSelectorDialog = new ModemSelectorDialog(MainActivity.this);
        modemSelectorDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                String modemId = readShared();
                if (modemId != null) {
                    getModem(modemId);
                }
            }
        });

        findViewById(R.id.hidden_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modemSelectorDialog.show();
            }
        });

        String modemId = readShared();
        if (modemId != null) {
            getModem(modemId);
        } else {
            modemSelectorDialog.show();
        }
    }

    private void getModem(String id) {
        fastAdapter.clear();
        apiService.get("/modem/get/" + id).enqueue(new AsyncRetrofitCallback<ResponseBody>(MainActivity.this) {
            JSONObject result = null;

            @Override
            public void onPreResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onBackgroundResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    result = new JSONObject(response.body().string());
                    Log.d("SKings", result.toString());
                } catch (Exception e) {
                    Log.d("SKings", "getModem@onbackground");
                    e.printStackTrace();
                }
            }

            @Override
            public void onPostResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (result.has("image")) {
                        Glide.with(MainActivity.this).load(Constant.BASE_URL + result.getString("image"))
                                .apply(RequestOptions.fitCenterTransform().placeholder(R.drawable.modem))
                                .into((ImageView) findViewById(R.id.modem_imageview));
                    }
                    fastAdapter.add(new SimpleItem("نام تجاری", result.getString("business_name"), tf));
                    JSONArray features = result.getJSONArray("features");
                    JSONObject feature = null;
                    for (int i = 0; i < features.length(); i++) {
                        feature = features.getJSONObject(i);
                        if (!(feature.getString("value").equalsIgnoreCase("")
                                || feature.getString("value") == null
                                || feature.getString("value").equalsIgnoreCase("null"))) {
                            fastAdapter.add(new SimpleItem(feature.getString("farsi"),
                                    feature.getString("value"), tf));
                        }
                    }
                } catch (Exception e) {
                    Log.d("SKings", "getModem@onPostResponse");
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
    }

    private String readShared() {
        SharedPreferences prefs = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString(Constant.MODEM_ID, null);
        return restoredText;
    }

}
