package edu.cnm.deepdive.keepintouch.controller.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.keepintouch.model.dto.Contact;
import edu.cnm.deepdive.keepintouch.model.entity.User;
import edu.cnm.deepdive.keepintouch.service.GoogleSignInService;
import edu.cnm.deepdive.keepintouch.service.UserRepository;

public class HomeViewModel extends ViewModel {

  private UserRepository userRepository;
  private MutableLiveData<String> mText;

  public HomeViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("Welcome to KiT \n Please select your user type.");
    //mText.setValue(userRepository.getUserById(GoogleSignInService.getInstance(id)));
  }

  public LiveData<String> getText() {
    return mText;
  }
}