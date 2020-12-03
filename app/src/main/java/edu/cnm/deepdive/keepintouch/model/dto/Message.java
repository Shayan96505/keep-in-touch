package edu.cnm.deepdive.keepintouch.model.dto;

import java.util.Date;

/**
 * A Dto, which allows us to store the information we get from the incoming messages in the phone to
 * fields we can use such as {@link #body}, {@link #sent}, {@link #contact}, and {@link #address}.
 * We can access these fields with getters and setters.
 */
public class Message {

  private String body;

  private Date sent;

  private Contact contact;

  private String address;

  /**
   * A getter that allows to get access to the actual text inside an SMS message.
   *
   * @return a string of text, that makes up the message.
   */
  public String getBody() {
    return body;
  }

  /**
   * Allows us to set the body of an SMS message.
   *
   * @param body , this is a String parameter that represents the body of a message.
   */
  public void setBody(String body) {
    this.body = body;
  }

  /**
   * Gets us the date stamp of when a message was received by the user.
   *
   * @return a sent Timestamp for the messages, so that we can send a reminder to the User.
   */
  public Date getSent() {
    return sent;
  }

  /**
   * Sets the timestamp for a message.
   *
   * @param sent , this is a Date timestamp parameter that represents the time a message is
   *             recieved.
   */
  public void setSent(Date sent) {
    this.sent = sent;
  }

  /**
   * A getter that allows us to get a Contact object back
   *
   * @return a Contact object
   */
  public Contact getContact() {
    return contact;
  }

  /**
   * A setter that allows us to set the Contact object associated with a given message
   *
   * @param contact, a Contact object
   */
  public void setContact(Contact contact) {
    this.contact = contact;
  }

  /**
   * a getter that allows us to get the address or phone number from which a Contact sends us a
   * message.
   *
   * @return the phone number associated with the specified mesage.
   */
  public String getAddress() {
    return address;
  }

  /**
   * a setter that allows us to set the phone number for which we send a message to.
   *
   * @param address, A string param, that represents a phone number.
   */
  public void setAddress(String address) {
    this.address = address;
  }
}
