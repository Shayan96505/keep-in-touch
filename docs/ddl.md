```sqlite
CREATE TABLE IF NOT EXISTS `AutoReply`
(
    `auto_reply_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `message`       TEXT,
    `user_type_id`  INTEGER                           NOT NULL,
    FOREIGN KEY (`user_type_id`) REFERENCES `UserType` (`user_type_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_AutoReply_message` ON `AutoReply` (`message`);

CREATE INDEX IF NOT EXISTS `index_AutoReply_user_type_id` ON `AutoReply` (`user_type_id`);

CREATE TABLE IF NOT EXISTS `IgnoreStatus`
(
    `ignore_status_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `contact_uri`      TEXT,
    `count`            INTEGER                           NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_IgnoreStatus_contact_uri` ON `IgnoreStatus` (`contact_uri`);

CREATE INDEX IF NOT EXISTS `index_IgnoreStatus_count` ON `IgnoreStatus` (`count`);

CREATE TABLE IF NOT EXISTS `User`
(
    `user_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `oauth_key`    TEXT,
    `user_type_id` INTEGER                           NOT NULL,
    FOREIGN KEY (`user_type_id`) REFERENCES `UserType` (`user_type_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_User_oauth_key` ON `User` (`oauth_key`);

CREATE INDEX IF NOT EXISTS `index_User_user_type_id` ON `User` (`user_type_id`);

CREATE TABLE IF NOT EXISTS `UserType`
(
    `user_type_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`         TEXT
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_UserType_name` ON `UserType` (`name`);
```
[Link to project-level ddl.sql](sql/ddl.sql)

[Link to github repository](https://github.com/shayan-golafshani/keep-in-touch/blob/master/docs/sql/ddl.sql)