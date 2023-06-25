package com.example.chatapp.Models.MessageEntity;

import android.database.Cursor;
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
public final class MessageDao_Impl implements MessageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Message> __insertionAdapterOfMessage;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MessageDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage = new EntityInsertionAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Message` (`id`,`created`,`dbId`,`sender`,`content`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
        if (value.getCreated() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCreated());
        }
        stmt.bindLong(3, value.dbId);
        if (value.getSender() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSender());
        }
        if (value.getContent() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getContent());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM message";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Message message) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage.insert(message);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
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
  public List<Message> getAllMessages() {
    final String _sql = "SELECT * FROM message";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
      final int _cursorIndexOfDbId = CursorUtil.getColumnIndexOrThrow(_cursor, "dbId");
      final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item;
        _item = new Message();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        final String _tmpCreated;
        if (_cursor.isNull(_cursorIndexOfCreated)) {
          _tmpCreated = null;
        } else {
          _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
        }
        _item.setCreated(_tmpCreated);
        _item.dbId = _cursor.getInt(_cursorIndexOfDbId);
        final String _tmpSender;
        if (_cursor.isNull(_cursorIndexOfSender)) {
          _tmpSender = null;
        } else {
          _tmpSender = _cursor.getString(_cursorIndexOfSender);
        }
        _item.setSender(_tmpSender);
        final String _tmpContent;
        if (_cursor.isNull(_cursorIndexOfContent)) {
          _tmpContent = null;
        } else {
          _tmpContent = _cursor.getString(_cursorIndexOfContent);
        }
        _item.setContent(_tmpContent);
        _result.add(_item);
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
