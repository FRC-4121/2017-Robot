package org.usfirst.frc.team4121.robot.extraClasses;

import org.opencv.core.Mat;
import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class VisionThreadGear implements Runnable{
	
	public Thread visionThread;
	public static Mat mat;
	public static VisionProcessor visionPro = new VisionProcessor();

	public VisionThreadGear() {
	}

	public void run() {
		/*// Get the UsbCamera from CameraServer
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("cam1", 1);
		// Set the resolution
		camera.setResolution(640, 480);

		// Get a CvSink. This will capture Mats from the camera
		CvSink cvSink = CameraServer.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard
		CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

		// Mats are very memory expensive. Lets reuse this Mat.
		mat = new Mat();

		// This cannot be 'true'. The program will never exit if it is. This
		// lets the robot stop this thread when restarting robot code or
		// deploying.
		while (!RobotMap.RUN_GEAR_THREAD) {
			// Tell the CvSink to grab a frame from the camera and put it
			// in the source mat. If there is an error notify the output.
			if (cvSink.grabFrame(mat) == 0) {
				// Send the output the error.
				outputStream.notifyError(cvSink.getError());
				// skip the rest of the current iteration
				continue;
			}
			// Put a rectangle on the image
			//Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
			// Give the output stream a new image to display
			synchronized(Robot.imgLock) {
				Robot.visionArray = visionPro.update(mat);
			}
			outputStream.putFrame(mat);
		}*/
	}
}
