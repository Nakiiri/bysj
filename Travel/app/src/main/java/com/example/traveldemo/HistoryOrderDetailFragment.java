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
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.Entity.TravelPlan;
import com.example.traveldemo.ViewModel.OrderViewModel;
import com.example.traveldemo.ViewModel.TravelPlanViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryOrderDetailFragment extends Fragment {
    private TextView textView_state,textView_orderid,textView_city,textView_origin,textView_time,textView_price,textView_isAssess;
    private EditText editText_mark;
    private RatingBar ratingBar;
    private OrderViewModel orderViewModel;
    private LiveData<List<Order>>findOrderByID;
    private TravelPlanViewModel travelPlanViewModel;
    private LiveData<List<TravelPlan>>findPlanByID;


    public HistoryOrderDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_order_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        orderViewModel = ViewModelProviders.of(requireActivity()).get(OrderViewModel.class);
        travelPlanViewModel = ViewModelProviders.of(requireActivity()).get(TravelPlanViewModel.class);

        int plan_id = getArguments().getInt("plan_id");
        int order_id = getArguments().getInt("order_id");

        textView_state = requireActivity().findViewById(R.id.textView_historyorder_detailState);
        textView_orderid = requireActivity().findViewById(R.id.textView_historyorder_detailorderID);
        textView_city = requireActivity().findViewById(R.id.textView_historyorder_detailCity);
        textView_origin = requireActivity().findViewById(R.id.textView_historyorder_detailOrigin);
        textView_time = requireActivity().findViewById(R.id.textView_historyorder_detailTime);
        textView_price = requireActivity().findViewById(R.id.textView_historyorder_detailPrice);
        textView_isAssess = requireActivity().findViewById(R.id.textView_historyorder_detailOrderIsAssess);
        ratingBar = requireActivity().findViewById(R.id.ratingBar);
        editText_mark = requireActivity().findViewById(R.id.editText_historyorder_mark);

        textView_orderid.setText(String.valueOf(plan_id));

        findOrderByID = orderViewModel.findOrderByID(order_id);
        findOrderByID.observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(final List<Order> orders) {
                if(orders.size()!=0){
                    if(orders.get(0).getOrder_state()==1){
                        textView_state.setText("交易失败");
                        textView_isAssess.setVisibility(View.GONE);
                        ratingBar.setVisibility(View.GONE);
                        editText_mark.setVisibility(View.GONE);
                    }else if(orders.get(0).getOrder_state()==2){
                        textView_state.setText("交易成功");
                        textView_isAssess.setVisibility(View.VISIBLE);
                        ratingBar.setVisibility(View.VISIBLE);
                        editText_mark.setVisibility(View.VISIBLE);
                    }
                    if(orders.get(0).getOrder_isassess()==0){
                        textView_isAssess.setText("请您对本次服务进行评价");
                        ratingBar.setIsIndicator(false);
                        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                            @Override
                            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                Order order = orders.get(0);
                                orderViewModel.deleteOrder(order);
                                String mark = editText_mark.getText().toString().trim();
                                if(mark.isEmpty()){
                                    mark = "此用户没有填写评价！";
                                }
                                Order order1 = new Order(order.getUser_loginname(),order.getPlan_id(),order.getAgency_loginname(),2,1,(int)(rating*10),mark);
                                order1.setOrder_id(order.getOrder_id());
                                orderViewModel.insertOrder(order1);

                                Toast.makeText(requireContext(),"感谢您的评价！",Toast.LENGTH_SHORT).show();
                                NavController controller = Navigation.findNavController(ratingBar);
                                controller.navigate(R.id.action_historyOrderDetailFragment_to_historyOrderFragment);
                            }
                        });
                    }else if(orders.get(0).getOrder_isassess()==1){
                        textView_isAssess.setText("感谢您的评价！");
                        ratingBar.setIsIndicator(true);
                        editText_mark.setVisibility(View.GONE);
                        ratingBar.setRating((float) orders.get(0).getOrder_assess()/(float) 10);
                    }
                }

            }
        });

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

    }
}
