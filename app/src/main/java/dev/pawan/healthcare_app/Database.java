package dev.pawan.healthcare_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 ="create table users(userFullName text,userMobile text,userEmail text,userPassword text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 ="create table cart(userFullName text,product text,price float,oType text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3 ="create table orderPlace(userFullName text,fullName text,mobile text,address text,pinCode int,date text,time text,price float,oType text)";
        sqLiteDatabase.execSQL(qry3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
    public  void  register (String userFullName ,String userMobile, String userEmail,String userPassword){
        ContentValues cv =new ContentValues();
        cv.put("userFullName",userFullName);
        cv.put("userMobile",userMobile);
        cv.put("userEmail",userEmail);
        cv.put("userPassword",userPassword);
        SQLiteDatabase db =getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String userFullName,String userPassword){
        int result =0;
        String str[]= new String[2];
        str[0] = userFullName;
        str[1] = userPassword;
        SQLiteDatabase db =getReadableDatabase();
        Cursor c= db.rawQuery("select * from users where userFullName =? and userPassword =?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }
    public  void  addCart (String userFullName ,String product, float price,String oType){
        ContentValues cv =new ContentValues();
        cv.put("userFullName",userFullName);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("oType",oType);
        SQLiteDatabase db =getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }
    public  int  checkCart(String userFullName ,String product){
        int result =0;
        String str[]= new String[2];
        str[0] = userFullName;
        str[1] = product;
        SQLiteDatabase db =getReadableDatabase();
        Cursor c= db.rawQuery("select * from cart where userFullName =? and product =?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;

    }
    public  void  removedCart(String userFullName,String oType){
        String str[] =new String[2];
        str[0]=userFullName;
        str[1]=oType;
        SQLiteDatabase db =getWritableDatabase();
        db.delete("cart","userFullName =? and oType=?",str );
        db.close();
    }

    public ArrayList getCartData(String userFullName , String oType){
        ArrayList <String> arr =new ArrayList<>();
        SQLiteDatabase db =getReadableDatabase();
        String str[]= new String[2];
        str[0] = userFullName;
        str[1] = oType;
        Cursor c= db.rawQuery("select * from cart where userFullName =? and oType =?",str);
        if(c.moveToFirst()){
            do{
                String product =c.getString(1);
                String price =c.getString(2);
                arr.add(product+"$"+price);
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void addBook(String userFullName, String fullName, String mobile, String address, int pinCode, String date, String time, float price, String oType) {
        ContentValues cv =new ContentValues();
        cv.put("userFullName",userFullName);
        cv.put("fullName",fullName);
        cv.put("mobile",mobile);
        cv.put("address",address);
        cv.put("pinCode",pinCode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("price",price);
        cv.put("oType",oType);
        SQLiteDatabase db =getWritableDatabase();
        db.insert("orderPlace",null,cv);
        db.close();
    }

    public ArrayList getOrderData(String userFullName ){
        ArrayList <String> arr =new ArrayList<>();
        SQLiteDatabase db =getReadableDatabase();
        String str[]= new String[1];
        str[0] = userFullName;
        Cursor c= db.rawQuery("select * from orderPlace where userFullName =?",str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(0)+"$"+c.getString(1)
                        +"$"+c.getString(2)+"$"+c.getString(3)
                        +"$"+c.getString(4)+"$"+c.getString(5)
                        +"$"+c.getString(6)+"$"+c.getString(7)
                        +"$"+c.getString(8));
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public int checkBookAppointment(String userFullName, String fullName, String mobile, String address, String date, String time) {
        int result =0;
        String str[] =new String[6];
        str[0]=userFullName;
        str[1]=fullName;
        str[2]=mobile;
        str[3]=address;
        str[4]=date;
        str[5]=time;
        SQLiteDatabase db =getReadableDatabase();
        Cursor c =db.rawQuery("select * from orderPlace where  userFullName =?  and fullName =? and  mobile =? and address =? and  date =? and time =? " ,str);
        if (c.moveToFirst()){
            result  = 1;
        }
        c.close();
        return result;
    }

}
