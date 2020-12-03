package edu.cnm.deepdive.keepintouch.controller.ui.toSms;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel for the fragment that will lead to (currently doesn't lead to) the external SMS messaging app.
 */
public class ToSmsViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  /**
   * A constructor for the viewModel class.
   */
  public ToSmsViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("Would you like to follow up your KiT from the Native Android Messaging App?");
  }

  /**
   * return a Live Data of type String.
   *
   */
  public LiveData<String> getText() {
    return mText;
  }
}