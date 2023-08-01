package Content.NPC;

import Content.NPC.Base.INPC;
import Content.NPC.Base.NPC;
import auxiliary.CommonUtils;
import base.ElementBasicProperties;

@INPC(probability = 80,number = 1)
@ElementBasicProperties("npc_boy_left.gif")
public class Boy extends NPC {
    public Boy(int x, int y ,int xSpeed) {
        super(x, y,xSpeed);
        if (this.xSpeed > 0)
            this.image = CommonUtils.getImage("npc_boy_right.gif");
    }
}
