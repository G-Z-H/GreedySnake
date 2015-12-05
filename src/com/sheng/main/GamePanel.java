package com.sheng.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ��Ϸ��壬ͬʱ��Ϊһ���̴߳���
 * */

public class GamePanel extends JPanel implements Runnable{
	protected Snake snake;
	protected Food food;
	protected int width,height;
	
	GamePanel(Snake snake,int width,int height){
		this.snake=snake;
		this.width=width;
		this.height=height;
		this.setBounds(0, 0, width, height);
		this.setBackground(Color.GRAY);
	}
	/**
	 * �������ʳ��
	 * */
	public Food createFood(){
		return new Food(snake, this);
	}
	
	/**
	 * ��д�����Ʒ��������������ʳ��
	 * */
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		snake.setGamePanel(this);
		g.setColor(Color.BLUE);
		g.fill3DRect(snake.head.x,snake.head.y, 8, 8,true);//������ͷ
		
		g.setColor(Color.CYAN);
		Iterator<Point> it=snake.body.iterator();
		Point tmpNode;
		while(it.hasNext()){
			tmpNode=it.next();
			g.fill3DRect(tmpNode.x, tmpNode.y, 8, 8,true);//���������ÿһ���ڵ�
		}
		
		if(food!=null){
			switch(food.flag){
			case 0:
				g.setColor(Color.RED);
				break;
			case 1:
				g.setColor(Color.GREEN);
				break;
			case 2:
				g.setColor(Color.YELLOW);
				break;
			}
			g.fill3DRect(food.pos.x, food.pos.y, 8, 8,true);//����ʳ��
		}
	}
	
	/**
	 * ��д�߳����з�����������ѭ�������̲߳����ս�
	 * �߳�ÿ˯��200ms��ʼ����
	 * ����ʼ�˶�һ��
	 * ���û��ʳ�����ʳ�ﱻ�Ե������»���ʳ��
	 * ��������������������
	 * ���ҧ���Լ��������������
	 * �����ػ�
	 * */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		snake.setGamePanel(this);
		try {
			while(true){
				Thread.sleep(200);
				snake.move();
				
				if(food==null || snake.isEat(food)){
					food=createFood();
				}
				
				if(snake.isOut()){
					snake.dead();
				}
				
				if(snake.isPitch()){
					if(snake.throughBody==0){
						snake.dead();
					}
					snake.throughBody--;
				}
				
				repaint();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
