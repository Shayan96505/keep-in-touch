package edu.cnm.deepdive.keepintouch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.keepintouch.adapter.MessageAdapter.Holder;
import edu.cnm.deepdive.keepintouch.databinding.ItemMessageBinding;
import edu.cnm.deepdive.keepintouch.model.dto.Message;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import java.text.DateFormat;
import java.util.List;

/**
 * This is an adapter for the recyclerView that contains both the late messages from contacts and
 * the autoReplies that they can send back to the their contacts. It deals with helping process
 * basic info like contact name, the message, and date received for display.
 */
public class MessageAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final LayoutInflater inflater;
  private final List<Message> messages;
  private final List<? extends AutoReply> autoReplies;
  private final OnSendClickListener listener;
  private final DateFormat dateFormatter;
  private final DateFormat timeFormatter;

  /**
   * Constructor for the messaging RecyclerView.
   *
   * @param context     is the context of the this activity
   * @param messages    a list of autoReplies prepopulated into the database with a CSV file parser
   * @param autoReplies
   * @param listener
   */
  public MessageAdapter(Context context, List<Message> messages,
      List<? extends AutoReply> autoReplies,
      OnSendClickListener listener) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.messages = messages;
    this.autoReplies = autoReplies;
    this.listener = listener;
    dateFormatter = android.text.format.DateFormat.getMediumDateFormat(context);
    timeFormatter = android.text.format.DateFormat.getTimeFormat(context);
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
  class Holder extends RecyclerView.ViewHolder {

    private final ItemMessageBinding binding;
    private Message message;

    /**
     *  A constructor for the Holder class that holds the views for Recycler View.
     * @param binding , a binding object for the item_message layout xml.
     */
    public Holder(ItemMessageBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      ArrayAdapter<? extends AutoReply> adapter = new ArrayAdapter<>(context,
          android.R.layout.simple_dropdown_item_1line, autoReplies);
      binding.autoReplies.setAdapter(adapter);
    }

    private void bind(int position) {
      message = messages.get(position);
      if (message.getContact() != null && message.getContact().getDisplayName() != null) {
        binding.contact.setText(message.getContact().getDisplayName());
      } else {
        binding.contact.setText(message.getAddress());
      }
      binding.message.setText(message.getBody());
      String date = dateFormatter.format(message.getSent());
      String time = timeFormatter.format(message.getSent());
      binding.date.setText(date + " " + time);
      binding.send.setOnClickListener((v) ->
          listener.onClick(message, (AutoReply) binding.autoReplies.getSelectedItem()));
    }

  }

  /**
   * An interface that listens for when the Send It button is clicked and an autoReply is selected
   * from the associated spinner.
   */
  public interface OnSendClickListener {

    void onClick(Message message, AutoReply autoReply);
  }


}
