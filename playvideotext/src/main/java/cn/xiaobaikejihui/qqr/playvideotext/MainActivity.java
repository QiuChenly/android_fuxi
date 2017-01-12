package cn.xiaobaikejihui.qqr.playvideotext;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
private VideoView videoView;
    boolean bf=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        videoView= (VideoView) findViewById(R.id.videoView2);
        
        initVideoView();//加载视频方法

        Button bt1= (Button) findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!videoView.isPlaying()){
                    bf=true;
                    videoView.start();//开始播放
                }
            }
        });
        
        Button bt2= (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoView.isPlaying())
                {
                    videoView.pause();//暂停播放
                }
            }
        });
        
        Button bt3= (Button) findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(bf==true){
                   videoView.resume();//将视频从头播放
                   videoView.start();
               }
                else 
               {
                   Toast.makeText(MainActivity.this,"请先播放视频",Toast.LENGTH_SHORT).show();
               }
                   
                
            }
        });
        
        
    }

    private void initVideoView() {
        File file=new File(Environment.getExternalStorageDirectory(),"ceshishipin.mp4");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();//返回键清空资源
        Log.d("清空资源","清空资源");
        if(videoView!=null){
            videoView.suspend();//清空资源
        }
    }
}
