package demo.common;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeUtils {


    //过多久到期
    public static synchronized Long extracted(LocalDateTime now, int number) {
        LocalDateTime ew = now.plusHours(number).withMinute(0).withSecond(0).withNano(0);
        System.out.println(ew);
        Long e = ChronoUnit.SECONDS.between(now,ew);
        System.out.println("距离下一个整点秒数为："+e);
        return  e;
    }
}
