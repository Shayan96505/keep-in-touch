package edu.cnm.deepdive.keepintouch.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


/**
 *  An entity class for ignoreStatus fields stored inside the KitDatabase
 */
@Entity(indices = @Index(value = "contact_uri", unique = true))
public class IgnoreStatus {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "ignore_status_id")
  private long ignoreStatusId;

  //This is a string that allows the functionality of getting access to the contact info associated
  //with the built in contacts features of android.
  @ColumnInfo(name= "contact_uri")
  private String contactUri;


  //created an entity attribute called count that is an index
  @ColumnInfo(name = "count", index = true)
  private int count;

  /**
   * Gets Ignore Status Id
   * @return , a long id
   */
  public long getIgnoreStatusId() {
    return ignoreStatusId;
  }

  /**
   * Sets Ignore Status Id
   * @param ignoreStatusId , a long id
   */
  public void setIgnoreStatusId(long ignoreStatusId) {
    this.ignoreStatusId = ignoreStatusId;
  }

  /**
   * Gets an Contact Uri
   * @return a string, that represents a specific contactUri.
   */
  public String getContactUri() {
    return contactUri;
  }

  /**
   * Sets an Contact Uri
   * @param contactUri a string, that represents a specific contactUri.
   */
  public void setContactUri(String contactUri) {
    this.contactUri = contactUri;
  }

  /**
   * Gets the count of ignores
   * @return an int, that represents how many times someone has been ignored
   */
  public int getCount() {
    return count;
  }

  /**
   * Gets the count of ignores
   * @param count an int, that represents how many times someone has been ignored
   */
  public void setCount(int count) {
    this.count = count;
  }
}
