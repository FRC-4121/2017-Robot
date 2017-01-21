package org.usfirst.frc.team4121.robot.extraClasses;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team4121.robot.subsystems.VisonSystemSubsystem;

public class VisionProcesser {
	private String IPAddress;
	private double returnedValue;
	private VideoCapture camera; 
	private VisonSystemSubsystem vsubsystem;
	private ArrayList<MatOfPoint> foundContours;
	private Point centerOfImage;
	private Mat sourceImg = new Mat();
	private double isFacing = 0;

	public VisionProcesser(String ip) {
		IPAddress = ip;
		initializeCamera();
	}

	public double getReturnedValue() { //change name of method
		return returnedValue;
	}
	
	public VideoCapture getCamera() {
		return camera;
	}
	
	public double getIsFacing() {
		return isFacing;
	}

	private void initializeCamera() {
		camera.open(IPAddress);
	}

	private Point calcClosestPoint(MatOfPoint a) {
		Point closestPoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

		for (Point p : a.toList()) {
			if (Math.abs(p.x) < Math.abs(closestPoint.x)) {
				closestPoint = p;
			}
		}

		return closestPoint;
	}

	private Point calcFarthestPoint(MatOfPoint a) {
		Point farthestPoint = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);

		for (Point p : a.toList()) {
			if (Math.abs(p.x) > Math.abs(farthestPoint.x)) {
				farthestPoint = p;
			}
		}

		return farthestPoint;
	}
	
	public double [] update() {
		camera.read(sourceImg);
		vsubsystem.process(sourceImg);
		foundContours = vsubsystem.filterContoursOutput();
		double [] returnedArray = new double[3];

		centerOfImage = new Point(sourceImg.width() / 2, sourceImg.height() / 2);

		// grab all of the rectangles
		ArrayList<Rect> rectangles = new ArrayList<Rect>();

		for (MatOfPoint a : foundContours) {
			rectangles.add(new Rect(calcClosestPoint(a), calcFarthestPoint(a)));
		}

		// check for either one or two
		if (rectangles.size() == 1) {
			Point centerofRect = new Point(rectangles.get(0).width / 2, rectangles.get(0).height / 2);
			returnedValue = centerofRect.x - centerOfImage.x; //change the name of returned value
			
			returnedArray[0] = returnedValue;
			returnedArray[1] = -5.0; //isFacing error value
			returnedArray[2] = 0.0; //areaRatio error value
			
		}
		else if (rectangles.size() == 2) {
			if (rectangles.get(0).area() > rectangles.get(1).area() + 1) {
				Point centerOfRect = new Point(((rectangles.get(1).tl().x - rectangles.get(0).br().x) / 2) , rectangles.get(0).height / 2);
				returnedValue = centerOfRect.x - centerOfImage.x;
				returnedArray[0] = returnedValue;
				isFacing = 1;
				returnedArray[1] = isFacing;
				returnedArray[2] = rectangles.get(0).area() / rectangles.get(1).area();
				
			}
			else if (rectangles.get(0).area() < rectangles.get(1).area() - 1) {
				Point centerOfRect = new Point(((rectangles.get(1).tl().x - rectangles.get(0).br().x) / 2) , rectangles.get(0).height / 2);
				returnedValue = centerOfRect.x - centerOfImage.x;	
				returnedArray[0] = returnedValue;
				isFacing = -1;
				returnedArray[1] = isFacing;
				returnedArray[2] = rectangles.get(0).area() / rectangles.get(1).area();
			}
			
			else {
				Point centerOfRect = new Point(((rectangles.get(1).tl().x - rectangles.get(0).br().x) / 2) , rectangles.get(0).height / 2);
				returnedValue = centerOfRect.x - centerOfImage.x;
				returnedArray[0] = returnedValue;
				isFacing = 0;
				returnedArray[1] = isFacing;
				returnedArray[2] = rectangles.get(0).area() / rectangles.get(1).area();
			}
		}
		
		return returnedArray;
	}
}
