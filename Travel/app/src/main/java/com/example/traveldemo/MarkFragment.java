package com.example.traveldemo;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.traveldemo.Adapter.MarkAdapter;
import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.ViewModel.OrderViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarkFragment extends Fragment {
    private RecyclerView recyclerView;
    private MarkAdapter markAdapter;
    private OrderViewModel orderViewModel;
    private LiveData<List<Order>>findOrderByPlan;


    public MarkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mark, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        orderViewModel = ViewModelProviders.of(requireActivity()).get(OrderViewModel.class);

        int plan_id = getArguments().getInt("plan_id");

        recyclerView = requireActivity().findViewById(R.id.recycleview_mark);
        markAdapter = new MarkAdapter(requireActivity(),getViewLifecycleOwner());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(markAdapter);

        findOrderByPlan = orderViewModel.findOrderByPlan(plan_id);
        findOrderByPlan.observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                markAdapter.setOrderByPlan(orders);
                markAdapter.notifyDataSetChanged();
            }
        });



    }
}
