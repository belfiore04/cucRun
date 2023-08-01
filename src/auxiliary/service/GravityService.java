package auxiliary.service;

import auxiliary.SharedCoordinate;
import auxiliary.ContentConstants;
import base.BaseService;
import base.IGravitation;

/**
 * 重力元素y坐标计算
 */
public class GravityService extends BaseService<IGravitation> {
    public void setGravitatedY(){
        this.getElementList().forEach(gravity -> {
            if(!gravity.isOnTheGround()){
                //如果与最大速度接近，就限制速度
                if((gravity.getYSpeed() < 0) && gravity.getYSpeed() - gravity.getYMaxSpeed() >= 0.1
                        || gravity.getYSpeed() > 0 && gravity.getYMaxDownSpeed() - gravity.getYSpeed() >= 0.1){
                    gravity.setYSpeed(gravity.getYSpeed()+ gravity.getYAcceleration());
                } else gravity.setYSpeed((float) 4.8 * gravity.getYSpeed()/Math.abs(gravity.getYSpeed()));
                //接近上边界强制拉回
                if(gravity.getY() >= ContentConstants.UPPER_CONTENT_BOUNDARY && !SharedCoordinate.isReversedCoordinate)
                    gravity.setY((int)(gravity.getY() + gravity.getYSpeed()));
                else if(gravity.getY() >= ContentConstants.UPPER_CONTENT_BOUNDARY && SharedCoordinate.isReversedCoordinate)
                    gravity.setY(gravity.getY());
                else gravity.setY(ContentConstants.UPPER_CONTENT_BOUNDARY);
                //如果十分接近地面，就把onTheGround设为true
                if(gravity.getYSpeed() > 0 && ContentConstants.GROUND_HEIGHT - ContentConstants.CHARACTER_HEIGHT - gravity.getY() <= 1){
                    gravity.setOnTheGround(true);
                    gravity.setYSpeed(gravity.getDefaultYSpeed());
                    gravity.setYAcceleration(0);
                }
//                System.out.println("y:"+gravity.getY()+" | speed:"+gravity.getYSpeed()+" | acceleration: "+gravity.getYAcceleration()+" | onTheGround :"+gravity.isOnTheGround());
            }
        });
    }
}
