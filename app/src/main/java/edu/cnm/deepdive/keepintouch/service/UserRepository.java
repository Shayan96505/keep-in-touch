package edu.cnm.deepdive.keepintouch.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.keepintouch.model.dao.UserDao;
import edu.cnm.deepdive.keepintouch.model.entity.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Currently not planning to use a query to getAll Users in this Repository back yet, because KiT is
 * currently limited to one user on the device, but I may implement the query in the near future.
 */
public class UserRepository {


  private final Context context;
  private final UserDao userDao;


  /**
   * A constructor for the User Repository class.
   *
   * @param context , a context object necessary for the repository.
   */
  public UserRepository(Context context) {
    this.context = context;
    userDao = KitDatabase.getInstance().getUserDao();
  }

  /**
   * A completable method that either adds the User to the database, and
   *
   * @param user , a user object
   */
  public Completable save(User user) {
    return (user.getId() == 0)
        ? userDao.insert(user).
        doAfterSuccess(user::setId)
        .ignoreElement()
        : userDao.update(user)
            .ignoreElement();
  }

  /**
   * A completable method that deletes the user record from the database
   *
   * @param user , a user object
   */
  public Completable delete(User user) {
    return (user.getId() == 0) ?
        Completable.complete()
        : userDao.delete(user)
            .ignoreElement();
  }


  /**
   * Return a LiveData list of type User
   *
   * @param userTypeId takes a long associated with a UserType.
   * @return a LiveData of type User
   */
  public LiveData<List<User>> getUserByUserType(long userTypeId) {
    return userDao.getUserByUserType(userTypeId);
  }

  /**
   * A repository method that gets the User by User Id.
   *
   * @param userId takes a long parameter in as a userId.
   * @return a LiveData of type User
   */
  public LiveData<User> getUserById(long userId) {
    return userDao.getUserById(userId);
  }


  /**
   * A method that either gets a User or creates one.
   *
   * @param oauthKey is the oauthKey associated with the user's Google Oauth account that is signed
   *                 in
   * @return a Single of User type
   */
  public Single<User> getOrCreate(String oauthKey) {
    return userDao.getUserByOauthKey(oauthKey)
        .switchIfEmpty((SingleSource<User>) (observer) -> {
          User user = new User();
          user.setOauthKey(oauthKey);
          user.setUserTypeId(4);
          userDao.insert(user)
              .map((id) -> {
                user.setId(id);
                return user;
              })
              .subscribe(observer);
        })
        .subscribeOn(Schedulers.io());
  }


}
