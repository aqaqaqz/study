package frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Util;
import model.PhotoConst;
import model.PhotoPath;

public class DefaultFrame extends JFrame{
	private Container c = getContentPane();
	
	public DefaultFrame(){
		setTitle(PhotoConst.TITLE);
		setSize(PhotoConst.WINDOW_WIDTH, PhotoConst.WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setButton();
		setVisible(true);
	}
	
	private void setButton() {
		c.setLayout(new BorderLayout(10,10));
		
		c.add(makeTitle("파일복사", PhotoConst.WINDOW_WIDTH, PhotoConst.TITLE_HEIGHT), BorderLayout.NORTH);
		c.add(getCenterPanel(), BorderLayout.CENTER);
		
		c.add(makeOption(), BorderLayout.SOUTH);
	}
	
	private JPanel makeTitle(String title, int width, int height){
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		
		titlePanel.add(titleLabel);
		if(width!=-1 && height!=-1) titlePanel.setSize(width, height);
		
		return titlePanel;
	}
		
	private JPanel getCenterPanel(){
		JPanel center = new JPanel(new GridLayout(2, 1));
		
		String[] arr = {PhotoConst.ORI_TEXT, PhotoConst.TRG_TEXT};
		for(String text : arr){
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 0));
			p.add(text, makeFileSelectButton(text, PhotoConst.WINDOW_WIDTH, PhotoConst.FILE_HEIGHT));
			center.add(p);
		}
		
		return center;
	}
	
	private JPanel makeFileSelectButton(final String buttonText, int width, int height){
		JPanel temp = new JPanel();
		JButton fileBtn = new JButton(buttonText);
		final JLabel pathLabel = new JLabel("경로를 선택하세요");
		
		fileBtn.addActionListener(new ActionListener() {
			JFileChooser jfc = new JFileChooser();
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jfc.setMultiSelectionEnabled(false);
				
				jfc.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e2) {
						if(e2.getModifiers() == 16){
							pathLabel.setText(jfc.getSelectedFile().toString());
							if(PhotoConst.ORI_TEXT.equals(buttonText)) PhotoPath.setOriPath(jfc.getSelectedFile().toString());
							if(PhotoConst.TRG_TEXT.equals(buttonText)) PhotoPath.setTrgPath(jfc.getSelectedFile().toString());
						}
					}
				});
				
				jfc.showOpenDialog(null);
			}
		});

		temp.add(fileBtn);
		temp.add(pathLabel);
		if(width!=-1 && height!=-1) temp.setSize(width, height);
		
		return temp;
	}
	
	private JPanel makeOption(){
		JPanel temp = new JPanel(new GridLayout(2,3));
		
		JPanel oriExtensionList = new JPanel();
		for(String text : PhotoConst.ORI_EXTENSION) oriExtensionList.add(new JCheckBox(text));
		
		JPanel trgExtensionList = new JPanel();
		for(String text : PhotoConst.TRG_EXTENSION) trgExtensionList.add(new JCheckBox(text));
		
		temp.add(new JLabel("원본확장자", null, JLabel.CENTER));
		temp.add(new JLabel("대상확장자", null, JLabel.CENTER));
		temp.add(new JLabel("", null, JLabel.CENTER));
		
		temp.add(oriExtensionList);
		temp.add(trgExtensionList);
		
		JPanel p = new JPanel();
		JButton copyBtn = new JButton("복사");
		copyBtn.setSize(100, 50);

		copyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(PhotoPath.getOriPath() + " --- " + PhotoPath.getTrgPath());
			}
		});
		p.add("복사", copyBtn);
		temp.add(p);
		
		return temp;
	}
}
