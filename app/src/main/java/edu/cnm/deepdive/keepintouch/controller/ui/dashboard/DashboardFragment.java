package edu.cnm.deepdive.keepintouch.controller.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.keepintouch.R;
import edu.cnm.deepdive.keepintouch.databinding.FragmentDashboardBinding;
import edu.cnm.deepdive.keepintouch.databinding.FragmentHomeBinding;

public class DashboardFragment extends Fragment {

  private FragmentDashboardBinding binding;

  private DashboardViewModel dashboardViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    dashboardViewModel =
        ViewModelProviders.of(this).get(DashboardViewModel.class);
    View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
    final TextView textView = root.findViewById(R.id.text_dashboard);
    dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    // Do whatever necessary with args
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    //Get references to a ViewModel instance, set observers on LiveData
  }
}