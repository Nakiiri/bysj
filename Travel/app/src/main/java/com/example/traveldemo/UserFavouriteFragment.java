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
import android.widget.Toast;

import com.example.traveldemo.Adapter.AttractionDetailAdapter;
import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.Entity.Favourite;
import com.example.traveldemo.ViewModel.AttractionViewModel;
import com.example.traveldemo.ViewModel.CurrentUserViewModel;
import com.example.traveldemo.ViewModel.FavouriteViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFavouriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private AttractionDetailAdapter attractionDetailAdapter;

    private FavouriteViewModel favouriteViewModel;
    private LiveData<List<Favourite>>findFavByUser;
    private CurrentUserViewModel currentUserViewModel;
    private AttractionViewModel attractionViewModel;
    private LiveData<List<Attraction>>getAllAttraction;
    private List<Attraction>getAttraction = new ArrayList<>();


    public UserFavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_favourite, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        favouriteViewModel = ViewModelProviders.of(requireActivity()).get(FavouriteViewModel.class);
        attractionViewModel = ViewModelProviders.of(requireActivity()).get(AttractionViewModel.class);
        currentUserViewModel = ViewModelProviders.of(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(CurrentUserViewModel.class);
        String Current_user = currentUserViewModel.loaduser();

        recyclerView = requireActivity().findViewById(R.id.recycleview_userFavourite);
        attractionDetailAdapter = new AttractionDetailAdapter(requireActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(attractionDetailAdapter);

        findFavByUser = favouriteViewModel.findFavByUser(Current_user);
        findFavByUser.observe(getViewLifecycleOwner(), new Observer<List<Favourite>>() {
            @Override
            public void onChanged(List<Favourite> favourites) {
                if(favourites.size()!=0){
                    for(int i=0;i<favourites.size();i++){
                        getAllAttraction = attractionViewModel.findAttractionByID(favourites.get(i).getAttraction_id());
                        getAllAttraction.observe(getViewLifecycleOwner(), new Observer<List<Attraction>>() {
                            @Override
                            public void onChanged(List<Attraction> attractions) {
                                getAttraction.add(attractions.get(0));
                                attractionDetailAdapter.setAttractionByPlanid(getAttraction);
                                attractionDetailAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    getAttraction = new ArrayList<>();


                }

            }
        });





    }
}
