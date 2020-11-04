package edu.cnm.deepdive.keepintouch.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface AutoReplyDao {

  @Insert
  Single<Long> insert (AutoReply autoReply);

  @Insert
  Single<List<Long>> insert (AutoReply... autoReplies);

  @Insert
  Single<List<Long>> insert (Collection<AutoReply> autoReplies);

  @Update
  Single<Integer> update (AutoReply autoReply);

  @Update
  Single<Integer> update (AutoReply... autoReplies);

  @Update
  Single<Integer> update (Collection<AutoReply> autoReplies);

  @Delete
  Single<Integer> delete (AutoReply autoReply);

  @Delete
  Single<Integer> delete (AutoReply... autoReplies);

  @Delete
  Single<Integer> delete (Collection<AutoReply> autoReplies);

  //TODO update your query
 // @Query("SELECT auto_reply_id, message WHERE userTypeId = :UserTypeId")
  //LiveData<List<AutoReply>> getAutoRepliesWithUserType(long UserTypeId);


}
