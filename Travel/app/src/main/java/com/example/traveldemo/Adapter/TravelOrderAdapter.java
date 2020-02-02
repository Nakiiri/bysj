package com.example.traveldemo.Adapter;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.Entity.TravelPlan;
import com.example.traveldemo.Entity.User;
import com.example.traveldemo.R;
import com.example.traveldemo.ViewModel.TravelPlanViewModel;
import com.example.traveldemo.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class TravelOrderAdapter extends RecyclerView.Adapter<TravelOrderAdapter.TravelOrderViewHolder> {
    private List<Order>allOrder = new ArrayList<>();
    private FragmentActivity fragmentActivity;
    private LifecycleOwner lifecycleOwner;
    private TravelPlanViewModel travelPlanViewModel;
    private LiveData<List<TravelPlan>>findPlanByID;
    private UserViewModel userViewModel;
    private LiveData<List<User>>findUserByLoginName;



    public void setAllOrder(List<Order> allOrder) {
        this.allOrder = allOrder;
    }

    public TravelOrderAdapter(FragmentActivity fragmentActivity, LifecycleOwner lifecycleOwner) {
        this.fragmentActivity = fragmentActivity;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public TravelOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_travel_order,parent,false);
        return new TravelOrderAdapter.TravelOrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TravelOrderViewHolder holder, int position) {
        Order order = allOrder.get(position);
        holder.textView_order_id.setText(String.valueOf(order.getOrder_id()));
        if(order.getOrder_state()==0){
            holder.textView_state.setText("已预约");
        }else if(order.getOrder_state()==1){
            holder.textView_state.setText("已取消");
        }else{
            holder.textView_state.setText("交易成功");
        }
        travelPlanViewModel = ViewModelProviders.of(fragmentActivity).get(TravelPlanViewModel.class);
        findPlanByID = travelPlanViewModel.findPlanByID(order.getPlan_id());
        findPlanByID.observe(lifecycleOwner, new Observer<List<TravelPlan>>() {
            @Override
            public void onChanged(List<TravelPlan> travelPlans) {
                holder.textView_city.setText(travelPlans.get(0).getCity());
                holder.textView_origin.setText(travelPlans.get(0).getOrigin());
            }
        });
        userViewModel = ViewModelProviders.of(fragmentActivity).get(UserViewModel.class);
        findUserByLoginName = userViewModel.findUserByLoginname(order.getUser_loginname());
        findUserByLoginName.observe(lifecycleOwner, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                holder.textView_name.setText(users.get(0).getUser_name());
                holder.textView_phone.setText(users.get(0).getLogin_name());
            }
        });


    }

    @Override
    public int getItemCount() {
        return allOrder.size();
    }

    class TravelOrderViewHolder extends RecyclerView.ViewHolder{
        TextView textView_order_id,textView_city,textView_origin,textView_state,textView_name,textView_phone;
        public TravelOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_order_id = itemView.findViewById(R.id.textView_travelOrder_orderid);
            textView_city = itemView.findViewById(R.id.textView_travelOrder_city);
            textView_origin = itemView.findViewById(R.id.textView_travelOrder_origin);
            textView_state = itemView.findViewById(R.id.textView_travelOrder_orderState);
            textView_name = itemView.findViewById(R.id.textView_travelOrder_username);
            textView_phone = itemView.findViewById(R.id.textView_travelOrder_userlogin);

        }
    }
}
