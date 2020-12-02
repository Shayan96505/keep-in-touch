package edu.cnm.deepdive.keepintouch.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.keepintouch.R;
import edu.cnm.deepdive.keepintouch.databinding.ActivityLoginBinding;
import edu.cnm.deepdive.keepintouch.service.GoogleSignInService;
import edu.cnm.deepdive.keepintouch.service.UserRepository;

/**
 * This is the activity in which the Google Sign In logging in takes place. It extends {@linkplain AppCompatActivity}.
 *  All together this class allows us to Login with Google Oauth 2.0.
 */
public class LoginActivity extends AppCompatActivity {

  private static final int LOGIN_REQUEST_CODE = 1000;
  private GoogleSignInService service;
  private ActivityLoginBinding binding;
  private UserRepository userRepository;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    userRepository = new UserRepository(this);
    service = GoogleSignInService.getInstance();
    service.refresh()
        .addOnSuccessListener(this::switchToMain)
        .addOnFailureListener((throwable) -> {
          binding = ActivityLoginBinding.inflate(getLayoutInflater());
          binding.signIn.setOnClickListener((v) -> service.startSignIn(this, LOGIN_REQUEST_CODE));
          setContentView(binding.getRoot());
        });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == LOGIN_REQUEST_CODE) {
      service.completeSignIn(data)
          .addOnSuccessListener(this::switchToMain)
          .addOnFailureListener((throwable) ->
              Toast.makeText(this, R.string.login_failure_message,
                  Toast.LENGTH_LONG).show());
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  private void switchToMain(GoogleSignInAccount account) {
    userRepository.getOrCreate(account.getId())
        .subscribe(
            (user) -> {
              Intent intent = new Intent(this, NavigationActivity.class)
                  .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
              startActivity(intent);
            },
            (throwable) ->
                Toast.makeText(this, "unable to create User Record", Toast.LENGTH_LONG).show()
        );


  }

}