package com.bwie.marry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.bwie.marry.adapter.MyBaseAdapter;
import com.bwie.marry.bean.RootBean;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private String path="http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=10&gender=2&ts=1871746850&page=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Log.i("TAG", "onSuccess: "+result);
                Gson gson = new Gson();
                RootBean bean = gson.fromJson(result, RootBean.class);
                List<RootBean.DataBean> data = bean.getData();
                listView.setAdapter(new MyBaseAdapter(MainActivity.this,data));


            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
}
