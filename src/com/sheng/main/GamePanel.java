package com.sheng.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * 游戏面板，同时作为一个线程存在
 * */

public class GamePanel extends JPanel implements Runnable{
	protected Snake snake;
	protected Food food;
	protected boolean isPaused;//暂停标志
	protected int x,y,width,height;//主游戏面板要添加状态面板，故游戏面板的初始位置需要改变，故添加x,y属性
	
	GamePanel(int x,int y,int width,int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.isPaused=false;
		this.setBounds(this.x, this.y, this.width, this.height);
		this.setBackground(new Color(143, 188, 143));
		this.setLayout(null);
		this.setBorder(new LineBorder(new Color(0, 0, 128)));
		addSnake(new Snake(this));
	}
	/**
	 * 为面板添加一条蛇
	 * */
	public void addSnake(Snake snake){
		this.snake=snake;
	}
	
	/**
	 * 随机产生食物
	 * */
	public Food createFood(){
		return new Food(snake, this);
	}
	
	/**
	 * 重写面板绘制方法，绘制蛇身和食物
	 * */
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.BLUE);
		g.fill3DRect(snake.head.x,snake.head.y, 8, 8,true);//绘制蛇头
		
		g.setColor(Color.CYAN);
		Iterator<Point> it=snake.body.iterator();
		Point tmpNode;
		while(it.hasNext()){
			tmpNode=it.next();
			g.fill3DRect(tmpNode.x, tmpNode.y, 8, 8,true);//绘制蛇身的每一个节点
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
			g.fill3DRect(food.pos.x, food.pos.y, 8, 8,true);//绘制食物
		}
	}
	
	/**
	 * 重写线程运行方法：做成死循环，则线程不会终结
	 * 线程每睡眠200ms后开始运行
	 * 蛇身开始运动一次
	 * 如果没有食物或者食物被吃掉则重新绘制食物
	 * 如果出界则调用死亡方法
	 * 如果咬到自己则调用死亡方法
	 * 进行重绘
	 * */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true){
				//根据游戏级别来调整线程睡眠时间的长短
				switch(snake.level){
				case 1:
					Thread.sleep(200);
					break;
				case 2:
					Thread.sleep(150);
					break;
				case 3:
					Thread.sleep(100);
					break;
				}
				
				if(!isPaused){
					snake.move();
				}
				
				if(food==null || snake.isEat(food)){
					food=createFood();
				}
				//进行级别的提升
				if(snake.score>10 && snake.score<=20){
					snake.level=2;
				}else if(snake.score>20){
					snake.level=3;
				}
				
				if(snake.isOut()){
					snake.dead(this);
					break;
				}
				
				if(snake.isPitch()){
					if(snake.throughBody==0){
						snake.dead(this);
						break;
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
