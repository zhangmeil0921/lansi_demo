package demo.common;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

    /**
     * @author th
     * @description: 定时任务测试
     * @projectName hashdog-ds
     * @date 2020/2/1223:07
     */
    @Component
    @Slf4j
    @AllArgsConstructor
    @PropertySource("classpath:/application.properties")
    public class ScheduleTask {
        private static final Object KEY = new Object();

        private static boolean taskFlag = false;

//        @Scheduled(cron = "0 0 0/1 * * ?")
        @Scheduled(cron = "${datasync.cron}")
        public void pushCancel() throws InterruptedException {
            System.out.println("进来了");
//        synchronized (KEY) {
//            if (TestCrontab.taskFlag) {
//                System.out.println("测试调度已经启动");
//                log.warn("测试调度已经启动");
//                return;
//            }
//            TestCrontab.taskFlag = true;
//        }
//
//        try {
            for (int i =0;i<=10;i++){
                System.out.println("执行："+i);
                Thread.sleep(2000);
            }
//        } catch (Exception e) {
//            log.error("测试调度执行出错", e);
//        }
//
//        TestCrontab.taskFlag = false;

            log.warn("测试调度执行完成");
        }
}
