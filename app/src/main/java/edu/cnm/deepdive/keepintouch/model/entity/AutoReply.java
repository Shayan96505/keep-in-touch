package edu.cnm.deepdive.keepintouch.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(
    indices = @Index(value = "message", unique = true),
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

  @ColumnInfo(name = "message")
  private String message;

  @NonNull
  @ColumnInfo(name = "user_type_id")
  private long userTypeId;


}
