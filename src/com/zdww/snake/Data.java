package com.zdww.snake;

import javax.swing.*;
import java.net.URL;

/**
 * 存放外部数据
 */
public class Data {
    // 头部的图片
    // URL：定位图片的地址   ImageIcon：图片
    public static URL headerUrl = Data.class.getResource("/static/header.png");
    public static ImageIcon header = new ImageIcon(headerUrl);

    // 头部
    public static URL headTopUrl = Data.class.getResource("/static/headTop.png");
    public static URL headRightUrl = Data.class.getResource("/static/headRight.png");
    public static URL headLeftUrl = Data.class.getResource("/static/headLeft.png");
    public static URL headBottomUrl = Data.class.getResource("/static/headBottom.png");
    public static ImageIcon headTop = new ImageIcon(headTopUrl);
    public static ImageIcon headRight = new ImageIcon(headRightUrl);
    public static ImageIcon headLeft = new ImageIcon(headLeftUrl);
    public static ImageIcon headBottom = new ImageIcon(headBottomUrl);

    // 身体
    public static URL bodyUrl = Data.class.getResource("/static/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);

    // 食物
    public static URL foodUrl = Data.class.getResource("/static/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);
}
