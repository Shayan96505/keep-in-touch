package edu.cnm.deepdive.keepintouch;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.keepintouch.service.GoogleSignInService;
import edu.cnm.deepdive.keepintouch.service.KitDatabase;
import io.reactivex.schedulers.Schedulers;

/**
 * This is the main application class for Kit. We kick-start the database here.
 */
public class KeepInTouchApplication extends Application {

// Leave this all the way through the 3rd personal android application milestone
  @Override
  public void onCreate() {
    super.onCreate();
    //TODO remove stetho, before publishing the app
    Stetho.initializeWithDefaults(this);
    GoogleSignInService.setContext(this);
    KitDatabase.setContext(this);

    //Use this to kickstart the database
    KitDatabase.getInstance().getAutoReplyDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }

}
