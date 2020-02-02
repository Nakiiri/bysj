package com.example.traveldemo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.traveldemo.Entity.TravelAgency;
import com.example.traveldemo.Repository.TravelAgencyRepository;

import java.util.List;

public class TravelAgencyViewModel extends AndroidViewModel {
    private TravelAgencyRepository travelAgencyRepository;

    public TravelAgencyViewModel(@NonNull Application application) {
        super(application);
        travelAgencyRepository = new TravelAgencyRepository(application);
    }

    public LiveData<List<TravelAgency>> findAgencyByLogin(String pattern){
        return travelAgencyRepository.findAgencyByLogin(pattern);
    }
    public LiveData<List<TravelAgency>>getAllAgency(){
        return travelAgencyRepository.getAllAgency();
    }





    public void insertAgency(TravelAgency...travelAgencies){
        travelAgencyRepository.insertAgency(travelAgencies);
    }
}
