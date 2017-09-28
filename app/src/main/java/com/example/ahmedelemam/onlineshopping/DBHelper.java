package com.example.ahmedelemam.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AHMED ELEMAM on 04/12/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    static String DBName="OnlineShoppingDB";
    private SQLiteDatabase OS_DB;

    String customers="create table Customers(CustID integer primary key autoincrement,UserName text ,Password text ,Gender text ,Birthdate text ,Security text );";

    String orders="create table Orders(OrdID integer primary key autoincrement,OrdDate text ,Address text, CustID integer);";

    String OrderDetails="create table OrderDetails(OrdID integer not null,CatID integer not null,Quantity integer not null,primary key(OrdID,CatID));";

    String categories="create table Categories(CatID integer primary key autoincrement,CatName text);";

    String products="create table Products(ProID integer primary key autoincrement,ProName text,Price text,Quantity text,CatID integer);";


    //-----------------------------------------------------------------------------------------------------------------
    public DBHelper(Context context) {
        super(context, DBName, null, 10);
    }
//-----------------------------------------------------------------------------------------------------------------
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(customers);
        db.execSQL(orders);
        db.execSQL(OrderDetails);
        db.execSQL(categories);
        db.execSQL(products);



    }
//-----------------------------------------------------------------------------------------------------------------
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists customers");
        db.execSQL("drop table if exists orders");
        db.execSQL("drop table if exists OrderDetails");
        db.execSQL("drop table if exists categories");
        db.execSQL("drop table if exists products");
        onCreate(db);


    }
//-----------------------------------------------------------------------------------------------------------------
    public void create_new_customer(String u,String p,String g,String b,String s)
    {
        ContentValues row=new ContentValues();
        OS_DB=this.getWritableDatabase();

        row.put("UserName",u);
        row.put("Password",p);
        row.put("Gender",g);
        row.put("Birthdate",b);
        row.put("Security",s);

        OS_DB.insert("Customers",null,row);
        OS_DB.close();
    }
//-----------------------------------------------------------------------------------------------------------------
    public void create_new_order(String d,String a,int c)
    {
        ContentValues row=new ContentValues();
        OS_DB=this.getWritableDatabase();

        row.put("OrdDate",d);
        row.put("Address",a);
        row.put("CustID",c);

        OS_DB.insert("Orders",null,row);
        OS_DB.close();
    }
//-----------------------------------------------------------------------------------------------------------------
    public int get_cust_id(String name)
    {
        OS_DB=this.getReadableDatabase();
        String[] arg={name};
        Cursor cursor=OS_DB.rawQuery("Select CustID from Customers where UserName like ?",arg);
        cursor.moveToFirst();
        OS_DB.close();
        return cursor.getInt(0);
    }

//-----------------------------------------------------------------------------------------------------------------
public String check_password(String name)
{
    OS_DB=this.getReadableDatabase();
    String[] arg={name};
    Cursor cursor=OS_DB.rawQuery("Select Password from Customers where UserName like ?",arg);
    cursor.moveToFirst();
    OS_DB.close();
    return cursor.getString(0);
}
//-----------------------------------------------------------------------------------------------------------------
    public void create_new_categorie()
    {
        ContentValues row=new ContentValues();
        OS_DB=this.getWritableDatabase();
        row.put("CatID","1");
        row.put("CatName","Mobiles");
        OS_DB.insert("Categories",null,row);

        row=new ContentValues();

        row.put("CatID","2");
        row.put("CatName","Laptops");
        OS_DB.insert("Categories",null,row);


        OS_DB.close();
    }
//-----------------------------------------------------------------------------------------------------------------

public void create_new_product()
{
    ContentValues row=new ContentValues();
    OS_DB=this.getWritableDatabase();
    row.put("ProID","1");
    row.put("ProName","Samsung Galaxy S3");
    row.put("Price","100");
    row.put("Quantity","10");
    //row.put("CatID","1");
    OS_DB.insert("Products",null,row);

    row=new ContentValues();

    row.put("ProID","2");
    row.put("ProName","Iphone S6");
    row.put("Price","500");
    row.put("Quantity","5");
    //row.put("CatID","1");
    OS_DB.insert("Products",null,row);


    row=new ContentValues();

    row.put("ProID","3");
    row.put("ProName","Hp");
    row.put("Price","3000");
    row.put("Quantity","10");
    //row.put("CatID","2");
    OS_DB.insert("Products",null,row);

    row=new ContentValues();

    row.put("ProID","4");
    row.put("ProName","Dell");
    row.put("Price","5000");
    row.put("Quantity","5");
    //row.put("CatID","2");
    OS_DB.insert("Products",null,row);

    OS_DB.close();
}
//-----------------------------------------------------------------------------------------------------------------
    public int get_pro_id(String name)
    {
    OS_DB=this.getReadableDatabase();
    String[] arg={name};
    Cursor cursor=OS_DB.rawQuery("Select ProID from Products where ProName like ?",arg);
    cursor.moveToFirst();
    OS_DB.close();
    return cursor.getInt(0);
    }

    //**********************************************************************************************
    public Cursor get_pro_data(int id)
    {
        OS_DB=this.getReadableDatabase();
        String[] arg={((String.valueOf(id))) };
        Cursor cursor=OS_DB.rawQuery("Select Price,Quantity from Products where ProID like ?",arg);
        cursor.moveToFirst();
        OS_DB.close();
        return cursor;
    }

//-----------------------------------------------------------------------------------------------------------------
public String return_password(String s)
{
    OS_DB = this.getReadableDatabase();
    String[] arg = {s};
    Cursor cursor = OS_DB.rawQuery("Select Password from Customers where Security like ?", arg);
    cursor.moveToFirst();
    OS_DB.close();
    return cursor.getString(0);
}
//-----------------------------------------------------------------------------------------------------------------
    public Cursor search_pro (String n)
    {
        OS_DB = getReadableDatabase();
        String[] arg={"%"+n+"%"};
        Cursor cursor = OS_DB.rawQuery("Select ProName,Price from Products where ProName like ?",arg);
        cursor.moveToFirst();
        OS_DB.close();
        return cursor;
    }
//-----------------------------------------------------------------------------------------------------------------

public String get_pro_price (String n)
{
    OS_DB = getReadableDatabase();
    String[] arg={n};
    Cursor cursor = OS_DB.rawQuery("Select Price from Products where ProName like ?",arg);
    cursor.moveToFirst();
    OS_DB.close();
    return cursor.getString(0);
}
//-----------------------------------------------------------------------------------------------------------------
}

