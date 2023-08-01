package Content;

import auxiliary.Audio;
import auxiliary.CommonConstants;
import auxiliary.CommonUtils;
import auxiliary.Keys;
import base.Drawable;

import java.awt.*;

public class PlayerState implements Drawable {
    protected Attribute hpGauge = new Attribute(3,3);
    protected Attribute score = new Attribute(0);
    protected Attribute callbackGauge = new Attribute(0,100);
    protected Attribute isHurtGauge = new Attribute(3000,3000);//满表示未被伤害，被伤害时归零并缓慢回复
    protected Attribute coinGauge = new Attribute(0);
    private Image runImage = CommonUtils.getImage("player_run.gif");
    private Image callbackImage = CommonUtils.getImage("player_callback.gif");
    private Image callbackIcon = CommonUtils.getImage("skill_e.gif");
    private Image hpImage = CommonUtils.getImage("hp.gif");
    private Image flyImage = CommonUtils.getImage("player_fly.gif");
    private Image fallImage = CommonUtils.getImage("player_fall.gif");
    private Image hurtImage = CommonUtils.getImage("player_hurt.gif");
    private Image coinImage = CommonUtils.getImage("coin.png");
    private Image gameOverSign = CommonUtils.getImage("gameover.png");

    @Override
    public void drawImage(Graphics g) {
        g.drawImage(hpImage, 2, 2, 24, 24, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("幼圆", Font.BOLD, 24));
        g.drawString("×" + hpGauge.getValue(), 24, 24);
        g.drawImage(callbackIcon,130,2,24,24,null);
        g.drawString(" "+callbackGauge.getValue(),150,24);
        g.drawString("SCORE:" + score.getValue(), 300, 24);
        g.drawImage(coinImage,600,2,24,24,null);
        g.drawString(" " + coinGauge.getValue(), 620,24);
        if (!hpGauge.isHealthy()) {
            g.drawImage(gameOverSign, CommonConstants.FRAME_WIDTH / 2 - 200, 200, 400, 71, null);
        }
    }
    public void addCoin(){
        this.coinGauge.add();
    }
    public Image getImage(Player player){
        if(player.callBackSkill.isSkillPerforming){
            return callbackImage;
        }
        if(!player.playerState.isHurtGauge.isMax()){
            return hurtImage;
        }
        if(Keys.FLY.use()){
            return flyImage;
        }
        if(!Keys.FLY.use() && !player.isOnTheGround()){
            return fallImage;
        }
        return runImage;
    }
    public PlayerStateEnum getPlayerState(Player player){
        if(player.callBackSkill.isSkillPerforming){
            return PlayerStateEnum.CALL_BACK;
        }
        if(!player.playerState.isHurtGauge.isMax()){
            return PlayerStateEnum.HURT;
        }
        if(Keys.FLY.use()){
            return PlayerStateEnum.FLY;
        }
        if(!Keys.FLY.use() && !player.isOnTheGround()){
            return PlayerStateEnum.FLY;
        }
        return PlayerStateEnum.RUN;
    }
}
