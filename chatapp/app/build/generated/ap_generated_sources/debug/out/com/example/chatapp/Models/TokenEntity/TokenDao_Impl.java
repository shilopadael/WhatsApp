package com.example.chatapp.Models.TokenEntity;

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
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TokenDao_Impl implements TokenDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Token> __insertionAdapterOfToken;

  private final SharedSQLiteStatement __preparedStmtOfDeleteToken;

  public TokenDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfToken = new EntityInsertionAdapter<Token>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Token` (`id`,`token`,`userName`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Token value) {
        stmt.bindLong(1, value.id);
        if (value.getToken() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getToken());
        }
        if (value.getUserName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUserName());
        }
      }
    };
    this.__preparedStmtOfDeleteToken = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM token";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Token token) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfToken.insert(token);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteToken() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteToken.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteToken.release(_stmt);
    }
  }

  @Override
  public Token getToken() {
    final String _sql = "SELECT * FROM token LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfToken = CursorUtil.getColumnIndexOrThrow(_cursor, "token");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "userName");
      final Token _result;
      if(_cursor.moveToFirst()) {
        final String _tmpToken;
        if (_cursor.isNull(_cursorIndexOfToken)) {
          _tmpToken = null;
        } else {
          _tmpToken = _cursor.getString(_cursorIndexOfToken);
        }
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        _result = new Token(_tmpToken,_tmpUserName);
        _result.id = _cursor.getInt(_cursorIndexOfId);
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
