package cn.xiaobaikejihui.qqr.recyclerview0104;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class fruitadapter extends RecyclerView.Adapter<fruitadapter.ViewHolder> {
    
    
    private List<Fruit> mfruitadapter;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit=mfruitadapter.get(position);
        holder.fruitImage.setImageResource(fruit.getImageID());
        holder.fruitName.setText(fruit.getName());

    }

    @Override
    public int getItemCount() {
        return mfruitadapter.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View itemView) {
            super(itemView);
            fruitImage= (ImageView) itemView.findViewById(R.id.imageView);
            fruitName= (TextView) itemView.findViewById(R.id.textView);
        }
    }
    public fruitadapter(List<Fruit> fruitList){
        mfruitadapter=fruitList;
    }
}
