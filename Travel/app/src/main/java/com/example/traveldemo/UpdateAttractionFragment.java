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
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.ViewModel.AttractionViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateAttractionFragment extends Fragment {
    EditText editText_name,editText_open,editText_des,editText_pos,editText_enjoy,editText_imageuri,editText_hugeimage,editText_des_detail;
    Button button_updateAttraction;
    TextView textView_fav;
    AttractionViewModel attractionViewModel;
    List<Attraction> getAttraction = new ArrayList<>();


    public UpdateAttractionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_attraction, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        attractionViewModel = ViewModelProviders.of(requireActivity()).get(AttractionViewModel.class);

        editText_name = requireActivity().findViewById(R.id.editText_attractionname);
        editText_open = requireActivity().findViewById(R.id.editText_attraction_opentime);
        editText_des = requireActivity().findViewById(R.id.editText_attraction_des);
        editText_pos = requireActivity().findViewById(R.id.editText_attraction_pos);
        editText_enjoy = requireActivity().findViewById(R.id.editText_attraction_enjoy);
        editText_imageuri = requireActivity().findViewById(R.id.editText_attraction_image);
        button_updateAttraction = requireActivity().findViewById(R.id.button_attractionupdate);
        textView_fav = requireActivity().findViewById(R.id.textView_attraction_favourite);
        editText_hugeimage = requireActivity().findViewById(R.id.editText_attractionUpdate_huge_image);
        editText_des_detail = requireActivity().findViewById(R.id.editText_attractionUpdate_des_detail);

        final int attraction_id = getArguments().getInt("attraction_id");

        LiveData<List<Attraction>>getAttractionByID = attractionViewModel.findAttractionByID(attraction_id);
        getAttractionByID.observe(getViewLifecycleOwner(), new Observer<List<Attraction>>() {
            @Override
            public void onChanged(final List<Attraction> attractions) {
                if(attractions.size()!=0){
                    getAttraction = attractions;
                    editText_name.setText(attractions.get(0).getAttraction_name());
                    editText_open.setText(attractions.get(0).getAttraction_opentime());
                    editText_des.setText(attractions.get(0).getAttraction_description());
                    editText_pos.setText(attractions.get(0).getAttraction_position());
                    editText_enjoy.setText(attractions.get(0).getAttraction_enjoytime());
                    editText_imageuri.setText(attractions.get(0).getImage_url());
                    textView_fav.setText(String.valueOf(attractions.get(0).getFavourite()));
                    editText_hugeimage.setText(attractions.get(0).getHuge_image());
                    editText_des_detail.setText(attractions.get(0).getDes_detail());



                }
            }
        });

        button_updateAttraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getAttraction.size()!=0){
                    String name = editText_name.getText().toString().trim();
                    String open = editText_open.getText().toString().trim();
                    String des = editText_des.getText().toString().trim();
                    String pos = editText_pos.getText().toString().trim();
                    String enjoy = editText_enjoy.getText().toString().trim();
                    String imageuri = editText_imageuri.getText().toString().trim();
                    String huge = editText_hugeimage.getText().toString().trim();
                    String des_detail = editText_des_detail.getText().toString().trim();
                    Attraction attraction = new Attraction(getAttraction.get(0).getPlan_id(),name,open,des,pos,enjoy,imageuri,getAttraction.get(0).getFavourite(),huge,des_detail);
                    attraction.setAttraction_id(attraction_id);
                    attractionViewModel.updateAttraction(attraction);
                    Bundle bundle = new Bundle();
                    bundle.putInt("plan_id",getAttraction.get(0).getPlan_id());

                    Toast.makeText(requireContext(),"修改成功！",Toast.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_updateAttractionFragment_to_attractionFragment,bundle);
                }


            }
        });



    }
}
