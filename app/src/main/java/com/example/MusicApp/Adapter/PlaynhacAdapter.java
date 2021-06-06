package com.example.MusicApp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MusicApp.Model.BaiHat;
import com.example.MusicApp.R;

import java.util.ArrayList;

public class PlaynhacAdapter extends RecyclerView.Adapter<PlaynhacAdapter.ViewHolde>{
    Context context;
    ArrayList<BaiHat> mangbaihat;
    public  PlaynhacAdapter(Context context,ArrayList<BaiHat> mangbaihat)
    {
        this.context=context;
        this.mangbaihat= mangbaihat;
    }
    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_play_bai_hat,parent,false);
        return new ViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  PlaynhacAdapter.ViewHolde holder, int position) {
        BaiHat baihat = mangbaihat.get(position);
        holder.txtcasi.setText(baihat.getTenCaSi());
        holder.txtindex.setText(position+1+"");
        holder.txttenbaihat.setText(baihat.getTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size() ;
    }

    public class ViewHolde extends RecyclerView.ViewHolder{
        TextView txtindex,txttenbaihat,txtcasi;
        public ViewHolde(View itemView){
            super(itemView);
            txtcasi=itemView.findViewById(R.id.textviewplaynhactencasi);
            txtindex=itemView.findViewById(R.id.textviewplaynhacindex);
            txttenbaihat=itemView.findViewById(R.id.textviewplaynhactenbaihat);
        }
    }
}
