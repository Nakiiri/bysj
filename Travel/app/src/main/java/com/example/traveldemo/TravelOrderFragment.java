package com.example.traveldemo;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.traveldemo.Adapter.TravelOrderAdapter;
import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.ViewModel.OrderViewModel;

import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class TravelOrderFragment extends Fragment {
    private RecyclerView recyclerView;
    private TravelOrderAdapter travelOrderAdapter;
    private OrderViewModel orderViewModel;
    private LiveData<List<Order>>findOrderByAgency;


    public TravelOrderFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(Objects.requireNonNull(getView()));
        switch (item.getItemId()){
            case R.id.agency_travelinfo:
                String current_agency = getArguments().getString(getResources().getString(R.string.agency_loginname));
                Bundle bundle = new Bundle();
                bundle.putString(getResources().getString(R.string.agency_loginname),current_agency);
                navController.navigate(R.id.action_travelOrderFragment_to_travelAgencyFragment,bundle);
                break;
            case R.id.agency_logout:
                navController.navigate(R.id.action_travelOrderFragment_to_loginFragment);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.travelagency_menu,menu);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_travel_order, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String current_agency = getArguments().getString(getResources().getString(R.string.agency_loginname));

        orderViewModel = ViewModelProviders.of(requireActivity()).get(OrderViewModel.class);

        recyclerView = requireActivity().findViewById(R.id.recycleview_travelOrder);
        travelOrderAdapter = new TravelOrderAdapter(requireActivity(),getViewLifecycleOwner());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(travelOrderAdapter);

        findOrderByAgency = orderViewModel.findOrderByAgency(current_agency);
        findOrderByAgency.observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                travelOrderAdapter.setAllOrder(orders);
                travelOrderAdapter.notifyDataSetChanged();
            }
        });



    }
}
