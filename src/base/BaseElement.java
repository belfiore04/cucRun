package base;

import Content.Player;
import auxiliary.CommonUtils;
import auxiliary.Direction;

import java.awt.*;

public abstract class BaseElement implements Drawable {
    protected int x,y; //coordination of the element
    /*
    displayed size of element,
    initialized by children class
     */
    protected int width,height;
    protected int rectWidth,rectHeight;
    protected Image image;
    /*
    direction in which element will move to
     */
    protected Direction direction;
    protected float xSpeed,ySpeed;
    public BaseElement(int x,int y){
        ElementBasicProperties properties = this.getClass().getAnnotation(ElementBasicProperties.class);
        this.width = properties.characterWidth();
        this.height = properties.characterHeight();
        rectHeight = this.height;
        rectWidth = this.width;
        this.x = x;
        this.y = y;
        this.image = CommonUtils.getImage(properties.value());
    }

    /**
     * 元素与玩家相遇的处理逻辑 留空等待子类重写
     * @param player
     */
    public boolean encounterPlayer(Player player) {
        return false;
    }

    /**
     * 元素动作方法，包含基本的位移逻辑
     */
    public void action(){
        this.xMove();
        this.yMove();
    }

    protected void xMove(){

    }

    /**
     * y方向位移，留空给子类实现
     */
    protected void yMove(){

    }
    public Rectangle getRect(){
        return new Rectangle(this.x,this.y,this.rectWidth,this.rectHeight);
    }

    /**
     * 元素相交判定
     * @param element
     * @return
     * @param <E>
     */
    public <E extends BaseElement> boolean intersects(E element){
        return this.getRect().intersects(element.getRect());
    }
    /**
     * 元素移除方法，默认返回否，等待子类重写
     */
    public boolean remove(Player player){
        return false;
    }
    @Override
    public void drawImage(Graphics g){
        g.drawImage(this.getImage(),this.x,this.y,this.width,this.height,null);
    }
    protected Image getImage(){
        return image;
    }
    public boolean beforeActionJudge(){
        return true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public float getxSpeed() {
        return xSpeed;
    }
    public float getySpeed(){
        return ySpeed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }
}
