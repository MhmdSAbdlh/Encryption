import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Encryption extends JFrame{
	
	private Font medF = new Font("Tahoma",Font.BOLD,25);
	private Font smallF = new Font("Tahoma",Font.LAYOUT_LEFT_TO_RIGHT,20);
	private Color darkC = new Color(0x161616);
	private Color lightC = new Color(0xECDBBA);
	private Color codingC = new Color(0xC84B31);
	private Color medC = new Color(0x346751);
	private ArrayList<Character> textList = new ArrayList<>();
	private ArrayList<Character> shuffledList = new ArrayList<>();
	private char[] keyArray;
	private char character = ' ';
	private String keyString;
	private JTextField key = new JTextField("Encryption Key");
	private JTextArea inputTA = new JTextArea(); 
	private JTextArea resultTA = new JTextArea();
	private JLabel finalText = new JLabel("AFTER CODING");
	
	Encryption(){
		//Frame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setTitle("Encryption");
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(darkC);this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("encryp.png")).getImage());
		
		//Label
		JLabel text = new JLabel("ENTER YOUR TEXT HERE");
		JLabel keyText = new JLabel("KEY");
		text.setBounds(0, 10, 700, 50);
		text.setFont(medF);
		text.setForeground(lightC);
		text.setHorizontalAlignment(0);
		finalText.setBounds(0, 240, 700, 50);
		finalText.setFont(medF);
		finalText.setForeground(codingC);
		finalText.setHorizontalAlignment(0);
		keyText.setBounds(0, 550, 700, 50);
		keyText.setFont(medF);
		keyText.setForeground(medC);
		keyText.setHorizontalAlignment(0);
		
		//TextArea&Field
		inputTA.setBounds(40, 70, 600, 150);
		inputTA.setBackground(lightC);
		inputTA.setFont(medF);
		resultTA.setBounds(40, 300, 600, 150);
		resultTA.setBackground(lightC);
		resultTA.setForeground(medC);
		resultTA.setFont(medF);
		resultTA.setEditable(false);
		key.setBounds(40,600,600,40);
		key.setForeground(darkC);
		key.setBackground(medC);
		key.setHorizontalAlignment(0);
		key.setFont(smallF);
		
		//Button
		JButton encryptB = new JButton("ENCRYPT");
		encryptB.setBounds(150, 470, 170, 50);
		encryptB.setBackground(lightC);
		encryptB.setForeground(darkC);
		encryptB.setFocusable(false);
		encryptB.setFont(medF);
		encryptB.addActionListener(e->encrypt());
		JButton decryptB = new JButton("DECRYPT");
		decryptB.setBounds(380, 470, 170, 50);
		decryptB.setBackground(lightC);
		decryptB.setForeground(darkC);
		decryptB.setFocusable(false);
		decryptB.setFont(medF);
		decryptB.addActionListener(e->decrypt());
		JButton newK = new JButton("NEW KEY");
		newK.setBounds(440, 550, 200, 40);
		newK.setBackground(codingC);
		newK.setForeground(darkC);
		newK.setFocusable(false);
		newK.setFont(medF);
		newK.addActionListener(e->newKey());
		JButton setKey = new JButton("SET KEY");
		setKey.setBounds(40,550,200,40);
		setKey.setHorizontalAlignment(0);
		setKey.setBackground(codingC);
		setKey.setForeground(darkC);
		setKey.setFocusable(false);
		setKey.setFont(medF);
		setKey.addActionListener(e->setKey());
		
		newKey();
		
		//ADD TO FRAME
		this.add(keyText);
		this.add(setKey);
		this.add(newK);
		this.add(decryptB);
		this.add(encryptB);
		this.add(resultTA);
		this.add(finalText);
		this.add(inputTA);
		this.add(key);
		this.add(text);
		this.setVisible(true);
	}
	
	private void newKey() {
		character = ' ';
		textList.clear();
		shuffledList.clear();
		for(int i=32;i<127;i++) {
			textList.add(Character.valueOf(character));
			character++;
		}
		shuffledList = new ArrayList<Character>(textList);
		keyArray = new char[shuffledList.size()];
		Collections.shuffle(shuffledList);
		for(int i=0;i<shuffledList.size();i++)
			keyArray[i] = shuffledList.get(i);
		keyString=String.valueOf(keyArray);
		key.setText(keyString);
	}
	
	private void setKey() {
		character = ' ';
		textList.clear();
		shuffledList.clear();
		for(int i=32;i<127;i++) {
			textList.add(Character.valueOf(character));
			character++;
		}
		shuffledList = new ArrayList<Character>();
		keyString = key.getText();
		keyArray = keyString.toCharArray();
		for(int i=0;i<keyArray.length;i++)
			shuffledList.add(keyArray[i]);
	}
	
	private void encrypt() {
		finalText.setText("ENCRYPTED TEXT");
		char[] encryText = new char[inputTA.getText().length()];
		encryText = inputTA.getText().toCharArray();
		for(int i=0; i<inputTA.getText().length();i++)
			for(int j=0;j<textList.size();j++)
				if(encryText[i]==textList.get(j)) {
					encryText[i]=shuffledList.get(j);
					break;
				}
		resultTA.setText(String.valueOf(encryText));
	}
	
	private void decrypt() {
		finalText.setText("DECRYPTED TEXT");
		char[] decryText = new char[inputTA.getText().length()];
		decryText = inputTA.getText().toCharArray();
		for(int i=0; i<inputTA.getText().length();i++)
			for(int j=0;j<shuffledList.size();j++)
				if(decryText[i]==shuffledList.get(j)) {
					decryText[i]=textList.get(j);
					break;
				}
		resultTA.setText(String.valueOf(decryText));
	}

}
