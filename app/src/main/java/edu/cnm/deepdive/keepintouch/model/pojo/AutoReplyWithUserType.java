package edu.cnm.deepdive.keepintouch.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.model.entity.UserType;

/**
 * Pojo class that is the compliment to UserType with AutoReply. Allows us to get the auto replies
 * associated with a certain user type more directly.
 */
public class AutoReplyWithUserType extends AutoReply {

  @Relation(
      entity = UserType.class,
      entityColumn = "user_type_id", parentColumn = "user_type_id"
  )
  private UserType userType;

  /**
   * Gets the userType object.
   *
   * @return the userType object.
   */
  public UserType getUserType() {
    return userType;
  }

  /**
   * Setter for the userType
   *
   * @param userType , an object of userType.
   */
  public void setUserType(UserType userType) {
    this.userType = userType;
  }
}
