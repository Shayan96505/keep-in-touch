package edu.cnm.deepdive.keepintouch.model.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Objects;

//labeling userType as an entity, and adding the name attribute as unique key.

/**
 * A userType Entity that holds the names of the userType and the ids.
 */
@Entity(
    indices = @Index(value = "name", unique = true)
)
public class UserType {

  // generate a primary key for userType
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_type_id")
  private long userTypeId;

  @ColumnInfo(name = "name")
  private String name = "";

  // below this point I added getters and setters for the attributes of UserType

  /**
   * A getter for UserTypeId
   * @return , long for User Type Id.
   */
  public long getUserTypeId() {
    return userTypeId;
  }

  /**
   * A Setter for UserTypeId
   * @param userTypeId , long for User Type Id.
   */
  public void setUserTypeId(long userTypeId) {
    this.userTypeId = userTypeId;
  }

  /**
   * Getter for the name of the UserType
   * @return , a String object that represents the name of usertype, ie "Millennial.
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   * Setter for the name of the UserType
   * @param name , a String object that represents the name of usertype, ie "Millennial.
   */
  public void setName(String name) {
    this.name = name;
  }

  @NonNull
  @Override
  public String toString() {
    return name;
  }

  @Override
  public int hashCode() {
    //return Objects.hash(userTypeId, name);
    return (int) (37 * userTypeId + name.hashCode());
  }

  @Override
  public boolean equals(@Nullable Object obj) {
    boolean equal;

    if (this == obj) {
      equal = true;
    } else if (obj instanceof UserType) {
      UserType other = (UserType) obj;
      equal = userTypeId == other.userTypeId
          && (userTypeId != 0 || name.equals(other.name));
    } else {
      equal = false;
    }
    return equal;
  }
}
