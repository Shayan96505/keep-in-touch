package edu.cnm.deepdive.keepintouch.controller.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.keepintouch.databinding.FragmentHomeBinding;
import edu.cnm.deepdive.keepintouch.model.entity.User;
import edu.cnm.deepdive.keepintouch.model.entity.UserType;

/**
 * The introductory fragment where the User selects their user type. It implements an
 * onItemSelectedListener to update when the spinner has changed and to change the user's account in
 * the database.
 */
public class HomeFragment extends Fragment implements OnItemSelectedListener {

  private HomeViewModel homeViewModel;
  private FragmentHomeBinding binding;
  private User user;

  /**
   * An onCreateView that inflates the FragmentHomeBinding
   *
   * @param inflater           , a {@link LayoutInflater} object that inflates the layout
   * @param container          , a {@link ViewGroup} object that contains the object in the view
   * @param savedInstanceState , a {@link Bundle} object
   * @return a View object to display on the user's screen
   */
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    binding.selectUserType.setOnItemSelectedListener(this);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    homeViewModel.getUserTypes().observe(getViewLifecycleOwner(), (userTypes) -> {
      ArrayAdapter<? extends UserType> adapter =
          new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, userTypes);
      binding.selectUserType.setAdapter(adapter);
      setUserType();
    });
    homeViewModel.getUser().observe(getViewLifecycleOwner(), (user) -> {
      this.user = user;
      setUserType();
    });
    homeViewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) ->
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show());
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    UserType userType = (UserType) parent.getItemAtPosition(position);
    if (user != null && user.getUserTypeId() != userType.getUserTypeId()) {
      user.setUserTypeId(userType.getUserTypeId());
      homeViewModel.save(user);
    }
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }

  private void setUserType() {
    if (user != null && binding.selectUserType.getCount() > 0) {
      for (int i = 0; i < binding.selectUserType.getCount(); i++) {
        UserType userType = (UserType) binding.selectUserType.getItemAtPosition(i);
        if (userType.getUserTypeId() == user.getUserTypeId()) {
          binding.selectUserType.setSelection(i);
          break;
        }
      }
    }
  }
}