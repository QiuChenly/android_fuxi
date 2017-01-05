package cn.xiaobaikejihui.qqr.sharedpreferences_0105;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_tijian= (Button) findViewById(R.id.button);
        bt_tijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();//调用SharedPreferences对象的edit方法获取到SharedPreferences.Editor的对象
                editor.putString("name","qqr");
                editor.putString("mima","123456");
                editor.putInt("mima2",1223456);
                editor.apply();//调用apply方法将添加的数据提交
                Toast.makeText(MainActivity.this,"数据添加成功",Toast.LENGTH_SHORT).show();
            }
        });
        
        Button bt_duqu= (Button) findViewById(R.id.button2);
        bt_duqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences duqu=getSharedPreferences("data",MODE_PRIVATE);
                String name=duqu.getString("name","");
                String mima=duqu.getString("mima","");
                int mima2=duqu.getInt("mima2",0);
                Toast.makeText(MainActivity.this,"name="+name+"mima="+mima+"mima2="+mima2,Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}
