package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;

//import org.junit.Test;

public class onLineWindow extends Thread {
	public static JFrame frame;
	private JTextArea showMsg;// 显示消息
	private String name;
	public onLineWindow(String username) {
		this.name=username;
	}
	@Override
	public void run() {
		frame = new JFrame("上线提醒");
		frame.setIconImage(new ImageIcon("image/icon.png").getImage());// 更改JFrame的图标
		showMsg = new JTextArea();
		showMsg.setText(" "+name+" 上线啦！");
		showMsg.setFont(new Font("宋体", Font.BOLD, 25));
		showMsg.setEditable(false);// 显示消息框不可编辑
		showMsg.setForeground(Color.WHITE);

		frame.setVisible(true);
		frame.setSize(250, 80);
		frame.setLayout(new BorderLayout());
		frame.add(showMsg);

		int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setLocation((screen_width - frame.getWidth()) ,
				(screen_height - frame.getHeight())-60);
	}

}
