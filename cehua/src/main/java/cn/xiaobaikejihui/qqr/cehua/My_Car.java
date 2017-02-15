package cn.xiaobaikejihui.qqr.cehua;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class My_Car extends AppCompatActivity {
    private  Spinner spinner1;
    private  Spinner spinner2;
    private TextView Car_money;
    private EditText czje_Car;
    private Button bt_cx;
    private  Button bt_cz;
    private String chaxun_st;
    private  String chongzhi_st;
    
    Handler handler=new  Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Toast.makeText(My_Car.this,"充值成功",Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(My_Car.this,"充值失败",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(My_Car.this,"未能获取到信息",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__car);
        spinner1= (Spinner) findViewById(R.id.spinner);
        spinner2= (Spinner) findViewById(R.id.spinner2);
        Car_money= (TextView) findViewById(R.id.Car_money);
        czje_Car= (EditText) findViewById(R.id.czje);
        bt_cx= (Button) findViewById(R.id.button_cx);
        bt_cz= (Button) findViewById(R.id.button_cz);
        
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        chaxun_st="{\"CarId\":1}";
                        break;
                    case 1:
                        chaxun_st="{\"CarId\":2}";
                        break;
                    case 2:
                        chaxun_st="{\"CarId\":3}";
                        break;
                    case 3:
                        chaxun_st="{\"CarId\":4}";
                        break;
                }
            }
            

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        chongzhi_st = "{\"CarId\":1,";
                        break;
                    case 1:
                        chongzhi_st = "{\"CarId\":2,";
                        break;
                    case 2:
                        chongzhi_st = "{\"CarId\":3,";
                        break;
                    case 3:
                        chongzhi_st = "{\"CarId\":4,";
                        break;
                }
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
           
        
        bt_cx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("log", chaxun_st);
                Log.d("log", chongzhi_st);
               
                chaxun_money(chaxun_st);
                
                
            }
        });
        
        bt_cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chongzhi_money(chongzhi_st);
            }
        });
    }
    private void chaxun_money(final String chaxunliu){
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader;
                HttpURLConnection conn = null;
                try {
                    URL url = new URL("http://192.168.1.119:8080/transportservice/type/jason/action/GetCarAccountBalance.do");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);   //需要输入
                    conn.setConnectTimeout(8000); // 连接的超时时间  
                    conn.setReadTimeout(5000); // 读数据的超时时间  
                    conn.setDoOutput(true); // 必须设置此方法, 允许输出****** 默认情况下, 系统不允许向服务器输出内容
                    conn.setRequestProperty("Content-Type", "application/json;");//文本信息

                    // post请求的参数  
                    String data = chaxunliu;
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
                    chaxun_money_jx(buffer.toString());

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
    private void chaxun_money_jx(String chaxun_money_jx){
        if(chaxun_money_jx!=null){
            try {
                JSONObject jsonObject=new JSONObject(chaxun_money_jx);
                String res=jsonObject.getString("serverinfo");
                JSONObject jsonObject1=new JSONObject(res);
                final String Balance=jsonObject1.getString("Balance");
                Car_money.post(new Runnable() {
                    @Override
                    public void run() {
                        Car_money.setText("余额："+Balance+"元");
                    }
                });
            } catch (JSONException e) {
                //e.printStackTrace();
                handler.sendEmptyMessage(2);
                
            }
        }
    }

    private void chongzhi_money(final String chongzhi_money){
        final String ed_huoqu=czje_Car.getText().toString();
        Log.d("获取到的数据",ed_huoqu);
        if(ed_huoqu!=null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    BufferedReader reader;
                    HttpURLConnection conn = null;
                    try {
                        URL url = new URL("http://192.168.1.119:8080/transportservice/type/jason/action/SetCarAccountRecharge.do");
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setDoInput(true);   //需要输入
                        conn.setConnectTimeout(8000); // 连接的超时时间  
                        conn.setReadTimeout(5000); // 读数据的超时时间  
                        conn.setDoOutput(true); // 必须设置此方法, 允许输出****** 默认情况下, 系统不允许向服务器输出内容
                        conn.setRequestProperty("Content-Type", "application/json;");//文本信息

                        // post请求的参数  
                        String data = chongzhi_money+"\"Money\":"+ed_huoqu+"}";
                        Log.d("拼接后",data);
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
                        chongzhi_money_jx(buffer.toString());

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
        
    }
    
    private void  chongzhi_money_jx(String chongzhi_money_liu){
        if(chongzhi_money_liu!=null){
            Log.d("chongzhi_money_liu",chongzhi_money_liu);

            try {
                JSONObject jsonObject=new JSONObject(chongzhi_money_liu);
                String chongzhi=jsonObject.getString("serverinfo");
                JSONObject jsonObject2=new JSONObject(chongzhi);
                String jieguo=jsonObject2.getString("result");
                if(jieguo.equals("ok")){
                    handler.sendEmptyMessage(0);
                }
                else {
                    handler.sendEmptyMessage(1);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
