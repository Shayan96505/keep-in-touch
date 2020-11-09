package edu.cnm.deepdive.keepintouch.service;

import android.content.Context;
import edu.cnm.deepdive.keepintouch.model.dao.IgnoreStatusDao;
import edu.cnm.deepdive.keepintouch.model.entity.IgnoreStatus;
import io.reactivex.Completable;

public class IgnoreStatusRepository {

  private final Context context;
  private final IgnoreStatusDao ignoreStatusDao;

  public IgnoreStatusRepository(Context context, IgnoreStatusDao ignoreStatusDao) {
    this.context = context;
    this.ignoreStatusDao = ignoreStatusDao;
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

  public Completable delete(IgnoreStatus ignoreStatus){
    return (ignoreStatus.getIgnoreStatusId() == 0)?
        Completable.complete()
        : ignoreStatusDao.delete(ignoreStatus)
            .ignoreElement();
  }


 // LiveData<IgnoreStatus> getIgnoreStatusForContact (String contactUri){
 //   return IgnoreStatusDao.getIgnoreStatusForContact(contactUri);
 // }


  //LiveData<List<IgnoreStatus>> getMostIgnoredContacts (int numContacts){
  //  return IgnoreStatusDao.getMostIgnoredContacts(numContacts);
  //}


  //LiveData<List<IgnoreStatus>> getAllIgnoredContacts(int ignoreLimit){
  //  return IgnoreStatusDao.getAllIgnoredContacts( ignoreLimit);
  //}


  //LiveData<IgnoreStatus> getIgnoreStatus(long ignoreStatusId){
  //  return IgnoreStatus.getIgnoreStatus(ignoreStatusId);
  //}


}
