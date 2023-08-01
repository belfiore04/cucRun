package auxiliary.Generator;

import auxiliary.CommonConstants;
import auxiliary.CommonUtils;

public class TerrainGenerator {
    private static boolean isFirstScreen = true;
    private static int terrainSum = 7;
    private static int largeTerrainSum = 2, smallTerrainSum = 3;
    private static int leftLargeTerrainToMake = 0, leftSmallTerrainToMake = 0;
    private static int terrainAlreadyMadeSum = 0;

    public static void generateTerrain(){
        do{
            if(isFirstScreen){
                //重置地形大小计数
                leftLargeTerrainToMake = 2;
                leftSmallTerrainToMake = 3;
                terrainAlreadyMadeSum++;
                isFirstScreen = false;
            }else{
                //随机选择大小和地形
                int randomNumForTerrainType = CommonUtils.getRandomNumBetween(1,4);//1代表金币地形，其他代表障碍物地形
                int randomNumForTerrainSize = CommonUtils.getRandomNumBetween(0,1);//0代表小，1代表大
                if(randomNumForTerrainType != 1){
                    if(leftLargeTerrainToMake != 0 && leftSmallTerrainToMake != 0){
                        if (randomNumForTerrainSize == 0) {
                            BlockerGenerator.getBlockerInterval(CommonConstants.FRAME_WIDTH,(smallTerrainSum - leftSmallTerrainToMake + 1) * CommonConstants.FRAME_WIDTH +(int) ((largeTerrainSum -leftLargeTerrainToMake) * CommonConstants.FRAME_WIDTH * 1.5));
                            leftSmallTerrainToMake--;
                        } else {
                            BlockerGenerator.getBlockerInterval((int) (CommonConstants.FRAME_WIDTH * 1.5),(smallTerrainSum - leftSmallTerrainToMake + 1) * CommonConstants.FRAME_WIDTH +(int) ((largeTerrainSum -leftLargeTerrainToMake) * CommonConstants.FRAME_WIDTH * 1.5));
                            leftLargeTerrainToMake--;
                        }
                    } else if (leftLargeTerrainToMake == 0 && leftSmallTerrainToMake != 0) {
                        BlockerGenerator.getBlockerInterval(CommonConstants.FRAME_WIDTH,(smallTerrainSum - leftSmallTerrainToMake + 1) * CommonConstants.FRAME_WIDTH +(int) ((largeTerrainSum -leftLargeTerrainToMake) * CommonConstants.FRAME_WIDTH * 1.5));
                        leftSmallTerrainToMake--;
                    } else if (leftLargeTerrainToMake != 0 && leftSmallTerrainToMake == 0) {
                        BlockerGenerator.getBlockerInterval((int)(CommonConstants.FRAME_WIDTH * 1.5),(smallTerrainSum - leftSmallTerrainToMake + 1) * CommonConstants.FRAME_WIDTH +(int) ((largeTerrainSum -leftLargeTerrainToMake) * CommonConstants.FRAME_WIDTH * 1.5));
                        leftLargeTerrainToMake--;
                    }
                } else {
                    if(leftLargeTerrainToMake != 0 && leftSmallTerrainToMake != 0){
                        if (randomNumForTerrainSize == 0) {
                            CoinGenerator.generateCoin(CommonConstants.FRAME_WIDTH,(smallTerrainSum - leftSmallTerrainToMake + 1) * CommonConstants.FRAME_WIDTH +(int) ((largeTerrainSum -leftLargeTerrainToMake) * CommonConstants.FRAME_WIDTH * 1.5));
                            leftSmallTerrainToMake--;
                        } else {
                            CoinGenerator.generateCoin((int) (CommonConstants.FRAME_WIDTH *1.5),(smallTerrainSum - leftSmallTerrainToMake + 1) * CommonConstants.FRAME_WIDTH +(int) ((largeTerrainSum -leftLargeTerrainToMake) * CommonConstants.FRAME_WIDTH * 1.5));
                            leftLargeTerrainToMake--;
                        }
                    } else if (leftLargeTerrainToMake == 0 && leftSmallTerrainToMake != 0) {
                        CoinGenerator.generateCoin(CommonConstants.FRAME_WIDTH,(smallTerrainSum - leftSmallTerrainToMake + 1) * CommonConstants.FRAME_WIDTH +(int) ((largeTerrainSum -leftLargeTerrainToMake) * CommonConstants.FRAME_WIDTH * 1.5));
                        leftSmallTerrainToMake--;
                    }
                    else if (leftLargeTerrainToMake != 0 && leftSmallTerrainToMake == 0){
                        CoinGenerator.generateCoin((int) (CommonConstants.FRAME_WIDTH *1.5),(smallTerrainSum - leftSmallTerrainToMake + 1) * CommonConstants.FRAME_WIDTH +(int) ((largeTerrainSum -leftLargeTerrainToMake) * CommonConstants.FRAME_WIDTH * 1.5));
                        leftLargeTerrainToMake--;
                    }
                }
                terrainAlreadyMadeSum++;
            }
        }while(terrainAlreadyMadeSum < 7);
            isFirstScreen = true;
            terrainAlreadyMadeSum = 0;
    }
}


