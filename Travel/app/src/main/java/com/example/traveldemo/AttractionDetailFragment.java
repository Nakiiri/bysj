package com.example.traveldemo;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveldemo.Adapter.AttractionDetailAdapter;
import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.Entity.TravelPlan;
import com.example.traveldemo.ViewModel.AttractionViewModel;
import com.example.traveldemo.ViewModel.CurrentUserViewModel;
import com.example.traveldemo.ViewModel.OrderViewModel;
import com.example.traveldemo.ViewModel.TravelPlanViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttractionDetailFragment extends Fragment {
    private RecyclerView recyclerView;
    private AttractionDetailAdapter attractionDetailAdapter;
    private AttractionViewModel attractionViewModel;
    private LiveData<List<Attraction>>findAllAttractionByPlanID;
    private TextView textView_price,textView_spend;
    private Button button_order,button_mark;
    private TravelPlanViewModel travelPlanViewModel;
    private LiveData<List<TravelPlan>>findAllPlanByID;
    private OrderViewModel orderViewModel;
    private LiveData<List<Order>>getOrderByUserAndPlan;
    private CurrentUserViewModel currentUserViewModel;
    private LiveData<List<Order>>findOrderByPlan;
    private List<TravelPlan>travelPlanZ = new ArrayList<>();




    public AttractionDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attraction_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        attractionViewModel = ViewModelProviders.of(requireActivity()).get(AttractionViewModel.class);
        travelPlanViewModel = ViewModelProviders.of(requireActivity()).get(TravelPlanViewModel.class);
        orderViewModel = ViewModelProviders.of(requireActivity()).get(OrderViewModel.class);
        currentUserViewModel = ViewModelProviders.of(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(CurrentUserViewModel.class);


        final int plan_id = getArguments().getInt("plan_id");
        final String agency_loginname = getArguments().getString("agency_loginname");

        textView_price = requireActivity().findViewById(R.id.textView_attractionDetailPrice);
        textView_spend = requireActivity().findViewById(R.id.textView_attractionDetailSpend);
        button_order = requireActivity().findViewById(R.id.button_attractionDetailOrder);
        button_mark = requireActivity().findViewById(R.id.button_mark);

        recyclerView = requireActivity().findViewById(R.id.recyclerView_AttractionDetail);
        attractionDetailAdapter = new AttractionDetailAdapter(requireActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(attractionDetailAdapter);

        findAllAttractionByPlanID = attractionViewModel.findAttractionByPlan(plan_id);
        findAllAttractionByPlanID.observe(getViewLifecycleOwner(), new Observer<List<Attraction>>() {
            @Override
            public void onChanged(List<Attraction> attractions) {
                attractionDetailAdapter.setAttractionByPlanid(attractions);
                attractionDetailAdapter.notifyDataSetChanged();
            }
        });


        travelPlanZ = new ArrayList<>();
        findAllPlanByID = travelPlanViewModel.findPlanByID(plan_id);
        findAllPlanByID.observe(getViewLifecycleOwner(), new Observer<List<TravelPlan>>() {
            @Override
            public void onChanged(List<TravelPlan> travelPlans) {
                if(travelPlans.size()!=0){
                    textView_price.setText("￥"+travelPlans.get(0).getPrice());
                    textView_spend.setText("/" + travelPlans.get(0).getSpend_time());
                    travelPlanZ = travelPlans;
                }
            }
        });

        final String current_user = currentUserViewModel.loaduser();
        getOrderByUserAndPlan = orderViewModel.findOrderByUserAndPlan(current_user,plan_id);
        getOrderByUserAndPlan.observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(final List<Order> orders) {

                button_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(orders.size()==0){
                            Order order = new Order(current_user,plan_id,agency_loginname,0,0,0,"此用户没有填写评价！");
                            orderViewModel.insertOrder(order);
                            Toast.makeText(requireContext(),"预定成功！",Toast.LENGTH_SHORT).show();


                         findOrderByPlan = orderViewModel.findOrderByPlan(plan_id);
                        findOrderByPlan.observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
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

                                        TravelPlan travelPlan = travelPlanZ.get(0);
                                        //Toast.makeText(requireActivity(),"qwq"+travelPlanZ.get(0).getPlan_sale(),Toast.LENGTH_SHORT).show();
                                        travelPlanViewModel.deleteTravlePlan(travelPlan);
                                        TravelPlan travelPlan1 = new TravelPlan(travelPlan.getAgency_loginname(),travelPlan.getCountry(),travelPlan.getCity(),travelPlan.getOrigin(),travelPlan.getPrice(),travelPlan.getDeparture_time(),travelPlan.getSpend_time(),travelPlan.getPlan_url(),travelPlan.getPlan_title(),j,(float)(total/j)/10);
                                        travelPlan1.setPlan_id(travelPlan.getPlan_id());
                                        travelPlanViewModel.insertTravlePlan(travelPlan1);


                                    }


                                }
                            }
                        });




                        }else{
                            Toast.makeText(requireContext(),"该订单已存在！",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        button_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int plan_id = getArguments().getInt("plan_id");
                Bundle bundle = new Bundle();
                bundle.putInt("plan_id",plan_id);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_attractionDetailFragment_to_markFragment,bundle);
            }
        });







    }
}
