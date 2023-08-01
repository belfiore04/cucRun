package auxiliary;

public class ContentConstants {
    public final static int CHARACTER_WIDTH = 72;
    public final static int CHARACTER_HEIGHT = 72;
    public final static int ELEMENT_SIZE = 24;
    public static long CALLBACK_TIME_MS = 1 * 1000;
    public static int CLOUD_PIC_NUM = 2;
    public static int BUILDING_PIC_NUM = 8;
    public static int GROUND_HEIGHT = 512 ;
    public static int UPPER_CONTENT_BOUNDARY = 20;
    public static int CONTENT_HEIGHT = GROUND_HEIGHT - UPPER_CONTENT_BOUNDARY;
    public static int BLOCKER_WIDTH = 40;
    public static int[][] rectMatrix = {{0,1,2,3,4,5,6,7,8},{0,1,2,3,4,5,6,7,8},{0,1,2,3,4,5,6,7,8},{0,1,2,3,4,5,6,7,8},{0,1,2,3,4,5,6,7,8},{0,1,2,3,4,5,6,7,8}};
    public static int[][] arrowMatrix = {{5},{5,6},{0,1,2,3,4,5,6,7},{0,1,2,3,4,5,6,7,8},{0,1,2,3,4,5,6,7},{5,6},{5}};
    public static int[][] waveMatrix = {{2,6},{1,3,5,7},{0,4,8}};
    public static int[][] stairsMatrix = {{6,7,8},{6},{3,4,5,6},{3},{0,1,2,3},{0},{0}};
    
}
