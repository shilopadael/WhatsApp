package com.example.chatapp.Models;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.chatapp.Models.ChatEntity.ChatsDao;
import com.example.chatapp.Models.ChatEntity.ChatsDao_Impl;
import com.example.chatapp.Models.ContactEntity.ContactDao;
import com.example.chatapp.Models.ContactEntity.ContactDao_Impl;
import com.example.chatapp.Models.MessageEntity.MessageDao;
import com.example.chatapp.Models.MessageEntity.MessageDao_Impl;
import com.example.chatapp.Models.TokenEntity.TokenDao;
import com.example.chatapp.Models.TokenEntity.TokenDao_Impl;
import com.example.chatapp.Models.UserEntity.UserDao;
import com.example.chatapp.Models.UserEntity.UserDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDB_Impl extends AppDB {
  private volatile ContactDao _contactDao;

  private volatile UserDao _userDao;

  private volatile TokenDao _tokenDao;

  private volatile ChatsDao _chatsDao;

  private volatile MessageDao _messageDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Contact` (`userId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT, `lastMessage` TEXT, `lastMessageTime` TEXT, `profilePicBase64` TEXT, `displayName` TEXT, `profilePic` INTEGER NOT NULL, `id` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Token` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `token` TEXT, `userName` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `User` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT, `password` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Message` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `created` TEXT, `dbId` INTEGER NOT NULL, `sender` TEXT, `content` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Chats` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `users` TEXT, `messages` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8886eafbba79acc28a5efb891045c292')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Contact`");
        _db.execSQL("DROP TABLE IF EXISTS `Token`");
        _db.execSQL("DROP TABLE IF EXISTS `User`");
        _db.execSQL("DROP TABLE IF EXISTS `Message`");
        _db.execSQL("DROP TABLE IF EXISTS `Chats`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsContact = new HashMap<String, TableInfo.Column>(8);
        _columnsContact.put("userId", new TableInfo.Column("userId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContact.put("username", new TableInfo.Column("username", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContact.put("lastMessage", new TableInfo.Column("lastMessage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContact.put("lastMessageTime", new TableInfo.Column("lastMessageTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContact.put("profilePicBase64", new TableInfo.Column("profilePicBase64", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContact.put("displayName", new TableInfo.Column("displayName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContact.put("profilePic", new TableInfo.Column("profilePic", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContact.put("id", new TableInfo.Column("id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysContact = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesContact = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoContact = new TableInfo("Contact", _columnsContact, _foreignKeysContact, _indicesContact);
        final TableInfo _existingContact = TableInfo.read(_db, "Contact");
        if (! _infoContact.equals(_existingContact)) {
          return new RoomOpenHelper.ValidationResult(false, "Contact(com.example.chatapp.Models.ContactEntity.Contact).\n"
                  + " Expected:\n" + _infoContact + "\n"
                  + " Found:\n" + _existingContact);
        }
        final HashMap<String, TableInfo.Column> _columnsToken = new HashMap<String, TableInfo.Column>(3);
        _columnsToken.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsToken.put("token", new TableInfo.Column("token", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsToken.put("userName", new TableInfo.Column("userName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysToken = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesToken = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoToken = new TableInfo("Token", _columnsToken, _foreignKeysToken, _indicesToken);
        final TableInfo _existingToken = TableInfo.read(_db, "Token");
        if (! _infoToken.equals(_existingToken)) {
          return new RoomOpenHelper.ValidationResult(false, "Token(com.example.chatapp.Models.TokenEntity.Token).\n"
                  + " Expected:\n" + _infoToken + "\n"
                  + " Found:\n" + _existingToken);
        }
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(3);
        _columnsUser.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("username", new TableInfo.Column("username", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("password", new TableInfo.Column("password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("User", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "User");
        if (! _infoUser.equals(_existingUser)) {
          return new RoomOpenHelper.ValidationResult(false, "User(com.example.chatapp.Models.UserEntity.User).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        final HashMap<String, TableInfo.Column> _columnsMessage = new HashMap<String, TableInfo.Column>(5);
        _columnsMessage.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("created", new TableInfo.Column("created", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("dbId", new TableInfo.Column("dbId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("sender", new TableInfo.Column("sender", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMessage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMessage = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMessage = new TableInfo("Message", _columnsMessage, _foreignKeysMessage, _indicesMessage);
        final TableInfo _existingMessage = TableInfo.read(_db, "Message");
        if (! _infoMessage.equals(_existingMessage)) {
          return new RoomOpenHelper.ValidationResult(false, "Message(com.example.chatapp.Models.MessageEntity.Message).\n"
                  + " Expected:\n" + _infoMessage + "\n"
                  + " Found:\n" + _existingMessage);
        }
        final HashMap<String, TableInfo.Column> _columnsChats = new HashMap<String, TableInfo.Column>(3);
        _columnsChats.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChats.put("users", new TableInfo.Column("users", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChats.put("messages", new TableInfo.Column("messages", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysChats = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesChats = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoChats = new TableInfo("Chats", _columnsChats, _foreignKeysChats, _indicesChats);
        final TableInfo _existingChats = TableInfo.read(_db, "Chats");
        if (! _infoChats.equals(_existingChats)) {
          return new RoomOpenHelper.ValidationResult(false, "Chats(com.example.chatapp.Models.ChatEntity.Chats).\n"
                  + " Expected:\n" + _infoChats + "\n"
                  + " Found:\n" + _existingChats);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "8886eafbba79acc28a5efb891045c292", "1d1f97da98f4fa49f603c1097717113a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Contact","Token","User","Message","Chats");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Contact`");
      _db.execSQL("DELETE FROM `Token`");
      _db.execSQL("DELETE FROM `User`");
      _db.execSQL("DELETE FROM `Message`");
      _db.execSQL("DELETE FROM `Chats`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ContactDao.class, ContactDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TokenDao.class, TokenDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ChatsDao.class, ChatsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MessageDao.class, MessageDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public ContactDao contactsDao() {
    if (_contactDao != null) {
      return _contactDao;
    } else {
      synchronized(this) {
        if(_contactDao == null) {
          _contactDao = new ContactDao_Impl(this);
        }
        return _contactDao;
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public TokenDao tokenDao() {
    if (_tokenDao != null) {
      return _tokenDao;
    } else {
      synchronized(this) {
        if(_tokenDao == null) {
          _tokenDao = new TokenDao_Impl(this);
        }
        return _tokenDao;
      }
    }
  }

  @Override
  public ChatsDao chatsDao() {
    if (_chatsDao != null) {
      return _chatsDao;
    } else {
      synchronized(this) {
        if(_chatsDao == null) {
          _chatsDao = new ChatsDao_Impl(this);
        }
        return _chatsDao;
      }
    }
  }

  @Override
  public MessageDao messageDao() {
    if (_messageDao != null) {
      return _messageDao;
    } else {
      synchronized(this) {
        if(_messageDao == null) {
          _messageDao = new MessageDao_Impl(this);
        }
        return _messageDao;
      }
    }
  }
}
