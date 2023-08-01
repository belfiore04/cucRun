package auxiliary.service;

import Content.Map.Blocker;
import Content.Map.Coin;
import Content.Player;
import auxiliary.Audio;
import auxiliary.SharedCoordinate;
import auxiliary.CommonConstants;
import base.BaseElement;
import base.BaseService;

import java.awt.*;

public class InteractiveElementService<T extends BaseElement> extends BaseService<T> {
    @Override
    public void init(){

    }
    public final void action(Player player , InteractiveElementService... services){
        this.getElementList().forEach(element -> {
            if(this.isElementToRemove(element,player)){
                this.remove(element);
                return;
            }
            if(!element.beforeActionJudge()){
                return;
            }
            Rectangle myself = element.getRect();
            Rectangle playerRect = player.getRect();
            if(myself.intersects(playerRect)){
                if(element instanceof Coin){
                    this.remove(element);
                    player.getPlayerState().addCoin();
                    Audio.Coin.play();
                }else {
                    player.isHurt();
                }
            }
            element.action();
        });
    }
    protected boolean isElementToRemove(BaseElement element,Player player){
        return element.remove(player) || element.getX() + SharedCoordinate.getX()< CommonConstants.FRAME_WIDTH/-2;
    }
    protected boolean hasEncounteredPlayer(T element,Player player){
        return element.encounterPlayer(player);
    }
    @Override
    public final void drawImage(Graphics g){
        this.getElementList().forEach(i -> {
            if(!(i instanceof Blocker))
                i.drawImage(g);
        });
    }
}
