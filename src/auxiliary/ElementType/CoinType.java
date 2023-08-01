package auxiliary.ElementType;

import auxiliary.CommonUtils;
import auxiliary.ContentConstants;

public enum CoinType {
    RECTANGLE(63,ContentConstants.rectMatrix),
    ARROW(30,ContentConstants.arrowMatrix),
    WAVE(9,ContentConstants.waveMatrix),
    STAIRWAY(9,ContentConstants.stairsMatrix);
    CoinType(int totalNum, int[][] coordMatrix){
        this.totalNum = totalNum;
        countYetToDraw = totalNum;
        this.coordMatrix = coordMatrix;
        lineIndex = 0;
        columnIndex = 0;
        columnCount =0;
    }

    private int totalNum;
    private int countYetToDraw;
    private int lineIndex;
    private int columnIndex;
    private int[][] coordMatrix;
    int rowCount,columnCount,desiredCoordIndex;
    int currentCordIndex;

    public static CoinType getRandomCoinType(){
        int i = CommonUtils.getRandomNumBetween(0,3);
        for(CoinType coinType : CoinType.values()){
            if (i == coinType.ordinal())
                return coinType;
        }
        return null;
    }
    public int getNextX(int startX){
        currentCordIndex = 0;
        rowCount = 0;
        for(int[] row : coordMatrix){
            for(int col : row){
                currentCordIndex++;
                if(currentCordIndex - 1 == this.desiredCoordIndex){
                    this.desiredCoordIndex++;
                    return startX + col * (ContentConstants.ELEMENT_SIZE + 24);
                }
            }
            rowCount++;
        }
        return -1;
    }
    public int getNextY(int startY){
        return startY + rowCount * (ContentConstants.ELEMENT_SIZE + 24);
    }
    private int getLineIndex (){
        return lineIndex;
    }
    private int getColumnIndex(){
        return columnIndex;
    }
    public int getTotalNum(){
        return totalNum;
    }
    private void lineIndexIncrease(int step){
        this.lineIndex += step;
    }
    private void columnIndexIncrease(int step){
        this.columnIndex += step;
    }
    private void lineIndexSet(int lineIndex){
        this.lineIndex = lineIndex;
    }
    private void setColumnIndex(int columnIndex){
        this.columnIndex = columnIndex;
    }
}
