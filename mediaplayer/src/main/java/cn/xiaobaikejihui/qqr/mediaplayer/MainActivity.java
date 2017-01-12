package cn.xiaobaikejihui.qqr.mediaplayer;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
     private MediaPlayer mediaPlayer=new MediaPlayer();
     boolean bf=false;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else
        {
            initMediaplayer();
        }
        */



        initMediaplayer();
        Button play1= (Button) findViewById(R.id.play);
        Button pause= (Button) findViewById(R.id.pause);
        Button stop= (Button) findViewById(R.id.stop);
        
        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mediaPlayer.isPlaying())
                {
                    mediaPlayer.start();//播放音频
                    bf=true;
                    Log.d("11111","play");
                    tv.setText("当前音乐加载所耗时间为"+mediaPlayer.getDuration());
                }
            }
        });
         
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();//暂停播放
                    Log.d("11111","pause");
                }
            }
        });
        
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(bf==true){
                   mediaPlayer.reset();//重新播放
                   initMediaplayer();
                   mediaPlayer.start();//播放音频
                   Log.d("11111","stop");
               }
                else
               {
                   Toast.makeText(MainActivity.this,"请先播放歌曲",Toast.LENGTH_SHORT).show();
               }
                    
                
            }
        });
        tv= (TextView) findViewById(R.id.textView);
        tv.setText("当前音乐加载所耗时间为"+mediaPlayer.getDuration());
        
        
        
        
    }

    private void initMediaplayer() {
        
        try {
            File file=new File(Environment.getExternalStorageDirectory(),"女儿情.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
            Log.d("进入到初始化状态","进入到初始化");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("进入到获取权限状态","进入到获取权限");
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initMediaplayer();
                }
                else 
                {
                    Toast.makeText(this,"拒绝权限无法访问",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
