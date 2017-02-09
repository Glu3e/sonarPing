import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class CameraTesting {
	public static void main(String [] args){
		System.out.print("Hello World");
		
		System.loadLibrary("opencv_java300");
		
		VideoCapture capture = new VideoCapture(0);
		
		if(!capture.isOpened()){
			System.out.print("Did not connect");
		}
		
      

		
		Mat frameCapture = new Mat();
		
		//Imgcodecs.imwrite("Test.bmp", frameCapture);
		Mat frameBlur = new Mat();
		Imgproc.blur(frameCapture,frameBlur,new Size(5,5));
		//Imgcodecs.imwrite("Test2.png", frameBlur);

		capture.retrieve(frameCapture);

		
		
  		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
         VideoCapture camera = new VideoCapture(0);
         if(!camera.isOpened()) {
                     System.out.println("Erro in opening camera");
         }

         else {
             Mat frame = new Mat();
             while(true) {
                 if(camera.read(frame)) {
                     System.out.println("Camera obtained");
                     System.out.println("Captured frame width" + frame.width()
                     + " catured frame height " + frame.height() );
                     Imgcodecs.imwrite("./pictures/cam.png", frame);
                     break;                  
                 }
             }
             
            
             
         }
         camera.release();
         
         
         
         

          
	}

	public void snap(){
		System.out.print("Hello Camera");
		System.loadLibrary("opencv_java300");
		
		VideoCapture capture = new VideoCapture(0);
		
		if(!capture.isOpened()){
			System.out.print("Did not connect");
		}
		
      

		
		Mat frameCapture = new Mat();
		
		//Imgcodecs.imwrite("Test.bmp", frameCapture);
		Mat frameBlur = new Mat();
		Imgproc.blur(frameCapture,frameBlur,new Size(5,5));
		//Imgcodecs.imwrite("Test2.png", frameBlur);

		capture.retrieve(frameCapture);

		
		
  		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
         VideoCapture camera = new VideoCapture(0);
         if(!camera.isOpened()) {
                     System.out.println("Erro in opening camera");
         }

         else {
             Mat frame = new Mat();
             while(true) {
                 if(camera.read(frame)) {
                     System.out.println("Camera obtained");
                     System.out.println("Captured frame width" + frame.width()
                     + " catured frame height " + frame.height() );
                     Imgcodecs.imwrite("./pictures/cam.png", frame);
                     break;                  
                 }
             }
             
            
             
         }
         camera.release();
	}
}
