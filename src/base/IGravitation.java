package base;

public interface IGravitation {
    boolean isOnTheGround();
    int getY();
    void setY(int y);
    float getDefaultYSpeed();
    float getYMaxSpeed();
    float getYSpeed();
    float getYAcceleration();
    void setYSpeed(float ySpeed);
    void setYAcceleration(float acceleration);
    void setOnTheGround(boolean isOnTheGround);
    float getYMaxDownSpeed();
}
