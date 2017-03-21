package cn.xiaobaikejihui.qqr.smart_city;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.Random;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class music_play extends AppCompatActivity {
    private ImageView imageView;
    private ImageView imageView1;
    private Button play_bt;
    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        findview();
        tupianjiazai();
        
        
        play_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //i等于1的时候开始旋转
                if(i==1){
                    xuanzhaun();
                    play_bt.setBackgroundResource(R.mipmap.zanting);
                    i=2;
                }
                //i不等于1的时候停止旋转
                else {
                    xuanzhuanzanting();
                    play_bt.setBackgroundResource(R.mipmap.play);
                    i=1;
                }
                
            }
        });
      
    }
    
    //开始旋转
    private void xuanzhaun(){            
        Animation mAnimation = AnimationUtils.loadAnimation(this, R.anim.xz);//图片旋转
        imageView1.startAnimation(mAnimation);
    }

    //停止旋转
    private  void xuanzhuanzanting(){     
        imageView1.clearAnimation();
    }

    //生成毛玻璃背景和圆形图片
    private void xuhuan_xuanzhaun(int id) {
        Glide.with(this).load(id)            //毛玻璃
                .bitmapTransform(new BlurTransformation(this, 35), new CenterCrop(this))
                .into(imageView);

        Glide.with(this).load(id)             //圆形
                .bitmapTransform(new CropCircleTransformation(this))
                .into(imageView1);
        
    }

    //声明ID
    private void findview() {             
        imageView= (ImageView) findViewById(R.id.imageView);
        imageView1= (ImageView) findViewById(R.id.imageview2);
        play_bt= (Button) findViewById(R.id.play_bt);
    }

    //随机背景图片
    private void tupianjiazai() {      
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
        xuhuan_xuanzhaun(bjtp);
        
    }
}
