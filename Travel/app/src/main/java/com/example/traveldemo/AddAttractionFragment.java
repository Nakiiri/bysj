package com.example.traveldemo;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.ViewModel.AttractionViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddAttractionFragment extends Fragment {
    EditText editText_name,editText_open,editText_des,editText_pos,editText_enjoy,editText_imageuri,editText_hugeimage,editText_des_detail;
    Button button_addAttraction;
    AttractionViewModel attractionViewModel;


    public AddAttractionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_attraction, container, false);
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
        button_addAttraction = requireActivity().findViewById(R.id.button_attractionadd);
        editText_hugeimage = requireActivity().findViewById(R.id.editText_attractionAdd_huge_image);
        editText_des_detail = requireActivity().findViewById(R.id.editText_attractionAdd_des_detail);

        button_addAttraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText_name.getText().toString().trim();
                String open = editText_open.getText().toString().trim();
                String des = editText_des.getText().toString().trim();
                String pos = editText_pos.getText().toString().trim();
                String enjoy = editText_enjoy.getText().toString().trim();
                String imageuri = editText_imageuri.getText().toString().trim();
                String huge = editText_hugeimage.getText().toString().trim();
                String des_detail = editText_des_detail.getText().toString().trim();
                int plan_id = getArguments().getInt("plan_id");

                if(!name.isEmpty() && !open.isEmpty() &&!des.isEmpty() &&!pos.isEmpty() &&!enjoy.isEmpty() &&!imageuri.isEmpty()){
                    Attraction attraction = new Attraction(plan_id,name,open,des,pos,enjoy,imageuri,500,huge,des_detail);
                    attractionViewModel.insertAttraction(attraction);
                    Bundle bundle = new Bundle();
                    bundle.putInt("plan_id",plan_id);
                    Toast.makeText(requireContext(),"添加成功！",Toast.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_addAttractionFragment_to_attractionFragment,bundle);

                }else {
                    Toast.makeText(requireContext(),"请填写完整！",Toast.LENGTH_SHORT).show();
                }




            }
        });

    }
}
