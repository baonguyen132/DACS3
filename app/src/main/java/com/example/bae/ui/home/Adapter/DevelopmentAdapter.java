package com.example.bae.ui.home.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.bae.R;

import java.util.ArrayList;

public class DevelopmentAdapter extends RecyclerView.Adapter<DevelopmentAdapter.viewHolder> {

    ArrayList<DevelopmentData> items ;
    Context context ;

    public DevelopmentAdapter(ArrayList<DevelopmentData> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public DevelopmentAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_development , parent , false) ;
        context = parent.getContext() ;


        return new viewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull DevelopmentAdapter.viewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.subtitle.setText(items.get(position).getSubtitle());

        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicAddress() , "drawable" , holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).transform(new GranularRoundedCorners(30 , 30 , 0 , 0)).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView title , subtitle ;
        ImageView pic ;

        @SuppressLint("WrongViewCast")
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
