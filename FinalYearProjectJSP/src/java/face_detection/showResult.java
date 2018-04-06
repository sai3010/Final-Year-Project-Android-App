/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package face_detection;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.opencv.core.Mat;

/**
 *
 * @author Anspro
 */
public class showResult {

    public showResult(Mat img,String title) {
        try {
        setSystemLookAndFeel();
        JFrame frame = new JFrame(title);
        Image loadedImage = toBufferedImage(img);
        
        
        		BufferedImage bi = (BufferedImage)loadedImage;
        		File f = new File("E:\\myImage.png");
        		ImageIO.write(bi, "png", f);
        
        
        
//        File f1 = new File("E://p.png");
//        ImageIO.write(loadedImage, "png", f1);
        
       // savePic(loadedImage,".jpg","C:\\myImage.jpg");
//        BufferedImage bufferedImage= new BufferedImage(toolkitImage.getWidth(), toolkitImage.getHeight(), BufferedImage.TYPE_INT_RGB);
//        loadedImage.getGraphics().drawImage(toolkitImage, 0, 0, null);
//        ImageIO.write(bufferedImage, "jpg", new File("C:\\myImage.jpg"));
        
        frame.getContentPane().add(new JLabel(new ImageIcon(loadedImage)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    
    


//public void savePic(Image iamge,String type,String dst ){ 
//    int width = iamge.getWidth( this); 
//    int height = iamge.getHeight( this);
//    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//    Graphics g = bi.getGraphics(); 
//    try { 
//        g.drawImage(iamge, 0, 0, null);
//        ImageIO.write(bi,type,new File(dst)); 
//    } catch (IOException e) { 
//        // TODO Auto-generated catch block 
//        e.printStackTrace(); 
//    } 
//}


    
    private void setSystemLookAndFeel() throws UnsupportedLookAndFeelException {
try {
UIManager.setLookAndFeel
(UIManager.getSystemLookAndFeelClassName());
} catch (ClassNotFoundException e) {
e.printStackTrace();
} catch (InstantiationException e) {
e.printStackTrace();
} catch (IllegalAccessException e) {
e.printStackTrace();
} catch (UnsupportedLookAndFeelException e) {
e.printStackTrace();
}
}
    public Image toBufferedImage(Mat m){
          int type = BufferedImage.TYPE_BYTE_GRAY;
          if ( m.channels() > 1 ) {
              type = BufferedImage.TYPE_3BYTE_BGR;
          }
          int bufferSize = m.channels()*m.cols()*m.rows();
          byte [] b = new byte[bufferSize];
          m.get(0,0,b); // get all the pixels
          BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
          final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
          System.arraycopy(b, 0, targetPixels, 0, b.length);  
          return image;

      }
}
