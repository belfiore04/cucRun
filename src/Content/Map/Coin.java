package Content.Map;

import auxiliary.CommonUtils;
import auxiliary.SharedCoordinate;
import base.BaseElement;
import base.ElementBasicProperties;

import java.awt.*;

@ElementBasicProperties("coin.png")
public class Coin extends BaseElement {
    public Coin(int x,int y) {
        super(x,y);
        this.height = 48;
        this.width = 48;
    }
    @Override
    public void drawImage(Graphics g) {
        Image image = CommonUtils.getImage("coin.png");
        g.drawImage(image,x + SharedCoordinate.getX(),y,width,height,null);
    }
    @Override
    public Rectangle getRect(){
        return new Rectangle(x+ SharedCoordinate.getX(),y,width,height);
    }
}

