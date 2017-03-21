package cn.xiaobaikejihui.qqr.smart_city.shujuku;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by qqr on 2017/3/4.
 */



public class shujuku  extends SQLiteOpenHelper{
    public static final String CREATE_ID="create table Zhanghao("
            +"name text primary key,"
            +"mima text)";
    
    
    private Context mcontext;
    public shujuku(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ID);
        Toast.makeText(mcontext,"创建成功",Toast.LENGTH_SHORT).show();
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

   
}
