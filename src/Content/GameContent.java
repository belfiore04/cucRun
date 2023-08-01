package Content;

import Content.NPC.Base.NPC;
import UI.GameFrame;
import auxiliary.*;
import auxiliary.Generator.NPCGenerator;
import auxiliary.Generator.TerrainGenerator;
import auxiliary.service.GravityService;
import auxiliary.service.InteractiveElementService;
import auxiliary.service.NPCService;
import base.Drawable;

import java.awt.*;

public class GameContent implements Drawable {

    public GameContent(Player player){
        GravityService gravityService = new GravityService();
        gravityService.add(player);
        CommonUtils.task(0,3 * CommonConstants.REFRESH_PERIOD,() -> {
            player.action();
            InteractiveElementService interactiveElementService = (InteractiveElementService) ElementBean.Interactive.getService();
            interactiveElementService.action(player);
            NPCService npcService = (NPCService) ElementBean.NPC.getService();
            npcService.action();
            gravityService.setGravitatedY();
            AudioChecker.checkAudio(player.playerState.getPlayerState(player));
        });
        CommonUtils.task(0,1000,()->{
            this.buildNPC(player);
        });
    }
    private int NPCCount = 0;
    private void buildNPC(Player player){
        NPCCount++;
        if(NPCCount < 4){
            NPCGenerator.buildNPC(player);
        }
        CommonUtils.task(0,10000,()->{
            NPCCount--;
        });
    }
    @Override
    public void drawImage(Graphics g) {
        for(ElementBean elementBean : ElementBean.values()){
            elementBean.getService().drawImage(g);
        }
    }
}
