package com.projctwash.com.proyek2_carwash.Rest;


import com.projctwash.com.proyek2_carwash.Model.GetKendaraan;
import com.projctwash.com.proyek2_carwash.Model.PostPutGetKendaraan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {

//    Kendaraan
    @GET("Kendaraan_android")
    Call<GetKendaraan> getKendaraan();

    @FormUrlEncoded
    @POST("Kendaraan")
    Call<PostPutGetKendaraan> postKendaraan(@Field("nama") String nama,
                                         @Field("harga") String harga,
                                         @Field("img") String img);
    @FormUrlEncoded
    @PUT("Kendaraan")
    Call<PostPutGetKendaraan> putKendaraan(@Field("id") String id,
                                        @Field("nama") String nama,
                                        @Field("harga") String harga,
                                        @Field("img") String img);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "kontak", hasBody = true)
    Call<PostPutGetKendaraan> deleteKontak(@Field("id") String id);
//      Kendaraan END
}
