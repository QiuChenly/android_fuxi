package cn.xiaobaikejihui.qqr.bisai_lianxi;

import android.app.AlertDialog;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView xianshi;
    private SwipeRefreshLayout swipe;
    private VideoView video;
    private Button bofang;
    private Button yingcang;
    private  Button duihuakuang;
    private  Button tongzhi;
    private  Button tiaozhuan;
    private int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intui();
        shuaxin();
        
        
        //跳转动画
        tiaozhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.no1,R.anim.no1_ed);
            }
        });
        
        //对话框
        duihuakuang.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                String format = simpleDateFormat.format(new Date());

                AlertDialog dialog=new AlertDialog.Builder(MainActivity.this)
                       .setTitle("标题")
                       .setMessage(format)
                       .setIcon(R.mipmap.ic_launcher)
                       .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               Toast.makeText(MainActivity.this,"点击了确定",Toast.LENGTH_SHORT).show();
                           }
                       })
                       .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               Toast.makeText(MainActivity.this,"点击了取消",Toast.LENGTH_SHORT).show();
                           }
                       })
                       .setNeutralButton("中立", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               Toast.makeText(MainActivity.this,"点击了中立",Toast.LENGTH_SHORT).show();
                           }
                       })
                       .create();

                Window window = dialog.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.alpha=0.5f;
                window.setAttributes(attributes);
                
                dialog.show();

            }
        });
        
        //显示通知
        tongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification= new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("这是一个标题")
                        .setContentText("这是一个内容")
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .build();
                notificationManager.notify(1,notification);
                
            }
        });
        
        //隐藏控件
        yingcang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.setVisibility(View.GONE);
            }
        });
        
        
        //下拉刷新
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuaxin();
            }
        });
        
        //播放按钮
        bofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示控件
                video.setVisibility(View.VISIBLE);
                video_start();
            }
        });
        
        
        
    }
    
    //视频播放
    private void video_start() {
        //播放res内raw的视频文件
        video.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.cs));
        video.start();
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                
                //循环播放
                mp.start();                     
            }
        });
        
    }


    //下拉刷新事件
    private void shuaxin() {
        xianshi.setText("这是第"+i+"次刷新");
        i++;
        swipe.setRefreshing(false);
        
    }

    private void intui() {
         xianshi= (TextView) findViewById(R.id.shuaxingzhi);
         swipe= (SwipeRefreshLayout) findViewById(R.id.sxkongjian);
        video= (VideoView) findViewById(R.id.videoView2);
        bofang= (Button) findViewById(R.id.bofangshiping);
        yingcang= (Button) findViewById(R.id.yincang);
        duihuakuang= (Button) findViewById(R.id.duihuakuang);
        tongzhi= (Button) findViewById(R.id.tongzhi);
        tiaozhuan= (Button) findViewById(R.id.tiaozhuan);
    }
}
