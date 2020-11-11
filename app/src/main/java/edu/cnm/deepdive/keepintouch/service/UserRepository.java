package edu.cnm.deepdive.keepintouch.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.keepintouch.model.dao.UserDao;
import edu.cnm.deepdive.keepintouch.model.entity.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;

/**
 * Currently not planning to use a query to getAll Users in this Repository back yet, because KiT
 *  is currently limited to one user on the device, but I may implement the query in the near future.
 */
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


  public LiveData<List<User>> getUserByUserType(long userTypeId) {
    return userDao.getUserByUserType(userTypeId);
  }


  public LiveData<User> getUserById(long userId) {
    return userDao.getUserById(userId);
  }


  public Single<User> getUserByOauthKey(String oauthKey) {
    return userDao.getUserByOauthKey(oauthKey);
  }


}
