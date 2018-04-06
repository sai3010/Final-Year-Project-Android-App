package face_detection;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
public class Capture extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Capture frame = new Capture();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Capture() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCapture = new JButton("Capture");
		btnCapture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
		            // TODO add your handling code here:
		            
		            
		            Desktop d=Desktop.getDesktop();
		            d.browse(new URI("http://localhost:8084/B_Demo/capture_face.jsp?user=KA01EW1010"));
		        } catch (Exception ex) {
		          ex.printStackTrace();
		        }
				 
				 try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 Main mn = new Main();
			        String path = "C:/Users/saipr/OneDrive/Desktop/HeadCount/web/image/images.jpg";//mn.browse();
			        BufferedImage image;
					try {
						image = ImageIO.read(new File(path));
						 byte[] b = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
					        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
					         mat.put(0,0, b);
                                                 new showResult(mat, "Original Image");

					      mn.init(mat,"C:/Users/saipr/Documents/NetBeansProjects/Final-Year-Project-Android-App/FinalYearProjectJSP/src/java/resources/haarcascades/haarcascade_frontalface_alt.xml");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			       
			}
		});
		btnCapture.setBounds(159, 24, 119, 23);
		contentPane.add(btnCapture);
	}
}
