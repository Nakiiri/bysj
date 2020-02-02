package com.example.traveldemo;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.traveldemo.Adapter.FirstViewPlanAdapter;
import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.Entity.TravelPlan;
import com.example.traveldemo.ViewModel.OrderViewModel;
import com.example.traveldemo.ViewModel.TravelPlanViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private RecyclerView recyclerView;
    private FirstViewPlanAdapter firstViewPlanAdapter;
    private LiveData<List<TravelPlan>>getAllPlan;
    private TravelPlanViewModel travelPlanViewModel;
    private LiveData<List<TravelPlan>>filteredPlan;
    private OrderViewModel orderViewModel;
    private LiveData<List<Order>>findOrderByPlan;


    public FirstFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.filter_price_desc:
                getAllPlan.removeObservers(getViewLifecycleOwner());
                getAllPlan = travelPlanViewModel.allPlanPriceDesc();
                getAllPlan.observe(getViewLifecycleOwner(), new Observer<List<TravelPlan>>() {
                    @Override
                    public void onChanged(List<TravelPlan> travelPlans) {
                        firstViewPlanAdapter.setGetAgencyPlan(travelPlans);
                        firstViewPlanAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case R.id.filter_price:
                getAllPlan.removeObservers(getViewLifecycleOwner());
                getAllPlan = travelPlanViewModel.allPlanPrice();
                getAllPlan.observe(getViewLifecycleOwner(), new Observer<List<TravelPlan>>() {
                    @Override
                    public void onChanged(List<TravelPlan> travelPlans) {
                        firstViewPlanAdapter.setGetAgencyPlan(travelPlans);
                        firstViewPlanAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case R.id.filter_sale:
                getAllPlan.removeObservers(getViewLifecycleOwner());
                getAllPlan = travelPlanViewModel.allPlanSaleDesc();
                getAllPlan.observe(getViewLifecycleOwner(), new Observer<List<TravelPlan>>() {
                    @Override
                    public void onChanged(List<TravelPlan> travelPlans) {
                        firstViewPlanAdapter.setGetAgencyPlan(travelPlans);
                        firstViewPlanAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case R.id.filter_mark:
                getAllPlan.removeObservers(getViewLifecycleOwner());
                getAllPlan = travelPlanViewModel.allPlanMarkDesc();
                getAllPlan.observe(getViewLifecycleOwner(), new Observer<List<TravelPlan>>() {
                    @Override
                    public void onChanged(List<TravelPlan> travelPlans) {
                        firstViewPlanAdapter.setGetAgencyPlan(travelPlans);
                        firstViewPlanAdapter.notifyDataSetChanged();
                    }
                });
                break;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.firstview_menu,menu);

        SearchView searchView =(SearchView)menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(1000);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String pattern = newText.trim();
                getAllPlan.removeObservers(getViewLifecycleOwner());
                filteredPlan = travelPlanViewModel.findPlanByCity(pattern);
                filteredPlan.observe(getViewLifecycleOwner(), new Observer<List<TravelPlan>>() {
                    @Override
                    public void onChanged(List<TravelPlan> travelPlans) {
                        firstViewPlanAdapter.setGetAgencyPlan(travelPlans);
                        firstViewPlanAdapter.notifyDataSetChanged();
                    }
                });
                return true;
            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        travelPlanViewModel = ViewModelProviders.of(requireActivity()).get(TravelPlanViewModel.class);
        orderViewModel = ViewModelProviders.of(requireActivity()).get(OrderViewModel.class);

        recyclerView = requireActivity().findViewById(R.id.recycleview_firstView);
        firstViewPlanAdapter = new FirstViewPlanAdapter(requireActivity(),getViewLifecycleOwner());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(firstViewPlanAdapter);


        getAllPlan = travelPlanViewModel.allPlanLive();
        getAllPlan.observe(getViewLifecycleOwner(), new Observer<List<TravelPlan>>() {
            @Override
            public void onChanged(final List<TravelPlan> travelPlans) {
//                if(travelPlans.size()!=0){
//                    for(int i=0;i<travelPlans.size();i++){
//                        findOrderByPlan = orderViewModel.findOrderByPlan(travelPlans.get(i).getPlan_id());
//                        final int finalI = i;
//                        findOrderByPlan.observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
//                            @Override
//                            public void onChanged(List<Order> orders) {
//                                if(orders.size()!=0){
//                                    int j=0;
//                                    int total=0;
//                                    for(int i=0;i<orders.size();i++){
//                                        if(orders.get(i).getOrder_state()==2){
//                                            j+=1;
//                                            total+=orders.get(i).getOrder_assess();
//
//                                        }
//                                    }
//                                    if(j!=0){
//                                        TravelPlan travelPlan = travelPlans.get(finalI);
//                                        travelPlanViewModel.deleteTravlePlan(travelPlan);
//                                        TravelPlan travelPlan1 = new TravelPlan(travelPlan.getAgency_loginname(),travelPlan.getCountry(),travelPlan.getCity(),travelPlan.getOrigin(),travelPlan.getPrice(),travelPlan.getDeparture_time(),travelPlan.getSpend_time(),travelPlan.getPlan_url(),travelPlan.getPlan_title(),j,(float)(total/j)/10);
//                                        travelPlan1.setPlan_id(travelPlan.getPlan_id());
//                                        travelPlanViewModel.insertTravlePlan(travelPlan1);
//                                    }
//
//
//                                }
//                            }
//                        });
//
//                    }
//                }




                firstViewPlanAdapter.setGetAgencyPlan(travelPlans);
                firstViewPlanAdapter.notifyDataSetChanged();
            }
        });



    }
}
