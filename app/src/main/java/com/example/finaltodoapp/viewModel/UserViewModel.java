package com.example.finaltodoapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.finaltodoapp.data.UserRepository;
import com.example.finaltodoapp.model.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    public UserRepository mUserRepository;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mUserRepository = new UserRepository(application);
        allUsers = mUserRepository.getAllUserList();
    }
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
    public void insert(User user)
    {
        mUserRepository.insert(user);
    }

}
