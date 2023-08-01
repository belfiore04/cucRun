package auxiliary;

import base.ITimer;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 * 封装getImage、Timer、随机数生成
 */
public abstract class CommonUtils {
    private static final Random RANDOM = new Random();

    /**
     * 获取整形随机数
     * @param start 随机数范围下界
     * @param end 随机数范围上界
     * @return 生成的随机数，如果上下界相等则返回上下界
     */
    public static int getRandomNumBetween(int start,int end){
        return start == end ? start :start + RANDOM.nextInt(end - start +1);
    }
    public static Image getImage(String imageName){
        return new ImageIcon(CommonConstants.RESOURCES_PATH +"image/"+ imageName).getImage();
    }

/*    *//**
     * 按照period执行t中任务
     * @param period
     * @param t
     *//*
    public static void task(long delay,long period, ITimer t){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(CommonConstants.TIMER_STOP){
                    timer.cancel();
                    return;
                }
                t.run();
            }
        };
        timer.schedule(timerTask,delay,period);
    }*/
/*    public static void task(long life,long delay,long period, ITimer t){
        Timer cancelee = new Timer();
        TimerTask canceleeTask = new TimerTask() {
            @Override
            public void run() {
                if(CommonConstants.TIMER_STOP){
                    cancelee.cancel();
                    return;
                }
                t.run();
            }
        };
        if(life == period){
            cancelee.schedule(canceleeTask,delay);
        }else{
            Timer canceler = new Timer();
            TimerTask cancelerTask = new TimerTask() {
                @Override
                public void run() {
                    cancelee.cancel();
                    canceler.cancel();
                }
            };
            cancelee.schedule(canceleeTask,delay,period);
            canceler.schedule(cancelerTask,delay + life);
        }*/



 /*   public static void task(long life, long delay, long period, Runnable task) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        AtomicBoolean isRunning = new AtomicBoolean(false);

        Runnable runnable = new Runnable() {
            long startTime = System.currentTimeMillis() + delay;
            long endTime = startTime + life;
            long lastTime = startTime;

            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                if (currentTime >= endTime) {
                    stop();
                } else {
                    long elapsedTime = currentTime - startTime;
                    long tickTime = currentTime - lastTime;
                    task.run();
                    lastTime = currentTime;
                    executor.schedule(this, period - tickTime, TimeUnit.MILLISECONDS);
                }
            }

            private void stop() {
                if (isRunning.compareAndSet(true, false)) {
                    executor.shutdown();
                }
            }
        };

        if (isRunning.compareAndSet(false, true)) {
            executor.schedule(runnable, delay, TimeUnit.MILLISECONDS);
        }
    }*/
    public static void task(long delay,long period, ITimer t){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(20);
        Runnable task = new Runnable() {
            public void run(){
                t.run();
            }
        };
        executor.scheduleAtFixedRate(task,delay,period, TimeUnit.MILLISECONDS);
    }
    public static void task(long life,long delay, long period, ITimer timer){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(20);
        ScheduledExecutorService killer = Executors.newScheduledThreadPool(20);
        Runnable task = new Runnable() {
            public void run(){
                timer.run();
            }
        };
        Runnable killerTask = () -> {
            executor.shutdown();
        };
        executor.scheduleAtFixedRate(task,delay,period, TimeUnit.MILLISECONDS);
        killer.schedule(killerTask,life+delay+100,TimeUnit.MILLISECONDS);
    }
    public static void task(long delay, ITimer timer){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(20);
        Runnable task = new Runnable() {
            public void run(){
                timer.run();
            }
        };
        executor.schedule(task,delay,TimeUnit.MILLISECONDS);
    }
}
