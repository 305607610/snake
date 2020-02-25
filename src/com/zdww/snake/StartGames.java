package com.zdww.snake;

import javax.swing.*;

/**
 * 启动类
 */
public class StartGames {
    public static void main(String[] args) {
        // 绘制一个静态窗口
        JFrame jFrame = new JFrame("贪吃蛇小游戏");
        // 设置界面大小
        jFrame.setBounds(100,100,900,720);
        // 窗口大小不可改变
        jFrame.setResizable(false);
        // 设置关闭事件
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.add(new GamePanel());

        // 让窗口可以显示出来
        jFrame.setVisible(true);
    }
}
