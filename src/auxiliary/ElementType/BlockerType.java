package auxiliary.ElementType;

import auxiliary.CommonUtils;
import auxiliary.ContentConstants;
import auxiliary.Generator.BlockerGenerator;

public enum BlockerType {
    V_H(2,2){
        @Override
        public int getNextX(int startX, int countYetToDraw, int blockerInterval, int totalNum, BlockerLength blockerLength){
            if(countYetToDraw == 2){
                return startX;
            }else{
                return startX + ContentConstants.BLOCKER_WIDTH + blockerInterval;
            }
        }
        @Override
        public int getNextY(int startY, int countYetToDraw, int blockerVerticalInterval, int totalNum){
            if(countYetToDraw == 2){
                return startY;
            }
            else{
                return startY + blockerVerticalInterval;
            }
        }
        @Override
        public BlockerDirection getBlockerDirection(int countYetToDraw){
            if(countYetToDraw == 2){
                return BlockerDirection.VERTICAL;
            }else return BlockerDirection.HORIZONTAL;
        }
    },
    V_V(2,4){
        @Override
        public int getNextX(int startX, int countYetToDraw, int blockerInterval, int totalNum, BlockerLength blockerLength){
            if(countYetToDraw == totalNum)
                return startX;
            return startX + ContentConstants.BLOCKER_WIDTH +( totalNum - countYetToDraw ) * blockerInterval;
        }
        @Override
        public int getNextY(int startY, int countYetToDraw, int blockerVerticalInterval, int totalNum){
            if(countYetToDraw == totalNum)
                return startY;
            return startY + ( totalNum - countYetToDraw ) * blockerVerticalInterval;
        }
        @Override
        public BlockerDirection getBlockerDirection(int a){
            return BlockerDirection.VERTICAL;
        }
    },
    WAVE(4,8){
        @Override
        public int getNextX(int startX, int countYetToDraw, int blockerInterval, int totalNum, BlockerLength blockerLength){
            if(countYetToDraw == totalNum)
                return startX;
            return startX + ContentConstants.BLOCKER_WIDTH + ( totalNum - countYetToDraw ) * blockerInterval;
        }
        @Override
        public int getNextY(int startY, int countYetToDraw, int blockerVerticalInterval, int totalNum){
            if(( totalNum - countYetToDraw ) % 2 == 0)
                return ContentConstants.UPPER_CONTENT_BOUNDARY;
            else return (ContentConstants.GROUND_HEIGHT- BlockerGenerator.getBlockerLength().getLength());
        }
        @Override
        public BlockerDirection getBlockerDirection(int a){
            return BlockerDirection.VERTICAL;
        }
    },
    STAIRWAY_HORIZONTAL(3,6){
        @Override
        public int getNextX(int startX, int countYetToDraw, int blockerInterval, int totalNum, BlockerLength blockerLength){
            if(countYetToDraw == totalNum)
                return startX;
            return startX + blockerLength.getLength() + ( totalNum - countYetToDraw ) * blockerInterval;
        }
        @Override
        public int getNextY(int startY, int countYetToDraw, int blockerVerticalInterval, int totalNum){
            if(countYetToDraw == totalNum)
                return startY;
            return startY + ContentConstants.BLOCKER_WIDTH + ( totalNum - countYetToDraw ) * blockerVerticalInterval;
        }
        @Override
        public BlockerDirection getBlockerDirection(int a){
            return BlockerDirection.VERTICAL;
        }
    },
    STAIRWAY_VERTICAL(2,4){
        @Override
        public int getNextX(int startX, int countYetToDraw, int blockerInterval, int totalNum,BlockerLength blockerLength){
            if(countYetToDraw == totalNum)
                return startX;
            return startX + ContentConstants.BLOCKER_WIDTH + ( totalNum - countYetToDraw ) * blockerInterval;
        }
        @Override
        public int getNextY(int startY, int countYetToDraw, int blockerVerticalInterval, int totalNum){
            if(countYetToDraw == totalNum)
                return startY;
            return startY + ( totalNum - countYetToDraw ) * blockerVerticalInterval;
        }
        @Override
        public BlockerDirection getBlockerDirection(int a){
            return BlockerDirection.VERTICAL;
        }
    };
    private static int countYetToDraw;
    private int minTotalNum,maxTotalNum;
    private int nextX,nextY;
    private int lastX,lastY;
    private static int blockerInterval;
    BlockerType(int minTotalNum,int maxTotalNum){
        this.maxTotalNum = maxTotalNum;
        this.minTotalNum = minTotalNum;
    }
    public int getTotalNum(){
        return CommonUtils.getRandomNumBetween(minTotalNum,maxTotalNum);
    }
    public BlockerDirection getBlockerDirection(int countYetToDraw){

        return null;
    }
    public int getInterval(int startX,int endX){
        return -1;
    }
    public int getNextX(int lastX, int countYetToDraw, int blockerInterval, int totalNum, BlockerLength blockerLength){
        return lastX;
    }
    public int getNextY(int lastY, int countYetToDraw, int blockerVerticalInterval, int totalNum){
        return lastY;
    }
    public static BlockerType getRandomBlockerType(){
        int i = CommonUtils.getRandomNumBetween(0,4);
        for(BlockerType blockerType : BlockerType.values()){
            if (blockerType.ordinal() ==  i)
                return blockerType;
        }
        return null;
    }
}
