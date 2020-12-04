package edu.cnm.deepdive.keepintouch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.keepintouch.model.entity.UserType;
import edu.cnm.deepdive.keepintouch.model.pojo.UserTypeWithAutoReply;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * An UserTypeDao which allows us to insert, delete, update and do specific queries on the
 * userType entities.
 */
@Dao
public interface UserTypeDao {

  @Insert
  Single<Long> insert(UserType userType);

  @Insert
  Single<List<Long>> insert(UserType... userTypes);

  @Insert
  Single<List<Long>> insert(Collection<UserType> userTypes);

  @Update
  Single<Integer> update(UserType userType);

  @Update
  Single<Integer> update(UserType... userTypes);

  @Update
  Single<Integer> update(Collection<UserType> userTypes);

  @Delete
  Single<Integer> delete(UserType userType);

  @Delete
  Single<Integer> delete(UserType... userTypes);

  @Delete
  Single<Integer> delete(Collection<UserType> userTypes);

  @Query("SELECT * FROM UserType WHERE `user_type_id` = :userTypeId")
  LiveData<UserType> getUserType(long userTypeId);

  @Query("SELECT * FROM UserType WHERE `name` = :name")
  LiveData<UserType> getUserTypeByName(String name);

  // added these transaction queries for diagnostic purposes with todd

  @Transaction
  @Query("SELECT * FROM UserType WHERE `user_type_id` = :userTypeId")
  Single<UserTypeWithAutoReply> selectById(long userTypeId);

  @Transaction
  @Query("SELECT * FROM UserType ORDER BY name")
  LiveData<List<UserTypeWithAutoReply>> selectAllWithAutoReplies();

}
