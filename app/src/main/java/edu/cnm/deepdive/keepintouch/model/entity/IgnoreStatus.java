package edu.cnm.deepdive.keepintouch.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;


@Entity(indices = @Index(value = "contact_uri", unique = true))
public class IgnoreStatus {

  //created an entity attribute called count that is an index
  @ColumnInfo(name = "count", index = true)
  private int count;

  //This is a string that allows the functionality of getting access to th econtact info associated
  //with the built in contacts features of android.

  @ColumnInfo(name= "contact_uri")
  private String contactUri;
}
