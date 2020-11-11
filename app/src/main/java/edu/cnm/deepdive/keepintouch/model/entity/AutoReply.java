package edu.cnm.deepdive.keepintouch.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    indices = {
        @Index(value = "message", unique = true),
        @Index(value = "user_type_id", unique = true)
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


  public long getAutoReplyId() {
    return autoReplyId;
  }

  public void setAutoReplyId(long autoReplyId) {
    this.autoReplyId = autoReplyId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getUserTypeId() {
    return userTypeId;
  }

  public void setUserTypeId(long userTypeId) {
    this.userTypeId = userTypeId;
  }
}
