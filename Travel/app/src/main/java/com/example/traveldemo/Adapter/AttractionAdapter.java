package com.example.traveldemo.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.R;

import java.util.ArrayList;
import java.util.List;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder> {
    private List<Attraction>getPlanAttraction = new ArrayList<>();
    private Context context;

    public void setGetPlanAttraction(List<Attraction> getPlanAttraction) {
        this.getPlanAttraction = getPlanAttraction;
    }

    public AttractionAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_attraction,parent,false);
        return new AttractionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionViewHolder holder, int position) {
        final Attraction attraction = getPlanAttraction.get(position);
        holder.textView_name.setText(attraction.getAttraction_name());
        holder.textView_des.setText(attraction.getAttraction_description());
        holder.textView_open.setText(attraction.getAttraction_opentime());
        Glide.with(context)
                .load(attraction.getImage_url())
                .placeholder(R.drawable.ic_launcher_background)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.imageView_attract);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int attraction_id = attraction.getAttraction_id();
                Bundle bundle = new Bundle();
                bundle.putInt("attraction_id",attraction_id);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_attractionFragment_to_updateAttractionFragment,bundle);
            }
        });


    }

    @Override
    public int getItemCount() {
        return getPlanAttraction.size();
    }

    class AttractionViewHolder extends RecyclerView.ViewHolder{
        TextView textView_name,textView_des,textView_open;
        ImageView imageView_attract,imageView_detail;
        public AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.textView_attractionname);
            textView_des = itemView.findViewById(R.id.textView_attractiondes);
            textView_open = itemView.findViewById(R.id.textView_attractionopen);
            imageView_attract = itemView.findViewById(R.id.imageView_attractionimage);
            imageView_detail = itemView.findViewById(R.id.imageView_attractiondetail);

        }
    }
}
