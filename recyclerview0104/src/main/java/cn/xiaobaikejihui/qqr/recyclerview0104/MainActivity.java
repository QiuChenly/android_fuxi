package cn.xiaobaikejihui.qqr.recyclerview0104;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    
private List<Fruit> fruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();
        RecyclerView recyclerview= (RecyclerView) findViewById(R.id.recycler);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
       // LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        /*layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);*///横向的listview
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);//设置为瀑布流
        recyclerview.setLayoutManager(layoutManager);
        fruitadapter adapter=new fruitadapter(fruitList);
        recyclerview.setAdapter(adapter);
    }

    private void initFruits() {
        for(int i=0;i<2;i++){
            Fruit apple=new Fruit(getRandomLengthName("apple"), R.mipmap.ic_launcher);
            fruitList.add(apple);
            Fruit banana=new Fruit(getRandomLengthName("banana"), R.mipmap.ic_launcher);
            fruitList.add(banana);
            Fruit orange=new Fruit(getRandomLengthName("orange"), R.mipmap.ic_launcher);
            fruitList.add(orange);
            Fruit watermelon=new Fruit(getRandomLengthName("watermelon"), R.mipmap.ic_launcher);
            fruitList.add(watermelon);
            Fruit pear=new Fruit(getRandomLengthName("pear"), R.mipmap.ic_launcher);
            fruitList.add(pear);
            Fruit grape=new Fruit(getRandomLengthName("grape"), R.mipmap.ic_launcher);
            fruitList.add(grape);
            Fruit pineapple=new Fruit(getRandomLengthName("pineapple"), R.mipmap.ic_launcher);
            fruitList.add(pineapple);
            Fruit starwberry=new Fruit(getRandomLengthName("starwberry"), R.mipmap.ic_launcher);
            fruitList.add(starwberry);
            Fruit cherry=new Fruit(getRandomLengthName("cherry"), R.mipmap.ic_launcher);
            fruitList.add(cherry);
            Fruit mango=new Fruit(getRandomLengthName("mango"), R.mipmap.ic_launcher);
            fruitList.add(mango);
            
            
            
        }
    }

    private String getRandomLengthName(String name) {   //设置随机生成的名字数量
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();//一个可变的字符序列。此类提供一个与 StringBuffer 兼容的 API，但不保证同步。
        // 该类被设计用作 StringBuffer 的一个简易替换，用在字符串缓冲区被单个线程使用的时候（这种情况很普遍）。
        for(int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }


}
