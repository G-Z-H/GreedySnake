package com.sheng.main;

import java.awt.Point;
import java.util.Random;

public class Food {
	protected Point pos;
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
	}
}