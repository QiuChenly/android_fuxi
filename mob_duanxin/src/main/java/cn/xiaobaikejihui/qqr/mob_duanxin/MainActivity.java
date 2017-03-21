package cn.xiaobaikejihui.qqr.mob_duanxin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity {
    private Button zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SMSSDK.initSDK(this, "15ab13f8ad8ff", "76d73ccdb2988ef2e97073189a626d52");
        zhuce= (Button) findViewById(R.id.zhuce);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                Toast.makeText(MainActivity.this,"注册成功1",Toast.LENGTH_SHORT).show();
                            }
                            //提交验证码成功，此时已经验证成功了
                            else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE)
                            {
                                Toast.makeText(MainActivity.this,"注册成功2",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                registerPage.show(MainActivity.this);
                Toast.makeText(MainActivity.this,"注册成功3",Toast.LENGTH_SHORT).show();
            }
        });
           
       
    }
}
