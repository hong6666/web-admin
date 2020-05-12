package com.swd.websiteadmin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtil {

    public static String RandomImgName(){
        Random random = new Random();
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String data = simp.format(new Date());
        int i = random.nextInt(9999);
        return i+"-"+data;
    }
}
