package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class myFrame extends JFrame implements ActionListener {

	boolean state = false;

	Panel panel1;
	JButton but1;
	JTextArea code;
	Label lb;
	JScrollPane codePane, MainPane;
	codeReader reader;

	public myFrame() {

		setTitle("函数绘图语言解释器");

		panel1 = new Panel();
		but1 = new JButton("运行");
		code = new JTextArea(10, 70);
		code.setLineWrap(true);
		reader = new codeReader();

		lb = new Label("在此编辑代码");
		MainPane = new JScrollPane(panel1);
		codePane = new JScrollPane(code);

		but1.addActionListener(this);

		panel1.add(lb);
		panel1.add(codePane);
		panel1.add(but1);

		add(panel1, BorderLayout.SOUTH);
		setBounds(600, 50, 1000, 900);
		setVisible(true);

	}

	public void paint(Graphics g) {
		if (state == true) {
			g.setColor(Color.white);
			g.fillRect(0, 0, 1000, 700);
			System.out.println("paint");
			g.setColor(Color.blue);
			for (int i = 0; i < reader.img.size(); i++) {
				int x = (int) (reader.img.get(i).x*50) + 15;
				int y = (int) (reader.img.get(i).y*50) + 50;
				g.fillRect(x, y, 3, 3);
			}
		}

	}

	public void update(Graphics g) {
		paint(g);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == but1) {
			String s = code.getText();
			reader.init();
			reader.accept(s);
			state = true;
			repaint();

		} else {
			state = false;
		}
	}

	public static void main(String[] args) {
		myFrame start = new myFrame();

	}

}
