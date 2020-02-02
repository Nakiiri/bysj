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
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;
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
import com.example.traveldemo.ViewModel.CurrentUserViewModel;

import java.util.ArrayList;
import java.util.List;

public class AttractionDetailAdapter extends RecyclerView.Adapter <AttractionDetailAdapter.AttractionDetailViewHolder>{
    private List<Attraction>attractionByPlanid = new ArrayList<>();
    private FragmentActivity fragmentActivity;
    private CurrentUserViewModel currentUserViewModel;

    public AttractionDetailAdapter(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    public void setAttractionByPlanid(List<Attraction> attractionByPlanid) {
        this.attractionByPlanid = attractionByPlanid;
    }

    @NonNull
    @Override
    public AttractionDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_attraction_detail,parent,false);
        return new AttractionDetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionDetailViewHolder holder, int position) {
        final Attraction attraction = attractionByPlanid.get(position);
        holder.textView_name.setText(attraction.getAttraction_name());
        holder.textView_des.setText(attraction.getAttraction_description());
        holder.textView_open.setText(attraction.getAttraction_opentime());
        holder.textView_pos.setText(attraction.getAttraction_position());
        Glide.with(fragmentActivity)
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
                .into(holder.imageView_detailImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUserViewModel = ViewModelProviders.of(fragmentActivity,new SavedStateViewModelFactory(fragmentActivity.getApplication(),fragmentActivity)).get(CurrentUserViewModel.class);
                String Current_user = currentUserViewModel.loaduser();
                if(!Current_user.equals("0")){
                    Bundle bundle = new Bundle();
                    bundle.putInt("attraction_id",attraction.getAttraction_id());
                    NavController navController = Navigation.findNavController(v);
                    if(navController.getCurrentDestination().getId()==R.id.secondFragment){
                        navController.navigate(R.id.action_secondFragment_to_attractionFavFragment3,bundle);
                    }else if(navController.getCurrentDestination().getId()==R.id.attractionDetailFragment){
                        navController.navigate(R.id.action_attractionDetailFragment_to_attractionFavFragment,bundle);
                    }else{
                        navController.navigate(R.id.action_userFavouriteFragment_to_attractionFavFragment,bundle);
                    }
                }else{
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_secondFragment_to_loginFragment);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return attractionByPlanid.size();
    }

    class AttractionDetailViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView_detailImage;
        TextView textView_name,textView_des,textView_open,textView_pos;
        public AttractionDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_detailImage = itemView.findViewById(R.id.imageView_attractionDetailImage);
            textView_name = itemView.findViewById(R.id.textView_attractionDetailName);
            textView_des = itemView.findViewById(R.id.textView_attractionDetailDes);
            textView_open = itemView.findViewById(R.id.textView_attractionDetailOpen);
            textView_pos = itemView.findViewById(R.id.textView_attractionDetailPos);
        }
    }
}
