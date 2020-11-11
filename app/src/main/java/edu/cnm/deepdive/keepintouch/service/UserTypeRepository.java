package edu.cnm.deepdive.keepintouch.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.keepintouch.model.dao.UserTypeDao;
import edu.cnm.deepdive.keepintouch.model.entity.UserType;
import io.reactivex.Completable;

/**
 * I'm not planning to use a query to getAll the UserTypes in this Repository back yet, because it's
 * currently useless, but I may implement it in the future.
 */
public class UserTypeRepository {

  private final Context context;
  private final UserTypeDao userTypeDao;


  public UserTypeRepository(Context context) {
    this.context = context;
    userTypeDao = KitDatabase.getInstance().getUserTypeDao();
  }

  public Completable save(UserType userType) {
    return (userType.getUserTypeId() == 0)
        ? userTypeDao.insert(userType).
        doAfterSuccess(userType::setUserTypeId)
        .ignoreElement()
        : userTypeDao.update(userType)
            .ignoreElement();
  }

  public Completable delete(UserType userType) {
    return (userType.getUserTypeId() == 0) ?
        Completable.complete()
        : userTypeDao.delete(userType)
            .ignoreElement();
  }

//currently these are the only functional queries, no need to do queries for a list of more usertype
  // may consider shifting this whole thing to an ENUM implementation after 2nd milestone....

  public LiveData<UserType> getUserType(long userTypeId) {
    return userTypeDao.getUserType(userTypeId);
  }

  public LiveData<UserType> getUserTypeByName(String name) {
    return userTypeDao.getUserTypeByName(name);
  }

}
