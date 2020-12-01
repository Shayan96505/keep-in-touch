package edu.cnm.deepdive.keepintouch.controller;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import edu.cnm.deepdive.keepintouch.R;
import edu.cnm.deepdive.keepintouch.service.SmsRepository;
import java.util.LinkedList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity {

  private static final int PERMISSIONS_REQUEST_CODE = 30;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_navigation);
    BottomNavigationView navView = findViewById(R.id.nav_view);
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
        .build();
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    NavigationUI.setupWithNavController(navView, navController);
    checkPermissions();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    if (requestCode == PERMISSIONS_REQUEST_CODE) {
      for (int i = 0; i < permissions.length; i++) {
        String permission = permissions[i];
        int result = grantResults[i];
        if (result == PackageManager.PERMISSION_GRANTED) {
          //TODO keep track whether this permission is granted
        } else {
          //TODO keep track if this permission is denied
        }
      }
    }
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  private void checkPermissions() {
    try {
      PackageInfo info = getPackageManager()
          .getPackageInfo(getPackageName(),
              PackageManager.GET_META_DATA | PackageManager.GET_PERMISSIONS);
      String[] permissions = info.requestedPermissions;
      List<String> permissionsToRequest = new LinkedList<>();
      List<String> permissionsToExplain = new LinkedList<>();
      for (String permission : permissions) {
        if (ContextCompat.checkSelfPermission(this, permission)
            != PackageManager.PERMISSION_GRANTED) {
          permissionsToRequest.add(permission);
          if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            permissionsToExplain.add(permission);
          }
        } else {
          //TODO keep track that we have this permission.
        }
      }
      if (!permissionsToExplain.isEmpty()) {
        //TODO explain permissions
      } else if (!permissionsToRequest.isEmpty()) {
        onAcknowledge(permissionsToRequest.toArray(new String[0]));
      }
    } catch (NameNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public void onAcknowledge(String[] permissionsToRequest) {
    ActivityCompat.requestPermissions(this, permissionsToRequest, PERMISSIONS_REQUEST_CODE);
  }
}