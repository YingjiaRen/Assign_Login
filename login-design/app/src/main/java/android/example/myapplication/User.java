package android.example.myapplication;

public class User {
    public int _id;
    public String name;
    public String email;
    public String password;

    public User(){
    }

    public User(String name){
        this.name = name;
    }

    public User(String name,String password){
        this.email = name;
        this.password = password;
    }

    public User(String name,String email,String password){
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
