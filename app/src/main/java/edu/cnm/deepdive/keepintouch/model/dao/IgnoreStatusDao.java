package edu.cnm.deepdive.keepintouch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.model.entity.IgnoreStatus;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface IgnoreStatusDao {

  @Insert
  Single<Long> insert (IgnoreStatus ignoreStatus);

  @Insert
  Single<List<Long>> insert (IgnoreStatus... ignoreStatuses);

  @Insert
  Single<List<Long>> insert (Collection<IgnoreStatus> ignoreStatuses);

  @Update
  Single<Integer> update (IgnoreStatus ignoreStatus);

  @Update
  Single<Integer> update (IgnoreStatus... ignoreStatuses);

  @Update
  Single<Integer> update (Collection<IgnoreStatus> ignoreStatuses);

  @Delete
  Single<Integer> delete (IgnoreStatus ignoreStatus);

  @Delete
  Single<Integer> delete (IgnoreStatus... ignoreStatuses);

  @Delete
  Single<Integer> delete (Collection<IgnoreStatus> ignoreStatuses);

  //TODO Make another query
  //@Query("SELECT auto_reply_id, message WHERE userTypeId = :UserTypeId")
  //LiveData<List<IgnoreStatus>> IgnoreStatus(long IgnoreStatus);


}
