import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class DrawPanel extends JPanel {
    
    BufferedImage bufferImage=null;
    Graphics2D bufferGraphics=null;
    
    Color currentColor=Color.black;
    Color currentBackgroundColor=Color.white;
    Float currentWidth=10.0f;
    
    public void createBuffer(int width, int height) {
        //バッファ用のImageとGraphicsを用意する
        bufferImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);
        bufferGraphics=bufferImage.createGraphics(); //getGraphicsと似ているが、戻り値がGraphics2D。
        bufferGraphics.setBackground(currentBackgroundColor);
        bufferGraphics.clearRect(0, 0, width, height); //バッファクリア
    }
    
    public void openFile(File file2open){
        BufferedImage pictureImage;
        try {
            pictureImage = ImageIO.read(file2open);
        } catch(Exception e){
            System.out.println("Error: reading file="+file2open.getName());
            return;
        }
        //画像に合わせたサイズでbufferImageとbufferGraphicsを作りなおして画像を読み込む
        //ImageIO.readの戻り値をbufferImageに代入するのでは駄目みたいです。
        this.createBuffer(pictureImage.getWidth(),pictureImage.getHeight());
        bufferGraphics.drawImage(pictureImage,0,0,this);
        repaint(); //画像を表示するためにpaintComponentを呼ぶ
    }
    
    public void saveFile(File file2save) {
        try {
            ImageIO.write(bufferImage, "jpg", file2save);
        } catch (Exception e) {
            System.out.println("Error: writing file="+file2save.getName());
            return;
        }
    }
    
    public void drawLine(int x1, int y1, int x2, int y2){
        /*if(null==bufferGraphics) {
            this.createBuffer(1400,1000);  //バッファをまだ作ってなければ作る
        }*/
        bufferGraphics.setColor(currentColor);
        bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
        bufferGraphics.drawLine(x1, y1-47, x2, y2-47); // バッファに描画する
        repaint();//再描画するためpaintComponentを呼び出す。
    }
    
    /*public void drawRect(int x,int y,int width int height)
    
        bufferGraphics.setColor(currentColor);
        bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
        bufferGraphics.drawRect(x1, y1-47, width, height); // バッファに描画する
        repaint();//再描画するためpaintComponentを呼び出す。
    }*/
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//他に描画するものがあるかもしれないので親を呼んでおく
        if(null!=bufferImage) g.drawImage(bufferImage,0,0,this);//バッファを表示する
    }
    
    public void setPenColor(Color newColor) {
        currentColor = newColor;
    }
    
    public void setPenWidth(float newWidth) {
        currentWidth = newWidth;
    }
    
    public void setBackgroundColor(Color newBackgroundColor) {
        currentBackgroundColor = newBackgroundColor;
    }
}
