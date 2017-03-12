package cn.xiaobaikejihui.qqr.cehua;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static cn.xiaobaikejihui.qqr.cehua.R.mipmap.bus;

public class Main3Activity extends AppCompatActivity {
    private TextView zt_bt;
    private  TextView zt_jl;
    private TextView yihao_juli;
    private TextView erhao_juli;
    private TextView cz_hj;
    private Button zt_1;
    private Button zt_2;
    public String yihao="";
    public String erhao="";
    
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String fanhui= (String) msg.obj;
                    Toast.makeText(Main3Activity.this,fanhui,Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(Main3Activity.this,"天气未能更新成功",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final String zt1="{\"BusStationId\":1}";
        final String zt2="{\"BusStationId\":2}";
        
        
        zt_bt= (TextView) findViewById(R.id.zt_bt);
        zt_jl= (TextView) findViewById(R.id.zt_jl);
        yihao_juli= (TextView) findViewById(R.id.yihao_juli);
        erhao_juli= (TextView) findViewById(R.id.erhao_juli);
        cz_hj= (TextView) findViewById(R.id.cz_hj);
        zt_1= (Button) findViewById(R.id.zt_1);
        zt_2= (Button) findViewById(R.id.zt_2);
        
        zt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daolupost(zt1);
                getdaolutianqi();
            }
        });
        zt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daolupost(zt2); 
                getdaolutianqi();
            }
        });

        getdaolutianqi();
        daolupost(zt1);
        
    }
    
    
    private void daolupost(final String zhantai){
        if(zhantai.equals("{\"BusStationId\":1}")){
            zt_bt.setText("一号站台");
            zt_jl.setText("距离一号站台");
        }
            
        else {
            zt_bt.setText("二号站台");
            zt_jl.setText("距离二号站台");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader;
                HttpURLConnection conn = null;
                try {
                    URL url = new URL("http://192.168.1.109:8080/transportservice/type/jason/action/GetBusStationInfo.do");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);   //需要输入
                    conn.setConnectTimeout(8000); // 连接的超时时间  
                    conn.setReadTimeout(5000); // 读数据的超时时间  
                    conn.setDoOutput(true); // 必须设置此方法, 允许输出****** 默认情况下, 系统不允许向服务器输出内容
                    conn.setRequestProperty("Content-Type", "application/json;");//文本信息

                    // post请求的参数  
                    String data = zhantai;
                    Log.d("002",zhantai);
                    // 获得一个输出流, 用于向服务器写数据, 默认情况下, 系统不允许向服务器输出内容  
                    OutputStream out = conn.getOutputStream();
                    out.write(data.getBytes());
                    out.flush();
                    out.close();

                    InputStream is = conn.getInputStream();
                    
                    reader=new BufferedReader(new InputStreamReader(is));
                    StringBuffer buffer=new StringBuffer();
                    String line;
                    if(is!=null){
                        while ((line=reader.readLine())!=null){
                            buffer.append(line);
                        }
                    }
                    Log.d("读取到的信息",buffer.toString());
                    conn.connect();
                    jiexizhantai(buffer.toString());
                    
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(conn != null) {
                        conn.disconnect();
                    }
                }
               
            }
        }).start();
    

    }
    
    public void  jiexizhantai (String zhantai_jx){
        
        Log.d("测试",zhantai_jx);
        if(zhantai_jx!=null){
            try {
                String jsonArray1=(new JSONObject(zhantai_jx)).getString("serverinfo");
                JSONArray res=new JSONArray(jsonArray1);
                Log.d("测试",zhantai_jx);
                
                for(int i = 0; i < res.length(); i++) {
                    JSONObject jsonObj = res.getJSONObject(i);
                    final String Distance=jsonObj.getString("Distance");
                    final String bus=jsonObj.getString("BusId");
                    Log.d("Distance",jsonObj.getString("Distance"));
                    Log.d("BusId",jsonObj.getString("BusId"));
                    Log.d("循环次数", String.valueOf(i));
                    Log.d("总循环次数", String.valueOf(res.length()));
                    if (i==0){
                        yihao=""+bus+"号公交距离站台"+Distance+"米";
                        Log.d("yihao",yihao);
                        yihao_juli.post(new Runnable() {
                            @Override
                            public void run() {
                                yihao_juli.setText(yihao);
                            }
                        });
                    }
                    if (i==1){
                        erhao=""+bus+"号公交距离站台"+Distance+"米";
                        Log.d("erhao",erhao);
                        erhao_juli.post(new Runnable() {
                            @Override
                            public void run() {
                                erhao_juli.setText(erhao);
                            }
                        });
                    }
                }
                } catch (JSONException e) {
                handler.sendEmptyMessage(0);
                Message message=new Message();
                message.obj="未能成功获取数据";
                handler.sendMessage(message);
            }
        }
        
    }
    
    //天气
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
                    jiexi(buffer.toString());
                    httpURLConnection.connect();
                    



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
                Log.d("获取天气",jsonliu);
                JSONObject jsonObject=new JSONObject(jsonliu);
                String serverinfo = jsonObject.getString("serverinfo");
                final JSONObject jsonObject1=new JSONObject(serverinfo);
                final int pm=jsonObject1.getInt("pm2.5");
                final String wendu=jsonObject1.getString("humidity");
                final String shidu=jsonObject1.getString("temperature");
                final int co2=jsonObject1.getInt("co2");
                cz_hj.post(new Runnable() {
                    @Override
                    public void run() {
                        cz_hj.setText("pm2.5:"+pm+"   温度:"+wendu+"   湿度:"+shidu+"   CO2:"+co2);
                        Log.d("显示天气","2333333");
                    }
                });
            } catch (JSONException e) {
                handler.sendEmptyMessage(1);
            }
        }
    }

    
}
