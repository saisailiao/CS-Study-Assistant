package com.example.gahui.httptest.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gahui.httptest.IndexActivity;
import com.example.gahui.httptest.MainActivity;
import com.example.gahui.httptest.R;
import com.example.gahui.httptest.ui.dashboard.DashboardViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private BarChart bar;
    private PieChart pie;
    private static int a,b,c,d,e,f,g,j,h;
    List<BarEntry> list=new ArrayList<>();//实例化一个List用来存储数据
    List<PieEntry> list2=new ArrayList<>();
    private String[] strArr;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        /*dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        super.onCreateView(inflater, container, savedInstanceState);
        View view2 = inflater.inflate(R.layout.fragment_dashboard, container,false);
        final TextView time1 = (TextView) view2.findViewById(R.id.time1);
        time1.setText(IndexActivity.strArr[10]);
        final TextView number = (TextView) view2.findViewById(R.id.number);
        number.setText(IndexActivity.strArr[9]);
        final TextView textaccuracy = (TextView) view2.findViewById(R.id.textaccuracy);
        textaccuracy.setText(IndexActivity.strArr[7]+"%");
        bar = (BarChart)view2.findViewById(R.id.bar);
        //添加数据
        list.add(new BarEntry(1, IndexActivity.a));
        list.add(new BarEntry(2,IndexActivity.b));
        list.add(new BarEntry(3,IndexActivity.c));
        list.add(new BarEntry(4,IndexActivity.d));
        list.add(new BarEntry(5,IndexActivity.e));
        list.add(new BarEntry(6,IndexActivity.f));
        list.add(new BarEntry(7,IndexActivity.g));

        BarDataSet barDataSet=new BarDataSet(list,"一周学习时长统计/min");
        BarData barData=new BarData(barDataSet);
        bar.setData(barData);

        bar.getDescription().setEnabled(false);//隐藏右下角英文
        bar.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置 默认为上面
        bar.getAxisRight().setEnabled(false);//隐藏右侧Y轴   默认是左右两侧都有Y轴
        bar.getDescription().setTextSize(20);                    //字体大小
        bar.getDescription().setTextColor(Color.parseColor("#F686B3"));             //字体颜色
        Legend legend=bar.getLegend();
        legend.setEnabled(false);    //是否显示图例

        barDataSet.setColors(Color.parseColor("#F686B3"),Color.parseColor("#FABCD5"));//设置柱子多种颜色  循环使用
        bar.animateY(3000);


        pie = (PieChart)view2.findViewById(R.id.pie);

        list2.add(new PieEntry((int)IndexActivity.k,"正确"));
        list2.add(new PieEntry((int)IndexActivity.l,"错误"));

        final PieDataSet pieDataSet=new PieDataSet(list2,"");
        PieData pieData=new PieData(pieDataSet);
        pie.setData(pieData);
        pieDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                String str = (int)value + "题";
                return str;
            }
        });


        //设置描述的字体大小
        pie.setEntryLabelTextSize(18);
        pie.setEntryLabelColor(Color.parseColor("#000000"));
        //设置数据的字体大小
        pieDataSet.setValueTextSize(18);
        pieDataSet.setValueTextColor(Color.parseColor("#000000"));

        pie.setHoleRadius(0);
        pie.setTransparentCircleRadius(0);
        pie.getDescription().setEnabled(false);

        Legend legend2=pie.getLegend();
        legend2.setEnabled(false);    //是否显示图例

        pieDataSet.setColors(Color.parseColor("#FABCD5"),Color.parseColor("#B9DDED"));//设置各个数据的颜色
        pie.animateXY(2000,1000);

        return view2;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.rightside_menu_2,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.person:
                Toast.makeText(getActivity(), "person", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}