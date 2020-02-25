package com.zdww.snake;

import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * 主界面
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int length; //蛇的长度
    int[] snakeX = new int[600]; //蛇的坐标X
    int[] snakeY = new int[500]; //蛇的坐标Y
    String fx; //方向：R:右，L：左，U：上，D：下

    boolean isStart = false; //游戏是否开始

    Timer timer = new Timer(100, this);

    // 食物
    int foodX;
    int foodY;
    Random random = new Random();

    // 死亡判断
    boolean isFail = false;

    // 积分系统
    int score;

    /**
     * 构造器
     */
    public GamePanel(){
        init();
        // 获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    /**
     * 初始化
     */
    public void init(){
        length = 3;
        // 头部坐标
        snakeX[0] = 100;
        snakeY[0] = 100;
        // 第一节身体
        snakeX[1] = 75;
        snakeY[1] = 100;
        // 第二节身体
        snakeX[2] = 50;
        snakeY[2] = 100;
        fx = "R";

        // 初始化食物
        foodX = 25 + 25 * random.nextInt(34);
        foodY = 75 + 25 * random.nextInt(24);

        score = 0;
    }

    // 画板：画界面，画蛇
    // Graphics：画笔
    @Override
    protected void paintComponent(Graphics g) {
        // 清屏
        super.paintComponent(g);
        // 设置背景颜色
        this.setBackground(Color.WHITE);

        // 绘制头部广告栏
        Data.header.paintIcon(this, g, 25, 11);

        // 绘制游戏区域
        g.fillRect(25, 75, 850, 600);

        // 画一条静态的小蛇
        if(fx.equals("R")){
            Data.headRight.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if(fx.equals("L")){
            Data.headLeft.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if(fx.equals("U")){
            Data.headTop.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if(fx.equals("D")){
            Data.headBottom.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        // 画积分
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD,18));
        g.drawString("长度：" + length,750,34);
        g.drawString("分数：" + score,750,52);

        // 画食物
        Data.food.paintIcon(this, g, foodX, foodY);

        // 游戏提示：是否开始
        if (!isStart){
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑", Font.BOLD,40));
            g.drawString("按下空格开始游戏",300,300);
        }

        // 失败提醒
        if(isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD,40));
            g.drawString("游戏失败，按下空格重新开始",200,300);
        }
    }

    //接收键盘的输入：监听
    // 键盘按下
    @Override
    public void keyPressed(KeyEvent e) {
        // 获取按下的是哪个键
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){
            if (isFail){ // 失败，重新开始
                isFail = false;
                init();
            } else {
                isStart = !isStart;
            }
            // 刷新界面
            repaint();
        }
        // 键盘控制方向
        if (keyCode == KeyEvent.VK_LEFT){
            fx = "L";
        } else if(keyCode == KeyEvent.VK_RIGHT){
            fx = "R";
        } else if(keyCode == KeyEvent.VK_UP){
            fx = "U";
        } else if(keyCode == KeyEvent.VK_DOWN){
            fx = "D";
        }
    }
    // 定时器，监听时间，执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStart && isFail == false){
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            if (fx.equals("R")){
                snakeX[0] += 25; // 头部移动
                // 边界判断
                if (snakeX[0] > 850){
                    snakeX[0] = 25;
                }
            } else if (fx.equals("L")){
                snakeX[0] -= 25; // 头部移动
                // 边界判断
                if (snakeX[0] < 25){
                    snakeX[0] = 850;
                }
            } else if(fx.equals("U")){
                snakeY[0] -= 25; // 头部移动
                // 边界判断
                if (snakeY[0] < 75){
                    snakeY[0] = 650;
                }
            } else if(fx.equals("D")){
                snakeY[0] += 25; // 头部移动
                // 边界判断
                if (snakeY[0] > 650){
                    snakeY[0] = 75;
                }
            }

            // 如果食物和小蛇头部重合
            if(snakeX[0] == foodX && snakeY[0] == foodY){
                length++;
                score += 10;

                // 重新生成食物
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);
            }

            // 结束判断
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                    isFail = true;
                }
            }

            repaint(); //刷新界面
        }
        timer.start(); //让时间动起来
    }


    // 键盘弹起
    @Override
    public void keyTyped(KeyEvent e) {

    }
    // 释放某个键
    @Override
    public void keyReleased(KeyEvent e) {

    }


}
