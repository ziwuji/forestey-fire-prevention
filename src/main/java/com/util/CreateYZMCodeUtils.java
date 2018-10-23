package com.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Auther: ziwuji
 * @Description:������֤��
 */
public class CreateYZMCodeUtils {
    private Integer width;//��֤��ͼƬ���
    private Integer height;//��֤��ͼƬ�߶�
    private Integer num;//��֤��ĸ���
    private String code;//������֤��һ���ַ���

    private static final Random ran=new Random();//�����
    private static CreateYZMCodeUtils createYZMCodeUtils;
    /**
     * ͨ��Ĭ�Ϲ����ʼ������
     */
    private CreateYZMCodeUtils(){
        width=100;
        height=30;
        code="123456789adcdefghijklmnopqrstuvwxyz";
        num=4;
    }
    /**
     * ���õ���ģʽ��������֤�빤����
     * @return
     */
    public static CreateYZMCodeUtils getInstance(){
        if(createYZMCodeUtils==null){
            createYZMCodeUtils=new CreateYZMCodeUtils();
        }
        return createYZMCodeUtils;
    }
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static Random getRan() {
        return ran;
    }
    public void setCreateYZMCodeUtils(Integer width,Integer height,Integer num,String code){
        this.width=width;
        this.height=height;
        this.num=num;
        this.code=code;
    }
    public void setCreateYZMCodeUtils(Integer width,Integer height,String code){
        this.width=width;
        this.height=height;
        this.code=code;
    }
    /**
     * ���������֤�� ������֤���һ���ַ���
     * @return
     */
    public String getCreateYZMCode(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            sb.append(code.charAt(ran.nextInt(code.length())));
        }
        return sb.toString();
    }
    /**
     * ����buffere ImageͼƬ
     * @param finshCode ���ɺõ���֤���ַ���
     * @return
     */
    public BufferedImage getCreateYZMImg(String finshCode){
        // ����BufferedImage����
        BufferedImage img = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = img.createGraphics();// ��������
        graphic.setColor(Color.WHITE);// ������ɫ
        graphic.fillRect(0, 0, width, height);//����Ԥ������ɫ���һ�����Σ��õ�һ����ɫ�ľ��ο顣
        graphic.setColor(Color.black);
//        graphic.drawRect(0, 0, width - 1, height - 1);// ��������
        // �������� ��� ��� �߶�
        Font font = new Font("΢���ź�", Font.BOLD + Font.ITALIC,(int) (height * 0.8));
        graphic.setFont(font);
        for (int i = 0; i < num; i++) {
            // �����������RGB��ɫ
            graphic.setColor(new Color(ran.nextInt(255), ran.nextInt(255),ran.nextInt(255)));
            // ������֤��
            graphic.drawString(String.valueOf(finshCode.charAt(i)), i* (width / num) + 4, (int) (height * 0.8));
        }
        for (int i = 0; i < (width + height); i++) {
            // �����������RGB��ɫ
            graphic.setColor(new Color(ran.nextInt(255), ran.nextInt(255),ran.nextInt(255)));
            // ���ɸ��ŵ�
            graphic.drawOval(ran.nextInt(width), ran.nextInt(height), 1, 1);
        }
        for(int i = 0; i <2; i++){
            // �����������RGB��ɫ
            graphic.setColor(new Color(ran.nextInt(255), ran.nextInt(255),ran.nextInt(255)));
            // ������ɸ�����
            graphic.drawLine(0, ran.nextInt(height), width,ran.nextInt(height));
        }
        return img;
    }
}
