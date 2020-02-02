package com.example.traveldemo.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.traveldemo.Dao.FavouriteDao;
import com.example.traveldemo.Database.FavouriteDatabase;
import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.Entity.Favourite;

import java.util.List;

public class FavouriteRepository {
    private FavouriteDao favouriteDao;

    public FavouriteRepository(Context context) {
        FavouriteDatabase favouriteDatabase = FavouriteDatabase.getFavouriteDatabase(context.getApplicationContext());
        favouriteDao = favouriteDatabase.getFavouriteDao();
    }

    public LiveData<List<Favourite>>findFavByUserAndAttraction(String pattern, int pattern2){
        return favouriteDao.findFavByUserAndAttraction(pattern,pattern2);
    }
    public LiveData<List<Favourite>>findFavByUser(String pattern){
        return favouriteDao.findFavByUser(pattern);
    }








    public void insertFavourite(Favourite...favourites){
        new InsertAsyncTask(favouriteDao).execute(favourites);
    }
    public void deleteFavourite(Favourite...favourites){
        new DeleteAsyncTask(favouriteDao).execute(favourites);
    }








    static class InsertAsyncTask extends AsyncTask<Favourite,Void,Void>{
        private FavouriteDao favouriteDao;

        public InsertAsyncTask(FavouriteDao favouriteDao) {
            this.favouriteDao = favouriteDao;
        }

        @Override
        protected Void doInBackground(Favourite... favourites) {
            favouriteDao.insertFavourite(favourites);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Favourite,Void,Void>{
        private FavouriteDao favouriteDao;

        public DeleteAsyncTask(FavouriteDao favouriteDao) {
            this.favouriteDao = favouriteDao;
        }

        @Override
        protected Void doInBackground(Favourite... favourites) {
            favouriteDao.deleteFavourite(favourites);
            return null;
        }
    }
}
