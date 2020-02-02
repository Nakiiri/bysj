package com.example.traveldemo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.Entity.Favourite;
import com.example.traveldemo.Repository.FavouriteRepository;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {
    private FavouriteRepository favouriteRepository;

    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        favouriteRepository = new FavouriteRepository(application);
    }

    public LiveData<List<Favourite>> findFavByUserAndAttraction(String pattern, int pattern2){
        return favouriteRepository.findFavByUserAndAttraction(pattern,pattern2);
    }
    public LiveData<List<Favourite>>findFavByUser(String pattern){
        return favouriteRepository.findFavByUser(pattern);
    }



    public void insertFavourite(Favourite...favourites){
        favouriteRepository.insertFavourite(favourites);
    }
    public void deleteFavourite(Favourite...favourites){
        favouriteRepository.deleteFavourite(favourites);
    }
}
