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

import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.Entity.TravelAgency;
import com.example.traveldemo.Entity.TravelPlan;
import com.example.traveldemo.Entity.User;
import com.example.traveldemo.ViewModel.CurrentUserViewModel;
import com.example.traveldemo.ViewModel.OrderViewModel;
import com.example.traveldemo.ViewModel.TravelAgencyViewModel;
import com.example.traveldemo.ViewModel.TravelPlanViewModel;
import com.example.traveldemo.ViewModel.UserViewModel;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private EditText editText_name,editText_password;
    private Button button_register,button_login,button_travellogin;
    private UserViewModel userViewModel;
    private CurrentUserViewModel currentUserViewModel;
    private TravelAgencyViewModel travelAgencyViewModel;



    //插入测试用旅行方案
    private TravelPlanViewModel travelPlanViewModel;


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
        travelAgencyViewModel = ViewModelProviders.of(requireActivity()).get(TravelAgencyViewModel.class);
        travelPlanViewModel = ViewModelProviders.of(requireActivity()).get(TravelPlanViewModel.class);

        currentUserViewModel = ViewModelProviders.of(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(CurrentUserViewModel.class);

        editText_name = requireActivity().findViewById(R.id.editText_login);
        editText_password = requireActivity().findViewById(R.id.editText_password);
        button_register = requireActivity().findViewById(R.id.button_register);
        button_login = requireActivity().findViewById(R.id.button_userlogin);
        button_travellogin = requireActivity().findViewById(R.id.button_travellogin);







        //测试用旅行方案
        LiveData<List<TravelAgency>>getAllAgency = travelAgencyViewModel.getAllAgency();
        getAllAgency.observe(getViewLifecycleOwner(), new Observer<List<TravelAgency>>() {
            @Override
            public void onChanged(final List<TravelAgency> travelAgencies) {
//                for(int i=0;i<travelAgencies.size();i++){
//                    int j = travelAgencies.size()-1-i;
//                    final String name = travelAgencies.get(j).getLogin_name();
//                    LiveData<List<TravelPlan>>getAgencyPlan = travelPlanViewModel.allPlanLive();
//                    getAgencyPlan.observe(getViewLifecycleOwner(), new Observer<List<TravelPlan>>() {
//                        @Override
//                        public void onChanged(List<TravelPlan> travelPlans) {
//
//
//                            if(travelPlans.size()==0){
//                                TravelPlan travelPlan2 = new TravelPlan(name,"日本","大阪","上海","￥33333","2020.01.02","5天","https://cdn.pixabay.com/photo/2016/03/16/13/41/cherry-blossom-1260641_960_720.jpg","东京大阪京都5日线路");
//                                TravelPlan  travelPlan1 = new TravelPlan(name,"中国","云南","上海","￥11111","2020.01.01","4天","https://cdn.pixabay.com/photo/2017/07/10/11/19/in-yunnan-province-2489793_960_720.jpg","云南拉市海4日攻略");
//                                TravelPlan travelPlan = new TravelPlan(name,"中国","重庆","上海","￥16161","2020.01.03","5天","https://cdn.pixabay.com/photo/2016/03/01/14/11/chongqing-night-1230448_960_720.jpg","重庆夜景·洪崖洞·黄花园大桥");
//                                travelPlanViewModel.insertTravlePlan(travelPlan,travelPlan1,travelPlan2);
//                            }
//                        }
//                    });
//                }
                LiveData<List<TravelPlan>>getAllPlan = travelPlanViewModel.allPlanLive();
                getAllPlan.observe(getViewLifecycleOwner(), new Observer<List<TravelPlan>>() {
                    @Override
                    public void onChanged(List<TravelPlan> travelPlans) {
                        if(travelPlans.size()==0){
                            String name = travelAgencies.get(1).getLogin_name();
                            TravelPlan travelPlan2 = new TravelPlan(name,"日本","大阪","上海","33333","2020.01.02","5天","https://cdn.pixabay.com/photo/2016/03/16/13/41/cherry-blossom-1260641_960_720.jpg","东京大阪京都5日线路",0,5);
                            TravelPlan  travelPlan1 = new TravelPlan(name,"中国","云南","上海","11111","2020.01.01","4天","https://cdn.pixabay.com/photo/2017/07/10/11/19/in-yunnan-province-2489793_960_720.jpg","云南拉市海4日攻略",0,5);
                            TravelPlan travelPlan = new TravelPlan(name,"中国","重庆","上海","16161","2020.01.03","5天","https://cdn.pixabay.com/photo/2016/03/01/14/11/chongqing-night-1230448_960_720.jpg","重庆夜景·洪崖洞·黄花园大桥",0,5);
                            travelPlanViewModel.insertTravlePlan(travelPlan,travelPlan1,travelPlan2);
                        }
                    }
                });





            }
        });
















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


                        findUser.observe(getViewLifecycleOwner(), new Observer<List<User>>() {
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
                                    User user = new User(name,password,"旅行者","男"," ","1");
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


                     findUser.observe(getViewLifecycleOwner(), new Observer<List<User>>() {
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




        button_travellogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String name = editText_name.getText().toString().trim();
                String password = editText_password.getText().toString().trim();
                if(!name.isEmpty()&&!password.isEmpty()){
                    LiveData<List<TravelAgency>>getAgency = travelAgencyViewModel.findAgencyByLogin(name);
                    getAgency.observe(getViewLifecycleOwner(), new Observer<List<TravelAgency>>() {
                        @Override
                        public void onChanged(List<TravelAgency> travelAgencies) {
                            if(travelAgencies.size()!=0){
                                final String name = editText_name.getText().toString().trim();
                                String password = editText_password.getText().toString().trim();
                                if(!name.isEmpty()){
                                    if(password.equals(travelAgencies.get(0).getLogin_password())){

                                        Bundle bundle = new Bundle();
                                        bundle.putString(getContext().getResources().getString(R.string.agency_loginname),name);
                                        Toast.makeText(requireContext(),"登录成功！",Toast.LENGTH_SHORT).show();
                                        editText_name.setText("");
                                        editText_password.setText("");
                                        NavController navController = Navigation.findNavController(v);
                                        navController.navigate(R.id.action_loginFragment_to_travelAgencyFragment,bundle);
                                    }else{
                                        Toast.makeText(requireContext(),"密码不正确！",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }else{
                                Toast.makeText(requireContext(),"该旅行社不存在！",Toast.LENGTH_SHORT).show();
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

