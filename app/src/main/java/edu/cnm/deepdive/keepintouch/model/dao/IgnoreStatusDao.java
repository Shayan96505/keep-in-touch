package edu.cnm.deepdive.keepintouch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.keepintouch.model.entity.IgnoreStatus;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * An IgnoreStatusDao which allows us to insert, delete, update and do specific queries on the
 * IgnoreStatus entities.
 */
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

  @Query("SELECT * FROM 'IgnoreStatus' WHERE contact_Uri = :contactUri")
  LiveData<IgnoreStatus> getIgnoreStatusForContact (String contactUri);

  //selecting the most ignored contacts
  @Query("SELECT * FROM 'IgnoreStatus' ORDER BY count DESC LIMIT :numContacts")
  LiveData<List<IgnoreStatus>> getMostIgnoredContacts (int numContacts);

  @Query("SELECT * FROM 'IgnoreStatus' WHERE count >= :ignoreLimit")
  LiveData<List<IgnoreStatus>> getAllIgnoredContacts(int ignoreLimit);

  @Query("SELECT * FROM `IgnoreStatus` WHERE `ignore_status_id` = :ignoreStatusId")
  LiveData<IgnoreStatus> getIgnoreStatus(long ignoreStatusId);
}
