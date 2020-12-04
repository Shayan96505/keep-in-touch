package edu.cnm.deepdive.keepintouch.controller.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.keepintouch.adapter.MessageAdapter;
import edu.cnm.deepdive.keepintouch.databinding.FragmentMessagesBinding;
import edu.cnm.deepdive.keepintouch.model.dto.Message;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.viewmodel.MainViewModel;
import java.util.List;

/**
 * A fragment that houses the messages and contact info of the user's contacts
 */
public class MessagesFragment extends Fragment {

  private FragmentMessagesBinding binding;

  private MainViewModel viewModel;
  private List<Message> messages;
  private List<? extends AutoReply> autoReplies;

  /**
   * Creates a view and binds this fragment to it.
   *
   * @param inflater           , an inflater object
   * @param container          , a container object
   * @param savedInstanceState , a saved Instance state.
   * @return a View
   */
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentMessagesBinding.inflate(inflater);
    return binding.getRoot();
  }


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    //Get references to a ViewModel instance, set observers on LiveData
    viewModel =
        new ViewModelProvider(this).get(MainViewModel.class);
    getLifecycle().addObserver(viewModel);
    viewModel.getMessages().observe(getViewLifecycleOwner(), (messages) -> {
      this.messages = messages;
      populateRecyclerView();
    });

    viewModel.getAutoReplies().observe(getViewLifecycleOwner(), (autoReplies) -> {
      this.autoReplies = autoReplies;
      populateRecyclerView();
    });
    viewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) -> {
      if (throwable != null) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
  }

  private void populateRecyclerView() {
    if (messages != null && autoReplies != null) {
      MessageAdapter adapter = new MessageAdapter(getContext(), messages, autoReplies,
          (message, autoReply) -> viewModel
              .sendMessage(message.getAddress(), autoReply.getMessage()));
      binding.messages.setAdapter(adapter);
      binding.waitingIndicator.setVisibility(View.GONE);
    }
  }
}