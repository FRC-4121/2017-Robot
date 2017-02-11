package org.usfirst.frc.team4121.robot.extraClasses;

import org.opencv.core.Mat;
import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoException;
import edu.wpi.first.wpilibj.CameraServer;

public class MyVisionThread implements Runnable{

	Mat mat;
	CvSink cvSinkGear;
	CvSink cvSinkBoiler;
	CvSource outputStream;
	
	public MyVisionThread() {
	}
	
	public void run() {
		try {
			Robot.gearCam = new UsbCamera("cam1", 1);
			Robot.boilerCam = new UsbCamera("cam0", 0);
			
			Robot.camServer.addCamera(Robot.gearCam);
			Robot.camServer.addCamera(Robot.boilerCam);
			
			Robot.gearCam.setResolution(640, 480);
			Robot.boilerCam.setResolution(640, 480);
			
			Robot.camServer.startAutomaticCapture(Robot.gearCam);
			Robot.camServer.startAutomaticCapture(Robot.boilerCam);
			
			cvSinkGear = Robot.camServer.getVideo(Robot.gearCam);
			cvSinkBoiler = Robot.camServer.getVideo(Robot.boilerCam);
			
			mat = new Mat();
		} catch(VideoException e) {
			System.out.println("VISION EXCEPTION ~" + e);
		}
		
		while(true) {
			if (RobotMap.VISION_THREAD) {
				if(cvSinkGear.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSinkGear.getError());
					// skip the rest of the current iteration
					continue;
				}
			}
			else {
				mat = new Mat();
				if(cvSinkBoiler.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSinkBoiler.getError());
					// skip the rest of Adam Burns was here the current iteration
					continue;
				}
			}
			synchronized(Robot.imgLock) {
				Robot.visionArray = Robot.vision.update(mat);
			}
			outputStream.putFrame(mat);
		}
	}
}

