package com.example.traveldemo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.traveldemo.Entity.Mark;
import com.example.traveldemo.Repository.MarkRepository;

import java.util.List;

public class MarkViewModel extends AndroidViewModel {
    private MarkRepository markRepository;

    public MarkViewModel(@NonNull Application application) {
        super(application);
        markRepository = new MarkRepository(application);
    }

    public LiveData<List<Mark>> findMarkByPlan(int pattern){
        return markRepository.findMarkByPlan(pattern);
    }
    public LiveData<List<Mark>>findMarkByUser(String pattern){
        return markRepository.findMarkByUser(pattern);
    }






    public void insertMark(Mark...marks){
        markRepository.insertMark(marks);
    }
    public void deleteMark(Mark...marks){
        markRepository.deleteMark(marks);
    }

}
