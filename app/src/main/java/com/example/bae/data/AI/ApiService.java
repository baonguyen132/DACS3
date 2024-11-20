package com.example.bae.data.AI;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @Multipart
    @POST("/upload_image")
    Call<ResponseDataAIModel> uploadImage(@Part MultipartBody.Part image);

}