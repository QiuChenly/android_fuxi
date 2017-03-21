package cn.xiaobaikejihui.qqr.smart_city;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.StandaloneActionMode;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import cn.xiaobaikejihui.qqr.smart_city.shujuku.shujuku;

public class register extends AppCompatActivity {
    private ImageView imageView;
    private EditText name;
    private EditText password;
    private EditText Confirm_password;
    private Button register_bt;
    private shujuku dbhelp;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SMSSDK.initSDK(this, "15ab13f8ad8ff", "76d73ccdb2988ef2e97073189a626d52");

        dbhelp=new shujuku(this,"Zhanghao.db",null,1);
        dbhelp.getWritableDatabase();
        
        
        findview();
        tubiaoxuanzhuang();
        
        register_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idname=name.getText().toString();
                String zc_password=password.getText().toString();
                String zc_password2=Confirm_password.getText().toString();
                zhuceyanzheng(idname,zc_password,zc_password2);
            }
        });
        
    }
    private void zhuceyanzheng(String zc_name,String zc_password,String zc_password2){
        if(zc_name!=null && zc_password!=null && zc_password2!=null){
            if(!zc_password.equals(zc_password2)){
                Toast.makeText(register.this,"两次填写密码不一样",Toast.LENGTH_SHORT).show();
                mimakejian();
            }
            else {
                //调用短信验证
                duanxinyanzheng(zc_name,zc_password);
            }
            
        }else {
            Toast.makeText(register.this,"请补全空缺处",Toast.LENGTH_SHORT).show();
        }
        
    }
    private void mimakejian(){
        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        Confirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }
    private void duanxinyanzheng(final String zc_name, final String zc_password){
       
        RegisterPage registerPage = new RegisterPage();
        //回调函数
        registerPage.setRegisterCallback(new EventHandler()
        {
            public void afterEvent(int event, int result, Object data)
            {
                // 解析结果
                if (result == SMSSDK.RESULT_COMPLETE)
                {
                    //提交验证码成功
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE)
                    {
                        Toast.makeText(register.this,"注册成功",Toast.LENGTH_SHORT).show();
                        
                        //在此处创建数据库数据
                        chuangjianshuju(zc_name,zc_password);
                        Intent intent=new Intent(register.this,MainActivity.class);
                        startActivity(intent);
                        
                    }
                    //提交验证码成功，此时已经验证成功了
                    else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE)
                        //在此处创建数据库数据
                        chuangjianshuju(zc_name,zc_password);
                    Intent intent=new Intent(register.this,MainActivity.class);
                    startActivity(intent);
                    {
                        
                    }
                }
            }
        });
        
        
    }
    private void chuangjianshuju(String zc_name,String zc_password){
        SQLiteDatabase db=dbhelp.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",zc_name);
        contentValues.put("mima",zc_password);
        db.insert("Zhanghao",null,contentValues);
        Toast.makeText(register.this,"注册成功",Toast.LENGTH_SHORT).show();
        
    }

    private void findview() {
        imageView= (ImageView) findViewById(R.id.touxing);
        name= (EditText) findViewById(R.id.idname);
        password= (EditText) findViewById(R.id.password);
        Confirm_password= (EditText) findViewById(R.id.Confirm_password);
        register_bt= (Button) findViewById(R.id.register_bt1);
    }

    private void tubiaoxuanzhuang(){
        Animation mAnimation = AnimationUtils.loadAnimation(this, R.anim.tubiaoxz);//图片旋转
        imageView.startAnimation(mAnimation);
    }
    
}
