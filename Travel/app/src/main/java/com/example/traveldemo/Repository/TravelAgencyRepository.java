package com.example.traveldemo.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.traveldemo.Dao.TravelAgencyDao;
import com.example.traveldemo.Database.TravelAgencyDatabase;
import com.example.traveldemo.Entity.TravelAgency;

import java.util.List;

public class TravelAgencyRepository {
    private TravelAgencyDao travelAgencyDao;

    public TravelAgencyRepository(Context context) {
        TravelAgencyDatabase travelAgencyDatabase = TravelAgencyDatabase.getTravelAgencyDatabase(context.getApplicationContext());
        travelAgencyDao = travelAgencyDatabase.getTravelAgencyDao();
    }

    public LiveData<List<TravelAgency>>findAgencyByLogin(String pattern){
        return travelAgencyDao.findAgencyByLogin(pattern);
    }
    public LiveData<List<TravelAgency>>getAllAgency(){
        return travelAgencyDao.getAllAgency();
    }



    public void insertAgency(TravelAgency...travelAgencies){
        new InsertAsyncTask(travelAgencyDao).execute(travelAgencies);
    }




    static class InsertAsyncTask extends AsyncTask<TravelAgency,Void,Void>{
        private TravelAgencyDao travelAgencyDao;

        public InsertAsyncTask(TravelAgencyDao travelAgencyDao) {
            this.travelAgencyDao = travelAgencyDao;
        }

        @Override
        protected Void doInBackground(TravelAgency... travelAgencies) {
            travelAgencyDao.insertAgency(travelAgencies);
            return null;
        }
    }


}
