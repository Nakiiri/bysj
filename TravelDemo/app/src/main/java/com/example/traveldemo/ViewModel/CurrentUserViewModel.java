package com.example.traveldemo.ViewModel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;

import com.example.traveldemo.R;

public class CurrentUserViewModel extends AndroidViewModel {
    SavedStateHandle handle;
    String key = getApplication().getResources().getString(R.string.current_key);
    String shp_current_name = getApplication().getResources().getString(R.string.shp_current_name);

    public CurrentUserViewModel(@NonNull Application application ,SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if(!handle.contains(key)){
            load();
        }

    }

//    public LiveData<String> getCurrentUser(){
//        return handle.getLiveData(key);
//    }

    public void load(){
        SharedPreferences shp = getApplication().getSharedPreferences(shp_current_name, Context.MODE_PRIVATE);
        String currentname = shp.getString(key,"0");
        handle.set(key,currentname);
    }

    public String loaduser(){
        SharedPreferences shp = getApplication().getSharedPreferences(shp_current_name, Context.MODE_PRIVATE);
        String currentname = shp.getString(key,"0");
        handle.set(key,currentname);
        return currentname;
    }

    public void save(String name){
        SharedPreferences shp = getApplication().getSharedPreferences(shp_current_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putString(key,name);
        editor.apply();

    }
}
