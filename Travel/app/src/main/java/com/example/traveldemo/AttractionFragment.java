package com.example.traveldemo;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.traveldemo.Adapter.AttractionAdapter;
import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.ViewModel.AttractionViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttractionFragment extends Fragment {
    private RecyclerView recyclerView;
    private AttractionAdapter attractionAdapter;
    private AttractionViewModel attractionViewModel;
    private FloatingActionButton floatingActionButton;
    private LiveData<List<Attraction>>getPlanAttraction;
    private List<Attraction>allAttraction;


    public AttractionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attraction, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String current_agency = getArguments().getString("agency_login2");
        final int plan_id = getArguments().getInt("plan_id");

        attractionViewModel = ViewModelProviders.of(requireActivity()).get(AttractionViewModel.class);

        floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton_addattraction);


        recyclerView = requireActivity().findViewById(R.id.recycleview_attraction);
        attractionAdapter = new AttractionAdapter(requireActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(attractionAdapter);


       getPlanAttraction = attractionViewModel.findAttractionByPlan(plan_id);
        getPlanAttraction.observe(requireActivity(), new Observer<List<Attraction>>() {
            @Override
            public void onChanged(List<Attraction> attractions) {
                allAttraction = attractions;
                attractionAdapter.setGetPlanAttraction(attractions);
                attractionAdapter.notifyDataSetChanged();
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("plan_id",plan_id);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_attractionFragment_to_addAttractionFragment,bundle);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.START) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final Attraction attractionToDelete = allAttraction.get(viewHolder.getAdapterPosition());
                attractionViewModel.deleteAttraction(attractionToDelete);
                Snackbar.make(requireActivity().findViewById(R.id.attractionFragmentView),"删除了一个景点",Snackbar.LENGTH_SHORT)
                        .setAction("撤销", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                attractionViewModel.insertAttraction(attractionToDelete);
                            }
                        })
                        .show();
            }
            Drawable icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_delete_forever_white_24dp);
            Drawable background = new ColorDrawable(Color.RED);

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                View itemView = viewHolder.itemView;
                int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight());

                int iconLeft,iconRight,iconTop,iconBottom;
                int backgroundLeft,backgroundRight,backgroundTop,backgroundBottom;
                backgroundTop = itemView.getTop() + 32;
                backgroundBottom = itemView.getBottom();
                iconTop = itemView.getTop() + iconMargin - 128;
                iconBottom = iconTop + icon.getIntrinsicHeight();
                if(dX>0){
                    backgroundLeft = itemView.getLeft();
                    backgroundRight = itemView.getLeft() + (int)dX;
                    background.setBounds(backgroundLeft,backgroundTop,backgroundRight,backgroundBottom);
                    iconLeft = itemView.getLeft() + iconMargin;
                    iconRight = iconLeft + icon.getIntrinsicWidth();
                    icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
                }else if(dX<0){
                    backgroundRight = itemView.getRight();
                    backgroundLeft = backgroundRight + (int)dX;
                    background.setBounds(backgroundLeft,backgroundTop,backgroundRight,backgroundBottom);
                    iconRight = itemView.getRight() - iconMargin;
                    iconLeft = iconRight - icon.getIntrinsicWidth();
                    icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
                }else{
                    background.setBounds(0,0,0,0);
                    icon.setBounds(0,0,0,0);
                }
                background.draw(c);
                icon.draw(c);
            }
        }).attachToRecyclerView(recyclerView);
    }
}
