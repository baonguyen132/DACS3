package com.example.bae.data.AI;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestDataAI {
    private Context context ;
    private static RequestDataAI requestDataAI ;
    private static String linkAI = "https://042f-2401-d800-95ae-3cdd-84e6-dc5-b810-ac8f.ngrok-free.app" ;
    public static void init(Context context){
        requestDataAI = new RequestDataAI() ;
        requestDataAI.context = context ;
    }

    public static RequestDataAI getInstance() {
        if(requestDataAI == null){
            requestDataAI = new RequestDataAI() ;
        }
        return requestDataAI ;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static RequestDataAI getRequestDataAI() {
        return requestDataAI;
    }

    public static void setRequestDataAI(RequestDataAI requestDataAI) {
        RequestDataAI.requestDataAI = requestDataAI;
    }

    public static String getLinkAI() {
        return linkAI;
    }

    public static void setLinkAI(String linkAI) {
        RequestDataAI.linkAI = linkAI;
    }

    public static void uploadImage(File file, String  nameNewFile ,hanldeResultUpload hanldeResult) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RequestDataAI.getLinkAI())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", nameNewFile+".jpg", requestBody);

        apiService.uploadImage(part).enqueue(new Callback<ResponseDataAIModel>() {
            @Override
            public void onResponse(Call<ResponseDataAIModel> call, retrofit2.Response<ResponseDataAIModel> response) {
                if (response.isSuccessful()) {
                    ResponseDataAIModel responseData = response.body();

                    JsonParser jsonParser = new JsonParser() ;
                    JsonElement jsonElement = jsonParser.parse(responseData.getMessage());

                    JsonObject jsonObject = jsonElement.getAsJsonObject() ;

                    hanldeResult.hanldeResult(jsonObject);

                }
            }

            @Override
            public void onFailure(Call<ResponseDataAIModel> call, Throwable t) {
                t.printStackTrace();
            }
        });



    }

    public interface hanldeResultUpload {
        void hanldeResult(JsonObject data) ;
    }
}
