package cn.xiaobaikejihui.qqr.smart_city;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.Random;

import cn.xiaobaikejihui.qqr.smart_city.shujuku.shujuku;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity {
    private EditText idname;
    private EditText password;
    private ImageView beijing;
    private Button find_password_bt;
    private Button register_bt;
    private Button Sign_in_bt;
    private ImageView tubiao;
    private shujuku dbhelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);
        dbhelp=new shujuku(this,"Zhanghao.db",null,1);
        dbhelp.getWritableDatabase();

        
        
        
        findviewid();
        tubiaoxuanzhuang();
        beijingjiazai();
         

        Sign_in_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ne=idname.getText().toString();
                String mm=password.getText().toString();
                denglupanduan(ne,mm);
                Log.d(ne,mm);
            }
        });

        register_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,register.class);
                startActivity(intent);
            }
        });
    }
    private void denglupanduan(String dlname,String dlmima){
        SQLiteDatabase db=dbhelp.getWritableDatabase();
        Cursor cur=db.query("Zhanghao",null, null, null,null, null, null);
        if(cur.moveToFirst()){
            do{
                String name1=cur.getString(cur.getColumnIndex("mima"));
                String password1=cur.getString(cur.getColumnIndex("mima"));
                Log.d("name1",name1);
                Log.d("mima1",password1);
                if((name1.equals(dlname))&& (password1.equals(dlmima))){
                    Intent intent=new Intent(MainActivity.this,music_play.class);
                    startActivity(intent);
                }else if ((name1.equals(dlname))&& (!password1.equals(dlmima)))
                {
                    Toast.makeText(MainActivity.this,"账户名或密码错误",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"请注册",Toast.LENGTH_SHORT).show();
                }
                
            }while (cur.moveToNext());
        }
        cur.close();
        
    }

    

    //图标旋转
    private void tubiaoxuanzhuang(){
        Animation mAnimation = AnimationUtils.loadAnimation(this, R.anim.tubiaoxz);//图片旋转
        tubiao.startAnimation(mAnimation);
    }
    
    //随机背景图片
    private void beijingjiazai() {
        int number = new Random().nextInt(4) + 1;
        int bjtp = 0;
        switch (number){
            case 1:
                bjtp=R.mipmap.beijing4;
                break;
            case 2:
                bjtp=R.mipmap.beijingwu;
                break;
            case 3:
                bjtp=R.mipmap.beijingliu;
                break;
            case 4:
                bjtp=R.mipmap.beijingqi;
                break;
        }
        edtext(bjtp,bjtp);
    }

    //此方法用于生成毛玻璃效果图片
    private void maoboli(int id){           
            Glide.with(this).load(id)
                    .bitmapTransform(new BlurTransformation(this, 35), new CenterCrop(this))
                    .into(beijing);
        
    }
    
    //此方法用于监听当前焦点，实现点击username或mima切换不同清晰度图片
    private void edtext(final int edid, final int mblid) {     
        idname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    beijing.setImageResource(edid);
                    Log.d("name得到焦点","清晰图片");
                } 
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    maoboli(mblid);
                    Log.d("mima得到焦点","毛玻璃图片");
                } 
            }
        });
    }

    //声明
    private void findviewid() {
        idname= (EditText) findViewById(R.id.idname);
        password= (EditText) findViewById(R.id.password);
        beijing= (ImageView) findViewById(R.id.beijing);
        register_bt= (Button) findViewById(R.id.register_bt);
        find_password_bt= (Button) findViewById(R.id.find_password_bt);
        tubiao= (ImageView) findViewById(R.id.tubiao);
        Sign_in_bt= (Button) findViewById(R.id.Sign_in_bt);
    }

    
}
