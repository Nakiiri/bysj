package com.example.traveldemo.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.traveldemo.Dao.AttractionDao;
import com.example.traveldemo.Database.AttractionDatabase;
import com.example.traveldemo.Entity.Attraction;

import java.util.List;

public class AttractionRepository {
    private AttractionDao attractionDao;
    public AttractionRepository(Context context) {
        AttractionDatabase attractionDatabase = AttractionDatabase.getAttractionDatabase(context.getApplicationContext());
        attractionDao = attractionDatabase.getAttractionDao();
    }

    public LiveData<List<Attraction>>findAttractionByPlan(int pattern){
        return attractionDao.findAttractionByPlan(pattern);
    }
    public LiveData<List<Attraction>>findAllAttraction(){
        return attractionDao.findAllAttraction();
    }
    public LiveData<List<Attraction>>findAttractionByID(int pattern){
        return attractionDao.findAttractionByID(pattern);
    }





    public void insertAttraction(Attraction...attractions){
        new InsertAsyncTask(attractionDao).execute(attractions);
    }
    public void deleteAttraction(Attraction...attractions){
        new DeleteAsyncTask(attractionDao).execute(attractions);
    }
    public void updateFavourite(Attraction...attractions){
        new UpdateFavouriteAsyncTask(attractionDao).execute(attractions);
    }
    public void updateAttraction(Attraction...attractions){
        new UpdateAsyncTask(attractionDao).execute(attractions);
    }
    public void updateAddAttraction(Attraction...attractions){
        new UpdateAddAsyncTask(attractionDao).execute(attractions);
    }
    public void updateMinusAttraction(Attraction...attractions){
        new UpdateMinusAsyncTask(attractionDao).execute(attractions);
    }



    static class InsertAsyncTask extends AsyncTask<Attraction,Void,Void> {
        AttractionDao attractionDao;

        public InsertAsyncTask(AttractionDao attractionDao) {
            this.attractionDao = attractionDao;
        }

        @Override
        protected Void doInBackground(Attraction... attractions) {
            attractionDao.insertAttraction(attractions);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Attraction,Void,Void> {
        AttractionDao attractionDao;

        public DeleteAsyncTask(AttractionDao attractionDao) {
            this.attractionDao = attractionDao;
        }

        @Override
        protected Void doInBackground(Attraction... attractions) {
            attractionDao.deleteAttraction(attractions);
            return null;
        }
    }

    static class UpdateFavouriteAsyncTask extends AsyncTask<Attraction,Void,Void> {
        AttractionDao attractionDao;

        public UpdateFavouriteAsyncTask(AttractionDao attractionDao) {
            this.attractionDao = attractionDao;
        }

        @Override
        protected Void doInBackground(Attraction... attractions) {
            attractionDao.updateAttractionFavourite(attractions[0].getFavourite(),attractions[0].getAttraction_id());
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Attraction,Void,Void> {
        AttractionDao attractionDao;

        public UpdateAsyncTask(AttractionDao attractionDao) {
            this.attractionDao = attractionDao;
        }

        @Override
        protected Void doInBackground(Attraction... attractions) {
            attractionDao.updateAttraction(attractions[0].getAttraction_name(),attractions[0].getAttraction_opentime(),attractions[0].getAttraction_description(),attractions[0].getAttraction_position(),attractions[0].getAttraction_enjoytime(),attractions[0].getImage_url(),attractions[0].getAttraction_id());
            return null;
        }
    }

    static class UpdateAddAsyncTask extends AsyncTask<Attraction,Void,Void> {
        AttractionDao attractionDao;

        public UpdateAddAsyncTask(AttractionDao attractionDao) {
            this.attractionDao = attractionDao;
        }

        @Override
        protected Void doInBackground(Attraction... attractions) {
            attractionDao.updateFavAdd(attractions[0].getAttraction_id());
            return null;
        }
    }

    static class UpdateMinusAsyncTask extends AsyncTask<Attraction,Void,Void> {
        AttractionDao attractionDao;

        public UpdateMinusAsyncTask(AttractionDao attractionDao) {
            this.attractionDao = attractionDao;
        }

        @Override
        protected Void doInBackground(Attraction... attractions) {
            attractionDao.updateFavMinus(attractions[0].getAttraction_id());
            return null;
        }
    }
}
