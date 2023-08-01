package auxiliary.service;

import Content.Map.BackgroundPicGroup;
import base.BaseService;

import java.awt.*;

/**
 * 图片背景->、需要两层、需要缓存、需要向左移动、需要能闪回
 * 闪回功能：从缓存中读取
 * @param
 */
public class BackgroundService extends BaseService {

    public void init(){
        backgroundPicGroup = new BackgroundPicGroup();
    }
    private BackgroundPicGroup backgroundPicGroup;

    @Override
    public void drawImage(Graphics g){
        backgroundPicGroup.drawImage(g);
    }

}
