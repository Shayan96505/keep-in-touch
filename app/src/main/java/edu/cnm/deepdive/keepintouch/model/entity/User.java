package edu.cnm.deepdive.keepintouch.model.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


//In ROOM, the annotations for Unique Keys and Foreign Indexes go inside the entity annotation,
// before the beginning of the entity class.
@Entity(
    //this is the proper way to denote a Unique Key, give it a name matching from your entity class
    // and also set it's indices value of unique to true/
    indices = @Index(value = "oauth_key", unique = true),
    foreignKeys = {
        //similarly for foreign keys one must denote what class the foreign key is coming from
        //in this case the foreign key is coming from the user type class. In the parent-class column
        //as well as in the child-class it is name user-type-id, follow our google style guide conventions
        @ForeignKey(
            entity = UserType.class,
            childColumns = "user_type_id",
            parentColumns = "user_type_id"
        )
    }
)
public class User {

  //created a primary key for user. I followed the guide at the
  // URL https://ddc-java-11.github.io/2020/10/21/data-model-implementation-scenarios/
  // In Room, one must make the column info match the SQL style by specifying is using the name
  // attribute
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  private long id;

  //This is an Oauth key for authentication of the user using Google sign in services
  @ColumnInfo(name = "oauth_key")
  private String oauthKey;

  //This is a foreign key of user_type_ which will allow us to associate whether the user is a
  // teen, millenial, college graduate, parent, or grandparent. In the long run, it will be used to
  //determine the types of pre-recorded message selection a User will be able to choose from.
  @ColumnInfo(name = "user_type_id", index = true)
  private long userTypeId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getOauthKey() {
    return oauthKey;
  }

  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }

  public long getUserTypeId() {
    return userTypeId;
  }

  public void setUserTypeId(long userTypeId) {
    this.userTypeId = userTypeId;
  }

  //TODO think about whether or not it would be useful to store a user's name as a String
  //TODO think about where I want to put @Nonnull annotations and also about which getters and setter
  // that I need
}
