package Content.Map;

import auxiliary.CommonUtils;
import auxiliary.ContentConstants;
import auxiliary.ElementType.BlockerDirection;
import auxiliary.ElementType.BlockerLength;
import auxiliary.SharedCoordinate;
import base.BaseElement;
import base.ElementBasicProperties;

import java.awt.*;
@ElementBasicProperties()
public class Blocker extends BaseElement {
    public Blocker(int startX, int startY, BlockerDirection blockerDirection, BlockerLength blockerLength){
        super(0,0);
        x = startX;
        y = startY;
        this.blockerDirection = blockerDirection;
        this.blockerLength = blockerLength;
        width = this.getWidth();
        height = this.getHeight();
    }
    private BlockerDirection blockerDirection;
    private BlockerLength blockerLength;
    private int startX;

    public Image getImage(){
       if(blockerDirection == BlockerDirection.HORIZONTAL)
           return CommonUtils.getImage("blocker_"+ blockerLength.name() + "_horizontal.png");
        return CommonUtils.getImage("blocker_" + blockerLength.name() + "_vertical.png");
    }
    public int getWidth(){
        return this.blockerDirection == BlockerDirection.HORIZONTAL ? blockerLength.getLength() : ContentConstants.BLOCKER_WIDTH;
    }
    public int getHeight(){
        return this.blockerDirection == BlockerDirection.HORIZONTAL ? ContentConstants.BLOCKER_WIDTH : blockerLength.getLength();
    }
    @Override
    public Rectangle getRect(){
        return new Rectangle(x+ SharedCoordinate.getX(),y,width,height);
    }
    @Override
    public void drawImage(Graphics g) {
        Image image = this.getImage();
        g.drawImage(image,x + SharedCoordinate.getX(),y,width,height,null);
    }}
