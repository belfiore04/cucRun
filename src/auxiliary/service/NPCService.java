package auxiliary.service;

import Content.NPC.Base.NPC;
import base.BaseElement;
import base.BaseService;
import base.IBaseService;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class NPCService extends BaseService<NPC> {
    @Override
    public void drawImage(Graphics g){
        this.getElementList().forEach(i -> i.drawImage(g));
    }
    public void action(){
        this.getElementList().forEach(BaseElement::action);
    }
}
