package com.example.traveldemo.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.traveldemo.Dao.UserDao;
import com.example.traveldemo.Database.UserDatabase;
import com.example.traveldemo.Entity.User;

import java.util.List;

public class UserRepository {
    private LiveData<List<User>>allUserLive;
    private UserDao userDao;

    public UserRepository(Context context) {
        UserDatabase userDatabase = UserDatabase.getUserDatabase(context.getApplicationContext());
        userDao = userDatabase.getUserDao();
        allUserLive = userDao.getAllUserLive();
    }

    public LiveData<List<User>> getAllUserLive() {
        return allUserLive;
    }
    public LiveData<List<User>>findUserByLoginname(String pattern){
        return userDao.findUserByLoginname(pattern);
    }



    public void insertUser(User... users){
        new InsertAsyncTask(userDao).execute(users);
    }
    public void updateUser(User... users){
        new UpdateAsyncTask(userDao).execute(users);
    }
    public void updatebylogin(User...users){
        new UpdateByloginAsyncTask(userDao).execute(users);
    }
//    List<User> findUser(String pattern){
//        return (List<User>) new FindUserAsyncTask(userDao).execute(pattern);
//    }

    static class InsertAsyncTask extends AsyncTask<User,Void,Void> {
        private UserDao userDao;

        public InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        public UpdateAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users);
            return null;
        }
    }

    static class UpdateByloginAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        public UpdateByloginAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUserBy(users[0].getUser_name(),users[0].getGender(),users[0].getSign(),users[0].getImage(),users[0].getLogin_name());
            return null;
        }
    }

//    static class FindUserAsyncTask extends AsyncTask<String,Void,Void>{
//        private UserDao userDao;
//
//        public FindUserAsyncTask(UserDao userDao) {
//            this.userDao = userDao;
//        }
//
//        @Override
//        protected Void doInBackground(String... strings) {
//            String pattern = strings[0];
//            userDao.findUser(pattern);
//            return null;
//        }
//    }




}
