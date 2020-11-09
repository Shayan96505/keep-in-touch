package edu.cnm.deepdive.keepintouch.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.model.entity.UserType;


public class AutoReplyWithUserType extends AutoReply {

  @Relation(
      entity = UserType.class,
      entityColumn = "user_type_id", parentColumn = "user_type_id"
  )
  private UserType userType;

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }
}
