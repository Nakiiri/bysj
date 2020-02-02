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

import com.example.traveldemo.Adapter.AttractionDetailAdapter;
import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.ViewModel.AttractionViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    private RecyclerView recyclerView;
    private AttractionDetailAdapter attractionDetailAdapter;
    private AttractionViewModel attractionViewModel;
    private LiveData<List<Attraction>>getAllAttractionByFav;


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        attractionViewModel = ViewModelProviders.of(requireActivity()).get(AttractionViewModel.class);

        recyclerView = requireActivity().findViewById(R.id.recycview_secondView);
        attractionDetailAdapter = new AttractionDetailAdapter(requireActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(attractionDetailAdapter);

        getAllAttractionByFav = attractionViewModel.findAllAttraction();
        getAllAttractionByFav.observe(getViewLifecycleOwner(), new Observer<List<Attraction>>() {
            @Override
            public void onChanged(List<Attraction> attractions) {
                attractionDetailAdapter.setAttractionByPlanid(attractions);
                attractionDetailAdapter.notifyDataSetChanged();
            }
        });





    }
}
