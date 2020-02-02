package com.example.traveldemo.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.traveldemo.Dao.TravelPlanDao;
import com.example.traveldemo.Database.TravelPlanDatabase;
import com.example.traveldemo.Entity.TravelPlan;

import java.util.List;

public class TravelPlanRepository {
    private TravelPlanDao travelPlanDao;
    public TravelPlanRepository(Context context) {
        TravelPlanDatabase travelPlanDatabase = TravelPlanDatabase.getTravelPlanDatabase(context.getApplicationContext());
        travelPlanDao = travelPlanDatabase.getTravelPlanDao();
    }

    public LiveData<List<TravelPlan>>findPlanByAgency(String pattern){
        return travelPlanDao.findPlanByAgency(pattern);
    }
    public LiveData<List<TravelPlan>>allPlan(){
        return travelPlanDao.allPlan();
    }
    public LiveData<List<TravelPlan>>findPlanByCity(String pattern){
        return travelPlanDao.findPlanByCity("%" + pattern + "%");
    }
    public LiveData<List<TravelPlan>>findPlanByID(int pattern){
        return travelPlanDao.findPlanByID(pattern);
    }
    public LiveData<List<TravelPlan>>allPlanPriceDesc(){
        return travelPlanDao.allPlanPriceDesc();
    }
    public LiveData<List<TravelPlan>>allPlanPrice(){
        return travelPlanDao.allPlanPrice();
    }
    public LiveData<List<TravelPlan>>allPlanSaleDesc(){
        return travelPlanDao.allPlanSaleDesc();
    }
    public LiveData<List<TravelPlan>>allPlanMarkDesc(){
        return travelPlanDao.allPlanMarkDesc();
    }





    public void insertPlan(TravelPlan...travelPlans){
        new InsertAsyncTask(travelPlanDao).execute(travelPlans);
    }
    public void deletePlan(TravelPlan...travelPlans){
        new DeleteAsyncTask(travelPlanDao).execute(travelPlans);
    }



    static class InsertAsyncTask extends AsyncTask<TravelPlan,Void,Void>{
        private TravelPlanDao travelPlanDao;

        public InsertAsyncTask(TravelPlanDao travelPlanDao) {
            this.travelPlanDao = travelPlanDao;
        }

        @Override
        protected Void doInBackground(TravelPlan... travelPlans) {
            travelPlanDao.insertPlan(travelPlans);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<TravelPlan,Void,Void>{
        private TravelPlanDao travelPlanDao;

        public DeleteAsyncTask(TravelPlanDao travelPlanDao) {
            this.travelPlanDao = travelPlanDao;
        }

        @Override
        protected Void doInBackground(TravelPlan... travelPlans) {
            travelPlanDao.deletePlan(travelPlans);
            return null;
        }
    }
}
