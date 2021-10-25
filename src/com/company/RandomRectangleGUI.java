package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Allen Muchini Assignment Solution

public class RandomRectangleGUI{
    JFrame frame;
    RandomRectDrawPanel drawPanel=new RandomRectDrawPanel();
    RandomColorListener randomColorListener;
    SizeListener sizeListener;
    JButton colorButton;
    JButton sizeButton;

    public static void main (String[] args){
        RandomRectangleGUI gui = new RandomRectangleGUI();
        gui.go();
    }

    public void go(){
        frame = new JFrame();
        randomColorListener = new RandomColorListener(frame);
        sizeListener = new SizeListener(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        colorButton = new JButton("Click me for a random colour");
        sizeButton = new JButton("Click me for a random size");

        colorButton.addActionListener(randomColorListener);
        sizeButton.addActionListener(sizeListener);
        frame.getContentPane().add(BorderLayout.PAGE_START, colorButton);
        frame.getContentPane().add(BorderLayout.PAGE_END, sizeButton);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.setSize(500,500);
        frame.setVisible(true);
    }





    class RandomRectDrawPanel extends JPanel{
        Color color;
        int rectangle_height = 50;
        int rectangle_width = 80;
        int start_point_x = 50;
        int start_point_y = 50;

        public void paintComponent (Graphics g){
            super.paintComponent(g);
            g.setColor(color);
            g.fillRect(start_point_x,start_point_y,rectangle_width,rectangle_height);
        }

        public void randomColor(){
            int red = (int)(Math.random()*255);
            int green = (int)(Math.random()*255);
            int blue = (int)(Math.random()*255);
            color = new Color(red,green,blue);
        }

        public void randomSize(){
            int displace_value = 5;
            rectangle_height = (int)(Math.random()*getHeight());
            rectangle_width = (int)(Math.random()*getWidth());

            int temporary_value;
            if ((start_point_y + rectangle_height) > getHeight()){  // this to keep all of the height of the rectangle inside the draw panel
                temporary_value = getHeight() - (start_point_y + rectangle_height);
                rectangle_height = rectangle_height + temporary_value - displace_value;  // temp is a negative number
            }
            if (rectangle_height < 5) rectangle_height = 5;//minimum height

            if ((start_point_x + rectangle_height) > getWidth()){  // this to keep all of the width of the rectangle inside the draw panel
                temporary_value = getWidth() - (start_point_x + rectangle_width);
                rectangle_width = rectangle_width + temporary_value - displace_value;  // temp is a negative number
            }
            if (rectangle_width < 5) rectangle_width = 5; //minimum width
        }
    }

    class RandomColorListener implements ActionListener {
        Color randomColor;
        public RandomColorListener(JFrame rectangle ){
            frame = rectangle;

        }
        @Override
        public void actionPerformed(ActionEvent e) {
            drawPanel.randomColor();
            randomColor = drawPanel.color;
            frame.setBackground(randomColor);
        }
    }

    class SizeListener implements ActionListener{
        public SizeListener(JFrame rectangle){
            frame = rectangle;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            drawPanel.randomSize();
            frame.repaint(drawPanel.start_point_x,drawPanel.start_point_y,drawPanel.rectangle_width,drawPanel.rectangle_width);
        }
    }



}