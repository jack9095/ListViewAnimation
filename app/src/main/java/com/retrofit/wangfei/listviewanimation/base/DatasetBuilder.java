package com.retrofit.wangfei.listviewanimation.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-24
 * Time: 12:26
 * Description:
 */
public abstract class DatasetBuilder {

    private final static String[] CARS = {
            "Volvo", "Mercedes", "Audi", "Land Rover", "BMW", "Ford", "GMC", "Mazda", "Acura", "Vaz", "Renault",
            "DeLorean", "Alfa Romeo", "Toyota", "Saab", "Ferrari", "Tesla"};


    private DatasetBuilder() {
    }

    public static List<String> build() {
        return Arrays.asList(CARS);
    }

    public static List<String> buildLarge() {
        return buildLarge(50);
    }

    public static List<String> buildLarge(int size) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < size-1; i++) Collections.addAll(arrayList, CARS); // 把数组中的数字添加到集合中
        Collections.shuffle(arrayList); // 打乱集合中的内容，是每次展现在列表中的数据排序不一样
        return arrayList;
    }


}
