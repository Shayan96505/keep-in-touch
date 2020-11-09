package edu.cnm.deepdive.keepintouch.service;

import android.content.Context;
import edu.cnm.deepdive.keepintouch.model.dao.UserTypeDao;
import edu.cnm.deepdive.keepintouch.model.entity.UserType;
import io.reactivex.Completable;

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


  //TODO Check out why these queries are giving you errors

  //LiveData<UserType> getUserType(long userTypeId){
    //return UserTypeDao.getUserType(userTypeId);
  //}

  //LiveData<UserType> getUserTypeByName(String name){
    //return UserTypeDao.getUserTypeByName(name);
  //}

}
