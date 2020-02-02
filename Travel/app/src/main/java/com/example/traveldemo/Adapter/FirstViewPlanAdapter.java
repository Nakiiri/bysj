package com.example.traveldemo.Adapter;

import android.app.Activity;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
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
import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.Entity.TravelAgency;
import com.example.traveldemo.Entity.TravelPlan;
import com.example.traveldemo.R;
import com.example.traveldemo.ThirdFragment;
import com.example.traveldemo.ViewModel.CurrentUserViewModel;
import com.example.traveldemo.ViewModel.OrderViewModel;
import com.example.traveldemo.ViewModel.TravelAgencyViewModel;

import java.util.ArrayList;
import java.util.List;

public class FirstViewPlanAdapter extends RecyclerView.Adapter<FirstViewPlanAdapter.FirstViewPlanHolder> {
    private List<TravelPlan> getAgencyPlan = new ArrayList<>();
    private LiveData<List<TravelAgency>>findAgencyByLogin;
    private TravelAgencyViewModel travelAgencyViewModel;
    private FragmentActivity fragmentActivity;
    private List<TravelAgency>agency = new ArrayList<>();
    private CurrentUserViewModel currentUserViewModel;
    private String current_user;
    private LifecycleOwner lifecycleOwner;
    private OrderViewModel orderViewModel;
    private LiveData<List<Order>>findOrderByPlan;



    public void setGetAgencyPlan(List<TravelPlan> getAgencyPlan) {
        this.getAgencyPlan = getAgencyPlan;
    }

    public FirstViewPlanAdapter(FragmentActivity fragmentActivity, LifecycleOwner lifecycleOwner) {
        this.fragmentActivity = fragmentActivity;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public FirstViewPlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_firstplan,parent,false);
        return new FirstViewPlanAdapter.FirstViewPlanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FirstViewPlanHolder holder, int position) {
        final TravelPlan travelPlan = getAgencyPlan.get(position);
        travelAgencyViewModel = ViewModelProviders.of(fragmentActivity).get(TravelAgencyViewModel.class);
        findAgencyByLogin = travelAgencyViewModel.findAgencyByLogin(travelPlan.getAgency_loginname());
        findAgencyByLogin.observe( lifecycleOwner, new Observer<List<TravelAgency>>() {
            @Override
            public void onChanged(List<TravelAgency> travelAgencies) {
                if (travelAgencies.size()!=0){
                    holder.textView_agencyname.setText(travelAgencies.get(0).getAgency_name());
                }
            }
        });


        holder.textView_city.setText(travelPlan.getPlan_title());
        holder.textView_origin.setText("始发地："+travelPlan.getOrigin());
        holder.textView_price.setText("￥"+travelPlan.getPrice());

        orderViewModel = ViewModelProviders.of(fragmentActivity).get(OrderViewModel.class);
        findOrderByPlan = orderViewModel.findOrderByPlan(travelPlan.getPlan_id());
        findOrderByPlan.observe(lifecycleOwner, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                if(orders.size()!=0){
                    int j=0;
                    int total=0;
                    for(int i=0;i<orders.size();i++){
                        if(orders.get(i).getOrder_state()==2){
                            j+=1;
                            total+=orders.get(i).getOrder_assess();

                        }
                    }
                    if(j!=0){
                        holder.textView_sale.setText("已售"+ j +"人");
                        holder.textView_mark.setText((float)(total/j)/10+"分");
                    }else{
                        holder.textView_sale.setText("已售0人");
                        holder.textView_mark.setText("5分");
                    }


                }else{
                    holder.textView_mark.setText("5分");
                    holder.textView_sale.setText("已售0人");
                }
            }
        });



        Glide.with(fragmentActivity)
                .load(travelPlan.getPlan_url())
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
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUserViewModel = ViewModelProviders.of(fragmentActivity,new SavedStateViewModelFactory(fragmentActivity.getApplication(),fragmentActivity)).get(CurrentUserViewModel.class);
                current_user = currentUserViewModel.loaduser();
                if(!current_user.equals("0")){
                    int plan_id = travelPlan.getPlan_id();
                    String agency_loginname = travelPlan.getAgency_loginname();
                    Bundle bundle = new Bundle();
                    bundle.putInt("plan_id",plan_id);
                    bundle.putString("agency_loginname",agency_loginname);
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_firstFragment_to_attractionDetailFragment,bundle);

                }else{
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_firstFragment_to_loginFragment2);
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return getAgencyPlan.size();
    }

    class FirstViewPlanHolder extends RecyclerView.ViewHolder{
        TextView textView_city,textView_origin,textView_agencyname,textView_price,textView_mark,textView_sale;
        ImageView imageView;
        public FirstViewPlanHolder(@NonNull View itemView) {
            super(itemView);
            textView_city = itemView.findViewById(R.id.textView_1PlanCity);
            textView_origin = itemView.findViewById(R.id.textView_1PlanOrigin);
            textView_agencyname = itemView.findViewById(R.id.textView_1PlanAgencyname);
            textView_price = itemView.findViewById(R.id.textView_1PlanPrice);

            textView_mark = itemView.findViewById(R.id.textView_1PlanMark);
            textView_sale = itemView.findViewById(R.id.textView_1PlanSale);
            imageView = itemView.findViewById(R.id.imageView_1PlanImage);
        }
    }
}
