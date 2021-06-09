package com.example.MusicApp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.MusicApp.Adapter.DanhsachtatcachudeAdapter;
import com.example.MusicApp.Adapter.DanhsachtheloaitheochudeAdapter;
import com.example.MusicApp.Model.ChuDe;
import com.example.MusicApp.Model.Chudevatheloai;
import com.example.MusicApp.Model.TheLoai;
import com.example.MusicApp.R;
import com.example.MusicApp.Service.APIService;
import com.example.MusicApp.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {
    ChuDe chude;
    RecyclerView recyclerViewtheloaitheochude;
    Toolbar toolbartheloaitheochude;
    DanhsachtheloaitheochudeAdapter danhsachtheloaitheochudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);
        GetIntent();
        GetData();
        init();

    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<TheLoai>> callback=dataservice.GetDanhSachTheLoaiTheoChuDe(chude.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai  = (ArrayList<TheLoai>) response.body();
                danhsachtheloaitheochudeAdapter=new DanhsachtheloaitheochudeAdapter(DanhsachtheloaitheochudeActivity.this,mangtheloai);
                recyclerViewtheloaitheochude.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this,2));
                recyclerViewtheloaitheochude.setAdapter(danhsachtheloaitheochudeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewtheloaitheochude = findViewById(R.id.recyclerviewtheloaitheochude);
        toolbartheloaitheochude = findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbartheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chude.getTenChuDe());
        toolbartheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent=getIntent();
        if(intent.hasExtra("chude")){
            chude=(ChuDe) intent.getSerializableExtra("chude");
            Toast.makeText(this,chude.getTenChuDe(),Toast.LENGTH_SHORT).show();
        }
    }
}