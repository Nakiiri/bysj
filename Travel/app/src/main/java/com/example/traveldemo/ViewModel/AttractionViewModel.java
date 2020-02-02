package com.example.traveldemo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.Repository.AttractionRepository;

import java.util.List;

public class AttractionViewModel extends AndroidViewModel {
    private AttractionRepository attractionRepository;

    public AttractionViewModel(@NonNull Application application) {
        super(application);
        attractionRepository = new AttractionRepository(application);
    }


    public LiveData<List<Attraction>>findAttractionByPlan(int pattern){
        return attractionRepository.findAttractionByPlan(pattern);
    }
    public LiveData<List<Attraction>>findAllAttraction(){
        return attractionRepository.findAllAttraction();
    }
    public LiveData<List<Attraction>>findAttractionByID(int pattern){
        return attractionRepository.findAttractionByID(pattern);
    }






    public void insertAttraction(Attraction...attractions){
        attractionRepository.insertAttraction(attractions);
    }
    public void deleteAttraction(Attraction...attractions){
        attractionRepository.deleteAttraction(attractions);
    }
    public void updateFavourite(Attraction...attractions){
        attractionRepository.updateFavourite(attractions);
    }
    public void updateAttraction(Attraction...attractions){
        attractionRepository.updateAttraction(attractions);
    }
    public void updateAddAttraction(Attraction...attractions){
        attractionRepository.updateAddAttraction(attractions);
    }
    public void updateMinusAttraction(Attraction...attractions){
        attractionRepository.updateMinusAttraction(attractions);
    }

}
