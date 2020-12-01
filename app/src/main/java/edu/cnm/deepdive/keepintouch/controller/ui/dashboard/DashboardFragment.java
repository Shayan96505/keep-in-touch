package edu.cnm.deepdive.keepintouch.controller.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.keepintouch.adapter.AutoReplyAdapter;
import edu.cnm.deepdive.keepintouch.databinding.FragmentDashboardBinding;
import edu.cnm.deepdive.keepintouch.viewmodel.MainViewModel;

public class DashboardFragment extends Fragment {

  private final Context context;
  private FragmentDashboardBinding binding;

  private MainViewModel viewModel;

  public DashboardFragment(Context context) {
    this.context = context;
  }

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentDashboardBinding.inflate(inflater);

    return binding.getRoot();
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
    viewModel =
        new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getMessages().observe(getViewLifecycleOwner(), (messages) -> {
      //TODO create an adapter containing messages and attach that adapter to my RecyclerView
      AutoReplyAdapter adapter = new AutoReplyAdapter(context); // find what's missing
      //added a context to this fragment!
      binding.showAutoReplies.setAdapter(adapter);
    });
  }
}