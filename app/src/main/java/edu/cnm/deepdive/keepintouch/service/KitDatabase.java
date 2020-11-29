package edu.cnm.deepdive.keepintouch.service;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.keepintouch.R;
import edu.cnm.deepdive.keepintouch.model.dao.AutoReplyDao;
import edu.cnm.deepdive.keepintouch.model.dao.IgnoreStatusDao;
import edu.cnm.deepdive.keepintouch.model.dao.UserDao;
import edu.cnm.deepdive.keepintouch.model.dao.UserTypeDao;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.model.entity.IgnoreStatus;
import edu.cnm.deepdive.keepintouch.model.entity.User;
import edu.cnm.deepdive.keepintouch.model.entity.UserType;
import io.reactivex.Single;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@Database(entities = {AutoReply.class, IgnoreStatus.class, User.class, UserType.class},
    version = 1,
    exportSchema = true)
public abstract class KitDatabase extends RoomDatabase {

  private static final String DB_NAME = "kit_db";

  private static Application context;

  public static void setContext(Application context) {
    KitDatabase.context = context;
  }

  public static KitDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract AutoReplyDao getAutoReplyDao();

  public abstract UserDao getUserDao();

  public abstract UserTypeDao getUserTypeDao();

  public abstract IgnoreStatusDao getIgnoreStatusDao();

  private static class InstanceHolder {

    private static final KitDatabase INSTANCE =
        Room.databaseBuilder(context, KitDatabase.class, DB_NAME)
            .build();

  }

  private static class AutoReplyCallback extends Callback {

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      try {
        Map<UserType, List<AutoReply>> map = parseFile(R.raw.autoreply);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

   private Map<UserType, List<AutoReply>> parseFile(int resourceId) throws IOException {
      try(
          InputStream input = KitDatabase.context.getResources().openRawResource(resourceId);
          Reader reader = new InputStreamReader(input);
          CSVParser parser = CSVParser.parse(reader, CSVFormat.EXCEL.withIgnoreSurroundingSpaces().withIgnoreEmptyLines());
          ) {
          Map<UserType, List<AutoReply>> map = new HashMap<>();
          for (CSVRecord record: parser){
            UserType userType = null;
            String sourceUserType = record.get(0).trim();
            if (!sourceUserType.isEmpty()){
              userType = new UserType();
              userType.setName(sourceUserType);
            }
            List<AutoReply> autoReplies = map.computeIfAbsent(userType, (ar) -> new LinkedList<>());
            AutoReply autoReply = new AutoReply();
            autoReply.setMessage(record.get(1).trim());
            autoReplies.add(autoReply);
          }
          return map;
      }
   }
  }


}
