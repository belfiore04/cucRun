package Content;

import auxiliary.CommonUtils;
import auxiliary.ContentConstants;
import auxiliary.Keys;
import base.BaseGravitationElement;
import base.ElementBasicProperties;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

@ElementBasicProperties("player_run.gif")
public class Player extends BaseGravitationElement {
    protected PlayerState playerState;//角色属性仓库
    protected Skill callBackSkill;
    public Player(){
        super(200, ContentConstants.GROUND_HEIGHT - ContentConstants.CHARACTER_HEIGHT);
        this.rectHeight = height-5;
        this.rectWidth = width - 5;
        defaultYSpeed = -1.5f;
        ySpeed = defaultYSpeed;
        yMaxUpSpeed = -5F;
        yMaxDownSpeed = 6.5f;
        this.playerState = new PlayerState();
        this.callBackSkill = new CallBackSkill(this,playerState.callbackGauge,Keys.E_SKILL);
        CommonUtils.task(0,50,()->{
            playerState.score.add();
        });
    }
    @Override
    public void action(){
        super.action();
        this.callBackSkill.action();
    }
    @Override
    public void drawImage(Graphics g){
        super.drawImage(g);
        this.playerState.drawImage(g);
    }
    @Override
    protected Image getImage(){
        return this.playerState.getImage(this);
    }
    @Override
    protected void yMove(){
        if(Keys.FLY.use()){
            this.image = CommonUtils.getImage("player_fly.gif");
            isOnTheGround = false;
            yAcceleration = -0.4f;
        }
        else{
            this.image = CommonUtils.getImage("player_run.gif");
            yAcceleration = 0.6f;
        }
    }
    public void isHurt(){
        playerState.hpGauge.subtract();
        playerState.isHurtGauge.setValue(0);
        CommonUtils.task(4000,0,1,() -> {
            if(playerState.isHurtGauge.getValue() < 3000){
                playerState.isHurtGauge.add();
            }
        });
        setCanNotBeHurt();
        CommonUtils.task(80,0,2,() -> {
            x--;
        });
        CommonUtils.task(15000,1050,150, () ->{
            x++;
        });
    }

    /**
     * 玩家锁血1.5秒
     */
    public void setCanNotBeHurt(){
            CommonUtils.task(0,() -> {
                this.rectHeight = 0;
                this.rectWidth = 0;
            });
            CommonUtils.task(4000,() -> {
                this.rectWidth = width - 5;
                this.rectHeight = height - 5;
        });

    }
    public boolean isDead() {
        return !this.playerState.hpGauge.isHealthy();
    }
    public PlayerState getPlayerState(){
        return this.playerState;
    }
}
