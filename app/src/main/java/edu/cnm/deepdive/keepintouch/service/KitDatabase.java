package edu.cnm.deepdive.keepintouch.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import edu.cnm.deepdive.keepintouch.model.dao.AutoReplyDao;
import edu.cnm.deepdive.keepintouch.model.dao.IgnoreStatusDao;
import edu.cnm.deepdive.keepintouch.model.dao.UserDao;
import edu.cnm.deepdive.keepintouch.model.dao.UserTypeDao;
import edu.cnm.deepdive.keepintouch.model.entity.AutoReply;
import edu.cnm.deepdive.keepintouch.model.entity.IgnoreStatus;
import edu.cnm.deepdive.keepintouch.model.entity.User;
import edu.cnm.deepdive.keepintouch.model.entity.UserType;

@Database(entities = {AutoReply.class, IgnoreStatus.class, User.class, UserType.class},
    version = 1,
    exportSchema = true)
public abstract class KitDatabase extends RoomDatabase{

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

}
