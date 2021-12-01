package demo.common;

import java.time.LocalDateTime;

public class DeCount {
    //获取当前还剩多少数量
    public static synchronized Integer  getInteger(LocalDateTime now) {
        Integer nowHours =  now.getHour();
        Integer remainCount = 24 - nowHours ;
//        Log.info("当前剩余名额为："+remainCount+"");
        System.out.println("当前剩余名额为："+remainCount);
        return remainCount;
    }
}
