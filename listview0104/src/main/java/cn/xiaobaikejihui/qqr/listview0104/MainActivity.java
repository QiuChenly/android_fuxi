package cn.xiaobaikejihui.qqr.listview0104;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView= (ListView) findViewById(R.id.listview1);
        /*String[] date={"APPLE","BANBANA","ORANGE","WATERMELON","PEAR","GRAPE","PINEAPPLE","STARWBERRY","CHERRY","MANGO","APPLE","BANBANA","ORANGE","WATERMELON","PEAR","GRAPE","PINEAPPLE","STARWBERRY","CHERRY","MANGO"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,date);
        listView.setAdapter(arrayAdapter);*/
    }
}
