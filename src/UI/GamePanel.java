package UI;

import base.Drawable;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Drawable[] drawables;//待绘制的元素
    private Image image;//缓冲

    public GamePanel(Drawable... drawables) {
        this.drawables = drawables;
    }
    /**
     * 绘制缓冲
     */
    private void drawBufferedImage() {
        image = createImage(this.getWidth(), this.getHeight());
        Graphics g = image.getGraphics();
        //绘制
        for (Drawable drawables : this.drawables) {
            drawables.drawImage(g);
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBufferedImage();
        g.drawImage(image, 0, 0, this);
    }
}
