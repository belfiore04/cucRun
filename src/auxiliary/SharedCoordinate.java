package auxiliary;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给障碍物和背景公用的辅助坐标类
 * 两类元素都按照此坐标类运动
 * 实现记录功能，方便闪回
 * 实现变速功能
 */
public class SharedCoordinate {
    private static int x = 0, reversedX = 0 ;
    private static float speed = 1.2f;
    private static float acceleration = 0.3f;
    private static final Deque<Integer> xCoordinates = new ArrayDeque<>(3);//记录x坐标

    public static boolean isReversedCoordinate = false;

    /**
     * 初始化坐标记录器：等待三秒后开始更新、记录x坐标
     */
    public static void init(){
        CommonUtils.task(0, CommonConstants.REFRESH_PERIOD,() -> {
            x -= speed;
            if(x + CommonConstants.FRAME_WIDTH * ContentConstants.BUILDING_PIC_NUM < 0){
                x = 0;
                ElementBean.Terrain.getService().init();
            }
        });
        //三秒钟之后，每10秒加速一次
        CommonUtils.task(0,20 * 1000,() -> {
            speed += acceleration;
        });
    }
    /**
     * 计算闪回坐标
     */
    public static void ReversedCoordinate(){
        reversedX = x;
        speed = SharedCoordinate.getSpeed();
        CommonUtils.task(ContentConstants.CALLBACK_TIME_MS,0,CommonConstants.REFRESH_PERIOD,() -> {
            reversedX += speed;
        });
    }

    /**
     * 返回正常坐标或闪回坐标
     * @return
     */
    public static int getX(){
        if(isReversedCoordinate){
            return reversedX;
        }
        return x;
    }
    public static float getSpeed(){
        return speed;
    }

    public static void setX(int targetX){
        x = targetX ;
    }
    public static void setIsReversedCoordinate(boolean reverseState){
        isReversedCoordinate = reverseState;
        if(isReversedCoordinate){
            ReversedCoordinate();
            CommonUtils.task(ContentConstants.CALLBACK_TIME_MS,() -> {
                SharedCoordinate.setX(reversedX);
            });
        }
    }
    public static void resetX(){
        x = 0;
    }
}

