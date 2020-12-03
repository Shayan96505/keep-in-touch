package edu.cnm.deepdive.keepintouch.model.dto;

/**
 * A Dto, which allows us to store the information we get from the Contacts in the phone to fields
 * we can use such as {@link #phoneNumber}, {@link #displayName}. We can access these fields with
 * getters and setters.
 */
public class Contact {

  private String phoneNumber;

  private String displayName;

  /**
   * This returns the phone number of the contact
   *
   * @return string, phone number.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * This sets the phone number of a contact.
   *
   * @param phoneNumber this is a parameter, phone number.
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * This returns the display name of a contact so we can display it.
   *
   * @return the Contact's display name.
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Setter that allows us to change the display name of a contact, necessary
   *
   * @param displayName a parameter, contact's display name.
   */
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
