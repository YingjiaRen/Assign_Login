package android.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Register,LoginScreen;
    private EditText edit_name,edit_email;
    private EditText edit_pwd;
    private DB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        init();
        dbHelper = new DB(this, "UserData.db", null, 1);

    }

    protected void init(){
        edit_name = (EditText)findViewById(R.id.name);
        edit_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    edit_name.clearFocus();
                    InputMethodManager imm =
                            (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_name.getWindowToken(), 0);
                }
                return false;
            }
        });
        edit_email = (EditText)findViewById(R.id.email);
        edit_email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    edit_email.clearFocus();
                    InputMethodManager imm =
                            (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_email.getWindowToken(), 0);
                }
                return false;
            }
        });
        edit_pwd = (EditText)findViewById(R.id.password);
        Register = (Button)findViewById(R.id.btnRegister);
        Register.setOnClickListener(this);
        LoginScreen = (Button)findViewById(R.id.btnLinkToLoginScreen);
        LoginScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                if ( CheckIsDataAlreadyInDBorNot(edit_name.getText().toString())) {
                    Toast.makeText(this, "该用户名已被注册", Toast.LENGTH_SHORT).show();
                } else
                {
                    registerUserInfo(edit_name.getText().toString(),edit_email.getText().toString(),
                            edit_pwd.getText().toString());
                    Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
                    Intent register_intent = new Intent(RegisterActivity.this,
                            MainActivity.class);
                    startActivity(register_intent);
                }
                break;
            case R.id.btnLinkToLoginScreen:
                Intent login_intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(login_intent);
                break;
            default:
                break;
        }
    }

    private void registerUserInfo(String username, String useremail,String userpwd) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("useremail",useremail);
        values.put("userpassword", userpwd);
        db.insert("Userinfo", null, values);
        db.close();
    }

    //是否已注册
    public boolean CheckIsDataAlreadyInDBorNot(String value) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String Query = "Select * from Userinfo where username =?";
        Cursor cursor = db.rawQuery(Query, new String[]{value});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }


}

