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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.traveldemo.Entity.User;
import com.example.traveldemo.ViewModel.CurrentUserViewModel;
import com.example.traveldemo.ViewModel.UserViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModfyFragment extends Fragment {
    EditText editText_username,editText_gender,editText_sign;
    Button button_save;
    UserViewModel userViewModel;
    CurrentUserViewModel currentUserViewModel;
    String Current_user;


    public ModfyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modfy, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel.class);
        currentUserViewModel = ViewModelProviders.of(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(CurrentUserViewModel.class);
        editText_username = requireActivity().findViewById(R.id.editText_username);
        editText_gender = requireActivity().findViewById(R.id.editText_usergender);
        editText_sign = requireActivity().findViewById(R.id.editText_usersign);
        button_save = requireActivity().findViewById(R.id.button_usersave);

        Current_user = currentUserViewModel.loaduser();
        LiveData<List<User>>currentUser = userViewModel.findUserByLoginname(Current_user);
        currentUser.observe(requireActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(final List<User> users) {
                editText_username.setText(users.get(0).getUser_name());
                editText_gender.setText(users.get(0).getGender());
                editText_sign.setText(users.get(0).getSign());
                if (users.size()!=0){
                    button_save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String username = editText_username.getText().toString().trim();
                            String usergender = editText_gender.getText().toString().trim();
                            String usersign = editText_sign.getText().toString().trim();
                            User user = new User(Current_user,users.get(0).getLogin_password(),username,usergender,usersign);
                            userViewModel.updateUserby(user);
                            Toast.makeText(requireContext(),"保存成功！",Toast.LENGTH_SHORT).show();
                            NavController controller = Navigation.findNavController(v);
                            controller.navigate(R.id.action_modfyFragment_to_thirdFragment);

                        }
                    });
                }
            }
        });





    }


}
