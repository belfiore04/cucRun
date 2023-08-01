package Content.NPC.Base;

import base.BaseElement;

import java.awt.*;

public class NPC extends BaseElement {
    public NPC(int x, int y,int xSpeed) {
        super(x, y);
        this.xSpeed = xSpeed;
    }
    @Override
    public void xMove(){
        this.x += xSpeed;
    }
}
