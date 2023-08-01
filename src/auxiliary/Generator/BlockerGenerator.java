package auxiliary.Generator;

import Content.Map.Blocker;
import auxiliary.CommonUtils;
import auxiliary.ContentConstants;
import auxiliary.ElementBean;
import auxiliary.ElementType.BlockerDirection;
import auxiliary.ElementType.BlockerLength;
import auxiliary.ElementType.BlockerType;

public class BlockerGenerator {
    public static int blockerTotalNum;
    private static int startX,endX;
    private static int startY,endY;
    private static int nextX,nextY;
    private static BlockerType blockerType;
    private static BlockerLength blockerLength;
    private static BlockerDirection blockerDirection;
    private static int blockerInterval,blockerVerticalInterval;
    private static int countYetToDraw;
    public static void getBlockerInterval(int blockerScreenScope,int screenScopeStartX){
        blockerType = BlockerType.getRandomBlockerType();
        blockerLength = BlockerLength.getRandomLength();
        blockerTotalNum = blockerType.getTotalNum();
        countYetToDraw = blockerTotalNum;
        startX = CommonUtils.getRandomNumBetween(blockerScreenScope/4,blockerScreenScope/3) + screenScopeStartX;
        do{
            startY = CommonUtils.getRandomNumBetween(ContentConstants.CHARACTER_HEIGHT + ContentConstants.UPPER_CONTENT_BOUNDARY + ContentConstants.CONTENT_HEIGHT / 3,ContentConstants.GROUND_HEIGHT - blockerLength.getLength());
        }while(startY + blockerLength.getLength() > ContentConstants.GROUND_HEIGHT);
        if(blockerType == BlockerType.V_H){
            blockerInterval = ContentConstants.BLOCKER_WIDTH * CommonUtils.getRandomNumBetween(1,3);
        }else{
            do{
                endX = CommonUtils.getRandomNumBetween(blockerScreenScope/3,blockerScreenScope) + screenScopeStartX;
                blockerInterval = (int) ((endX - startX)/(blockerTotalNum-1));
            }while(blockerInterval <= 2.5 * ContentConstants.BLOCKER_WIDTH);
        }
        if(blockerType == BlockerType.WAVE){
            while(blockerLength == BlockerLength.LONG)
                blockerLength = BlockerLength.getRandomLength();
        }
            if(blockerType == BlockerType.STAIRWAY_HORIZONTAL){
                do{
                    endY = CommonUtils.getRandomNumBetween(ContentConstants.UPPER_CONTENT_BOUNDARY,ContentConstants.UPPER_CONTENT_BOUNDARY + ContentConstants.CONTENT_HEIGHT / 3);
                    do{
                        startY = CommonUtils.getRandomNumBetween(ContentConstants.UPPER_CONTENT_BOUNDARY + (int) (ContentConstants.CONTENT_HEIGHT / 3),ContentConstants.GROUND_HEIGHT);
                    }while(startY + ContentConstants.BLOCKER_WIDTH > ContentConstants.GROUND_HEIGHT);
                    blockerVerticalInterval = (int) ((endY - startY)) / (blockerTotalNum - 1);
                }while(blockerVerticalInterval > 1.5 * ContentConstants.BLOCKER_WIDTH);
            } else if (blockerType == BlockerType.STAIRWAY_VERTICAL) {
                do{
                    endY = CommonUtils.getRandomNumBetween(ContentConstants.UPPER_CONTENT_BOUNDARY,ContentConstants.UPPER_CONTENT_BOUNDARY + ContentConstants.CONTENT_HEIGHT / 3);
                    do{
                        startY = CommonUtils.getRandomNumBetween(ContentConstants.UPPER_CONTENT_BOUNDARY + ContentConstants.CONTENT_HEIGHT / 3,ContentConstants.GROUND_HEIGHT);
                    }while(startY + blockerLength.getLength() > ContentConstants.GROUND_HEIGHT);
                    blockerVerticalInterval = (int) ((endY - startY)) / (blockerTotalNum - 1);
                }while(blockerVerticalInterval > 1.5 * ContentConstants.BLOCKER_WIDTH);
            }else{
                do{
                    blockerVerticalInterval = 5 * CommonUtils.getRandomNumBetween(-3,3);
                }while( (blockerVerticalInterval > 0)&& (startY + (blockerTotalNum - 1)  * blockerVerticalInterval + blockerLength.getLength() > ContentConstants.GROUND_HEIGHT) ||(blockerVerticalInterval<0)&&( startY +(blockerTotalNum -1) * blockerVerticalInterval - blockerLength.getLength() < ContentConstants.UPPER_CONTENT_BOUNDARY + ContentConstants.CHARACTER_HEIGHT -5));
            }
            buildNextCoordinate();
        }
    public static void buildNextCoordinate(){
        while(countYetToDraw != 0){
            nextX = blockerType.getNextX(startX,countYetToDraw,blockerInterval,blockerTotalNum,blockerLength);
            nextY = blockerType.getNextY(startY,countYetToDraw,blockerVerticalInterval,blockerTotalNum);
            blockerDirection = blockerType.getBlockerDirection(countYetToDraw);
            Blocker blocker = new Blocker(nextX,nextY,blockerDirection,blockerLength);
            ElementBean.Terrain.getService().add(blocker);
            ElementBean.Interactive.getService().add(blocker);
            countYetToDraw--;
        }
    }
    public static BlockerLength getBlockerLength(){
        return blockerLength;
    }

}
