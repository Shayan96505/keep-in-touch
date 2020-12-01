package edu.cnm.deepdive.keepintouch.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.keepintouch.model.dto.Message;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.model.pojo.AutoReplyWithUserType;
import edu.cnm.deepdive.keepintouch.service.AutoReplyRepository;
import edu.cnm.deepdive.keepintouch.service.IgnoreStatusRepository;
import edu.cnm.deepdive.keepintouch.service.SmsRepository;
import edu.cnm.deepdive.keepintouch.service.UserRepository;
import edu.cnm.deepdive.keepintouch.service.UserTypeRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final AutoReplyRepository autoReplyRepository;
  private final IgnoreStatusRepository ignoreStatusRepository;
  private final UserRepository userRepository;
  private final UserTypeRepository userTypeRepository;
  private final SmsRepository smsRepository;
  private final MutableLiveData<List<Message>> messages;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public MainViewModel(
      @NonNull Application application) {
    super(application);
    autoReplyRepository = new AutoReplyRepository(application);
    ignoreStatusRepository = new IgnoreStatusRepository(application);
    userRepository = new UserRepository(application);
    userTypeRepository = new UserTypeRepository(application);
    smsRepository = new SmsRepository(application);
    messages = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    refreshMessages();
  }

  public LiveData<List<AutoReplyWithUserType>> getAutoReplies() {
    return autoReplyRepository.getAllAutoReplies();
  }

  public void refreshMessages() {
    pending.add(
        smsRepository.getMessages()
            .subscribe(
                messages::postValue,
                throwable::postValue
            )
    );
  }

  public LiveData<List<Message>> getMessages() {
    return messages;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }
}
