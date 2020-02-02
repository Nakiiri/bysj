package com.example.traveldemo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.traveldemo.Entity.TravelPlan;
import com.example.traveldemo.Repository.TravelPlanRepository;

import java.util.List;

public class TravelPlanViewModel extends AndroidViewModel {
    private TravelPlanRepository travelPlanRepository;

    public TravelPlanViewModel(@NonNull Application application) {
        super(application);
        travelPlanRepository = new TravelPlanRepository(application);
    }

    public LiveData<List<TravelPlan>>findPlanByAgency(String pattern){
        return travelPlanRepository.findPlanByAgency(pattern);
    }
    public LiveData<List<TravelPlan>>allPlanLive(){
        return  travelPlanRepository.allPlan();
    }
    public LiveData<List<TravelPlan>>findPlanByCity(String pattern){
        return travelPlanRepository.findPlanByCity(pattern);
    }
    public LiveData<List<TravelPlan>>findPlanByID(int pattern){
        return travelPlanRepository.findPlanByID(pattern);
    }
    public LiveData<List<TravelPlan>>allPlanPriceDesc(){
        return travelPlanRepository.allPlanPriceDesc();
    }
    public LiveData<List<TravelPlan>>allPlanPrice(){
        return travelPlanRepository.allPlanPrice();
    }
    public LiveData<List<TravelPlan>>allPlanSaleDesc(){
        return travelPlanRepository.allPlanSaleDesc();
    }
    public LiveData<List<TravelPlan>>allPlanMarkDesc(){
        return travelPlanRepository.allPlanMarkDesc();
    }




    public void insertTravlePlan(TravelPlan...travelPlans){
        travelPlanRepository.insertPlan(travelPlans);
    }
    public void deleteTravlePlan(TravelPlan...travelPlans){
        travelPlanRepository.deletePlan(travelPlans);
    }
}
