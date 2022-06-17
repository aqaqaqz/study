package frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PhotoConst;

public class DefaultFrame extends JFrame{
	private Container c = getContentPane();;
	
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
		
		titlePanel.add(new JLabel(title));
		if(width!=-1 && height!=-1) titlePanel.setSize(width, height);
		
		return titlePanel;
	}
		
	private JPanel getCenterPanel(){
		JPanel center = new JPanel(new GridLayout(2,1));
		
		center.add(makeFileSelectButton("from", PhotoConst.WINDOW_WIDTH, PhotoConst.FILE_HEIGHT));
		center.add(makeFileSelectButton("to", PhotoConst.WINDOW_WIDTH, PhotoConst.FILE_HEIGHT));
		
		return center;
	}
	
	private JPanel makeFileSelectButton(String buttonText, int width, int height){
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
		JPanel temp = new JPanel(new GridLayout(2,2));
		
		JPanel fromExtensionList = new JPanel();
		fromExtensionList.add(new JCheckBox("jpg"));
		fromExtensionList.add(new JCheckBox("etc"));
		
		JPanel toExtensionList = new JPanel();
		toExtensionList.add(new JCheckBox("png"));
		toExtensionList.add(new JCheckBox("bmp"));
		
		
		temp.add(new JLabel("from확장자", null, JLabel.CENTER));
		temp.add(new JLabel("to확장자", null, JLabel.CENTER));
		temp.add(toExtensionList);
		temp.add(fromExtensionList);
		
		
		return temp;
	}
}
