package Content;

import auxiliary.Audio;
import auxiliary.CommonUtils;
import auxiliary.ContentConstants;
import auxiliary.Keys;
import base.BaseElement;

public class Skill{
    private Keys[] keys;
    protected Attribute attribute;//需要扣减的属性
    protected Player player;
    public boolean isSkillPerforming,isSkillCharging = false;

    public Skill(Player player,Attribute attribute, Keys... keys){
        this.attribute = attribute;
        this.keys = keys;
        this.player = player;
    }
    /**
     * 技能充能、技能释放判断
     */
    public void action() {
        if(keysJudge() && attribute.isMax()){
            isSkillPerforming = true;
            perform();
            attribute.setValue(0);
        }
        if(!isSkillPerforming && !attribute.isMax() &&!isSkillCharging){
            isSkillCharging = true;
            CommonUtils.task(10000,0, 50,() -> {
                if(!attribute.isMax() && !isSkillPerforming){
                    attribute.add();
                }
                if(attribute.isMax()){
                    isSkillCharging = false;
                }
            });
        }

    }
    public void perform(){

    }

    protected boolean keysJudge() {
        for (Keys key : keys) {
            if(key.use())
                return true;
        }
        return false;
    }
}
