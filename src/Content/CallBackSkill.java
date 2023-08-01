package Content;

import auxiliary.*;


public class CallBackSkill extends Skill {
    public CallBackSkill(Player player,Attribute attribute, Keys... keys){
        super(player,attribute,keys);
    }
    @Override
    public void perform(){
        SharedCoordinate.setIsReversedCoordinate(true);
        CommonUtils.task(ContentConstants.CALLBACK_TIME_MS,() -> {
            SharedCoordinate.setIsReversedCoordinate(false);
            isSkillPerforming = false;
        });
    }
}
