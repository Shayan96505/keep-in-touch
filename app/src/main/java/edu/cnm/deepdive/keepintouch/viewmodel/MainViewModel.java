package edu.cnm.deepdive.keepintouch.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.service.AutoReplyRepository;
import edu.cnm.deepdive.keepintouch.service.IgnoreStatusRepository;
import edu.cnm.deepdive.keepintouch.service.UserRepository;
import edu.cnm.deepdive.keepintouch.service.UserTypeRepository;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final AutoReplyRepository autoReplyRepository;
  private final IgnoreStatusRepository ignoreStatusRepository;
  private final UserRepository userRepository;
  private final UserTypeRepository userTypeRepository;
  private final MutableLiveData<AutoReply> autoReplies;

  public MainViewModel(
      @NonNull Application application) {
    super(application);
    autoReplyRepository = new AutoReplyRepository(application);
    ignoreStatusRepository = new IgnoreStatusRepository(application);
    userRepository = new UserRepository(application);
    userTypeRepository = new UserTypeRepository(application);
    autoReplies = new MutableLiveData<>();
    loadMessages();
  }

  public LiveData<AutoReply> getAutoReplies() {
    return autoReplies;
  }

  public void loadMessages(){
    autoReplyRepository.getAllAutoReplies();
  }
}
