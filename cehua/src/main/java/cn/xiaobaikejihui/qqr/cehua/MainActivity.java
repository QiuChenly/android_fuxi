package cn.xiaobaikejihui.qqr.cehua;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.LocaleDisplayNames;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private LinearLayout linearLayout;
    private String wendu;
    private String daima;
    private String tianqi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        linearLayout= (LinearLayout) findViewById(R.id.main_zuo);
        
        //Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        //侧滑drawerLayout
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        //侧滑菜单显示按钮
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.menu);
        }
        
        //处理侧滑菜单点击事件
        navigationView= (NavigationView) findViewById(R.id.nav_view);
        /*navigationView.setCheckedItem(R.id.c);//设置默认选择*/
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            //通过setNavigationItemSelectedListener来设置一个菜单点击监听器，任何点击都会回调onNavigationItemSelected方法
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.c:
                        Toast.makeText(MainActivity.this,"我的座驾",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.c);
                        Intent intentMyCar=new Intent(MainActivity.this,My_Car.class);
                        startActivity(intentMyCar);
                        //动画
                        overridePendingTransition(R.anim.no1,R.anim.no1_exit);
                        break;
                    case R.id.r:
                        Toast.makeText(MainActivity.this,"我的路况",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.r);
                        Intent intent5=new Intent(MainActivity.this,MY_lukuang.class);
                        startActivity(intent5);
                        break;
                    case R.id.p:
                        Toast.makeText(MainActivity.this,"停车查询",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.p);
                        Intent intent4=new Intent(MainActivity.this,Parking.class);
                        startActivity(intent4);
                        overridePendingTransition(R.anim.no2,R.anim.no2_exit);
                        break;
                    case R.id.b:
                        Toast.makeText(MainActivity.this,"公交查询",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MainActivity.this, Main3Activity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.no3,R.anim.no3_exit);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.b);
                        break;
                    case R.id.e:
                        Toast.makeText(MainActivity.this,"道路环境",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        navigationView.setCheckedItem(R.id.e);
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                        break;
                    }
                    
                return false;
            }
        });
        
        
    }
    //重写按钮方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
    
}
