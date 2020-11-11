package edu.cnm.deepdive.keepintouch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.model.pojo.AutoReplyWithUserType;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface AutoReplyDao {

  @Insert
  Single<Long> insert(AutoReply autoReply);

  @Insert
  Single<List<Long>> insert(AutoReply... autoReplies);

  @Insert
  Single<List<Long>> insert(Collection<AutoReply> autoReplies);

  @Update
  Single<Integer> update(AutoReply autoReply);

  @Update
  Single<Integer> update(AutoReply... autoReplies);

  @Update
  Single<Integer> update(Collection<AutoReply> autoReplies);

  @Delete
  Single<Integer> delete(AutoReply autoReply);

  @Delete
  Single<Integer> delete(AutoReply... autoReplies);

  @Delete
  Single<Integer> delete(Collection<AutoReply> autoReplies);


  @Transaction
  @Query("SELECT * FROM AutoReply WHERE `user_type_id` = :userTypeId ORDER BY message ASC")
  LiveData<List<AutoReplyWithUserType>> getAutoRepliesWithUserType(long userTypeId);

  @Transaction
  @Query("SELECT ar.* FROM AutoReply AS ar INNER JOIN UserType AS ut ON ut.user_type_id = ar.user_type_id ORDER BY ut.name ASC, message ASC")
  LiveData<List<AutoReplyWithUserType>> getAllAutoReplies();


}
