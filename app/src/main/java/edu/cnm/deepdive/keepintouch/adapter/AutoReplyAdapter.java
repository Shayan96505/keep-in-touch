package edu.cnm.deepdive.keepintouch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.keepintouch.adapter.AutoReplyAdapter.Holder;
import edu.cnm.deepdive.keepintouch.databinding.ItemAutoReplyBinding;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class AutoReplyAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final LayoutInflater inflater;
  private final List<AutoReply> autoReplies;
  private final NumberFormat formatter;

  public AutoReplyAdapter(Context context) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    autoReplies = new ArrayList<>();
    formatter = NumberFormat.getInstance();
  }

  public List<AutoReply> getAutoReplies() {
    return autoReplies;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemAutoReplyBinding binding = ItemAutoReplyBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
  holder.bind(position);
  }

  //allows us to get the length for the recycler view
  @Override
  public int getItemCount() {
    return autoReplies.size();
  }

   class Holder extends RecyclerView.ViewHolder {
   private final ItemAutoReplyBinding binding;

     public Holder(ItemAutoReplyBinding binding) {
       super(binding.getRoot());
       this.binding = binding;
     }

     private void bind (int position){
       AutoReply autoReply = autoReplies.get(position);
       String message = autoReply.getMessage();
       binding.message.setText(formatter.format(autoReply.getMessage()));
     }
   }
}
