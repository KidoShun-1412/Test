package com.example.MusicApp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.MusicApp.Adapter.DanhsachcacplaylistAdapter;
import com.example.MusicApp.Model.Playlist;
import com.example.MusicApp.R;
import com.example.MusicApp.Service.APIService;
import com.example.MusicApp.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachcacplaylistActivity extends AppCompatActivity {
    Toolbar toolbardanhsachcacplaylist;
    RecyclerView recyclerViewdanhsachcacplaylist;
    DanhsachcacplaylistAdapter danhsachcacplaylistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback= dataservice.GetDanhSachPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangplaylist = (ArrayList<Playlist>) response.body();
                Log.d("BBB",mangplaylist.get(0).getTen());
                danhsachcacplaylistAdapter = new DanhsachcacplaylistAdapter(DanhsachcacplaylistActivity.this,mangplaylist);
                recyclerViewdanhsachcacplaylist.setLayoutManager(new GridLayoutManager(DanhsachcacplaylistActivity.this,2));
                recyclerViewdanhsachcacplaylist.setAdapter(danhsachcacplaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        toolbardanhsachcacplaylist = findViewById(R.id.toolbardanhsachcacplaylist);
        recyclerViewdanhsachcacplaylist= findViewById(R.id.recyclerviewdanhsachcacplaylist);
        setSupportActionBar(toolbardanhsachcacplaylist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả playlist");
        toolbardanhsachcacplaylist.setTitleTextColor(getResources().getColor(R.color.colorToolbar));
        toolbardanhsachcacplaylist.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}