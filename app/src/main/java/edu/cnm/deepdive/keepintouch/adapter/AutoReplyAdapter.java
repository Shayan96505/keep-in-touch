package edu.cnm.deepdive.keepintouch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.keepintouch.adapter.AutoReplyAdapter.Holder;
import edu.cnm.deepdive.keepintouch.databinding.ItemAutoReplyBinding;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import java.text.NumberFormat;
import java.util.List;

/**
 * This is an adapter for the recyclerView that contains both the late messages from contacts
 * and the autoReplies that they can send back to the their contacts. It contains basic info
 * like contact name, the message, and date received.
 */
public class AutoReplyAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final LayoutInflater inflater;
  private final List<? extends AutoReply> autoReplies;
  private final NumberFormat formatter;

  /**
   * Constructor for the messaging RecyclerView
   * @param context is the context of the this activity
   * @param autoReplies a list of autoReplies prepopulated into the database with a CSV file parser
   */
  public AutoReplyAdapter(Context context, List<? extends AutoReply> autoReplies) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.autoReplies = autoReplies;
    formatter = NumberFormat.getInstance();
  }

  //this creates a new view holder when there are no existing view holders which the RecyclerView can reuse.
  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemAutoReplyBinding binding = ItemAutoReplyBinding.inflate(inflater, parent, false);
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
    return autoReplies.size();
  }


  /**
   * this class Holds and binds the layouts of what we will displaying in the RecyclerView we're
   * implementing. It extends the ViewHolder class.
   */
  class Holder extends RecyclerView.ViewHolder {

    private final ItemAutoReplyBinding binding;

    public Holder(ItemAutoReplyBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      AutoReply autoReply = autoReplies.get(position);
      String message = autoReply.getMessage();
      binding.message.setText(message);
    }
  }
}
