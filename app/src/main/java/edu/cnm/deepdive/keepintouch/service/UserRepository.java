package edu.cnm.deepdive.keepintouch.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.keepintouch.model.dao.UserDao;
import edu.cnm.deepdive.keepintouch.model.entity.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;

public class UserRepository {


  private final Context context;
  private final UserDao userDao;


  public UserRepository(Context context) {
    this.context = context;
    userDao = KitDatabase.getInstance().getUserDao();
  }

  public Completable save(User user) {
    return (user.getId() == 0)
        ? userDao.insert(user).
        doAfterSuccess(user::setId)
        .ignoreElement()
        : userDao.update(user)
            .ignoreElement();
  }

  public Completable delete(User user) {
    return (user.getId() == 0) ?
        Completable.complete()
        : userDao.delete(user)
            .ignoreElement();
  }


  LiveData<List<User>> getUserByUserType(long userTypeId) {
    return userDao.getUserByUserType(userTypeId);
  }


  LiveData<User> getUserById(long userId) {
    return userDao.getUserById(userId);
  }


  Single<User> getUserByOauthKey(String oauthKey) {
    return userDao.getUserByOauthKey(oauthKey);
  }


}
