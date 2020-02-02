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

import com.example.traveldemo.Entity.TravelPlan;
import com.example.traveldemo.ViewModel.TravelPlanViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlanFragment extends Fragment {
    private EditText editText_country,editText_city,editText_origin,editText_departuretime,editText_enjoytime,editText_totalprice,editText_image,editText_title;
    private Button button_agencyAdd;
    private TravelPlanViewModel travelPlanViewModel;


    public AddPlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_plan, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        travelPlanViewModel = ViewModelProviders.of(requireActivity()).get(TravelPlanViewModel.class);


        editText_country = requireActivity().findViewById(R.id.editText_plancountry);
        editText_city = requireActivity().findViewById(R.id.editText_plancity);
        editText_origin = requireActivity().findViewById(R.id.editText_origin);
        editText_departuretime = requireActivity().findViewById(R.id.editText_departuretime);
        editText_enjoytime = requireActivity().findViewById(R.id.editText_enjoytime);
        editText_totalprice = requireActivity().findViewById(R.id.editText_totalprice);
        button_agencyAdd = requireActivity().findViewById(R.id.button_agencyadd);
        editText_image = requireActivity().findViewById(R.id.editText_addPlan_image);
        editText_title = requireActivity().findViewById(R.id.editText_addPlan_title);

        button_agencyAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = editText_country.getText().toString().trim();
                String city = editText_city.getText().toString().trim();
                String origin = editText_origin.getText().toString().trim();
                String departure = editText_departuretime.getText().toString().trim();
                String enjoytime = editText_enjoytime.getText().toString().trim();
                String price = "￥"+editText_totalprice.getText().toString().trim();
                String current_agency = getArguments().getString(getResources().getString(R.string.agency_loginnameadd));
                String image_url = editText_image.getText().toString().trim();
                String title = editText_title.getText().toString().trim();

                if(!country.isEmpty()&&!city.isEmpty()&&!origin.isEmpty()&&!departure.isEmpty()&&!enjoytime.isEmpty()&&!price.isEmpty()&&!image_url.isEmpty()&&!title.isEmpty()){
                    TravelPlan travelPlan = new TravelPlan(current_agency,country,city,origin,price,departure,enjoytime,image_url,title,0,5);
                    travelPlanViewModel.insertTravlePlan(travelPlan);
                    Bundle bundle = new Bundle();
                    bundle.putString(getResources().getString(R.string.agency_loginname),current_agency);
                    Toast.makeText(requireContext(),"添加成功！",Toast.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_addPlanFragment_to_travelAgencyFragment,bundle);
                }else{
                    Toast.makeText(requireContext(),"请填写完整！",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
