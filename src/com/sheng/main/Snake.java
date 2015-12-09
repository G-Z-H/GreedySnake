package com.sheng.main;

import java.awt.Point;
import java.util.LinkedList;
import javax.swing.*;

public class Snake {
	protected Point head;
	protected LinkedList<Point> body;
	protected int length;
	protected int throughWall;//可穿墙属性
	protected int throughBody;//可穿身属性
	protected GamePanel gamePanel;
	protected int direction;
	protected int score;
	//protected GameOver gameover;
	/**
	 * 级别，1,2,3分别代表低中高级，级别的改变会影响蛇的速度，级别可以在开始面板中进行控制，也可以游戏进行过程中随着得分增加进行提高
	 * */
	protected int level;	
	/**
	 * 蛇的构造方法，蛇的产生依赖于游戏面板
	 * */
	Snake(GamePanel gamePanel){//蛇的产生依赖于游戏面板
		this.gamePanel=gamePanel;
		head=new Point(4*8, 0);
		body=new LinkedList<Point>();
		body.add(new Point(3*8, 0));
		body.add(new Point(2*8, 0));
		body.add(new Point(1*8, 0));
		body.add(new Point(0*8, 0));
		length=5;
		direction=1;
		throughWall=0;
		throughBody=0;
		score=0;
		level=1;//游戏级别默认为1，最低，增加一个set方法可以进行设置
	}
	
	
	/**
	 * 设置游戏级别，通过蛇的level属性来体现
	 * */
	public void setLevel(int level){
		this.level=level;
	}
	
	/**
	 * 设置蛇的运动方向
	 * */
	public void setDirection(int direction){
		this.direction=direction;
	}
	/**
	 * 是否吃到食物。
	 * 算法：每次保留住最后一个节点，当蛇头与食物重合，则将保留节点加入蛇尾，同时长度加1，返回true。
	 * */
	public boolean isEat(Food food){
		Point tmpNode=this.body.getLast();
		if(head.equals(food.pos)){
			
			//若吃到特殊食物可增加相应技能和分数
			if(food.flag==1){
				throughBody++;
				score+=3;
			}else if(food.flag==2){
				throughWall++;
				score+=2;
			}else{
				score++;
			}
			
			body.add(tmpNode);
			length++;
			return true;
		}
		return false;
	}
	/**
	 * 移动算法：每次移动按照最新移动方向新建临时头结点，然后新建临时身体链表，从原来头节点依次加入节点，去掉原来身体链表的最后一个节点，
	 * 然后将蛇头和蛇身重新指向新建蛇头和蛇身，完成一次移动
	 * */
	public void move(){
		Point tmpHeadNode=new Point(0, 0);
		Point tmpBodyNode;
		LinkedList<Point> tmpBody=new LinkedList<Point>();
		switch(direction){
		case 0:
			tmpHeadNode=new Point(head.x,head.y-8);
			if(tmpHeadNode.y<0 && throughWall>0){					//若出界则加上相应高度在对边出现，下面的移动原理同此
				tmpHeadNode.y+=gamePanel.height;
				throughWall--;
			}
			tmpBody.add(head);
			for(int i=0;i<body.size()-1;i++){
				tmpBodyNode=body.get(i);
				if(tmpBodyNode.y<0){
					tmpBodyNode.y+=gamePanel.height;
				}
				tmpBody.add(tmpBodyNode);
			}
			break;
		case 1:
			tmpHeadNode=new Point(head.x+8,head.y);
			if(tmpHeadNode.x>=gamePanel.width && throughWall>0){
				tmpHeadNode.x-=gamePanel.width;
				throughWall--;
			}
			tmpBody.add(head);
			for(int i=0;i<body.size()-1;i++){
				tmpBodyNode=body.get(i);
				if(tmpBodyNode.x>=gamePanel.width){
					tmpBodyNode.x-=gamePanel.width;
				}
				tmpBody.add(tmpBodyNode);
			}
			break;
		case 2:
			tmpHeadNode=new Point(head.x,head.y+8);
			if(tmpHeadNode.y>=gamePanel.height && throughWall>0){
				tmpHeadNode.y-=gamePanel.height;
				throughWall--;
			}
			tmpBody.add(head);
			for(int i=0;i<body.size()-1;i++){
				tmpBodyNode=body.get(i);
				if(tmpBodyNode.y>=gamePanel.height){
					tmpBodyNode.y-=gamePanel.height;
				}
				tmpBody.add(tmpBodyNode);
			}
			break;
		case 3:
			tmpHeadNode=new Point(head.x-8,head.y);
			if(tmpHeadNode.x<0 && throughWall>0){
				tmpHeadNode.x+=gamePanel.width;
				throughWall--;
			}
			tmpBody.add(head);
			for(int i=0;i<body.size()-1;i++){
				tmpBodyNode=body.get(i);
				if(tmpBodyNode.x<0){
					tmpBodyNode.x+=gamePanel.width;
				}
				tmpBody.add(tmpBodyNode);
			}
			break;
		}
		head=tmpHeadNode;
		body=tmpBody;		
	}
	/**
	 * 是否出界
	 * */
	public boolean isOut(){
		if(head.x>gamePanel.width || head.x<0 || head.y <0 || head.y >gamePanel.height){
			return true;
		}
		return false;
	}
	/**
	 * 是否咬到自己
	 * */
	public boolean isPitch(){
		if(body.contains(head)){//当身体节点包括头部节点判断为咬到自己
			return true;
		}
		return false;
	}
	/**
	 * 死亡方法，直接退出游戏。
	 * */
	public void dead(GamePanel gamePanel){
		
		JFrame frame1 = new GameOver(gamePanel);
	    frame1.setTitle("GameOver frame");
		frame1.setSize(600, 400);
		frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setUndecorated(true);
		frame1.setVisible(true);
	}
	
}

