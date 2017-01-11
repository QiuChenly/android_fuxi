package cn.xiaobaikejihui.qqr.rumtimepermissiontest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button makeCall= (Button) findViewById(R.id.button);
        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                try {
                    Intent intent=new Intent(Intent.ACTION_CALL);//拨打电话
                    intent.setData(Uri.parse("tel:10086"));//字符串解析为url传入intent
                    startActivity(intent);
                }catch (SecurityException e){
                    e.printStackTrace();
                }
                
            }              
        });
    }
}
