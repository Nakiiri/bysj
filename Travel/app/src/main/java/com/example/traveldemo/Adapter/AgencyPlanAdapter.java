package com.example.traveldemo.Adapter;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveldemo.Entity.TravelPlan;
import com.example.traveldemo.R;

import java.util.ArrayList;
import java.util.List;

public class AgencyPlanAdapter extends RecyclerView.Adapter<AgencyPlanAdapter.AgencyPlanViewHolder> {
    List<TravelPlan>getAgencyPlan = new ArrayList<>();

    public void setGetAgencyPlan(List<TravelPlan> getAgencyPlan) {
        this.getAgencyPlan = getAgencyPlan;
    }

    @NonNull
    @Override
    public AgencyPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_travelplan,parent,false);
        return new AgencyPlanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AgencyPlanViewHolder holder, int position) {
        final TravelPlan travelPlan = getAgencyPlan.get(position);
        holder.textView_number.setText(String.valueOf(position + 1));
        holder.textView_city.setText(travelPlan.getCity());
        holder.textView_origin.setText(travelPlan.getOrigin());
        holder.textView_totalprice.setText("￥"+travelPlan.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int plan_id = travelPlan.getPlan_id();
                String current_agency = travelPlan.getAgency_loginname();
                Bundle bundle = new Bundle();
                bundle.putInt("plan_id",plan_id);
                bundle.putString("agency_login2",current_agency);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_travelAgencyFragment_to_attractionFragment,bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return getAgencyPlan.size();
    }





    class AgencyPlanViewHolder extends RecyclerView.ViewHolder {
        TextView textView_number,textView_city,textView_origin,textView_totalprice;
        ImageButton imageButton_detail;
        public AgencyPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_number = itemView.findViewById(R.id.textView_number);
            textView_city = itemView.findViewById(R.id.textView_city);
            textView_origin = itemView.findViewById(R.id.textView_origin);
            textView_totalprice = itemView.findViewById(R.id.textView_totalprice);
            imageButton_detail = itemView.findViewById(R.id.imageButton_detailattraction);

        }
    }
}
