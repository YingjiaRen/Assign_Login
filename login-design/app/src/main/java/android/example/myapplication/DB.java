package android.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DB extends SQLiteOpenHelper {

    public static final String CREATE_USERINFO = "create table Userinfo ("
            + "id integer primary key autoincrement, "
            + "username text, "
            + "useremail text, "
            + "userpassword text)";
    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "UserInfo.db";
    private Context mContext;
    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERINFO);
        Toast.makeText(mContext,"Succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table usertable add column other string");
    }
}