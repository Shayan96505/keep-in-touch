package edu.cnm.deepdive.keepintouch.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * An entity class for autoReply fields stored inside the KitDatabase
 */
@Entity(
    indices = {
        @Index(value = "message", unique = true),
        @Index(value = "user_type_id")
    },

    foreignKeys = {
        //same reasoning I used in the User entity applies here.
        @ForeignKey(
            entity = UserType.class,
            childColumns = "user_type_id",
            parentColumns = "user_type_id"
        )
    }
)
public class AutoReply {

  /// created and added a primary key
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "auto_reply_id")
  private long autoReplyId;


  // holds reply message
  @ColumnInfo(name = "message")
  private String message;

  @SuppressWarnings("NullableProblems")
  @NonNull
  @ColumnInfo(name = "user_type_id")
  private long userTypeId;


  /**
   * Gets the AutoReply id
   *
   * @return , a long that represents the id
   */
  public long getAutoReplyId() {
    return autoReplyId;
  }

  /**
   * Sets the AutoReply id
   *
   * @param autoReplyId , a long that represents the id
   */
  public void setAutoReplyId(long autoReplyId) {
    this.autoReplyId = autoReplyId;
  }

  /**
   * Gets the actual auto replies.
   *
   * @return , a String that is the message making up the AutoReply
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the actual auto replies.
   *
   * @param message , a String that is the message making up the AutoReply
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets the UserType id.
   *
   * @return , a long that represents the User Type Id
   */
  public long getUserTypeId() {
    return userTypeId;
  }

  /**
   * Sets the UserType id.
   *
   * @param userTypeId , a long that represents the User Type Id
   */
  public void setUserTypeId(long userTypeId) {
    this.userTypeId = userTypeId;
  }

  @NonNull
  @Override
  public String toString() {
    return message;
  }
}
