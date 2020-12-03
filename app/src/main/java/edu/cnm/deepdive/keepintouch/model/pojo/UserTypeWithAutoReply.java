package edu.cnm.deepdive.keepintouch.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.model.entity.UserType;
import java.util.List;

public class UserTypeWithAutoReply extends UserType {

  @Relation(entity = AutoReply.class,
      entityColumn = "user_type_id", parentColumn = "user_type_id")
  private List<AutoReply> autoReplies;

  public List<AutoReply> getAutoReplies() {
    return autoReplies;
  }

  public void setAutoReplies(
      List<AutoReply> autoReplies) {
    this.autoReplies = autoReplies;
  }
}
