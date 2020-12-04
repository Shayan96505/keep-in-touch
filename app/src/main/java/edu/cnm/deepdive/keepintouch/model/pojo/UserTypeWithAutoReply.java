package edu.cnm.deepdive.keepintouch.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.model.entity.UserType;
import java.util.List;

/**
 * Pojo class that is the compliment to AutoReply with UserType.
 */
public class UserTypeWithAutoReply extends UserType {

  @Relation(entity = AutoReply.class,
      entityColumn = "user_type_id", parentColumn = "user_type_id")
  private List<AutoReply> autoReplies;

  /**
   * @return a list of AutoReplies
   */
  public List<AutoReply> getAutoReplies() {
    return autoReplies;
  }

  /**
   * Set a list of Auto Replies
   *
   * @param autoReplies , a list of AutoReplies.
   */
  public void setAutoReplies(
      List<AutoReply> autoReplies) {
    this.autoReplies = autoReplies;
  }
}
