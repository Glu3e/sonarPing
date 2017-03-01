package playgroundPackages;

//Dealing with the sepcific file produced by the camer.
import java.io.File;
import java.awt.Image;
import java.awt.image.ImageProducer;

//Creating a GUI to Test in
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.Videoio;
import org.opencv.videoio.VideoCapture;

public class VideoTesting {
	
	static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
	
	private JFrame frame;
	private JLabel imageLabel;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VideoTesting application = new VideoTesting();
		application.initGUI();
		application.runMainLoop(args);
		

	}
	
	private void initGUI(){
		frame = new JFrame("Camera Input Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		
		imageLabel = new JLabel();
		frame.add(imageLabel);
		frame.setVisible(true);
	}
	
	public void runMainLoop(String[] args){
		//Setting Up the Image Processor
		ImageProcessor imageProcessor = new ImageProcessor();
		Mat capturedIamge = new Mat();
		Image tempImage;
		
		//Creating the web cam
		VideoCapture capture = new VideoCapture(0);
		capture.set(Videoio.CAP_PROP_FRAME_WIDTH, 320);
		capture.set(Videoio.CAP_PROP_FRAME_HEIGHT,240);
		
		//Logic 
		
		//if the video capture device is opened
		if(capture.isOpened()){
			
			while(true){
				capture.read(capturedIamge);
				
				//and the image is not blank
				if(!capturedIamge.empty()){
					tempImage = imageProcessor.toBufferedImage(capturedIamge);
					ImageIcon imageIcon = new ImageIcon(tempImage,"Captured Video");
					imageLabel.setIcon(imageIcon);
				}else{
					System.out.println("-- Frame not Captured -- Break!");
					break;
				}
			}
		}else{
			System.out.print("Couldn't open capture.");
		}
		
		
		
	}

}
