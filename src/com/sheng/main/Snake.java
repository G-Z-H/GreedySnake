package com.sheng.main;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {
	protected Point head;
	protected LinkedList<Point> body;
	protected int length;
	protected GamePanel gamePanel;
	protected int direction;	
	
	Snake(){
		head=new Point(4*8, 0);
		body=new LinkedList<Point>();
		body.add(new Point(3*8, 0));
		body.add(new Point(2*8, 0));
		body.add(new Point(1*8, 0));
		body.add(new Point(0*8, 0));
		length=5;
		direction=1;
		gamePanel=null;
	}
	/**
	 * ������������Ϸ���
	 * */
	public void setGamePanel(GamePanel gamePanel){
		this.gamePanel=gamePanel;
	}
	
	/**
	 * �����ߵ��˶�����
	 * */
	public void setDirection(int direction){
		this.direction=direction;
	}
	/**
	 * �Ƿ�Ե�ʳ�
	 * �㷨��ÿ�α���ס���һ���ڵ㣬����ͷ��ʳ���غϣ��򽫱����ڵ������β��ͬʱ���ȼ�1������true��
	 * */
	public boolean isEat(Food food){
		Point tmpNode=this.body.getLast();
		if(head.equals(food.pos)){
			body.add(tmpNode);
			length++;
			return true;
		}
		return false;
	}
	/**
	 * �ƶ��㷨��ÿ���ƶ����������ƶ������½���ʱͷ��㣬Ȼ���½���ʱ����������ԭ��ͷ�ڵ����μ���ڵ㣬ȥ��ԭ��������������һ���ڵ㣬
	 * Ȼ����ͷ����������ָ���½���ͷ���������һ���ƶ�
	 * */
	public void move(){
		Point tmpHeadNode;
		LinkedList<Point> tmpBody=new LinkedList<Point>();
		switch(direction){
		case 0:
			tmpHeadNode=new Point(head.x,head.y-8);
			tmpBody.add(head);
			for(int i=0;i<body.size()-1;i++){
				tmpBody.add(body.get(i));
			}
			head=tmpHeadNode;
			body=tmpBody;
			break;
		case 1:
			tmpHeadNode=new Point(head.x+8,head.y);
			tmpBody.add(head);
			for(int i=0;i<body.size()-1;i++){
				tmpBody.add(body.get(i));
			}
			head=tmpHeadNode;
			body=tmpBody;
			break;
		case 2:
			tmpHeadNode=new Point(head.x,head.y+8);
			tmpBody.add(head);
			for(int i=0;i<body.size()-1;i++){
				tmpBody.add(body.get(i));
			}
			head=tmpHeadNode;
			body=tmpBody;
			break;
		case 3:
			tmpHeadNode=new Point(head.x-8,head.y);
			tmpBody.add(head);
			for(int i=0;i<body.size()-1;i++){
				tmpBody.add(body.get(i));
			}
			head=tmpHeadNode;
			body=tmpBody;
			break;
		}
	}
	//�Ƿ����
	public boolean isOut(){
		if(head.x>gamePanel.width || head.x<0 || head.y <0 || head.y >gamePanel.height){
			return true;
		}
		return false;
	}
	/**
	 * �Ƿ�ҧ���Լ�
	 * */
	public boolean isPitch(){
		if(body.contains(head)){//������ڵ����ͷ���ڵ��ж�Ϊҧ���Լ�
			return true;
		}
		return false;
	}
	/**
	 * ����������ֱ���˳���Ϸ��
	 * */
	public void dead(){
		System.exit(0);
	}
}

