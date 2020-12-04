package edu.cnm.deepdive.keepintouch.controller.ui.home;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.keepintouch.model.entity.User;
import edu.cnm.deepdive.keepintouch.model.pojo.UserTypeWithAutoReply;
import edu.cnm.deepdive.keepintouch.service.GoogleSignInService;
import edu.cnm.deepdive.keepintouch.service.UserRepository;
import edu.cnm.deepdive.keepintouch.service.UserTypeRepository;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * This holds the ViewModel logic for the fragment shown to the User. It allows users to select
 * their user type. It implements an onItemSelectedListener to update when the spinner has changed
 * and to change the user's account in the database.
 */
public class HomeViewModel extends AndroidViewModel implements LifecycleObserver {

  private final UserRepository userRepository;
  private final UserTypeRepository userTypeRepository;
  private final MutableLiveData<User> user;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final GoogleSignInService signInService;


  /**
   * A constructor for this viewModel.
   *
   * @param application , takes an Application object in as a parameter
   */
  public HomeViewModel(@NonNull Application application) {
    super(application);
    userRepository = new UserRepository(application);
    userTypeRepository = new UserTypeRepository(application);
    user = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    signInService = GoogleSignInService.getInstance();
    loadCurrentUser();
  }

  /**
   * @return A method that returns a LiveData List of all the UserTypes
   */
  public LiveData<List<UserTypeWithAutoReply>> getUserTypes() {
    return userTypeRepository.getAll();
  }

  /**
   * @return A getter that returns a LiveData of type User.
   */
  public LiveData<User> getUser() {
    return user;
  }

  /**
   * @return A getter that returns a LiveData of type Throwable.
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  //made this run on the background thread with reactiveX, so that the main UI

  /**
   * A method that saves the User or throws a throwable.
   *
   * @param user , a User object.
   */
  public void save(User user) {
    pending.add(
        userRepository.save(user).subscribeOn(Schedulers.io())
            .subscribe(
                () -> {
                },
                throwable::postValue
            )
    );
  }

  private void loadCurrentUser() {
    pending.add(
        userRepository.getOrCreate(signInService.getAccount().getId())
            .subscribe(
                user::postValue,
                throwable::postValue
            )
    );
  }

}