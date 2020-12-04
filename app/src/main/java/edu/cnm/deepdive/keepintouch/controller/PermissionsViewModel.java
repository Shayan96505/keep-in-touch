package edu.cnm.deepdive.keepintouch.controller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.HashSet;
import java.util.Set;

/**
 * A view model for the Permissions Dialogue fragment
 */
public class PermissionsViewModel extends ViewModel {
  private final MutableLiveData<Set<String>> permissions = new MutableLiveData<>(new HashSet<>());

  /**
   *
   * @return a liveData set of strings of the permissions you'll be getting.
   */
  public LiveData<Set<String>> getPermissions() {
    return permissions;
  }

  /**
   * Grant permissions to the user with this method
   * @param permission , a string of permissions granted
   */
  public void grantPermission(String permission) {
    Set<String> permissions = this.permissions.getValue();
    //noinspection ConstantConditions
    if (permissions.add(permission)) {
      this.permissions.setValue(permissions);
    }
  }

  /**
   * Revoke currently granted permissions with this method
   * @param permission, a string of permissions to be revoked.
   */
  public void revokePermission(String permission) {
    Set<String> permissions = this.permissions.getValue();
    //noinspection ConstantConditions
    if (permissions.remove(permission)) {
      this.permissions.setValue(permissions);
    }
  }
}