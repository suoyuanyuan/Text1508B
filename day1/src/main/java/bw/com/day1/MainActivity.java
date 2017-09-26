package bw.com.day1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bw.com.day1.utils.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
adapter.notifyDataSetChanged();
        close();
    }
};
    private List<String> list;
    private XListView xlv;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xlv = (XListView) findViewById(R.id.xlv);
        initData();
        xlv.setPullLoadEnable(true);
        xlv.setPullRefreshEnable(true);
        xlv.setXListViewListener(this);
        adapter = new MyAdapter();
        xlv.setAdapter(adapter);
    }

    private void initData() {
        list=new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add("第27届河南畜牧交易会");
        }
    }
public void close(){
    xlv.stopLoadMore();
    xlv.stopRefresh();
}
    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add("国际服装");
                handler.sendEmptyMessage(0);
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
handler.postDelayed(new Runnable() {
    @Override
    public void run() {
        list.add("博览会");
        handler.sendEmptyMessage(0);
    }
},2000);
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv=new TextView(MainActivity.this);
            tv.setText("aa");
            return tv;
        }
    }
}
