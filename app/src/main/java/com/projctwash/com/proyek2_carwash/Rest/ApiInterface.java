package com.projctwash.com.proyek2_carwash.Rest;


import com.projctwash.com.proyek2_carwash.Model.GetKaryawan;
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
    @GET("Kendaraan")
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
    @HTTP(method = "DELETE", path = "Kendaraan", hasBody = true)
    Call<PostPutGetKendaraan> deleteKendaraan(@Field("id") String id);
//      Kendaraan END

//    Karyawan Start
    @GET("Karyawan")
    Call<GetKaryawan> getKaryawan();

    @FormUrlEncoded
    @POST("Karyawan")
    Call<PostPutGetKendaraan> postKaryawan(@Field("nama") String nama,
                                           @Field("nohp") String nohp,
                                           @Field("alamat") String alamat,
                                           @Field("password") String password);
    @FormUrlEncoded
    @PUT("Karyawan")
    Call<PostPutGetKendaraan> putKaryawan(@Field("id") String id,
                                          @Field("nama") String nama,
                                          @Field("nohp") String nohp,
                                          @Field("alamat") String alamat,
                                          @Field("password") String password);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Karyawan", hasBody = true)
    Call<PostPutGetKendaraan> deleteKaryawan(@Field("id") String id);
//  Karyawan END

}
