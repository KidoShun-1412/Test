package com.example.MusicApp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MusicApp.Activity.PlayNhacActivity;
import com.example.MusicApp.Model.BaiHat;
import com.example.MusicApp.R;
import com.example.MusicApp.Service.APIService;
import com.example.MusicApp.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatHotAdapter extends  RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public BaiHatHotAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_hot,parent,false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  BaiHatHotAdapter.ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.txtcasi.setText(baiHat.getTenCaSi());
        holder.txtten.setText(baiHat.getTenBaiHat());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imghinh);
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtten,txtcasi;
        ImageView imghinh,imgluotthich;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            txtten= itemView.findViewById(R.id.textviewtenbaihathot);
            txtcasi= itemView.findViewById(R.id.textviewcasibaihathot);
            imghinh=itemView.findViewById(R.id.imageviewbaihathot);
            imgluotthich=itemView.findViewById(R.id.imageviewluotthich);
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1",baiHatArrayList.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua= response.body();
                            if(ketqua.equals("success"))
                            {
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(context,"BỊ lỗi",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
