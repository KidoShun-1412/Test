package com.example.MusicApp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MusicApp.Adapter.BaiHatHotAdapter;
import com.example.MusicApp.Model.BaiHat;
import com.example.MusicApp.R;
import com.example.MusicApp.Service.APIService;
import com.example.MusicApp.Service.Dataservice;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewbaihathot;
    BaiHatHotAdapter baiHatHotAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_bai_hat_thich_nhat,container,false);
         recyclerViewbaihathot=view.findViewById(R.id.recyclerviewbaihathot);
         Getdata();
        return view;
    }

    private void Getdata() {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetBaiHatYeuThich();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangbaihat = (ArrayList<BaiHat>) response.body();
                Log.d("dddd",mangbaihat.get(0).getHinhBaiHat());
                baiHatHotAdapter = new BaiHatHotAdapter(getActivity(),mangbaihat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerViewbaihathot.setLayoutManager(linearLayoutManager);
                recyclerViewbaihathot.setAdapter(baiHatHotAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
