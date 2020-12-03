package edu.cnm.deepdive.keepintouch.controller.ui.notifications;

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

public class NotificationsFragment extends Fragment implements OnItemSelectedListener {

  private NotificationsViewModel notificationsViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    notificationsViewModel =
        ViewModelProviders.of(this).get(NotificationsViewModel.class);
    View root = inflater.inflate(R.layout.fragment_notifications, container, false);
    final TextView textView = root.findViewById(R.id.text_notifications);
    notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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