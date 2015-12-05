package com.sheng.main;

import java.awt.Point;
import java.util.Random;

public class Food {
	protected Point pos;
	protected int flag;//��ʾ��ͬ��ʳ�0��ʾһ��ʳ�ֻ���ӳ��ȣ���ɫ�죻1��ʾ�����Ӵ������ʳ���ɫ�̣�2��ʾ�����Ӵ�ǽ����ʳ���ɫ��
	protected Snake snake;
	protected GamePanel gamePanel;
	
	Food(Snake snake,GamePanel gamePanel){
		this.snake=snake;
		this.gamePanel=gamePanel;
		
		/**
		 * �������ʳ��λ���㷨������õ�һ�����������������߽�����ģ���õ�λ�ú�Ҫ����λ����������Ϊÿ���ڵ�Ŀ�߶���8����ôʳ��������ֵ�λ��
		 * ������8�ı�������Ȼ����ִ�λ���߳Բ���ʳ���������������������ȳ���8�õ�С��Ȼ�����8
		 * */
		Point tmpNode=new Point(1, 1);
		do{
			tmpNode.x=(Math.abs(new Random().nextInt()))%(gamePanel.getWidth());
			tmpNode.y=(Math.abs(new Random().nextInt()))%(gamePanel.getHeight());
			tmpNode.x=tmpNode.x/8*8;
			tmpNode.y=tmpNode.y/8*8;
		}while(snake.head.equals(tmpNode) || snake.body.contains(tmpNode));
		pos=tmpNode;
		
		/**
		 * �������ʳ����ɫ�㷨�������С����Χ��0.0��0.2֮������ɴ�ǽʳ�����Χ��0.9��1.0֮������ɴ���ʳ����෶Χ����һ��ʳ��
		 * */
		double tmpFlag=new Random().nextDouble();
		if(tmpFlag<0.2){
			flag=2;
		}else if(tmpFlag>=0.2 && tmpFlag<0.9){
			flag=0;
		}else{
			flag=1;
		}
	}
}