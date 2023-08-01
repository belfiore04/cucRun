package Content.NPC;

import Content.NPC.Base.INPC;
import Content.NPC.Base.NPC;
import auxiliary.CommonUtils;
import base.ElementBasicProperties;

@INPC(probability = 60,number = 2)
@ElementBasicProperties("npc_girl_a_left.gif")
public class GirlA extends NPC {
    public GirlA(int x, int y ,int xSpeed) {
        super(x,y,xSpeed);
        if(this.xSpeed > 0)
            this.image = CommonUtils.getImage("npc_girl_a_right.gif");
    }
}
