package edu.cnm.deepdive.keepintouch.service;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

/**
 * A service class that allows users to sign in through Google.
 */
public class GoogleSignInService {

  private static Application context;

  private final GoogleSignInClient client;

  private GoogleSignInAccount account;

  //this constructor is used to help us get the info of the user from google
  private GoogleSignInService() {
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail()
        .requestId()
        .requestProfile()
        //.requestIdToken(BuildConfig.CLIENT_ID)
        .build();
// this is a bearer token, the back end takes this bearer token and passes it to google, and asks
    // google if the bearer token matches what it requests.

    client = GoogleSignIn.getClient(context, options);
  }

  /**
   * A setter for the context
   *
   * @param context an application object that gives the service a context.
   */
  public static void setContext(Application context) {
    GoogleSignInService.context = context;
  }

  /**
   * A method that gets the instance of the Google Sign In sevice
   *
   * @return
   */
  public static GoogleSignInService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Gets back the associated google account.
   *
   * @return , a google sign in account
   */
  public GoogleSignInAccount getAccount() {
    return account;
  }

  //return a task to silently sign the user in if their token is expired!

  /**
   * A google task that silently signs the user in if their token is expired!
   */
  public Task<GoogleSignInAccount> refresh() {
    return client.silentSignIn()
        .addOnSuccessListener(this::setAccount);
  }

  /**
   * A method that starts the Sign in activity.
   *
   * @param activity    an activity object.
   * @param requestCode an int, the request code to start the signIn process.
   */
  public void startSignIn(Activity activity, int requestCode) {
    account = null;
    Intent intent = client.getSignInIntent();
    activity.startActivityForResult(intent, requestCode);
  }

  // we created this method to check for a completed sign-in.

  /**
   * this method checks for a completed sign-in.
   *
   * @param data , another intent object.
   */
  public Task<GoogleSignInAccount> completeSignIn(Intent data) {
    Task<GoogleSignInAccount> task = null;
    try {
      task = GoogleSignIn.getSignedInAccountFromIntent(data);
      setAccount(task.getResult(ApiException.class));
    } catch (ApiException e) {
      //exception will be pass automatically to onFailureListener
    }
    return task;
  }

  //regardless of whether the account signs out or not.... sign us out.

  /**
   * This task is a google task that signs out the user.
   */
  public Task<Void> signOut() {
    return client.signOut()
        .addOnCompleteListener((ignored) -> setAccount(null));
  }

  /**
   * This task sets the account
   *
   * @param account , a Google Sign in account used for Oauth2.0 authentication.
   */
  private void setAccount(GoogleSignInAccount account) {
    this.account = account;
    if (account != null) {
      //noinspection ConstantConditions
      //Log.d(getClass().getSimpleName(), account.getIdToken());
    }
  }

  private static class InstanceHolder {

    private static final GoogleSignInService INSTANCE = new GoogleSignInService();

  }

}
