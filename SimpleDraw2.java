import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;

import javax.swing.JFrame;

public class SimpleDraw2 extends JFrame implements ActionListener, MouseMotionListener, MouseListener {
    int lastx=0, lasty=0, newx, newy;
    DrawPanel panel;
    JFileChooser fileChooser;

    private void addMenuItem
    (JMenu targetMenu, String itemName, String actionName, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(itemName);
        menuItem.setActionCommand(actionName);
        menuItem.addActionListener(listener);
        targetMenu.add(menuItem);
    }

    private void initMenu() {
        JMenuBar menubar=new JMenuBar();
        JMenu menuFile = new JMenu("File");
        this.addMenuItem(menuFile,"New","New",this);
        this.addMenuItem(menuFile,"Open...","Open",this);
        this.addMenuItem(menuFile,"Save...","Save",this);
        menubar.add(menuFile);
        JMenu menuPen = new JMenu("Pen");
        JMenu menuColor = new JMenu("Color");
        this.addMenuItem(menuColor, "ChooseColor", "ChooseColor", this);
        this.addMenuItem(menuColor, "Black", "Black", this);
        this.addMenuItem(menuColor, "Blue", "Blue", this);
        this.addMenuItem(menuColor, "Yellow", "Yellow", this);
        this.addMenuItem(menuColor, "Green", "Green", this);
        this.addMenuItem(menuColor, "Red", "Red", this);
        JMenu menuWidth = new JMenu("Width");
        this.addMenuItem(menuWidth, "width1", "width1", this);
        this.addMenuItem(menuWidth, "width5", "width5", this);
        this.addMenuItem(menuWidth, "width10", "width10", this);
        this.addMenuItem(menuWidth, "width20", "width20", this);
        JMenu menuEraser = new JMenu("Eraser");
        this.addMenuItem(menuEraser, "width1", "eraser1", this);
        this.addMenuItem(menuEraser, "width5", "eraser5", this);
        this.addMenuItem(menuEraser, "width10", "eraser10", this);
        this.addMenuItem(menuEraser, "width20", "eraser20", this);
        JMenu menuBackground = new JMenu("Background");
        this.addMenuItem(menuBackground, "ChooseColor", "ChooseColor2", this);
        this.addMenuItem(menuBackground, "Black", "Black2", this);
        this.addMenuItem(menuBackground, "Blue", "Blue2", this);
        this.addMenuItem(menuBackground, "Yellow", "Yellow2", this);
        this.addMenuItem(menuBackground, "Green", "Green2", this);
        this.addMenuItem(menuBackground, "Red", "Red2", this);
        menuPen.add(menuColor);
        menuPen.add(menuWidth);
        menubar.add(menuPen);
        menubar.add(menuEraser);
        menubar.add(menuBackground);
        this.setJMenuBar(menubar);
    }

    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getActionCommand().equals("width1"))
            panel.setPenWidth(1);
        else if(arg0.getActionCommand().equals("width5"))
            panel.setPenWidth(5);
        else if(arg0.getActionCommand().equals("width10"))
            panel.setPenWidth(10);
        else if(arg0.getActionCommand().equals("width20"))
            panel.setPenWidth(20);
        else if(arg0.getActionCommand().equals("ChooseColor")){
            JColorChooser colorchooser = new JColorChooser();
            Color color = colorchooser.showDialog(this,"choose a color",Color.blue);
            panel.setPenColor(color);
        }
        else if(arg0.getActionCommand().equals("Black"))
            panel.setPenColor(Color.black);
        else if(arg0.getActionCommand().equals("Blue"))
            panel.setPenColor(Color.blue);
        else if(arg0.getActionCommand().equals("Yellow"))
            panel.setPenColor(Color.yellow);
        else if(arg0.getActionCommand().equals("Green"))
            panel.setPenColor(Color.green);
        else if(arg0.getActionCommand().equals("Red"))
            panel.setPenColor(Color.red);
        else if(arg0.getActionCommand().equals("ChooseColor2")){
            JColorChooser colorchooser = new JColorChooser();
            Color color = colorchooser.showDialog(this,"choose a color",Color.blue);
            panel.setBackgroundColor(color);
            panel.createBuffer(1400,1000);
            repaint();
        }
        else if(arg0.getActionCommand().equals("Black2")){
            panel.setBackgroundColor(Color.black);
            panel.createBuffer(1400,1000);
            repaint();
        }
        else if(arg0.getActionCommand().equals("Blue2")){
            panel.setBackgroundColor(Color.blue);
            panel.createBuffer(1400,1000);
            repaint();
        }
        else if(arg0.getActionCommand().equals("Yellow2")){
            panel.setBackgroundColor(Color.yellow);
            panel.createBuffer(1400,1000);
            repaint();
        }
        else if(arg0.getActionCommand().equals("Green2")){
            panel.setBackgroundColor(Color.green);
            panel.createBuffer(1400,1000);
            repaint();
        }
        else if(arg0.getActionCommand().equals("Red2")){
            panel.setBackgroundColor(Color.red);
            panel.createBuffer(1400,1000);
            repaint();
        }

        else if(arg0.getActionCommand().equals("eraser1")){
            panel.setPenWidth(1);
            panel.setPenColor(Color.white);
        }
        else if(arg0.getActionCommand().equals("eraser5")){
            panel.setPenWidth(5);
            panel.setPenColor(Color.white);
        }

        else if(arg0.getActionCommand().equals("eraser10")){
            panel.setPenWidth(10);
            panel.setPenColor(Color.white);
        }
        else if(arg0.getActionCommand().equals("eraser20")){
            panel.setPenWidth(20);
            panel.setPenColor(Color.white);
        }
        else if(arg0.getActionCommand().equals("New")){
            panel.setBackgroundColor(Color.white);
            panel.createBuffer(1400,1000);
            repaint();

        }
        else if(arg0.getActionCommand().equals("Open")) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                panel.openFile(fileChooser.getSelectedFile());
            }
        }
        else if(arg0.getActionCommand().equals("Save")) {
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                panel.saveFile(fileChooser.getSelectedFile());
            }
        }
        else if(arg0.getActionCommand().equals("Copy")){
        }
        else if(arg0.getActionCommand().equals("Paste")){
        }
        else if(arg0.getActionCommand().equals("Oval")){

        }
        else if(arg0.getActionCommand().equals("Arc")){
        }

    }

    public void mouseMoved(MouseEvent arg0) {
    }

    public void mouseDragged(MouseEvent arg0) {
	newx=arg0.getX();
	newy=arg0.getY();
	panel.drawLine(lastx,lasty,newx,newy);
	lastx=newx;
	lasty=newy;

    }

    public void mouseReleased(MouseEvent arg0) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR )); //手の形のカーソルに変更

    }

    public void mouseClicked(MouseEvent arg0){

    }

    public void mouseEntered(MouseEvent arg0){

    }

    public void mouseExited(MouseEvent arg0){

    }

    public void mousePressed(MouseEvent arg0){
	lastx=arg0.getX();
	lasty=arg0.getY();
    setCursor(new Cursor(Cursor.HAND_CURSOR)); //手の形のカーソルに変更
    }


    private void init() {
	this.setTitle("Simple Draw");
	this.setSize(600, 400);
	this.addMouseMotionListener(this);
	this.addMouseListener(this);
	panel=new DrawPanel();
    //panel.setBackground(Color.red);
    panel.setBackground(Color.white);
    panel.createBuffer(1400,1000);
    fileChooser = new JFileChooser();
	this.getContentPane().add(panel);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
	SimpleDraw2 frame=new SimpleDraw2();
    frame.initMenu();
	frame.init();
    }
}
