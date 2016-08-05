package com.example.givemepass.charttest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rick.wu on 2016/6/7.
 */
public class YahooJsonData {
    private double ratio;

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    @SerializedName("mem")
    private Mem mem;

    public Mem getMem() {
        return mem;
    }

    public void setMem(Mem mem) {
        this.mem = mem;
    }

    @SerializedName("ta")
    private List<TA> TAList;

    public List<TA> getTAList() {
        return TAList;
    }

    public void setTAList(List<TA> TAList) {
        this.TAList = TAList;
    }

    public static class TA{
        @SerializedName("t")
        private long time;
        @SerializedName("o")
        private double o;
        @SerializedName("h")
        private double hight;
        @SerializedName("l")
        private double low;
        @SerializedName("c")
        private double c;
        @SerializedName("v")
        private long volume;

        public long getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public double getO() {
            return o;
        }

        public void setO(double o) {
            this.o = o;
        }

        public double getHight() {
            return hight;
        }

        public void setHight(double hight) {
            this.hight = hight;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getC() {
            return c;
        }

        public void setC(double c) {
            this.c = c;
        }

        public long getVolume() {
            return volume;
        }

        public void setVolume(long volume) {
            this.volume = volume;
        }
    }
    public static class Mem{
        private String id;
        private String name;
        @SerializedName("184")
        private double increase;

        public double getIncrease() {
            return increase;
        }

        public void setIncrease(double increase) {
            this.increase = increase;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
