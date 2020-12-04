package edu.cnm.deepdive.keepintouch.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.keepintouch.model.dao.AutoReplyDao;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.model.pojo.AutoReplyWithUserType;
import io.reactivex.Completable;
import java.util.List;

/**
 * The autoReply Repository class that holds save, delete, and get AutoReplies by User type
 * methods.
 */
public class AutoReplyRepository {

  private final Context context;
  private final AutoReplyDao autoReplyDao;

  /**
   * A constructor for the AutoReply repository
   *
   * @param context , taking in a context parameter.
   */
  public AutoReplyRepository(Context context) {
    this.context = context;
    autoReplyDao = KitDatabase.getInstance().getAutoReplyDao();
  }

  /**
   * A method that saves the autoReplies so they cant be sent out by the different User types.
   * Insert them if they don't exist, else update them.
   *
   * @param autoReply , an AutoReply object.
   */
  public Completable save(AutoReply autoReply) {
    return (autoReply.getAutoReplyId() == 0)
        ? autoReplyDao.insert(autoReply).
        doAfterSuccess(autoReply::setAutoReplyId)
        .ignoreElement()
        : autoReplyDao.update(autoReply)
            .ignoreElement();
  }

  /**
   * Allows us to delete certain AutoReplies from the database
   *
   * @param autoReply , an AutoReply object.
   */
  public Completable delete(AutoReply autoReply) {
    return (autoReply.getAutoReplyId() == 0) ?
        Completable.complete()
        : autoReplyDao.delete(autoReply)
            .ignoreElement();
  }

  /**
   * A repository method that gets all the AutoReplies for a specific userType. (For example,
   * grandparent teen, millennial, parent.)
   *
   * @param userTypeId , a long  associated to one of the aforementioned user types.
   * @return a LiveData List of AutoReplies with UserType
   */
  public LiveData<List<AutoReplyWithUserType>> getAutoRepliesByUserType(long userTypeId) {
    return autoReplyDao.getAutoRepliesWithUserType(userTypeId);
  }

  /**
   * A repository method that is not currently being used. However, it gets all the AutoReply
   * options currently loaded into the database.
   *
   * @return a LiveData List of AutoReplies with UserType
   */
  public LiveData<List<AutoReplyWithUserType>> getAllAutoReplies() {
    return autoReplyDao.getAllAutoReplies();
  }

}

