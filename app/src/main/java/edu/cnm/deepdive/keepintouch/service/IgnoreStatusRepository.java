package edu.cnm.deepdive.keepintouch.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.keepintouch.model.dao.IgnoreStatusDao;
import edu.cnm.deepdive.keepintouch.model.entity.IgnoreStatus;
import io.reactivex.Completable;
import java.util.List;

public class IgnoreStatusRepository {

  private final Context context;
  private final IgnoreStatusDao ignoreStatusDao;

  public IgnoreStatusRepository(Context context) {
    this.context = context;
    ignoreStatusDao = KitDatabase.getInstance().getIgnoreStatusDao();
  }


  public Completable save(IgnoreStatus ignoreStatus) {
    return (ignoreStatus.getIgnoreStatusId() == 0)
        ? ignoreStatusDao.insert(ignoreStatus).
        doAfterSuccess(ignoreStatus::setIgnoreStatusId)
        .ignoreElement()
        : ignoreStatusDao.update(ignoreStatus)
            .ignoreElement();
  }

  public Completable delete(IgnoreStatus ignoreStatus) {
    return (ignoreStatus.getIgnoreStatusId() == 0) ?
        Completable.complete()
        : ignoreStatusDao.delete(ignoreStatus)
            .ignoreElement();
  }


  public LiveData<IgnoreStatus> getIgnoreStatusForContact(String contactUri) {
    return ignoreStatusDao.getIgnoreStatusForContact(contactUri);
  }


  public LiveData<List<IgnoreStatus>> getMostIgnoredContacts(int numContacts) {
    return ignoreStatusDao.getMostIgnoredContacts(numContacts);
  }


  public LiveData<List<IgnoreStatus>> getAllIgnoredContacts(int ignoreLimit) {
    return ignoreStatusDao.getAllIgnoredContacts(ignoreLimit);
  }


  public LiveData<IgnoreStatus> getIgnoreStatus(long ignoreStatusId) {
    return ignoreStatusDao.getIgnoreStatus(ignoreStatusId);
  }


}
