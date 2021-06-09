package com.example.MusicApp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MusicApp.Model.BaiHat;
import com.example.MusicApp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
        }
    }
}
