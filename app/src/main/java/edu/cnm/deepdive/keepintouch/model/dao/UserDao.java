package edu.cnm.deepdive.keepintouch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.keepintouch.model.entity.User;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface UserDao {

  @Insert
  Single<Long> insert(User user);

  @Insert
  Single<List<Long>> insert(User... users);

  @Insert
  Single<List<Long>> insert(Collection<User> users);

  @Update
  Single<Integer> update(User user);

  @Update
  Single<Integer> update(User... users);

  @Update
  Single<Integer> update(Collection<User> users);

  @Delete
  Single<Integer> delete(User user);

  @Delete
  Single<Integer> delete(User... users);

  @Delete
  Single<Integer> delete(Collection<User> users);

  //TODO
  // check to make sure this query is necessary, it's likely not
  @Query("SELECT * FROM  User WHERE `user_type_id` = :userTypeId")
  LiveData<List<User>> getUserByUserType(long userTypeId);

  @Query("SELECT * FROM  User WHERE `user_id` = :userId")
  LiveData<User> getUserById(long userId);

  @Query("SELECT * FROM  User WHERE `oauth_key` = :oauthKey")
  Maybe<User> getUserByOauthKey(String oauthKey);

  //not needed right? because currently only one user is signed in
//  @Query("SELECT * FROM User")
//  LiveData<List<User>> getAllUsers();


}
