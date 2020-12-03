package edu.cnm.deepdive.keepintouch.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.keepintouch.model.dao.UserTypeDao;
import edu.cnm.deepdive.keepintouch.model.entity.UserType;
import edu.cnm.deepdive.keepintouch.model.pojo.UserTypeWithAutoReply;
import io.reactivex.Completable;
import java.util.List;

/**
 * A UserType repository with a save method, delete method, and queries
 */
public class UserTypeRepository {

  private final Context context;
  private final UserTypeDao userTypeDao;


  /**
   * Constructor for the userType Repository.
   *
   * @param context , takes in a Context object
   */
  public UserTypeRepository(Context context) {
    this.context = context;
    userTypeDao = KitDatabase.getInstance().getUserTypeDao();
  }

  /**
   * Either insert a userType into the DB, or update the userType
   *
   * @param userType , an object of type UserType
   * @return a completable
   */
  public Completable save(UserType userType) {
    return (userType.getUserTypeId() == 0)
        ? userTypeDao.insert(userType).
        doAfterSuccess(userType::setUserTypeId)
        .ignoreElement()
        : userTypeDao.update(userType)
            .ignoreElement();
  }

  /**
   * Allows us to delete the userType.
   *
   * @param userType , an object of type UserType
   * @return a completable
   */
  public Completable delete(UserType userType) {
    return (userType.getUserTypeId() == 0) ?
        Completable.complete()
        : userTypeDao.delete(userType)
            .ignoreElement();
  }


  /**
   * A method to get the UserTypeId.
   *
   * @param userTypeId , the long id associated with a userType
   * @return a LiveData of UserType.
   */
  public LiveData<UserType> getUserType(long userTypeId) {
    return userTypeDao.getUserType(userTypeId);
  }

  /**
   * A method to get the userType by name.
   *
   * @param name , the name of the UserType. Ie, grandparent, millennial, parent, grandparent.
   * @return a LiveData of UserType.
   */
  public LiveData<UserType> getUserTypeByName(String name) {
    return userTypeDao.getUserTypeByName(name);
  }

  /**
   * A method to get all the autoReplies of a certain userType.
   *
   * @return a LiveData of UserType.
   */
  public LiveData<List<UserTypeWithAutoReply>> getAll() {
    return userTypeDao.selectAllWithAutoReplies();
  }

}
