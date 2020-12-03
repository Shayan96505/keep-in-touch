package edu.cnm.deepdive.keepintouch.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.keepintouch.model.dao.AutoReplyDao;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.model.pojo.AutoReplyWithUserType;
import io.reactivex.Completable;
import java.util.List;

public class AutoReplyRepository {

  private final Context context;
  private final AutoReplyDao autoReplyDao;

  public AutoReplyRepository(Context context) {
    this.context = context;
    autoReplyDao = KitDatabase.getInstance().getAutoReplyDao();
  }

  public Completable save(AutoReply autoReply) {
    return (autoReply.getAutoReplyId() == 0)
        ? autoReplyDao.insert(autoReply).
        doAfterSuccess(autoReply::setAutoReplyId)
        .ignoreElement()
        : autoReplyDao.update(autoReply)
            .ignoreElement();
  }

  public Completable delete(AutoReply autoReply) {
    return (autoReply.getAutoReplyId() == 0) ?
        Completable.complete()
        : autoReplyDao.delete(autoReply)
            .ignoreElement();
  }

  public LiveData<List<AutoReplyWithUserType>> getAutoRepliesByUserType(long userTypeId) {
    return autoReplyDao.getAutoRepliesWithUserType(userTypeId);
  }

  public LiveData<List<AutoReplyWithUserType>> getAllAutoReplies() {
    return autoReplyDao.getAllAutoReplies();
  }

}

