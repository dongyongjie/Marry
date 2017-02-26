package com.bwie.marry.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.marry.DingweiActivity;
import com.bwie.marry.R;
import com.bwie.marry.bean.RootBean;
import com.bwie.marry.utils.DataCleanManager;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by lenovo on 2017/2/23.
 */

public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<RootBean.DataBean> list;

    public MyBaseAdapter(Context context, List<RootBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v;
        if (convertView == null) {
            convertView=View.inflate(context, R.layout.item,null);
            v=new ViewHolder();
            v.imageView= (ImageView) convertView.findViewById(R.id.imageView);
            v.name= (TextView) convertView.findViewById(R.id.name);
            v.age= (Button) convertView.findViewById(R.id.age);
            v.company= (Button) convertView.findViewById(R.id.company);
            v.huancun= (Button) convertView.findViewById(R.id.btn_huan);
            v.dingwei= (Button) convertView.findViewById(R.id.btn_dingwei);
            v.dingwei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DingweiActivity.class);
                    context.startActivity(intent);

                }
            });
            v.huancun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataCleanManager.cleanInternalCache(context.getApplicationContext());
                    Toast.makeText(context, "清除缓存成功", Toast.LENGTH_SHORT).show();

                }
            });
            v.message= (TextView) convertView.findViewById(R.id.message);
            convertView.setTag(v);
        }else {
            v= (ViewHolder) convertView.getTag();
        }
        BitmapUtils bitmapUtils = new BitmapUtils(context);
        bitmapUtils.display(v.imageView,list.get(position).getUserImg());
        v.name.setText(list.get(position).getUserName());
        v.age.setText(list.get(position).getUserAge()+"");
        v.company.setText(list.get(position).getOccupation());
        v.message.setText(list.get(position).getIntroduction());

        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        Button company,age,dingwei,huancun;
        TextView name,message;
    }
}
