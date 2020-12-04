package edu.cnm.deepdive.keepintouch.controller.ui.toSms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.keepintouch.R;

/**
 * The ToSmsFragment class that will lead to the native android messaging application.
 */
public class ToSmsFragment extends Fragment implements OnItemSelectedListener {

  private ToSmsViewModel toSmsViewModel;

  /**
   * The deprecated, but built in way to create the view for a fragment in Java.
   * @param inflater , a Layout inflater
   * @param container , a View group object
   * @param savedInstanceState , a bundle object
   * @return a View object which is how the fragment is displayed.
   */
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    toSmsViewModel =
        ViewModelProviders.of(this).get(ToSmsViewModel.class);
    View root = inflater.inflate(R.layout.fragment_to_sms, container, false);
    final TextView textView = root.findViewById(R.id.text_notifications);
    toSmsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}