package auxiliary.Generator;

import Content.Map.Coin;
import auxiliary.CommonUtils;
import auxiliary.ContentConstants;
import auxiliary.ElementBean;
import auxiliary.ElementType.CoinType;

public class CoinGenerator {
    private static int startX,startY;
    private static int nextX,nextY;
    private static CoinType coinType;
    private static int coinTotalNum;
    private static int coinYetToDraw;
    public static void generateCoin(int terrainScreenScope, int screenScopeStartX){
        coinType = CoinType.getRandomCoinType();
        coinTotalNum = coinType.getTotalNum();
        coinYetToDraw = coinTotalNum;
        startX = CommonUtils.getRandomNumBetween(terrainScreenScope/4,terrainScreenScope/3) + screenScopeStartX;
        startY = CommonUtils.getRandomNumBetween(ContentConstants.UPPER_CONTENT_BOUNDARY + ContentConstants.CHARACTER_HEIGHT,ContentConstants.UPPER_CONTENT_BOUNDARY + ContentConstants.CONTENT_HEIGHT /4);
        do{
            nextX = coinType.getNextX(startX);
            nextY = coinType.getNextY(startY);
            Coin coin = new Coin(nextX,nextY);
            ElementBean.Interactive.getService().add(coin);
            coinYetToDraw--;
        }while (coinYetToDraw>0);

    }
}
