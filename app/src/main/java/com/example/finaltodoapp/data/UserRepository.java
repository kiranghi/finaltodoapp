package com.example.finaltodoapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.finaltodoapp.model.TodoRoomDatabase;
import com.example.finaltodoapp.model.User;

import java.util.List;

public class UserRepository {
    private UserDAO mUserDAO;
    private LiveData<List<User>> allUserList;

    public UserRepository(Application application){
        TodoRoomDatabase database = TodoRoomDatabase.getDatabase(application);
        mUserDAO = database.mUserDao();
        allUserList = mUserDAO.getAllUsers();
    }

    public UserDAO getmUserDAO() {
        return mUserDAO;
    }

    public void setmUserDAO(UserDAO mUserDAO) {
        this.mUserDAO = mUserDAO;
    }

    public LiveData<List<User>> getAllUserList() {
        return allUserList;
    }

    public void setAllUserList(LiveData<List<User>> allUserList) {
        this.allUserList = allUserList;
    }

  public void insert(User user){
        new insertUserAsyncTask(mUserDAO).execute(user);
  }

    private static class insertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO mUserDao;
        private insertUserAsyncTask(UserDAO userDAO){
            mUserDao=userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.insert(users[0]);
            return null;
        }
    }


}
