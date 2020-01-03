package com.example.traveldemo;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.traveldemo.Entity.User;
import com.example.traveldemo.ViewModel.CurrentUserViewModel;
import com.example.traveldemo.ViewModel.UserViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    Button button_login,button_logout;
    CurrentUserViewModel currentUserViewModel;
    TextView textView_login,textView_tips,textView_loginname,textView_personal;
    SwipeRefreshLayout swipeRefreshLayout;
    String Current_user;
    UserViewModel userViewModel;
    ImageView imageView_update,imageView_userimage;




    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        currentUserViewModel = ViewModelProviders.of(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(CurrentUserViewModel.class);
        userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel.class);
        button_login = requireActivity().findViewById(R.id.button_login);
        textView_login = requireActivity().findViewById(R.id.Login_hi);
        textView_tips = requireActivity().findViewById(R.id.textView_tips);
        textView_loginname = requireActivity().findViewById(R.id.textView_loginname);
        textView_personal = requireActivity().findViewById(R.id.textView_personal);
        imageView_update = requireActivity().findViewById(R.id.imageView_update);
        button_logout = requireActivity().findViewById(R.id.button_logout);
        swipeRefreshLayout = requireActivity().findViewById(R.id.swipRefreshLayout);
        imageView_userimage = requireActivity().findViewById(R.id.imageView_userimage);



        isUserLogin();
        if(!Current_user.equals("0")){
            button_login.setVisibility(View.GONE);
            textView_tips.setVisibility(View.GONE);
            textView_login.setVisibility(View.GONE);
            textView_loginname.setVisibility(View.VISIBLE);
            textView_personal.setVisibility(View.VISIBLE);
            imageView_update.setVisibility(View.VISIBLE);
            imageView_userimage.setVisibility(View.VISIBLE);
            LiveData<List<User>>current_user = userViewModel.findUserByLoginname(Current_user);
            current_user.observe(requireActivity(), new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    textView_loginname.setText(users.get(0).getUser_name());
                    textView_personal.setText(users.get(0).getSign());
                }
            });

        }

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_thirdFragment_to_loginFragment);
            }
        });

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUserViewModel.save("0");
                button_login.setVisibility(View.VISIBLE);
                textView_tips.setVisibility(View.VISIBLE);
                textView_login.setVisibility(View.VISIBLE);
                textView_loginname.setVisibility(View.GONE);
                textView_personal.setVisibility(View.GONE);
                imageView_update.setVisibility(View.GONE);
                imageView_userimage.setVisibility(View.GONE);

            }
        });

        imageView_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_thirdFragment_to_modfyFragment);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                isUserLogin();

            }
        });

    }

    void isUserLogin(){
        currentUserViewModel = ViewModelProviders.of(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(CurrentUserViewModel.class);
        Current_user = currentUserViewModel.loaduser();
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
