package com.example.traveldemo.Adapter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.Entity.TravelPlan;
import com.example.traveldemo.R;
import com.example.traveldemo.ViewModel.TravelPlanViewModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.HistoryOrderViewHolder> {
    private List<Order> userOrder = new ArrayList<>();
    private FragmentActivity fragmentActivity;
    private TravelPlanViewModel travelPlanViewModel;
    private LiveData<List<TravelPlan>> findPlanByID;
    private LifecycleOwner lifecycleOwner;

    public void setUserOrder(List<Order> userOrder) {
        this.userOrder = userOrder;
    }

    public HistoryOrderAdapter(FragmentActivity fragmentActivity, LifecycleOwner lifecycleOwner) {
        this.fragmentActivity = fragmentActivity;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public HistoryOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_history_order,parent,false);
        return new HistoryOrderAdapter.HistoryOrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryOrderViewHolder holder, int position) {
        final Order order = userOrder.get(position);
        holder.textView_orderid.setText(String.valueOf(order.getOrder_id()));
        travelPlanViewModel = ViewModelProviders.of(fragmentActivity).get(TravelPlanViewModel.class);
        findPlanByID = travelPlanViewModel.findPlanByID(order.getPlan_id());
        findPlanByID.observe(lifecycleOwner, new Observer<List<TravelPlan>>() {
            @Override
            public void onChanged(List<TravelPlan> travelPlans) {
                if(travelPlans.size()!=0){
                    holder.textView_ordercity.setText(travelPlans.get(0).getCity());
                    holder.textView_ordertime.setText(travelPlans.get(0).getDeparture_time());
                    holder.textView_price.setText(travelPlans.get(0).getPrice());
                }
            }
        });
        if(order.getOrder_state()==1){
            holder.textView_state.setBackgroundColor(Color.parseColor("#000000"));
            holder.textView_state.setTextColor(Color.parseColor("#FFFFFF"));
            holder.textView_state.setText("交易关闭");
        }else if(order.getOrder_state()==2){
            holder.textView_state.setBackgroundColor(Color.parseColor("#E53935"));
            holder.textView_state.setText("交易成功");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int plan_id = order.getPlan_id();
                int order_id = order.getOrder_id();
                Bundle bundle = new Bundle();
                bundle.putInt("plan_id",plan_id);
                bundle.putInt("order_id",order_id);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_historyOrderFragment_to_historyOrderDetailFragment,bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userOrder.size();
    }

    class HistoryOrderViewHolder extends RecyclerView.ViewHolder{
        TextView textView_orderid,textView_ordercity,textView_ordertime,textView_price,textView_state;
        public HistoryOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_orderid = itemView.findViewById(R.id.textView_historyOrder);
            textView_ordercity = itemView.findViewById(R.id.textView_historyOrder_city);
            textView_ordertime = itemView.findViewById(R.id.textView_historyOrder_time);
            textView_price = itemView.findViewById(R.id.textView_historyOrder_price);
            textView_state = itemView.findViewById(R.id.textView_historyOrder_state);
        }
    }
}
