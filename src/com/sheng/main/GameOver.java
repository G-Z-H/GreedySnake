package com.sheng.main;
//游戏结束的对话框确认
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;


public class GameOver extends JFrame {
	protected ImagePanel jpanel;
	
        public GameOver(GamePanel gamePanel){
        	
        	Image image=Toolkit.getDefaultToolkit().getImage("E:/GitHub/GreedySnake/background.png");
        	jpanel=new ImagePanel(image);
        	jpanel.setLayout(null);
        	
        	
        	ImageIcon againIcon = new ImageIcon("E:/GitHub/GreedySnake/again.png");
        	ImageIcon exitIcon = new ImageIcon("E:/GitHub/GreedySnake/exit.png");
        	
        	JLabel label = new JLabel("Your Score is:" + gamePanel.snake.score);
        	JButton  jbtAgain=new JButton(againIcon);
        	JButton  jbtExit=new JButton(exitIcon);
        	
 	
        	label.setFont(new Font("Dialog",1,10)); 
        	label.setForeground(Color.red);
        	label.setBounds(50, 0, 100, 200);
        	
        	jbtAgain.setBounds(50,150,124,54);
        	jbtExit.setBounds(50,250,124,54);
        	

        	jpanel.add(label);
        	jpanel.add(jbtAgain); 	
        	jpanel.add(jbtExit);
        	
        	
        	add(jpanel);
        
        	AgainListenerClass listener1=new AgainListenerClass();
        	ExitListenerClass listener2=new ExitListenerClass();
        	
        	jbtAgain.addActionListener(listener1);
        	jbtExit.addActionListener(listener2);
        	

        }
        
        
        
        class AgainListenerClass implements ActionListener{
        	public void actionPerformed(ActionEvent e){
        		dispose();
        		new GameFrame();
        	}
        	
        }
        
        class ExitListenerClass implements ActionListener{
        	public void actionPerformed(ActionEvent e){
        		System.exit(0);
        	}
        }
        public class ImagePanel extends JPanel{
        	private Image image;
        	
        	public ImagePanel(Image image){
        		this.image=image;
        	}
        	
        	protected void paintComponent(Graphics g){
        		super.paintComponent(g);
        		
        		int width=image.getWidth(this);
        		int height=image.getHeight(this);
        		int x=(this.getWidth()-width)/2;
        		int y=(this.getHeight()-height)/2;
        		g.drawImage(image,x,y,this);
        	}
        }
        
}
