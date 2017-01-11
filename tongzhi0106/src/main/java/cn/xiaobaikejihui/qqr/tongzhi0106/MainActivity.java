package cn.xiaobaikejihui.qqr.tongzhi0106;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(this,NotificationActivity.class);
        final PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);//通过PendingIntent设置响应事件

        Button bt1= (Button) findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification= new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("这是一个通知标题")//设置标题
                        .setContentText("这是一个通知内容")//设置通知内容
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)//设置通知的小图标
                        .setContentIntent(pi)//设置按钮点击事件
                        .setAutoCancel(true)//设置通知消失事件
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))//设置声音
                        .setVibrate(new long[]{0,1000})//设置震动一秒
                        .setLights(Color.GREEN,1000,1000)//设置led灯
                       // .setDefaults(NotificationCompat.DEFAULT_ALL) //设置直接使用默认效果
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置通知的大图标
                        .build();
                manager.notify(1,notification);
            }
        });
        
        
        Button bt2= (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("对话框");
                builder.setMessage("test");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();
                        
                    }
                });
                builder.show();
            }
        });
        
        Button bt3= (Button) findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification= new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("这是一个长内容通知标题")//设置标题
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)//设置通知的小图标
                        .setContentIntent(pi)//设置按钮点击事件
                        .setAutoCancel(true)//设置通知消失事件
                        .setLights(Color.GREEN,1000,1000)//设置led灯
                        .setDefaults(NotificationCompat.DEFAULT_ALL) //设置直接使用默认效果
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置通知的大图标
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("这是一个长内容通知,这是一个长内容通知,这是一个长内容通知,这是一个长内容通知,这是一个长内容通知,这是一个长内容通知,"))
                        .build();
                manager.notify(2,notification);
                
            }
        });
           Button bt4= (Button) findViewById(R.id.button4);
           bt4.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                   Notification notification= new NotificationCompat.Builder(MainActivity.this)
                           .setContentTitle("这是一个图片通知标题")//设置标题
                           .setWhen(System.currentTimeMillis())
                           .setSmallIcon(R.mipmap.ic_launcher)//设置通知的小图标
                           .setContentIntent(pi)//设置按钮点击事件
                           .setAutoCancel(true)//设置通知消失事件
                           .setDefaults(NotificationCompat.DEFAULT_ALL) //设置直接使用默认效果
                           .setLights(Color.GREEN,1000,1000)//设置led灯
                           .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置通知的大图标
                           .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.beijing1)))//设置通知内容的图片
                           .build();
                   manager.notify(3,notification);
                   
               }
           });
        
        Button bt5= (Button) findViewById(R.id.button5);
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification= new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("这是一个最高等级通知")//设置标题
                        .setContentText("too young too simple")//设置通知内容
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)//设置通知的小图标
                        .setContentIntent(pi)//设置按钮点击事件
                        .setAutoCancel(true)//设置通知消失事件
                        .setDefaults(NotificationCompat.DEFAULT_ALL) //设置直接使用默认效果
                        .setLights(Color.GREEN,1000,1000)//设置led灯
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置通知的大图标
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.zhangzhe)))//设置通知内容的图片
                        .setPriority(NotificationCompat.PRIORITY_MAX)//设置通知等级为最高
                        .build();
                manager.notify(4,notification);
                
            }
        });
        
        
        
    }
}
