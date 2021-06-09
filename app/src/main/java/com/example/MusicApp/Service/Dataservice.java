package com.example.MusicApp.Service;

import com.example.MusicApp.Model.Album;
import com.example.MusicApp.Model.BaiHat;
import com.example.MusicApp.Model.BangXepHang;
import com.example.MusicApp.Model.ChuDe;
import com.example.MusicApp.Model.Chudevatheloai;
import com.example.MusicApp.Model.NgheSi;
import com.example.MusicApp.Model.Playlist;
import com.example.MusicApp.Model.Quangcao;
import com.example.MusicApp.Model.TheLoai;
import com.example.MusicApp.Model.ThinhHanh;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("quangcaocurrent.php")
    Call<List<Quangcao>> GetQuangCao();

    @GET("playlistcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("danhsachplaylist.php")
    Call<List<Playlist>> GetDanhSachPlaylist();

    @GET("nghesicurrent.php")
    Call<List<NgheSi>> GetNgheSiCurrent();

    @GET("thinhhanhcurrent.php")
    Call<List<ThinhHanh>> GetThinhHanhCurrent();



    @GET("chudecurrent.php")
    Call<List<ChuDe>> GetAllChuDe();

    @GET("bangxephangcurrent.php")
    Call<List<BangXepHang>> GetBangXepHangCurrent();

    @GET("chudevatheloaicurrent.php")
    Call<Chudevatheloai> GetChudeTheLoai();

    @GET("albumhot.php")
    Call <List<Album>> GetAlbumhot();

    @GET("tatcaalbum.php")
    Call <List<Album>> GetAllAlbum();

    @GET("baihatduocthich.php")
    Call <List<BaiHat>> GetBaiHatYeuThich();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatplaylist(@Field("idplaylist") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatnghesi(@Field("idnghesi") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatthinhhanh(@Field("idthinhhanh") String id);



    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatchude(@Field("idchude") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheloai(@Field("idtheloai") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatbangxephang(@Field("idbangxephang") String id);

    @FormUrlEncoded
    @POST("timkiembaihat.php")
    Call<List<BaiHat>> GetTimKiembaihat(@Field("tukhoa") String tukhoa);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetDanhSachTheLoaiTheoChuDe(@Field("idchude") String idchude);


    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich,@Field("idbaihat") String idbaihat);

}
