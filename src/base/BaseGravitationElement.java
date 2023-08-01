package base;

/**
 * 受到某种引力影响的元素
 */
public abstract class BaseGravitationElement extends BaseElement implements IGravitation {
    protected boolean isOnTheGround = true;
    protected float yAcceleration = 0;
    protected float defaultYSpeed = 0;
    protected float ySpeed;
    protected float yMaxUpSpeed = Integer.MAX_VALUE;
    protected float yMaxDownSpeed = yMaxUpSpeed;

    public BaseGravitationElement(int x, int y) {
        super(x, y);
    }
    @Override
    public float getYSpeed(){
        return ySpeed;
    }
    @Override
    public float getDefaultYSpeed(){
        return defaultYSpeed;
    }
    @Override
    public float getYMaxSpeed() {
        return yMaxUpSpeed;
    }
    @Override
    public float getYMaxDownSpeed(){
        return yMaxDownSpeed;
    }
    @Override
    public float getYAcceleration(){
        return yAcceleration;
    }
    @Override
    public boolean isOnTheGround(){
        return this.isOnTheGround;
    }
    @Override
    public void setOnTheGround(boolean isOnTheGround){
        this.isOnTheGround = isOnTheGround;
    }


    @Override
    public void setYAcceleration(float acceleration){
       this.yAcceleration = acceleration;
    }
    @Override
    public void setYSpeed(float speed) {
        this.ySpeed = speed;
    }
}
