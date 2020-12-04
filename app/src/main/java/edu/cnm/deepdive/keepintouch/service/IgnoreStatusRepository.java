package edu.cnm.deepdive.keepintouch.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.keepintouch.model.dao.IgnoreStatusDao;
import edu.cnm.deepdive.keepintouch.model.entity.IgnoreStatus;
import io.reactivex.Completable;
import java.util.List;

/**
 * This is the IgnoreStatus repository which checks to see how many times a User has been, prompted
 * to send a response back to someone and then subsequently how many times they've been ignored.
 */
public class IgnoreStatusRepository {

  private final Context context;
  private final IgnoreStatusDao ignoreStatusDao;

  /**
   * A constructor for this repository.
   *
   * @param context , takes in a context object
   */
  public IgnoreStatusRepository(Context context) {
    this.context = context;
    ignoreStatusDao = KitDatabase.getInstance().getIgnoreStatusDao();
  }

  /**
   * A save method to update the ignoreStatus on each contact. If there is none, it inserts a new
   * one. else, it just updates the ignore status on the other contact.
   *
   * @param ignoreStatus , takes in an IgnoreStatus object, which is tied to a contact.
   */
  public Completable save(IgnoreStatus ignoreStatus) {
    return (ignoreStatus.getIgnoreStatusId() == 0)
        ? ignoreStatusDao.insert(ignoreStatus).
        doAfterSuccess(ignoreStatus::setIgnoreStatusId)
        .ignoreElement()
        : ignoreStatusDao.update(ignoreStatus)
            .ignoreElement();
  }

  /**
   * This is a method that deletes the ignore status.
   *
   * @param ignoreStatus , takes in an IgnoreStatus object, which is tied to a contact.
   */
  public Completable delete(IgnoreStatus ignoreStatus) {
    return (ignoreStatus.getIgnoreStatusId() == 0) ?
        Completable.complete()
        : ignoreStatusDao.delete(ignoreStatus)
            .ignoreElement();
  }

  /**
   * A method to check for the ignore status of each contact.
   *
   * @param contactUri , a String that represents the contact in Android.
   * @return a list of livedata, type Ignore status
   */
  public LiveData<IgnoreStatus> getIgnoreStatusForContact(String contactUri) {
    return ignoreStatusDao.getIgnoreStatusForContact(contactUri);
  }

  /**
   * @param numContacts , an int that represents the top ignored contacts, for instance the top 5.
   *                    Helpful for users to consider deleting these contacts or re-evaluating.
   * @return a list of livedata, type Ignore status
   */
  public LiveData<List<IgnoreStatus>> getMostIgnoredContacts(int numContacts) {
    return ignoreStatusDao.getMostIgnoredContacts(numContacts);
  }

  /**
   * A method that checks to see all of your ignored contacts.
   *
   * @param ignoreLimit , how many times a user is ignored, before they get put on the ignore list.
   * @return a list of livedata, type Ignore status
   */
  public LiveData<List<IgnoreStatus>> getAllIgnoredContacts(int ignoreLimit) {
    return ignoreStatusDao.getAllIgnoredContacts(ignoreLimit);
  }

  /**
   * A method that gets the ignore status by Id.
   *
   * @param ignoreStatusId , a long that is ignorestatusId.
   * @return a live data, type Ignore status
   */
  public LiveData<IgnoreStatus> getIgnoreStatus(long ignoreStatusId) {
    return ignoreStatusDao.getIgnoreStatus(ignoreStatusId);
  }


}
