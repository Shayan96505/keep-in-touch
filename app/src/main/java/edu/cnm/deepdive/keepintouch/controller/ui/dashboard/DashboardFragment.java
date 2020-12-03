package edu.cnm.deepdive.keepintouch.controller.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

/**
 * A fragment that houses the messages and contact info of the user's contacts
 */
public class DashboardFragment extends Fragment {

  private FragmentDashboardBinding binding;

  private MainViewModel viewModel;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    // Do whatever necessary with args
  }

  @Override
  public void onAttach(@NonNull Context context) {
    if (getContext() == null){
      Log.d(getClass().getSimpleName(), "no Context");
    }
    super.onAttach(context);
    if (getContext() != null){
      Log.d(getClass().getSimpleName(), "have Context");
    }
  }

  /**
   * Creates a view and binds this fragment to it.
   * @param inflater , an inflater object
   * @param container , a container object
   * @param savedInstanceState , a saved Instance state.
   * @return a View
   */
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentDashboardBinding.inflate(inflater);

    return binding.getRoot();
  }


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    //Get references to a ViewModel instance, set observers on LiveData
    viewModel =
        new ViewModelProvider(this).get(MainViewModel.class);
//    viewModel.getMessages().observe(getViewLifecycleOwner(), (messages) -> {
//      //TODO create an adapter containing messages and attach that adapter to my RecyclerView
//      AutoReplyAdapter adapter = new AutoReplyAdapter(getContext()); // find what's missing
//      //added a context to this fragment!
//      binding.showAutoReplies.setAdapter(adapter);
//    });

    viewModel.getAutoReplies().observe(getViewLifecycleOwner(), (autoReplies) -> {
      AutoReplyAdapter adapter = new AutoReplyAdapter(getContext(), autoReplies); // find what's missing
      //added a context to this fragment!
      binding.showAutoReplies.setAdapter(adapter);
    });
  }
}