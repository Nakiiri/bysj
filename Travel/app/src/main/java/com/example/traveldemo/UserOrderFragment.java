package com.example.traveldemo;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.traveldemo.Adapter.MyOrderAdapter;
import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.ViewModel.CurrentUserViewModel;
import com.example.traveldemo.ViewModel.OrderViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserOrderFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyOrderAdapter myOrderAdapter;
    private CurrentUserViewModel currentUserViewModel;
    private OrderViewModel orderViewModel;
    private LiveData<List<Order>>getUserOrder;


    public UserOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_order, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        orderViewModel = ViewModelProviders.of(requireActivity()).get(OrderViewModel.class);

        currentUserViewModel = ViewModelProviders.of(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(CurrentUserViewModel.class);
        String current_user = currentUserViewModel.loaduser();

        recyclerView = requireActivity().findViewById(R.id.recycleview_userOrder);
        myOrderAdapter = new MyOrderAdapter(requireActivity(),getViewLifecycleOwner());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(myOrderAdapter);

        getUserOrder = orderViewModel.findOrderByUserState0(current_user);
        getUserOrder.observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                myOrderAdapter.setUserOrder(orders);
                myOrderAdapter.notifyDataSetChanged();
            }
        });



    }
}
