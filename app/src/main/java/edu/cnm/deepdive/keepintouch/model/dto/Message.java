package edu.cnm.deepdive.keepintouch.model.dto;

import android.net.Uri;
import java.util.Date;

public class Message {

  private String body;

  private Date sent;

  private  String contactId;

  private Contact contact;

  private  String address;

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Date getSent() {
    return sent;
  }

  public void setSent(Date sent) {
    this.sent = sent;
  }

  public String getContactId() {
    return contactId;
  }

  public void setContactId(String contactId) {
    this.contactId = contactId;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
