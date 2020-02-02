package com.example.traveldemo.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.traveldemo.Dao.MarkDao;
import com.example.traveldemo.Database.MarkDatabase;
import com.example.traveldemo.Entity.Mark;

import java.util.List;

public class MarkRepository {
    private MarkDao markDao;

    public MarkRepository(Context context) {
        MarkDatabase markDatabase = MarkDatabase.getMarkDatabase(context.getApplicationContext());
        markDao = markDatabase.getMarkDao();
    }

    public LiveData<List<Mark>> findMarkByPlan(int pattern){
        return markDao.findMarkByPlan(pattern);
    }
    public LiveData<List<Mark>>findMarkByUser(String pattern){
        return markDao.findMarkByUser(pattern);
    }




    public void insertMark(Mark...marks){
        new InsertAsyncTask(markDao).execute(marks);
    }
    public void deleteMark(Mark...marks){
        new DeleteAsyncTask(markDao).execute(marks);
    }



    static class InsertAsyncTask extends AsyncTask<Mark,Void,Void> {
        private MarkDao markDao;

        public InsertAsyncTask(MarkDao markDao) {
            this.markDao = markDao;
        }

        @Override
        protected Void doInBackground(Mark... marks) {
            markDao.insertMark(marks);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Mark,Void,Void> {
        private MarkDao markDao;

        public DeleteAsyncTask(MarkDao markDao) {
            this.markDao = markDao;
        }

        @Override
        protected Void doInBackground(Mark... marks) {
            markDao.deleteMark(marks);
            return null;
        }
    }
}
