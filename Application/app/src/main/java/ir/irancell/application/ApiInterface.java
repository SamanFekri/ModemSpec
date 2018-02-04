package ir.irancell.application;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

interface ApiInterface
{
    @GET
    Call<ResponseBody> get(@Url String url);

    @GET
    Call<ResponseBody> get(@Url String url, @QueryMap HashMap<String, String> params);

    @GET
    Call<ResponseBody> get(@Url String url, @HeaderMap HashMap<String, String> headers, @QueryMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> post(@Url String url);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> post(@Url String url, @FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> post(@Url String url, @HeaderMap HashMap<String, String> headers, @FieldMap HashMap<String, String> params);
}
