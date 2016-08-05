package com.example.givemepass.charttest;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

/**
 * Created by rick.wu on 2016/8/2.
 */
public class YahooData {
    private static YahooData ourInstance = new YahooData();

    public static YahooData getInstance() {
        return ourInstance;
    }

    private YahooData() {
    }
    public YahooJsonData getYahooJsonData(String stockNum){
        String title = "jQuery111308632092973217345_" + System.currentTimeMillis();
        String u = "https://tw.quote.finance.yahoo.net/quote/q?type=ta&perd=30m&mkt=10&sym="+ stockNum+"&callback="+title+"&_=" + (System.currentTimeMillis()+2);
        try {
            String json = GetJsonFromUrl.readUrl(u);
            json = json.substring(title.length()+1, json.length()-2);
            Gson gson = new Gson();
            return gson.fromJson(json, YahooJsonData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
