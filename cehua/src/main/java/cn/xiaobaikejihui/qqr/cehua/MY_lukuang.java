package cn.xiaobaikejihui.qqr.cehua;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MY_lukuang extends AppCompatActivity {
      
    private  final int wc= ViewGroup.LayoutParams.WRAP_CONTENT;
    private  final int fp= ViewGroup.LayoutParams.FILL_PARENT;
    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lukuang);
        
        bt1= (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableLayout tableLayout= (TableLayout) findViewById(R.id.tableLayout);
                tableLayout.setStretchAllColumns(true);
                for(int row=0;row<3;row++){
                    TableRow tableRow=new TableRow(MY_lukuang.this);
                    tableRow.setBackgroundColor(Color.rgb(220,220,220));
                    for (int col=0;col<4;col++){
                        TextView tv=new TextView(MY_lukuang.this);
                        tv.setBackgroundResource(R.drawable.shapee);
                        tv.setText("选择");
                        tableRow.addView(tv);
                    }
                    tableLayout.addView(tableRow,new TableLayout.LayoutParams(fp,wc));
                }
            }
        });
        
    }
}
