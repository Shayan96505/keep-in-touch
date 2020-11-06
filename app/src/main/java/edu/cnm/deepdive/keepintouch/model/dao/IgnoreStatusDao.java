package edu.cnm.deepdive.keepintouch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.keepintouch.model.entity.IgnoreStatus;
import edu.cnm.deepdive.keepintouch.model.entity.User;
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
  @Query("SELECT * FROM 'ignorestatus' WHERE contact_Uri = :contactUri ORDER BY count ASC")
  LiveData<List<IgnoreStatus>> getMostIgnoredContactsInOrder (String contactUri);

  @Query("SELECT contact_uri FROM 'ignorestatus' WHERE :count >= 3")
  LiveData<List<IgnoreStatus>> getAllIgnoredContacts(int count);

  @Query("SELECT * FROM  ignorestatus WHERE `ignore_status_id` = :ignoreStatusId")
  LiveData<User> getIgnoreStatus(long ignoreStatusId);
}
