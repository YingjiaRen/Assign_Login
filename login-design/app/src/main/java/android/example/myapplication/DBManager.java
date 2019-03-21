package android.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DB dbHelper;
    private SQLiteDatabase db;

    public DBManager(Context context){

    }

    public void add(List<User> usersInfos){
        db.beginTransaction();//开始事务
        for (User usersInfo : usersInfos){
            db.execSQL("insert into Userinfo values(null,?,?)",new Object[]{
                    usersInfo.name,usersInfo.email,usersInfo.password
            });
        }
        db.setTransactionSuccessful();//设置事务完成
        db.endTransaction();//结束事务
    }

    public void add(String username,String useremail,String userpassword){
        db.beginTransaction();
        db.execSQL("insert into Userinfo values(null,?,?)",new Object[]{
                username,useremail,userpassword
        });
    }

    public void update(){

    }


    public List<User> query(){
        ArrayList<User> usersInfos = new ArrayList<>();
        Cursor c  = queryTheCursor();
        while (c.moveToNext()){
            User usersInfo = new User();
            usersInfo._id = c.getInt(c.getColumnIndex("_id"));
            usersInfo.email = c.getString(c.getColumnIndex("email"));
            usersInfo.password = c.getString(c.getColumnIndex("password"));
            usersInfo.name = c.getString(c.getColumnIndex("name"));
            usersInfos.add(usersInfo);
        }
        c.close();
        return usersInfos;
    }


    public Cursor queryTheCursor(){
        Cursor c = db.rawQuery("SELECT * FROM Userinfo", null);
        return c;
    }

    public void closeDB(){
        db.close();
    }
}
