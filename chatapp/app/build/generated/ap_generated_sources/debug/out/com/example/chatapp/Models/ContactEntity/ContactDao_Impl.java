package com.example.chatapp.Models.ContactEntity;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ContactDao_Impl implements ContactDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Contact> __insertionAdapterOfContact;

  private final EntityDeletionOrUpdateAdapter<Contact> __deletionAdapterOfContact;

  private final EntityDeletionOrUpdateAdapter<Contact> __updateAdapterOfContact;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLastMessageAndTime;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ContactDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContact = new EntityInsertionAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Contact` (`userId`,`username`,`lastMessage`,`lastMessageTime`,`profilePicBase64`,`displayName`,`fullDate`,`timestamp`,`profilePic`,`id`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        stmt.bindLong(1, value.getUserId());
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
        if (value.getLastMessage() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLastMessage());
        }
        if (value.getLastMessageTime() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getLastMessageTime());
        }
        if (value.getProfilePicBase64() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getProfilePicBase64());
        }
        if (value.getDisplayName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDisplayName());
        }
        if (value.fullDate == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.fullDate);
        }
        if (value.lastMessageTimeStamp == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.lastMessageTimeStamp);
        }
        stmt.bindLong(9, value.getProfilePic());
        stmt.bindLong(10, value.getId());
      }
    };
    this.__deletionAdapterOfContact = new EntityDeletionOrUpdateAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Contact` WHERE `userId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        stmt.bindLong(1, value.getUserId());
      }
    };
    this.__updateAdapterOfContact = new EntityDeletionOrUpdateAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Contact` SET `userId` = ?,`username` = ?,`lastMessage` = ?,`lastMessageTime` = ?,`profilePicBase64` = ?,`displayName` = ?,`fullDate` = ?,`timestamp` = ?,`profilePic` = ?,`id` = ? WHERE `userId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        stmt.bindLong(1, value.getUserId());
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
        if (value.getLastMessage() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLastMessage());
        }
        if (value.getLastMessageTime() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getLastMessageTime());
        }
        if (value.getProfilePicBase64() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getProfilePicBase64());
        }
        if (value.getDisplayName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDisplayName());
        }
        if (value.fullDate == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.fullDate);
        }
        if (value.lastMessageTimeStamp == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.lastMessageTimeStamp);
        }
        stmt.bindLong(9, value.getProfilePic());
        stmt.bindLong(10, value.getId());
        stmt.bindLong(11, value.getUserId());
      }
    };
    this.__preparedStmtOfUpdateLastMessageAndTime = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Contact SET lastMessage = ?, lastMessageTime = ? WHERE userId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Contact";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Contact... contacts) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfContact.insert(contacts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Contact... contacts) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfContact.handleMultiple(contacts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Contact... contacts) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfContact.handleMultiple(contacts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateLastMessageAndTime(final String newLastMessage, final String newLastMessageTime,
      final int userId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLastMessageAndTime.acquire();
    int _argIndex = 1;
    if (newLastMessage == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newLastMessage);
    }
    _argIndex = 2;
    if (newLastMessageTime == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newLastMessageTime);
    }
    _argIndex = 3;
    _stmt.bindLong(_argIndex, userId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateLastMessageAndTime.release(_stmt);
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Contact> index() {
    final String _sql = "SELECT * FROM contact";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfLastMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessage");
      final int _cursorIndexOfLastMessageTime = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessageTime");
      final int _cursorIndexOfProfilePicBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "profilePicBase64");
      final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "displayName");
      final int _cursorIndexOfFullDate = CursorUtil.getColumnIndexOrThrow(_cursor, "fullDate");
      final int _cursorIndexOfLastMessageTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final int _cursorIndexOfProfilePic = CursorUtil.getColumnIndexOrThrow(_cursor, "profilePic");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<Contact> _result = new ArrayList<Contact>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Contact _item;
        _item = new Contact();
        _item.userId = _cursor.getInt(_cursorIndexOfUserId);
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        _item.setUsername(_tmpUsername);
        final String _tmpLastMessage;
        if (_cursor.isNull(_cursorIndexOfLastMessage)) {
          _tmpLastMessage = null;
        } else {
          _tmpLastMessage = _cursor.getString(_cursorIndexOfLastMessage);
        }
        _item.setLastMessage(_tmpLastMessage);
        final String _tmpLastMessageTime;
        if (_cursor.isNull(_cursorIndexOfLastMessageTime)) {
          _tmpLastMessageTime = null;
        } else {
          _tmpLastMessageTime = _cursor.getString(_cursorIndexOfLastMessageTime);
        }
        _item.setLastMessageTime(_tmpLastMessageTime);
        final String _tmpProfilePicBase64;
        if (_cursor.isNull(_cursorIndexOfProfilePicBase64)) {
          _tmpProfilePicBase64 = null;
        } else {
          _tmpProfilePicBase64 = _cursor.getString(_cursorIndexOfProfilePicBase64);
        }
        _item.setProfilePicBase64(_tmpProfilePicBase64);
        final String _tmpDisplayName;
        if (_cursor.isNull(_cursorIndexOfDisplayName)) {
          _tmpDisplayName = null;
        } else {
          _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
        }
        _item.setDisplayName(_tmpDisplayName);
        if (_cursor.isNull(_cursorIndexOfFullDate)) {
          _item.fullDate = null;
        } else {
          _item.fullDate = _cursor.getString(_cursorIndexOfFullDate);
        }
        if (_cursor.isNull(_cursorIndexOfLastMessageTimeStamp)) {
          _item.lastMessageTimeStamp = null;
        } else {
          _item.lastMessageTimeStamp = _cursor.getLong(_cursorIndexOfLastMessageTimeStamp);
        }
        final int _tmpProfilePic;
        _tmpProfilePic = _cursor.getInt(_cursorIndexOfProfilePic);
        _item.setProfilePic(_tmpProfilePic);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Contact> getAllContactByOrder() {
    final String _sql = "SELECT * FROM Contact ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfLastMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessage");
      final int _cursorIndexOfLastMessageTime = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessageTime");
      final int _cursorIndexOfProfilePicBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "profilePicBase64");
      final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "displayName");
      final int _cursorIndexOfFullDate = CursorUtil.getColumnIndexOrThrow(_cursor, "fullDate");
      final int _cursorIndexOfLastMessageTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final int _cursorIndexOfProfilePic = CursorUtil.getColumnIndexOrThrow(_cursor, "profilePic");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<Contact> _result = new ArrayList<Contact>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Contact _item;
        _item = new Contact();
        _item.userId = _cursor.getInt(_cursorIndexOfUserId);
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        _item.setUsername(_tmpUsername);
        final String _tmpLastMessage;
        if (_cursor.isNull(_cursorIndexOfLastMessage)) {
          _tmpLastMessage = null;
        } else {
          _tmpLastMessage = _cursor.getString(_cursorIndexOfLastMessage);
        }
        _item.setLastMessage(_tmpLastMessage);
        final String _tmpLastMessageTime;
        if (_cursor.isNull(_cursorIndexOfLastMessageTime)) {
          _tmpLastMessageTime = null;
        } else {
          _tmpLastMessageTime = _cursor.getString(_cursorIndexOfLastMessageTime);
        }
        _item.setLastMessageTime(_tmpLastMessageTime);
        final String _tmpProfilePicBase64;
        if (_cursor.isNull(_cursorIndexOfProfilePicBase64)) {
          _tmpProfilePicBase64 = null;
        } else {
          _tmpProfilePicBase64 = _cursor.getString(_cursorIndexOfProfilePicBase64);
        }
        _item.setProfilePicBase64(_tmpProfilePicBase64);
        final String _tmpDisplayName;
        if (_cursor.isNull(_cursorIndexOfDisplayName)) {
          _tmpDisplayName = null;
        } else {
          _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
        }
        _item.setDisplayName(_tmpDisplayName);
        if (_cursor.isNull(_cursorIndexOfFullDate)) {
          _item.fullDate = null;
        } else {
          _item.fullDate = _cursor.getString(_cursorIndexOfFullDate);
        }
        if (_cursor.isNull(_cursorIndexOfLastMessageTimeStamp)) {
          _item.lastMessageTimeStamp = null;
        } else {
          _item.lastMessageTimeStamp = _cursor.getLong(_cursorIndexOfLastMessageTimeStamp);
        }
        final int _tmpProfilePic;
        _tmpProfilePic = _cursor.getInt(_cursorIndexOfProfilePic);
        _item.setProfilePic(_tmpProfilePic);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Contact get(final int id) {
    final String _sql = "SELECT * FROM Contact WHERE userId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfLastMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessage");
      final int _cursorIndexOfLastMessageTime = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessageTime");
      final int _cursorIndexOfProfilePicBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "profilePicBase64");
      final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "displayName");
      final int _cursorIndexOfFullDate = CursorUtil.getColumnIndexOrThrow(_cursor, "fullDate");
      final int _cursorIndexOfLastMessageTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final int _cursorIndexOfProfilePic = CursorUtil.getColumnIndexOrThrow(_cursor, "profilePic");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final Contact _result;
      if(_cursor.moveToFirst()) {
        _result = new Contact();
        _result.userId = _cursor.getInt(_cursorIndexOfUserId);
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        _result.setUsername(_tmpUsername);
        final String _tmpLastMessage;
        if (_cursor.isNull(_cursorIndexOfLastMessage)) {
          _tmpLastMessage = null;
        } else {
          _tmpLastMessage = _cursor.getString(_cursorIndexOfLastMessage);
        }
        _result.setLastMessage(_tmpLastMessage);
        final String _tmpLastMessageTime;
        if (_cursor.isNull(_cursorIndexOfLastMessageTime)) {
          _tmpLastMessageTime = null;
        } else {
          _tmpLastMessageTime = _cursor.getString(_cursorIndexOfLastMessageTime);
        }
        _result.setLastMessageTime(_tmpLastMessageTime);
        final String _tmpProfilePicBase64;
        if (_cursor.isNull(_cursorIndexOfProfilePicBase64)) {
          _tmpProfilePicBase64 = null;
        } else {
          _tmpProfilePicBase64 = _cursor.getString(_cursorIndexOfProfilePicBase64);
        }
        _result.setProfilePicBase64(_tmpProfilePicBase64);
        final String _tmpDisplayName;
        if (_cursor.isNull(_cursorIndexOfDisplayName)) {
          _tmpDisplayName = null;
        } else {
          _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
        }
        _result.setDisplayName(_tmpDisplayName);
        if (_cursor.isNull(_cursorIndexOfFullDate)) {
          _result.fullDate = null;
        } else {
          _result.fullDate = _cursor.getString(_cursorIndexOfFullDate);
        }
        if (_cursor.isNull(_cursorIndexOfLastMessageTimeStamp)) {
          _result.lastMessageTimeStamp = null;
        } else {
          _result.lastMessageTimeStamp = _cursor.getLong(_cursorIndexOfLastMessageTimeStamp);
        }
        final int _tmpProfilePic;
        _tmpProfilePic = _cursor.getInt(_cursorIndexOfProfilePic);
        _result.setProfilePic(_tmpProfilePic);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Contact getByUserName(final String username) {
    final String _sql = "SELECT * FROM Contact WHERE username=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfLastMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessage");
      final int _cursorIndexOfLastMessageTime = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessageTime");
      final int _cursorIndexOfProfilePicBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "profilePicBase64");
      final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "displayName");
      final int _cursorIndexOfFullDate = CursorUtil.getColumnIndexOrThrow(_cursor, "fullDate");
      final int _cursorIndexOfLastMessageTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final int _cursorIndexOfProfilePic = CursorUtil.getColumnIndexOrThrow(_cursor, "profilePic");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final Contact _result;
      if(_cursor.moveToFirst()) {
        _result = new Contact();
        _result.userId = _cursor.getInt(_cursorIndexOfUserId);
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        _result.setUsername(_tmpUsername);
        final String _tmpLastMessage;
        if (_cursor.isNull(_cursorIndexOfLastMessage)) {
          _tmpLastMessage = null;
        } else {
          _tmpLastMessage = _cursor.getString(_cursorIndexOfLastMessage);
        }
        _result.setLastMessage(_tmpLastMessage);
        final String _tmpLastMessageTime;
        if (_cursor.isNull(_cursorIndexOfLastMessageTime)) {
          _tmpLastMessageTime = null;
        } else {
          _tmpLastMessageTime = _cursor.getString(_cursorIndexOfLastMessageTime);
        }
        _result.setLastMessageTime(_tmpLastMessageTime);
        final String _tmpProfilePicBase64;
        if (_cursor.isNull(_cursorIndexOfProfilePicBase64)) {
          _tmpProfilePicBase64 = null;
        } else {
          _tmpProfilePicBase64 = _cursor.getString(_cursorIndexOfProfilePicBase64);
        }
        _result.setProfilePicBase64(_tmpProfilePicBase64);
        final String _tmpDisplayName;
        if (_cursor.isNull(_cursorIndexOfDisplayName)) {
          _tmpDisplayName = null;
        } else {
          _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
        }
        _result.setDisplayName(_tmpDisplayName);
        if (_cursor.isNull(_cursorIndexOfFullDate)) {
          _result.fullDate = null;
        } else {
          _result.fullDate = _cursor.getString(_cursorIndexOfFullDate);
        }
        if (_cursor.isNull(_cursorIndexOfLastMessageTimeStamp)) {
          _result.lastMessageTimeStamp = null;
        } else {
          _result.lastMessageTimeStamp = _cursor.getLong(_cursorIndexOfLastMessageTimeStamp);
        }
        final int _tmpProfilePic;
        _tmpProfilePic = _cursor.getInt(_cursorIndexOfProfilePic);
        _result.setProfilePic(_tmpProfilePic);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
