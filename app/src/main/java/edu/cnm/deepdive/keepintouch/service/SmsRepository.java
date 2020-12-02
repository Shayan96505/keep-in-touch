package edu.cnm.deepdive.keepintouch.service;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.Telephony.Sms.Inbox;
import androidx.annotation.NonNull;
import edu.cnm.deepdive.keepintouch.model.dto.Contact;
import edu.cnm.deepdive.keepintouch.model.dto.Message;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SmsRepository {

  private static final String[] contactProjection = {
      Phone.NUMBER, Phone.DISPLAY_NAME
  };

  //TODO Please find these constants in android and replace them!
  private static final String[] messageProjection = {
      "_id", "address", "person", "body", "date"
  };

  private final Context context;
  private final ContentResolver resolver;

  public SmsRepository(Context context) {
    this.context = context;
    resolver = context.getContentResolver();
  }

  public Single<Contact> getContactInfo(Uri contentUri) {
    return Single.fromCallable(() -> getContact(contentUri))
        .subscribeOn(Schedulers.io());
  }

  @NonNull
  private Contact getContact(Uri contentUri) {
    try (
        Cursor cursor =
            resolver.query(contentUri, contactProjection, null, null, null)
    ) {
      cursor.moveToFirst();
      int phoneNumberIndex = cursor.getColumnIndex(Phone.NUMBER);
      int displayNameIndex = cursor.getColumnIndex(Phone.DISPLAY_NAME);
      Contact contact = new Contact();
      contact.setPhoneNumber(cursor.getString(phoneNumberIndex));
      contact.setDisplayName(cursor.getString(displayNameIndex));
      return contact;
    }
  }

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
            String personId = cursor.getString(personIndex);
            if (personId != null) {
              Message message = new Message();
              message.setBody(cursor.getString(bodyIndex));
              message.setAddress(cursor.getString(addressIndex));
              message.setContactId(cursor.getString(personIndex));
              message.setSent(new Date(Long.parseLong(cursor.getString(dateIndex))));
              messages.add(message);
            }
          } while (cursor.moveToNext());
        }
        return messages;
      }
    })
        .map((messages) -> messages.stream()
            .map((message) -> {
              message.setContact(getContact(
                  Uri.withAppendedPath(Contacts.CONTENT_URI, message.getContactId()).buildUpon()
                      .build()));
              return message;
            })
            .collect(Collectors.toList())
        )
        .subscribeOn(Schedulers.io());
  }
}
