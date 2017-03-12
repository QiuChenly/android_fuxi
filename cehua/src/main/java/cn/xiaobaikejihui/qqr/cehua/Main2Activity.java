package cn.xiaobaikejihui.qqr.cehua;

import android.app.Activity;
import android.media.session.MediaController;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static cn.xiaobaikejihui.qqr.cehua.R.id.imageView;


public class Main2Activity extends Activity {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private Button bt_cx1;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv1= (TextView) findViewById(R.id.tv_1);
        tv2= (TextView) findViewById(R.id.tv_2);
        tv3= (TextView) findViewById(R.id.tv_3);
        tv4= (TextView) findViewById(R.id.tv_4);
        tv5= (TextView) findViewById(R.id.tv_5);
        tv6= (TextView) findViewById(R.id.tv_6);
        tv7= (TextView) findViewById(R.id.tv_7);
        bt_cx1= (Button) findViewById(R.id.bt_cx1);
        
        getdaolutianqi();
        videoView= (VideoView) findViewById(R.id.videoView2);
        
        bt_cx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdaolutianqi();
            }
        });
        
    }

    public void getdaolutianqi(){
        new Thread(new Runnable() {
            BufferedReader reader;
            @Override
            public void run() {
                try {
                    URL url=new URL("http://192.168.1.109:8080/transportservice/type/jason/action/GetAllSense.do");
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    InputStream in=httpURLConnection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(in));
                    StringBuffer buffer=new StringBuffer();
                    String line;
                    if(in!=null){
                        while ((line=reader.readLine())!=null){
                            buffer.append(line);
                        }
                    }
                    httpURLConnection.connect();
                    jiexi(buffer.toString());
                   
                    

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    public void jiexi(String jsonliu){
        if(jsonliu!=null){
            try {
                JSONObject jsonObject=new JSONObject(jsonliu);
                String serverinfo = jsonObject.getString("serverinfo");
                final JSONObject jsonObject1=new JSONObject(serverinfo);
                final int pm=jsonObject1.getInt("pm2.5");
                final String wendu=jsonObject1.getString("humidity");
                final String shidu=jsonObject1.getString("temperature");
                final int liangdu=jsonObject1.getInt("LightIntensity");
                final int co2=jsonObject1.getInt("co2");
                tv1.post(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText("温度"+wendu+"℃"+"   "+"pm2.5:"+pm+"ug/m");
                    }
                });
                tv2.post(new Runnable() {
                    @Override
                    public void run() {
                        tv2.setText("湿度"+shidu+"%"+"   "+"co2:"+co2+"ug/m");
                    }
                });
                tv6.post(new Runnable() {
                    @Override
                    public void run() {
                        tv6.setText("光照强度"+liangdu+"lux");
                    }
                });
                if(liangdu>2500){
                    tv7.post(new Runnable() {
                        @Override
                        public void run() {
                            tv7.setText("光照强度过大");
                        }
                    });
                }
                if(liangdu<2500){
                    tv7.post(new Runnable() {
                        @Override
                        public void run() {
                            tv7.setText("光照强度正常");
                        }
                    });
                }
                if(pm<100){
                    videoView.post(new Runnable(){

                        @Override
                        public void run() {
                            Uri uri = Uri.parse("http://ws.acgvideo.com/9/96/1218577-1.mp4?wsTime=1487242541&platform=pc&wsSecret2=337da7e6f81928de67ff988f84906750&oi=22206599&rate=60");
                            videoView.setVideoURI(uri);
                            videoView.start();
                            
                        }

                    });
                    tv3.post(new Runnable() {
                        @Override
                        public void run() {
                            tv3.setText("PM2.5在正常范围内");
                        }
                    });
                }
                if(pm>100){
                    videoView.post(new Runnable(){

                        @Override
                        public void run() {
                            Uri uri = Uri.parse("http://ws.acgvideo.com/b/7d/821876-1.mp4?wsTime=1487242644&platform=pc&wsSecret2=a60c05f3e2fba9626123dd18d4612f6b&oi=1885046643&rate=60");
                            videoView.setVideoURI(uri);
                            videoView.start();
                            
                        }

                    });
                    tv3.post(new Runnable() {
                        @Override
                        public void run() {
                            tv3.setText("PM2.5浓度太高了");
                        }
                    });
                }
                if(co2>6000){
                    tv4.post(new Runnable() {
                        @Override
                        public void run() {
                            tv4.setText("CO2浓度过高");
                        }
                    });
                    
                }
                if(co2<6000){
                    tv4.post(new Runnable() {
                        @Override
                        public void run() {
                            tv4.setText("CO2浓度正常");
                        }
                    });

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    
                
                
                
               
    
}
