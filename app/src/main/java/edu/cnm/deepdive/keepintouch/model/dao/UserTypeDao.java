package edu.cnm.deepdive.keepintouch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import edu.cnm.deepdive.keepintouch.model.entity.IgnoreStatus;
import edu.cnm.deepdive.keepintouch.model.entity.UserType;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface UserTypeDao {

  @Insert
  Single<Long> insert (UserType userType);

  @Insert
  Single<List<Long>> insert (UserType... userTypes);

  @Insert
  Single<List<Long>> insert (Collection<UserType> userTypes);

  @Update
  Single<Integer> update (UserType userType);

  @Update
  Single<Integer> update (UserType... userTypes);

  @Update
  Single<Integer> update (Collection<UserType> userTypes);

  @Delete
  Single<Integer> delete (UserType userType);

  @Delete
  Single<Integer> delete (UserType... userTypes);

  @Delete
  Single<Integer> delete (Collection<UserType> userTypes);

  //TODO Make another query
  //@Query("SELECT auto_reply_id, message WHERE userTypeId = :UserTypeId")
  //LiveData<List<IgnoreStatus>> IgnoreStatus(long IgnoreStatus);


}
