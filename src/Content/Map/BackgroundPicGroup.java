package Content.Map;

import auxiliary.*;
import base.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 实现背景图片读取、循环播放
 */
public class BackgroundPicGroup implements Drawable {
    Image foregroundBoard1, foregroundBoard2;
    Image backgroundBoard1,backgroundBoard2;
    Image bg_1,bg_2,bg_3,bg_4,bg_5,bg_6,bg_7,bg_8;
    Image bg_cloud_1,bg_cloud_2;
    private List <Image> imageList;
    public BackgroundPicGroup(){
        SharedCoordinate.init();
        bg_1 = CommonUtils.getImage("bg_building_1.png");
        bg_2 = CommonUtils.getImage("bg_building_2.png");
        bg_3 = CommonUtils.getImage("bg_building_3.png");
        bg_4 = CommonUtils.getImage("bg_building_4.png");
        bg_5 = CommonUtils.getImage("bg_building_5.png");
        bg_6 = CommonUtils.getImage("bg_building_6.png");
        bg_7 = CommonUtils.getImage("bg_building_7.png");
        bg_8 = CommonUtils.getImage("bg_building_8.png");
        bg_cloud_1 = CommonUtils.getImage("bg_cloud_1.png");
        bg_cloud_2 = CommonUtils.getImage("bg_cloud_2.png");
        CreatePicBoards();
    }
    public void CreatePicBoards(){
        //前景建筑
        foregroundBoard1 = new BufferedImage(CommonConstants.FRAME_WIDTH * ContentConstants.BUILDING_PIC_NUM, CommonConstants.FRAME_HEIGHT,BufferedImage.TYPE_INT_ARGB);
        Graphics g = foregroundBoard1.getGraphics();
        g.drawImage(bg_1,0,0, CommonConstants.FRAME_WIDTH, CommonConstants.FRAME_HEIGHT,null);
        g.drawImage(bg_2, CommonConstants.FRAME_WIDTH,0, CommonConstants.FRAME_WIDTH, CommonConstants.FRAME_HEIGHT,null);
        g.drawImage(bg_3, CommonConstants.FRAME_WIDTH * 2,0, CommonConstants.FRAME_WIDTH, CommonConstants.FRAME_HEIGHT,null);
        g.drawImage(bg_4, CommonConstants.FRAME_WIDTH * 3,0, CommonConstants.FRAME_WIDTH, CommonConstants.FRAME_HEIGHT,null);
        g.drawImage(bg_5, CommonConstants.FRAME_WIDTH * 4,0, CommonConstants.FRAME_WIDTH, CommonConstants.FRAME_HEIGHT,null);
        g.drawImage(bg_6, CommonConstants.FRAME_WIDTH * 5,0, CommonConstants.FRAME_WIDTH, CommonConstants.FRAME_HEIGHT,null);
        g.drawImage(bg_7, CommonConstants.FRAME_WIDTH * 6,0, CommonConstants.FRAME_WIDTH, CommonConstants.FRAME_HEIGHT,null);
        g.drawImage(bg_8, CommonConstants.FRAME_WIDTH * 7,0, CommonConstants.FRAME_WIDTH, CommonConstants.FRAME_HEIGHT,null);
        foregroundBoard2 = foregroundBoard1;
        //背景云朵：运动速度较慢
        backgroundBoard1 = new BufferedImage(CommonConstants.FRAME_WIDTH * ContentConstants.CLOUD_PIC_NUM,CommonConstants.FRAME_HEIGHT,BufferedImage.TYPE_INT_ARGB);
        g = backgroundBoard1.getGraphics();
        g.drawImage(bg_cloud_1,0,0, CommonConstants.FRAME_WIDTH, CommonConstants.FRAME_HEIGHT,null);
        g.drawImage(bg_cloud_2,CommonConstants.FRAME_WIDTH,0, CommonConstants.FRAME_WIDTH, CommonConstants.FRAME_HEIGHT,null);
        backgroundBoard2 = backgroundBoard1;
    }

    public void loopPic(Graphics g){
        g.drawImage(backgroundBoard1,(int)(SharedCoordinate.getX() * 0.25),0, CommonConstants.FRAME_WIDTH * ContentConstants.CLOUD_PIC_NUM, CommonConstants.FRAME_HEIGHT,null);
        g.drawImage(backgroundBoard2,(int)(SharedCoordinate.getX() * 0.25) + CommonConstants.FRAME_WIDTH * ContentConstants.CLOUD_PIC_NUM,0,CommonConstants.FRAME_WIDTH * ContentConstants.CLOUD_PIC_NUM,CommonConstants.FRAME_HEIGHT,null);
        g.drawImage(foregroundBoard1,SharedCoordinate.getX(),0, CommonConstants.FRAME_WIDTH * ContentConstants.BUILDING_PIC_NUM, CommonConstants.FRAME_HEIGHT,null);
        g.drawImage(foregroundBoard2,SharedCoordinate.getX() + CommonConstants.FRAME_WIDTH * ContentConstants.BUILDING_PIC_NUM,0,CommonConstants.FRAME_WIDTH * ContentConstants.BUILDING_PIC_NUM,CommonConstants.FRAME_HEIGHT,null);
    }
    @Override
    public void drawImage(Graphics g) {
        loopPic(g);
    }
}
