package UI;

import Content.GameContent;
import Content.Player;
import auxiliary.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {

    public GameFrame() {

        this.setTitle("CUC RUN");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);//固定窗体
        //窗体居中
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = new Dimension(CommonConstants.FRAME_WIDTH, CommonConstants.FRAME_HEIGHT);
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        this.setBounds((int) (width - size.getWidth()) / 2,
                (int) (height - size.getHeight()) / 3, (int) size.getWidth(), (int) size.getHeight());
        init();
    }
    private void init() {
        //游戏内容
        ElementBean.init();//初始化游戏服务
        //玩家
        Player player = new Player();
        //游戏内容
        GameContent gameContent = new GameContent(player);
        //画板
        GamePanel gamePanel = new GamePanel(gameContent,player);

        //显示窗体
        this.add(gamePanel);
        this.setVisible(true);

        //更新面板内容
        CommonUtils.task(0,CommonConstants.REFRESH_PERIOD, () -> {
            gamePanel.repaint();
            if (player.isDead()) {
                CommonConstants.TIMER_STOP = true;//计时器结束开关为真，意味着游戏结束
                Audio.GameOver.play();
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.dispose();
                System.exit(0);//此处可以替换为打开新的窗体等
            }
        });
        //玩家键盘监听
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Keys.add(e.getKeyCode());
            }
            @Override
            public void keyReleased(KeyEvent e) {
                Keys.remove(e.getKeyCode());
            }
        });
    }
    public static void main(String[] args) {
        new GameFrame();
    }
}
