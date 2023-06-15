package com.example.chatapp.Models.ChatEntity;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.chatapp.Models.Convertors.Converters;
import com.example.chatapp.Models.MessageEntity.Message;
import com.example.chatapp.Models.UserEntity.User;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ChatsDao_Impl implements ChatsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Chats> __insertionAdapterOfChats;

  private final EntityDeletionOrUpdateAdapter<Chats> __updateAdapterOfChats;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public ChatsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChats = new EntityInsertionAdapter<Chats>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Chats` (`id`,`users`,`messages`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Chats value) {
        stmt.bindLong(1, value.getId());
        final String _tmp = Converters.fromUserList(value.getUsers());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp);
        }
        final String _tmp_1 = Converters.fromMessageList(value.getMessages());
        if (_tmp_1 == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp_1);
        }
      }
    };
    this.__updateAdapterOfChats = new EntityDeletionOrUpdateAdapter<Chats>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Chats` SET `id` = ?,`users` = ?,`messages` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Chats value) {
        stmt.bindLong(1, value.getId());
        final String _tmp = Converters.fromUserList(value.getUsers());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp);
        }
        final String _tmp_1 = Converters.fromMessageList(value.getMessages());
        if (_tmp_1 == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp_1);
        }
        stmt.bindLong(4, value.getId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM chats WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Chats chats) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfChats.insert(chats);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Chats chats) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfChats.handle(chats);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final int chatId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, chatId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public List<Chats> getAllChats() {
    final String _sql = "SELECT * FROM chats";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUsers = CursorUtil.getColumnIndexOrThrow(_cursor, "users");
      final int _cursorIndexOfMessages = CursorUtil.getColumnIndexOrThrow(_cursor, "messages");
      final List<Chats> _result = new ArrayList<Chats>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Chats _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final List<User> _tmpUsers;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfUsers)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfUsers);
        }
        _tmpUsers = Converters.fromUserListString(_tmp);
        final List<Message> _tmpMessages;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfMessages)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfMessages);
        }
        _tmpMessages = Converters.fromMessageListString(_tmp_1);
        _item = new Chats(_tmpId,_tmpUsers,_tmpMessages);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Chats getChatById(final int chatId) {
    final String _sql = "SELECT * FROM chats WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, chatId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUsers = CursorUtil.getColumnIndexOrThrow(_cursor, "users");
      final int _cursorIndexOfMessages = CursorUtil.getColumnIndexOrThrow(_cursor, "messages");
      final Chats _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final List<User> _tmpUsers;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfUsers)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfUsers);
        }
        _tmpUsers = Converters.fromUserListString(_tmp);
        final List<Message> _tmpMessages;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfMessages)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfMessages);
        }
        _tmpMessages = Converters.fromMessageListString(_tmp_1);
        _result = new Chats(_tmpId,_tmpUsers,_tmpMessages);
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
  public Chats getChatByUsernames(final String username1, final String username2) {
    final String _sql = "SELECT * FROM Chats INNER JOIN User u1 ON u1.id = (SELECT id FROM User WHERE username = ?) INNER JOIN User u2 ON u2.id = (SELECT id FROM User WHERE username = ?) WHERE EXISTS (SELECT 1 FROM Chats c WHERE c.id = Chats.id AND u1.id IN (c.users) AND u2.id IN (c.users)) LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (username1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username1);
    }
    _argIndex = 2;
    if (username2 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username2);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUsers = CursorUtil.getColumnIndexOrThrow(_cursor, "users");
      final int _cursorIndexOfMessages = CursorUtil.getColumnIndexOrThrow(_cursor, "messages");
      final int _cursorIndexOfId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfId_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final Chats _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final List<User> _tmpUsers;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfUsers)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfUsers);
        }
        _tmpUsers = Converters.fromUserListString(_tmp);
        final List<Message> _tmpMessages;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfMessages)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfMessages);
        }
        _tmpMessages = Converters.fromMessageListString(_tmp_1);
        final int _tmpId_1;
        _tmpId_1 = _cursor.getInt(_cursorIndexOfId_1);
        final int _tmpId_2;
        _tmpId_2 = _cursor.getInt(_cursorIndexOfId_2);
        _result = new Chats(_tmpId,_tmpUsers,_tmpMessages);
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
