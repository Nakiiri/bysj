package com.example.traveldemo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.traveldemo.Entity.Order;
import com.example.traveldemo.Repository.OrderRepository;

import java.util.List;

public class OrderViewModel extends AndroidViewModel {
    private OrderRepository orderRepository;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        orderRepository = new OrderRepository(application);
    }

    public LiveData<List<Order>> findOrderByUserState12(String pattern){
        return orderRepository.findOrderByUserState12(pattern);
    }
    public LiveData<List<Order>>findOrderByUserState0(String pattern){
        return orderRepository.findOrderByUserState0(pattern);
    }
    public LiveData<List<Order>>findOrderByAgencyLogin(String pattern){
        return orderRepository.findOrderByAgencyLogin(pattern);
    }
    public LiveData<List<Order>>findOrderByUserAndPlan(String pattern,int pattern2){
        return orderRepository.findOrderByUserAndPlan(pattern,pattern2);
    }
    public LiveData<List<Order>>findOrderByID(int pattern){
        return orderRepository.findOrderByID(pattern);
    }
    public LiveData<List<Order>>findOrderByAgency(String pattern){
        return orderRepository.findOrderByAgency(pattern);
    }
    public LiveData<List<Order>>findOrderByPlan(int pattern){
        return orderRepository.findOrderByPlan(pattern);
    }




    public void insertOrder(Order...orders){
        orderRepository.insertOrder(orders);
    }
    public void deleteOrder(Order...orders){
        orderRepository.deleteOrder(orders);
    }
    public void updateState1(Order...orders){
        orderRepository.updateState1(orders);
    }
    public void updateState2(Order...orders){
        orderRepository.updateState2(orders);
    }
    public void updateAssess(Order...orders){
        orderRepository.updateAssess(orders);
    }

}
