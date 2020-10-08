package edu.cnm.deepdive.keepintouch;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class KeepInTouchApplication extends Application {

// Leave this all the way through the 3rd personal android application milestone
  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
