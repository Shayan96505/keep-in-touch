package edu.cnm.deepdive.keepintouch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.keepintouch.adapter.MessageAdapter.Holder;
import edu.cnm.deepdive.keepintouch.databinding.ItemMessageBinding;
import edu.cnm.deepdive.keepintouch.model.dto.Message;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import java.text.NumberFormat;
import java.util.List;

/**
 * This is an adapter for the recyclerView that contains both the late messages from contacts
 * and the autoReplies that they can send back to the their contacts. It contains basic info
 * like contact name, the message, and date received.
 */
public class MessageAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final LayoutInflater inflater;
  private final List<Message> messages;
  private final List<? extends AutoReply> autoReplies;
  private final OnAutoReplySelectedListener listener;

  /**
   * Constructor for the messaging RecyclerView
   * @param context is the context of the this activity
   * @param messages a list of autoReplies prepopulated into the database with a CSV file parser
   * @param autoReplies
   * @param listener
   */
  public MessageAdapter(Context context, List<Message> messages,
      List<? extends AutoReply> autoReplies,
      OnAutoReplySelectedListener listener) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.messages = messages;
    this.autoReplies = autoReplies;
    this.listener = listener;
  }

  //this creates a new view holder when there are no existing view holders which the RecyclerView can reuse.
  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemMessageBinding binding = ItemMessageBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  //onBindViewHolder() inflates the layout for an item, and puts the data in the views in the
  // layout. Because the RecyclerView knows nothing about the data, the Adapter needs to inform
  // the RecyclerView when that data changes.
  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  // this allows us to get the length for the recycler view
  @Override
  public int getItemCount() {
    return messages.size();
  }


  /**
   * this class Holds and binds the layouts of what we will displaying in the RecyclerView we're
   * implementing. It extends the ViewHolder class.
   */
  class Holder extends RecyclerView.ViewHolder implements OnItemSelectedListener {

    private final ItemMessageBinding binding;
    private Message message;

    public Holder(ItemMessageBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      ArrayAdapter<? extends AutoReply> adapter = new ArrayAdapter<>(context,
          android.R.layout.simple_dropdown_item_1line, autoReplies);
      binding.autoReplies.setAdapter(adapter);
      binding.autoReplies.setOnItemSelectedListener(this);
    }

    private void bind(int position) {
      message = messages.get(position);
      binding.contact.setText(message.getContact().getDisplayName());
      binding.message.setText(message.getBody());
      binding.date.setText(String.valueOf(message.getSent()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      AutoReply autoReply = (AutoReply) parent.getItemAtPosition(position);
      listener.onSelect(message, autoReply);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
  }

  public interface OnAutoReplySelectedListener {
    void onSelect(Message message, AutoReply autoReply);
  }
}
