package cn.xiaobaikejihui.qqr.cehua;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Parking extends AppCompatActivity {
    private Button money_sz;
    private Button tccx;
    private EditText tcfy;          
    private ImageView im1;
    private ImageView im2;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String fanhuiliu = (String) msg.obj;
                    Toast.makeText(Parking.this, fanhuiliu, Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    String fanhuiliu1 = (String) msg.obj;
                    Toast.makeText(Parking.this, fanhuiliu1, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        money_sz = (Button) findViewById(R.id.bt_sz);
        tccx = (Button) findViewById(R.id.bt_tccx);
        tcfy = (EditText) findViewById(R.id.tingche_money);
        im1 = (ImageView) findViewById(R.id.imageView2);
        im2 = (ImageView) findViewById(R.id.imageView3);
        tccx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parkingmoney();
            }
        });
        money_sz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(tcfy.getText().toString());
                if (0 <i && i <= 99) {
                    parkingmoney_sz(i);
                } else {
                    Toast.makeText(Parking.this, "请输入合法数字", Toast.LENGTH_SHORT).show();
                    tcfy.setText("");
                }
            }
        });
    }

    private void parkingmoney() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader;
                try {
                    URL url = new URL("http://192.168.1.109:8080/transportservice/type/jason/action/GetParkFree.do");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(8000); // 连接的超时时间  
                    httpURLConnection.setReadTimeout(5000); // 读数据的超时时间
                    httpURLConnection.setRequestProperty("Content-Type", "application/json;");//文本信息

                    InputStream is = httpURLConnection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(is));
                    StringBuffer buffer = new StringBuffer();
                    String line;
                    if (is != null) {
                        while ((line = reader.readLine()) != null) {
                            buffer.append(line);
                        }
                    }
                    Log.d("读取到的信息", buffer.toString());
                    httpURLConnection.connect();

                    parkingmoney_jx(buffer.toString());


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void parkingmoney_jx(String parkingmoney_liu) {
        if (parkingmoney_liu != null) {
            try {
                JSONObject jsonObject = new JSONObject(parkingmoney_liu);
                String liu = jsonObject.getString("serverinfo");
                JSONArray jsonArray = new JSONArray(liu);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    if (i == 0) {
                        int chewei1 = jsonObject1.getInt("ParkFreeId");
                        if (chewei1 == 1) {
                            im1.post(new Runnable() {
                                @Override
                                public void run() {
                                    im1.setImageResource(R.mipmap.bus);
                                }
                            });

                        } else {
                            im1.post(new Runnable() {
                                @Override
                                public void run() {
                                    im1.setImageResource(R.mipmap.ic_launcher);
                                }
                            });
                        }
                    }
                    if (i == 1) {
                        int chewei2 = jsonObject1.getInt("ParkFreeId");
                        if (chewei2 == 1) {
                            im2.post(new Runnable() {
                                @Override
                                public void run() {
                                    im2.setImageResource(R.mipmap.bus);
                                }
                            });

                        } else {
                            im2.post(new Runnable() {
                                @Override
                                public void run() {
                                    im2.setImageResource(R.mipmap.ic_launcher);
                                }
                            });
                        }
                    }

                }
            } catch (JSONException e) {
                handler.sendEmptyMessage(1);
                Message message = new Message();
                message.obj = "查询失败";
                handler.sendMessage(message);
            }
        }
    }

    private void parkingmoney_sz(final int moner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader;
                try {
                    URL url = new URL("http://192.168.1.109:8080/transportservice/type/jason/action/SetParkRate.do");
                    Log.d("拼接url",url.toString());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setRequestProperty("Content-Type", "application/json;");//头文件
                    
                    String data = "{\"RateType\":\"\",\"Money\":"+moner+"}";
                    // 获得一个输出流, 用于向服务器写数据, 默认情况下, 系统不允许向服务器输出内容  
                    OutputStream out = httpURLConnection.getOutputStream();
                    out.write(data.getBytes());
                    out.flush();
                    out.close();

                    InputStream is = httpURLConnection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(is));
                    StringBuffer buffer = new StringBuffer();
                    String line;
                    if (is != null) {
                        while ((line = reader.readLine()) != null) {
                            buffer.append(line);
                        }
                    }
                    Log.d("读取到的信息", buffer.toString());
                    httpURLConnection.connect();
                    parkingmoney_sz_jx(buffer.toString());


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void parkingmoney_sz_jx(String parkingmoney_jx_liu) {
        if (parkingmoney_jx_liu != null) {
            try {
                JSONObject jsonObject = new JSONObject(parkingmoney_jx_liu);
                String string = jsonObject.getString("serverinfo");
                JSONObject jsonObject1 = new JSONObject(string);
                if (jsonObject1.getString("result").equals("ok")) {
                    handler.sendEmptyMessage(0);
                    Message message = new Message();
                    message.obj = "设置成功";
                    handler.sendMessage(message);
                }
            } catch (JSONException e) {
                handler.sendEmptyMessage(1);
                Message message = new Message();
                message.obj = "设置失败";
                handler.sendMessage(message);
            }

        }
    }
}
