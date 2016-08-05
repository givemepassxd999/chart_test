package com.example.givemepass.charttest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedHashTreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChartActivity extends DemoBase {
    private ArrayList<Entry> yVals1, yVals2, yVals3, yVals4, yVals5;
    private Map<String, List<Double>> yahooDataMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        initData();
        initView();
    }

    private void initData() {
        yVals1 = new ArrayList<>();
        yVals2 = new ArrayList<>();
        yVals3 = new ArrayList<>();
        yVals4 = new ArrayList<>();
        yVals5 = new ArrayList<>();
        yahooDataMap = new LinkedHashTreeMap<>();
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        YahooJsonData yahooJsonData = new Gson().fromJson(data, YahooJsonData.class);
        List<YahooJsonData.TA> taList = yahooJsonData.getTAList();

        for(YahooJsonData.TA ta : taList){
            String key = String.valueOf(ta.getTime()).substring(0,8);
            if(yahooDataMap.containsKey(key)){
                List<Double> tList = yahooDataMap.get(key);
                tList.add(ta.getC());
            } else{
                List<Double> tList = new ArrayList<>();
                tList.add(ta.getC());
                yahooDataMap.put(key, tList);
            }
        }
        int index = 0;
        for(String key : yahooDataMap.keySet()){
            List<Double> list = yahooDataMap.get(key);
            int count = 0;
            for(Double d : list){
                double dd = d;
                float f = (float) dd;
                switch (index) {
                    case 0:
                        yVals1.add(new Entry(count, f));
                        break;
                    case 1:
                        yVals2.add(new Entry(count, f));
                        break;
                    case 2:
                        yVals3.add(new Entry(count, f));
                        break;
                    case 3:
                        yVals4.add(new Entry(count, f));
                        break;
                    case 4:
                        yVals5.add(new Entry(count, f));
                        break;
                }
                count++;
            }
            index++;
        }
    }

    private void initView() {
        LineChart chart1 = (LineChart) findViewById(R.id.chart1);
        LineChart chart2 = (LineChart) findViewById(R.id.chart2);
        LineChart chart3 = (LineChart) findViewById(R.id.chart3);
        LineChart chart4 = (LineChart) findViewById(R.id.chart4);
        LineChart chart5 = (LineChart) findViewById(R.id.chart5);
        initChart(yVals1, chart1, Color.RED);
        initChart(yVals2, chart2, Color.GREEN);
        initChart(yVals3, chart3, Color.BLUE);
        initChart(yVals4, chart4, Color.YELLOW);
        initChart(yVals5, chart5, Color.CYAN);
    }

    private void initChart(ArrayList<Entry> yVals, LineChart chart, int color) {

        chart.setViewPortOffsets(0, 0, 0, 0);
        // no description text
        chart.setDescription("");

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(300);

        XAxis x = chart.getXAxis();
        x.setEnabled(false);

        YAxis y = chart.getAxisLeft();
        y.setTypeface(mTfLight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.BLACK);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.BLACK);

        chart.getAxisRight().setEnabled(false);

        setData(yVals, chart, color);

        chart.getLegend().setEnabled(false);

        chart.animateXY(2000, 2000);

        // dont forget to refresh the drawing
        chart.invalidate();
    }


    private void setData(ArrayList<Entry> yVals, LineChart mChart, int color) {

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            //set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.BLACK);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            //線的顏色
            set1.setColor(color);
            set1.setFillColor(Color.BLACK);
            set1.setFillAlpha(0);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new FillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return -10;
                }
            });

            // create a data object with the datasets
            LineData data = new LineData(set1);
            data.setValueTypeface(mTfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            mChart.setData(data);
        }
    }
}
