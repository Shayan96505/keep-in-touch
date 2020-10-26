package edu.cnm.deepdive.keepintouch.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

//labeling userType as an entity, and adding the name attribute as unique key.
@Entity(
    indices = @Index(value = "name", unique = true)
)
public class UserType {

  // generate a primary key for userType
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name ="user_type_id")
  private long userTypeId;

  @ColumnInfo(name = "name")
  private String name;


  // below this point I added getters and setters for the attributes of UserType
  public long getUserTypeId() {
    return userTypeId;
  }

  public void setUserTypeId(long userTypeId) {
    this.userTypeId = userTypeId;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
