package com.example.MusicApp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MusicApp.Adapter.DanhsachbaihatAdapter;
import com.example.MusicApp.Model.BaiHat;
import com.example.MusicApp.Model.BangXepHang;
import com.example.MusicApp.Model.ChuDe;
import com.example.MusicApp.Model.NgheSi;
import com.example.MusicApp.Model.PhoBien;
import com.example.MusicApp.Model.Playlist;
import com.example.MusicApp.Model.Quangcao;
import com.example.MusicApp.Model.TheLoai;
import com.example.MusicApp.Model.ThinhHanh;
import com.example.MusicApp.R;
import com.example.MusicApp.Service.APIService;
import com.example.MusicApp.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    TextView txtcollapsing;
    Playlist playlist = null;
    NgheSi ngheSi = null;
    PhoBien phoBien = null;
    ThinhHanh thinhHanh = null;
    ChuDe chuDe = null;
    BangXepHang bangXepHang = null;
    Quangcao quangcao = null;
    ImageView imgdanhsachcakhuc;
    ArrayList<BaiHat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    TheLoai theLoai = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        AnhXa();
        init();
        floatingActionButton.setEnabled(false);
        DataIntent();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        if (ngheSi != null && !ngheSi.equals("")){
            setValueInView(ngheSi.getTenNgheSi(), ngheSi.getHinhNgheSi());
            GetDataNgheSi(ngheSi.getIdNgheSi());
            txtcollapsing.setText(ngheSi.getTenNgheSi());
            getSupportActionBar().setTitle(ngheSi.getTenNgheSi());
        }
        if (quangcao != null && !quangcao.equals("")){
            setValueInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangCao(quangcao.getId());
            txtcollapsing.setText(quangcao.getTenBaiHat());
            getSupportActionBar().setTitle(quangcao.getTenBaiHat());
        }
        if (playlist != null && !playlist.equals("")){
            setValueInView(playlist.getTen(), playlist.getHinhPlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
            txtcollapsing.setText(playlist.getTen());
            getSupportActionBar().setTitle(playlist.getTen());
        }
        if (theLoai != null && !theLoai.equals("")){
            setValueInView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
            txtcollapsing.setText(theLoai.getTenTheLoai());
            getSupportActionBar().setTitle(theLoai.getTenTheLoai());
        }

        if (thinhHanh != null && !thinhHanh.equals("")){
            setValueInView(thinhHanh.getTenThinhHanh(), thinhHanh.getHinhThinhHanh());
            GetDataThinhHanh(thinhHanh.getIdThinhHanh());
            txtcollapsing.setText(thinhHanh.getTenThinhHanh());
            getSupportActionBar().setTitle(thinhHanh.getTenThinhHanh());
        }
        if (phoBien != null && !phoBien.equals("")){
            setValueInView(phoBien.getTenPhoBien(), phoBien.getHinhPhoBien());
            GetDataPhoBien(phoBien.getIdPhoBien());
            txtcollapsing.setText(phoBien.getTenPhoBien());
            getSupportActionBar().setTitle(phoBien.getTenPhoBien());
        }
        if (chuDe != null && !chuDe.equals("")){
            setValueInView(chuDe.getTenChuDe(), chuDe.getHinhChuDe());
            GetDataChuDe(chuDe.getIdChuDe());
            txtcollapsing.setText(chuDe.getTenChuDe());
            getSupportActionBar().setTitle(chuDe.getTenChuDe());
        }
        if (bangXepHang != null && !bangXepHang.equals("")){
            setValueInView(bangXepHang.getTenBangXepHang(), bangXepHang.getHinhBangXepHang());
            GetDataBangXepHang(bangXepHang.getIdBangXepHang());
            txtcollapsing.setText(bangXepHang.getTenBangXepHang());
            getSupportActionBar().setTitle(bangXepHang.getTenBangXepHang());
        }

        floatActionButtonClick();

    }

    private void GetDataTheLoai(String idTheLoai) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihattheloai(idTheLoai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }


    private void setValueInView(String ten, String hinh) {
          try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        collapsingToolbarLayout.setTitle(ten);

        Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
    }

    private void GetDataQuangCao(String idquangcao  ) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }
            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataPlaylist(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatplaylist(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }
            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataBangXepHang(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatbangxephang(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataChuDe(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatchude(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataNgheSi(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatnghesi(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataPhoBien(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatphobien(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataThinhHanh(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatthinhhanh(id);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsachbaihat);
        recyclerViewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsachbaihat);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        txtcollapsing = findViewById(R.id.textViewcollapsing);


    }
    private void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }
    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("intentplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("intentplaylist");
            }else
            if (intent.hasExtra("intentnghesi")){
                ngheSi = (NgheSi) intent.getSerializableExtra("intentnghesi");
            }else
            if (intent.hasExtra("intentthinhhanh")){
                thinhHanh = (ThinhHanh) intent.getSerializableExtra("intentthinhhanh");
            }else
            if (intent.hasExtra("intentphobien")){
                phoBien = (PhoBien) intent.getSerializableExtra("intentphobien");
            }else
            if (intent.hasExtra("intentchude")){
                chuDe = (ChuDe) intent.getSerializableExtra("intentchude");
            }else
            if (intent.hasExtra("intentbangxephang")){
                bangXepHang = (BangXepHang) intent.getSerializableExtra("intentbangxephang");
            }
            else
            if(intent.hasExtra("banner")){
                quangcao=(Quangcao) intent.getSerializableExtra("banner");
                //Toast.makeText(this,quangcao.getTenBaiHat(),Toast.LENGTH_SHORT).show();
            }
            else
            if (intent.hasExtra("idtheloai")){
                theLoai=(TheLoai) intent.getSerializableExtra("idtheloai");
            }
        }
    }
    private void floatActionButtonClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhsachbaihatActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat", mangbaihat);
                startActivity(intent);
            }
        });
    }
}