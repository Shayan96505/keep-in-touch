package edu.cnm.deepdive.keepintouch.controller.ui.messages;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * BUilt in implementation of a viewModel for the messages fragment. (currently not in use).
 */
public class MessagesViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public MessagesViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is dashboard fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}