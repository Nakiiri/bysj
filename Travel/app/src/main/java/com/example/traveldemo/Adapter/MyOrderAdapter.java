package com.example.traveldemo.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.example.traveldemo.ViewModel.OrderViewModel;
import com.example.traveldemo.ViewModel.TravelPlanViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyOrderViewHolder> {
    private List<Order> userOrder = new ArrayList<>();
    private FragmentActivity fragmentActivity;
    private TravelPlanViewModel travelPlanViewModel;
    private LiveData<List<TravelPlan>>findPlanByID;
    private OrderViewModel orderViewModel;
    private LiveData<List<Order>>findOrderByID;
    private LifecycleOwner lifecycleOwner;

    public void setUserOrder(List<Order> userOrder) {
        this.userOrder = userOrder;
    }


    public MyOrderAdapter(FragmentActivity fragmentActivity, LifecycleOwner lifecycleOwner) {
        this.fragmentActivity = fragmentActivity;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_userorder,parent,false);
        return new MyOrderAdapter.MyOrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrderViewHolder holder, int position) {
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int plan_id = order.getPlan_id();
                int order_id = order.getOrder_id();
                Bundle bundle = new Bundle();
                bundle.putInt("plan_id",plan_id);
                bundle.putInt("order_id",order_id);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_userOrderFragment_to_orderDetailFragment,bundle);
            }
        });

//        orderViewModel = ViewModelProviders.of(fragmentActivity).get(OrderViewModel.class);
//        findOrderByID = orderViewModel.findOrderByID(order.getOrder_id());
//        holder.button_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findOrderByID.observe(lifecycleOwner, new Observer<List<Order>>() {
//                    @Override
//                    public void onChanged(List<Order> orders) {
//                        if(orders.size()!=0){
//                            orderViewModel.updateState1(orders.get(0));
//                            Toast.makeText(fragmentActivity,"取消成功！",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        });
//        holder.button_complete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findOrderByID.observe(lifecycleOwner, new Observer<List<Order>>() {
//                    @Override
//                    public void onChanged(List<Order> orders) {
//                        if(orders.size()!=0){
//                            orderViewModel.updateState2(orders.get(0));
//                            Toast.makeText(fragmentActivity,"已确认完成订单！",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return userOrder.size();
    }

    class MyOrderViewHolder extends RecyclerView.ViewHolder{
        TextView textView_orderid,textView_ordercity,textView_ordertime,textView_price;
        Button button_cancel,button_complete;
        public MyOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_orderid = itemView.findViewById(R.id.textView_myorder_id);
            textView_ordercity = itemView.findViewById(R.id.textView_myorder_city);
            textView_ordertime = itemView.findViewById(R.id.textView_myorder_time);
            textView_price = itemView.findViewById(R.id.textView_myorder_price);
            //button_cancel = itemView.findViewById(R.id.button_myorder_cannelorder);
            //button_complete = itemView.findViewById(R.id.button_myorder_successorder);
        }
    }
}
