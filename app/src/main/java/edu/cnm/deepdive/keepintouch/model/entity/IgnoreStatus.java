package edu.cnm.deepdive.keepintouch.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


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


  public long getIgnoreStatusId() {
    return ignoreStatusId;
  }

  public void setIgnoreStatusId(long ignoreStatusId) {
    this.ignoreStatusId = ignoreStatusId;
  }

  public String getContactUri() {
    return contactUri;
  }

  public void setContactUri(String contactUri) {
    this.contactUri = contactUri;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
