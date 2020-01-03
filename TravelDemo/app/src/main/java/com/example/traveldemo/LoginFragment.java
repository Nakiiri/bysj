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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    EditText editText_name,editText_password;
    Button button_register,button_login,button_travellogin;
    UserViewModel userViewModel;
    CurrentUserViewModel currentUserViewModel;



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel.class);
        currentUserViewModel = ViewModelProviders.of(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(CurrentUserViewModel.class);

        editText_name = requireActivity().findViewById(R.id.editText_login);
        editText_password = requireActivity().findViewById(R.id.editText_password);
        button_register = requireActivity().findViewById(R.id.button_register);
        button_login = requireActivity().findViewById(R.id.button_userlogin);
        button_travellogin = requireActivity().findViewById(R.id.button_travellogin);

//        button_register.setEnabled(false);
//        button_login.setEnabled(false);
//        button_travellogin.setEnabled(false);
//
//
//
//        TextWatcher textWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String name = editText_name.getText().toString().trim();
//                String password = editText_password.getText().toString().trim();
//                if(!name.isEmpty() && !password.isEmpty()){
//                    button_register.setEnabled(true);
//                    button_login.setEnabled(true);
//                    button_travellogin.setEnabled(true);
//                }
//
//            }
//        };

//        editText_name.addTextChangedListener(textWatcher);
//        editText_password.addTextChangedListener(textWatcher);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                 String name = editText_name.getText().toString().trim();
                 String password = editText_password.getText().toString().trim();
                if(!name.isEmpty()&&!password.isEmpty()){
                    if(isMobile(name)){
                        //在UserViewModel中取出所有User
                        final LiveData<List<User>>findUser = userViewModel.findUserByLoginname(name);


                        findUser.observe(requireActivity(), new Observer<List<User>>() {
                            @Override
                            public void onChanged(List<User> users) {
                                if(users.size()!=0){
                                    String name = editText_name.getText().toString().trim();
                                    if(!name.isEmpty()){
                                        Toast.makeText(requireContext(),"该用户已存在",Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    String name = editText_name.getText().toString().trim();
                                    String password = editText_password.getText().toString().trim();
                                    User user = new User(name,password,"旅行者","男"," ");
                                    userViewModel.insertUser(user);
                                    Toast.makeText(requireContext(),"注册成功！",Toast.LENGTH_SHORT).show();
                                    currentUserViewModel.save(name);

                                    editText_name.setText("");
                                    editText_password.setText("");
                                    NavController navController = Navigation.findNavController(v);
                                    navController.navigate(R.id.action_loginFragment_to_thirdFragment);

                                }
                            }

                        });

                    }else{
                        Toast.makeText(requireContext(),"请输入正确的手机号！",Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(requireContext(),"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
                }

            }
        });




        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                String name = editText_name.getText().toString().trim();
                String password = editText_password.getText().toString().trim();
                if(!name.isEmpty()&&!password.isEmpty()){
                     //在UserViewModel中取出所有User
                     final LiveData<List<User>>findUser = userViewModel.findUserByLoginname(name);


                     findUser.observe(requireActivity(), new Observer<List<User>>() {
                         @Override
                         public void onChanged(List<User> users) {
                             if(users.size()!=0){
                                 String name = editText_name.getText().toString().trim();
                                 String password = editText_password.getText().toString().trim();
                                 if(!name.isEmpty()){
                                     if(password.equals(users.get(0).getLogin_password())){

                                         Toast.makeText(requireContext(),"登录成功！",Toast.LENGTH_SHORT).show();
                                         currentUserViewModel.save(name);
                                         editText_name.setText("");
                                         editText_password.setText("");
                                         NavController navController = Navigation.findNavController(v);
                                         navController.navigate(R.id.action_loginFragment_to_thirdFragment);
                                     }else{
                                         Toast.makeText(requireContext(),"密码不正确！",Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             } else {
                                 Toast.makeText(requireContext(),"该用户不存在！",Toast.LENGTH_SHORT).show();

                             }
                         }

                     });

                }else{
                    Toast.makeText(requireContext(),"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public static boolean isMobile(String str) {
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

}

