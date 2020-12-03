package edu.cnm.deepdive.keepintouch.service;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.Telephony.Sms.Inbox;
import android.provider.Telephony.TextBasedSmsColumns;
import android.telephony.SmsManager;
import androidx.annotation.NonNull;
import edu.cnm.deepdive.keepintouch.model.dto.Contact;
import edu.cnm.deepdive.keepintouch.model.dto.Message;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A repository for my DTOs of Contacts and Messages....
 */
public class SmsRepository {

  private static final String[] contactProjection = {Phone.DISPLAY_NAME};


  private static final String[] messageProjection = {
      BaseColumns._ID,
      TextBasedSmsColumns.ADDRESS,
      TextBasedSmsColumns.PERSON,
      TextBasedSmsColumns.BODY,
      TextBasedSmsColumns.DATE
  };

  private final Context context;
  private final ContentResolver resolver;
  private final SmsManager smsManager;

  /**
   * A constructor for this Sms Repository
   * @param context , a context object
   */
  public SmsRepository(Context context) {
    this.context = context;
    resolver = context.getContentResolver();
    smsManager = SmsManager.getDefault();
  }

  /**
   *  A method to get a Contact and the associated information
   * @param contentUri , takes ina  contentUri object, which is a reference to the specific contact
   * @return , return a Contact or fail.
   */
  public Single<Contact> getContactInfo(Uri contentUri) {
    return Single.fromCallable(() -> getContact(contentUri))
        .subscribeOn(Schedulers.io());
  }

  private Contact getContact(Uri contentUri) {
    try (
        Cursor cursor =
            resolver.query(contentUri, contactProjection, null, null, null)
    ) {
      Contact contact = null;
      //TODO check this if, else-if ladder because it is causing issues with my app on my Pixel device
      if (cursor.moveToFirst()) {
        contact = new Contact();
        //int phoneNumberIndex = cursor.getColumnIndex(Phone.NUMBER);
        int displayNameIndex = cursor.getColumnIndex(Phone.DISPLAY_NAME);
        //contact.setPhoneNumber(cursor.getString(phoneNumberIndex));
        contact.setDisplayName(cursor.getString(displayNameIndex));
      }
      return contact;
    }
  }

  /**
   *
   * @return a List of Message objects, with the body, the address (phone number), person, and date... etc.
   *
   */
  public Single<List<Message>> getMessages() {
    return Single.fromCallable(() -> {

      try (
          Cursor cursor = resolver.query(Inbox.CONTENT_URI, messageProjection, null, null, null)
      ) {
        List<Message> messages = new LinkedList<>();
        if (cursor.moveToFirst()) {
          int idIndex = cursor.getColumnIndex("_id");
          int addressIndex = cursor.getColumnIndex("address");
          int bodyIndex = cursor.getColumnIndex("body");
          int personIndex = cursor.getColumnIndex("person");
          int dateIndex = cursor.getColumnIndex("date");
          do {
            Message message = new Message();
            message.setBody(cursor.getString(bodyIndex));
            message.setAddress(cursor.getString(addressIndex));
            message.setSent(new Date(Long.parseLong(cursor.getString(dateIndex))));
            String personId = cursor.getString(personIndex);
            if (personId != null) {
              message.setContact(getContact(
                  Uri.withAppendedPath(Contacts.CONTENT_URI, personId).buildUpon()
                      .build()));
            }
              messages.add(message);
          } while (cursor.moveToNext());
        }
        return messages;
      }
    })
        .subscribeOn(Schedulers.io());
  }

  // sending an SMS of a kit
  public Completable sendMessage(String phoneNumber, String text) {
    return Completable.fromAction(() -> {
      smsManager.sendTextMessage(phoneNumber, null, text, null, null);
    })
        .subscribeOn(Schedulers.io());
  }
}
