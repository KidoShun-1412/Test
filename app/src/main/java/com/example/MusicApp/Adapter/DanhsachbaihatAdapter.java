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

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat> mangbaihat;

    public DanhsachbaihatAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());
        holder.txttencasi.setText(baiHat.getTenCaSi());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.hinhbaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenbaihat, txttencasi;
        ImageView hinhbaihat, tim;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenbaihat = itemView.findViewById(R.id.textViewtenbaihat);
            txttencasi = itemView.findViewById(R.id.textViewtencasi);
            hinhbaihat = itemView.findViewById(R.id.imageViewhinhbaihat);
            tim = itemView.findViewById(R.id.imageViewtimdanhsachbaihat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", mangbaihat.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
            tim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tim.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1",mangbaihat.get(getPosition()).getIdBaiHat());
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
                    tim.setEnabled(false);
                }
            });
        }
    }
}
