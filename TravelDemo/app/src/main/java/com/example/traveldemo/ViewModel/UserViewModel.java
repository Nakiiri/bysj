package com.example.traveldemo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.traveldemo.Dao.UserDao;
import com.example.traveldemo.Entity.User;
import com.example.traveldemo.Repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserDao userDao;
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);

    }

    public LiveData<List<User>> getAllUserLive() {
        return userRepository.getAllUserLive();
    }
    public LiveData<List<User>> findUserByLoginname(String pattern){
        return userRepository.findUserByLoginname(pattern);
    }


    public void insertUser(User... users){
        userRepository.insertUser(users);
    }
    void updateUser(User...users){
        userRepository.updateUser(users);
    }
    public void updateUserby(User...users){
        userRepository.updatebylogin(users);
    }
//    List<User> findUser(String pattern){
//        return userRepository.findUser(pattern);
//    }


}
