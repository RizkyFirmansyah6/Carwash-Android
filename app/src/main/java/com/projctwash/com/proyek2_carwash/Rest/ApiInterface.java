package com.projctwash.com.proyek2_carwash.Rest;


import com.projctwash.com.proyek2_carwash.Model.EventCheckUser;
import com.projctwash.com.proyek2_carwash.Model.GetEvent;
import com.projctwash.com.proyek2_carwash.Model.GetKaryawan;
import com.projctwash.com.proyek2_carwash.Model.GetKendaraan;
import com.projctwash.com.proyek2_carwash.Model.GetKondisi;
import com.projctwash.com.proyek2_carwash.Model.GetMember;
import com.projctwash.com.proyek2_carwash.Model.GetTransaksi;
import com.projctwash.com.proyek2_carwash.Model.PostPutDellEvent;
import com.projctwash.com.proyek2_carwash.Model.PostPutDellKaryawan;
import com.projctwash.com.proyek2_carwash.Model.PostPutDellKendaraan;
import com.projctwash.com.proyek2_carwash.Model.PostPutDellKondisi;
import com.projctwash.com.proyek2_carwash.Model.PostPutDellMember;
import com.projctwash.com.proyek2_carwash.Model.PostputDellTransaksi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {

//    Login
    @FormUrlEncoded
    @POST("Login")
    Call<PostPutDellKaryawan> getLogin(@Field("nohp") String nohp,
                                       @Field("password") String password);

//    Kendaraan
    @GET("Kendaraan")
    Call<GetKendaraan> getKendaraan();

    @FormUrlEncoded
    @POST("Kendaraan")
    Call<PostPutDellKendaraan> postKendaraan(@Field("nama") String nama,
                                             @Field("harga") String harga,
                                             @Field("img") String img);
    @FormUrlEncoded
    @PUT("Kendaraan")
    Call<PostPutDellKendaraan> putKendaraan(@Field("id") String id,
                                            @Field("nama") String nama,
                                            @Field("harga") String harga,
                                            @Field("img") String img);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Kendaraan", hasBody = true)
    Call<PostPutDellKendaraan> deleteKendaraan(@Field("id") String id);
//      Kendaraan END

//    Member Start
    @GET("Member")
    Call<GetMember> getMember();

    @FormUrlEncoded
    @POST("Member")
    Call<PostPutDellMember> postMember(@Field("nama") String nama,
                                         @Field("no_telp") String no_telp,
                                         @Field("alamat") String alamat,
                                         @Field("jenis") String jenis);
    @FormUrlEncoded
    @PUT("Member")
    Call<PostPutDellMember> putMember(@Field("id") String id,
                                          @Field("nama") String nama,
                                          @Field("no_telp") String no_telp,
                                          @Field("alamat") String alamat,
                                          @Field("jenis") String jenis);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Member", hasBody = true)
    Call<PostPutDellMember> deleteMember(@Field("id") String id);
//  Karyawan END

    //    Karyawan Start
    @GET("Karyawan")
    Call<GetKaryawan> getKaryawan();

    @FormUrlEncoded
    @POST("Karyawan")
    Call<PostPutDellKaryawan> postKaryawan(@Field("nama") String nama,
                                           @Field("nohp") String nohp,
                                           @Field("alamat") String alamat,
                                           @Field("password") String password,
                                           @Field("level") String level);
    @FormUrlEncoded
    @PUT("Karyawan")
    Call<PostPutDellKaryawan> putKaryawan(@Field("id") String id,
                                          @Field("nama") String nama,
                                          @Field("nohp") String nohp,
                                          @Field("alamat") String alamat,
                                          @Field("password") String password,
                                          @Field("level") String level);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Karyawan", hasBody = true)
    Call<PostPutDellKaryawan> deleteKaryawan(@Field("id") String id);
//  Karyawan END

//    Kondisi Motor Start
    @GET("Kondisi")
    Call<GetKondisi> getKondisi();

    @FormUrlEncoded
    @POST("kondisi")
    Call<PostPutDellKondisi> postKondisi(@Field("nama") String nama,
                                         @Field("harga") String harga,
                                         @Field("img") String img);
    @FormUrlEncoded
    @PUT("kondisi")
    Call<PostPutDellKondisi> putKondisi(@Field("id") String id,
                                            @Field("nama") String nama,
                                            @Field("harga") String harga,
                                            @Field("img") String img);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "kondisi", hasBody = true)
    Call<PostPutDellKondisi> deleteKondisi(@Field("id") String id);


//        Transaksi Start
    @GET("Transaksi")
    Call<GetTransaksi> getTransaksi();

    @FormUrlEncoded
    @POST("UserTransaksi")
    Call<GetTransaksi> getTransaksiBy  (@Field("kasir") String kasir,
                                        @Field("where") String where);

    @FormUrlEncoded
    @POST("Transaksi")
    Call<PostputDellTransaksi> postTransaksi(@Field("nopol") String nopol,
                                             @Field("kendaraan") String kendaraan,
                                             @Field("harga_kendaraan") String harga_kendaraan,
                                             @Field("kondisi") String kondisi,
                                             @Field("harga_kondisi") String harga_kondisi,
                                             @Field("total") String total,
                                             @Field("kasir") String kasir);
    @FormUrlEncoded
    @PUT("Transaksi")
    Call<PostputDellTransaksi> putTransaksi(@Field("id") String id,
                                            @Field("nopol") String nopol,
                                            @Field("kendaraan") String kendaraan,
                                            @Field("harga_kendaraan") String harga_kendaraan,
                                            @Field("kondisi") String kondisi,
                                            @Field("harga_kondisi") String harga_kondisi,
                                            @Field("total") String total,
                                            @Field("kasir") String kasir);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Transaksi", hasBody = true)
    Call<PostputDellTransaksi> deleteTransaksi(@Field("id") String id);
//  Transaksi END


//    EventUSer
    @GET("Event/eventuser")
    Call<GetEvent> getEventUser();
    @GET("Event")
    Call<GetEvent> getEventAdmin();

    //    EventUSerCheckstatus
    @FormUrlEncoded
    @POST("Event/checkevent")
    Call<EventCheckUser> getEventUserCheck(@Field("nopol") String nopol,
                                           @Field("bln") String bln);
    @FormUrlEncoded
    @POST("Event/newEvent")
    Call<PostPutDellEvent> newEvent(@Field("nama_event") String nama_event,
                                    @Field("img") String img,
                                    @Field("detail") String detail,
                                    @Field("diskon") String diskon,
                                    @Field("required") String required,
                                    @Field("bulan") String bulan,
                                    @Field("status") String status);
//  put status
    @FormUrlEncoded
    @PUT("Event/gantiStatusEvent")
    Call<PostPutDellEvent> putStatusEvent(@Field("id") String id,
                                        @Field("status") String status);
}
