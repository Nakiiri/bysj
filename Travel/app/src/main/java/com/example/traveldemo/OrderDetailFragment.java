package com.example.traveldemo;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.Entity.TravelPlan;
import com.example.traveldemo.ViewModel.OrderViewModel;
import com.example.traveldemo.ViewModel.TravelPlanViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderDetailFragment extends Fragment {
    private TextView textView_orderid,textView_city,textView_origin,textView_time,textView_price;
    private Button button_cancel,button_complete;
    private TravelPlanViewModel travelPlanViewModel;
    private LiveData<List<TravelPlan>>findPlanByID;
    private OrderViewModel orderViewModel;
    private LiveData<List<Order>>findOrderByID;
    private List<Order> userOrder = new ArrayList<>();


    public OrderDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int plan_id = getArguments().getInt("plan_id");
        int order_id = getArguments().getInt("order_id");

        travelPlanViewModel = ViewModelProviders.of(requireActivity()).get(TravelPlanViewModel.class);
        orderViewModel = ViewModelProviders.of(requireActivity()).get(OrderViewModel.class);

        textView_orderid = requireActivity().findViewById(R.id.textView_orderDetail_orderid);
        textView_city = requireActivity().findViewById(R.id.textView_orderDetail_city);
        textView_origin = requireActivity().findViewById(R.id.textView_orderDetail_origin);
        textView_time = requireActivity().findViewById(R.id.textView_orderDetail_time);
        textView_price = requireActivity().findViewById(R.id.textView_orderDetail_price);
        button_cancel = requireActivity().findViewById(R.id.button_orderDetail_cancel);
        button_complete = requireActivity().findViewById(R.id.button_orderDetail_complete);

        textView_orderid.setText(String.valueOf(order_id));

        findPlanByID = travelPlanViewModel.findPlanByID(plan_id);
        findPlanByID.observe(getViewLifecycleOwner(), new Observer<List<TravelPlan>>() {
            @Override
            public void onChanged(List<TravelPlan> travelPlans) {
                textView_city.setText(travelPlans.get(0).getCity());
                textView_origin.setText(travelPlans.get(0).getOrigin());
                textView_time.setText(travelPlans.get(0).getDeparture_time());
                textView_price.setText(travelPlans.get(0).getPrice());
            }
        });

        findOrderByID = orderViewModel.findOrderByID(order_id);
        findOrderByID.observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                userOrder = orders;
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userOrder.size()!=0){
                    orderViewModel.updateState1(userOrder.get(0));
                    userOrder = new ArrayList<>();
                    Toast.makeText(requireActivity(),"取消成功！",Toast.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_orderDetailFragment_to_thirdFragment);
                }
            }
        });
        button_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userOrder.size()!=0){
                    orderViewModel.updateState2(userOrder.get(0));
                    userOrder = new ArrayList<>();
                    Toast.makeText(requireActivity(),"成功完成订单！",Toast.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_orderDetailFragment_to_thirdFragment);
                }
            }
        });



    }
}
