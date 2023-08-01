package Content.NPC;

import Content.NPC.Base.INPC;
import Content.NPC.Base.NPC;
import auxiliary.CommonUtils;
import base.ElementBasicProperties;

@INPC(probability = 40,number = 3)
@ElementBasicProperties("npc_girl_b_left.gif")
public class GirlB extends NPC {
    public GirlB(int x, int y ,int xSpeed) {
        super(x, y,xSpeed);
        if (this.xSpeed > 0)
            this.image = CommonUtils.getImage("npc_girl_b_right.gif");
    }
}
