package edu.cnm.deepdive.keepintouch.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.keepintouch.model.dto.Message;
import edu.cnm.deepdive.keepintouch.model.entity.User;
import edu.cnm.deepdive.keepintouch.model.pojo.AutoReplyWithUserType;
import edu.cnm.deepdive.keepintouch.service.AutoReplyRepository;
import edu.cnm.deepdive.keepintouch.service.GoogleSignInService;
import edu.cnm.deepdive.keepintouch.service.IgnoreStatusRepository;
import edu.cnm.deepdive.keepintouch.service.SmsRepository;
import edu.cnm.deepdive.keepintouch.service.UserRepository;
import edu.cnm.deepdive.keepintouch.service.UserTypeRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

/**
 * This is the MainViewModel class which extends {@link AndroidViewModel} and implements {@link
 * LifecycleObserver}.
 */
public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final AutoReplyRepository autoReplyRepository;
  private final IgnoreStatusRepository ignoreStatusRepository;
  private final UserRepository userRepository;
  private final UserTypeRepository userTypeRepository;
  private final SmsRepository smsRepository;
  private final MutableLiveData<List<Message>> messages;
  private final MutableLiveData<User> user;
  private final LiveData<List<AutoReplyWithUserType>> autoReplies;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;


  /**
   * Constructor for the MainViewModel
   *
   * @param application takes in an application object
   */
  public MainViewModel(
      @NonNull Application application) {
    super(application);
    autoReplyRepository = new AutoReplyRepository(application);
    ignoreStatusRepository = new IgnoreStatusRepository(application);
    userRepository = new UserRepository(application);
    userTypeRepository = new UserTypeRepository(application);
    smsRepository = new SmsRepository(application);
    messages = new MutableLiveData<>();
    user = new MutableLiveData<>();
    autoReplies = Transformations
        .switchMap(user, (u) -> autoReplyRepository.getAutoRepliesByUserType(u.getUserTypeId()));
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    getCurrentUser();
    refreshMessages();
  }

  /**
   * A query to get access to all the AutoReplies of a certain userType.
   *
   * @return a LiveData List of AutoReplies.
   */
  public LiveData<List<AutoReplyWithUserType>> getAutoReplies() {
    return autoReplies;
  }

  /**
   * refreshes the incoming messages, or throws a throwable.
   */
  public void refreshMessages() {
    pending.add(
        smsRepository.getMessages()
            .subscribe(
                messages::postValue,
                throwable::postValue
            )
    );
  }

  /**
   * Gets a LiveData list of messages
   *
   * @return a LiveData list, of messages.
   */
  public LiveData<List<Message>> getMessages() {
    return messages;
  }

  /**
   * Gets a LiveData of type, Throwable for us.
   *
   * @return a LiveData, of Throwable
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  /**
   * This is a method  to send a text message out, it utilizes the same method in the
   * smsRepository.
   *
   * @param phoneNumber ,  a String that contains the phone number the text will be sent to.
   * @param text        , a String that containts the body of the text that is being sent.
   */
  public void sendMessage(String phoneNumber, String text) {
    pending.add(
        smsRepository.sendMessage(phoneNumber, text)
            .subscribe(
                () -> {
                },
                throwable::postValue
            )
    );
  }

  private void getCurrentUser() {
    throwable.setValue(null);
    String oauthKey = GoogleSignInService.getInstance().getAccount().getId();
    pending.add(
        userRepository.getOrCreate(oauthKey)
            .subscribe(
                user::postValue,
                throwable::postValue
            )
    );
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}
