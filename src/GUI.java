import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.TextField;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.Position;

import org.w3c.dom.Text;

public class GUI extends JFrame implements ActionListener, KeyListener {

    private JFrame frame;
    private JButton startButton;
    private JButton stopButton;
    private JLabel label;
    private JTextField inputText;

    private int switchClick = 0;

    private final int NO_USER_INPUT_IN_JTEXTFIELD = 100;

    GUI() {

        label = new JLabel();
        label.setText("Milisecond:");
        label.setBounds(10, 0, 80, 50);

        inputText = new JTextField();
        inputText.setBounds(80, 16, 130, 20);

        startButton = new JButton();
        startButton.setText("Start [F6]");
        startButton.setBounds(10, 50, 100, 25);
        startButton.addActionListener(this);

        stopButton = new JButton();
        stopButton.setText("Stop [F6]");
        stopButton.setBounds(120, 50, 100, 25);
        stopButton.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(240, 150);
        this.setVisible(true);
        this.addKeyListener(this);

        this.add(label);
        this.add(startButton);
        this.add(stopButton);
        this.add(inputText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getSource().toString();

        if (s.contains("Start [F6]")) {
            startStopClick();
        } else if (s.contains("Stop [F6]")) {
            startStopClick();
        }
    }

    private void startStopClick() {
        switchClick =(switchClick == 0) ? 1 : 0;
        try {
            int t;
            String userInput = inputText.getText();
            Robot bot = new Robot();
            int mask = InputEvent.BUTTON1_DOWN_MASK;
            
            t = (userInput.length() == 0) ? NO_USER_INPUT_IN_JTEXTFIELD : Integer.parseInt(userInput);
            
            if (switchClick == 0) {
                for (int i = 0; i < 50; i++) {
                    System.out.println("HERE");
                    int positionX = MouseInfo.getPointerInfo().getLocation().x;
                    int positionY = MouseInfo.getPointerInfo().getLocation().y;
                    
                    System.out.println("Mouse Clicked at X:" + positionX + " Y:" + positionY);
                    bot.mousePress(mask);
                    bot.mouseRelease(mask);
                    Thread.sleep(t);
                }
            } else if(switchClick == 1){
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F6) {
            startStopClick();
            switchClick = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_F6) {
            startStopClick();
            switchClick = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
