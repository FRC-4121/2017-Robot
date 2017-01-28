package org.usfirst.frc.team4121.robot.extraClasses;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.videoio.VideoCapture;

public class VisionProcessor {
	private int IPAddress;
	private double returnedValue;
	private VideoCapture camera; 
	private VisionRead vsubsystem;
	private ArrayList<MatOfPoint> foundContours;
	private Point centerOfImage;
	private Mat sourceImg = new Mat();
	private double isFacing = 0;

	public VisionProcessor(int ip) {
		IPAddress = ip;
		initializeCamera();
	}
	
	//used to grab camera feed if necessary
	public VideoCapture getCamera() {
		return camera;
	}

	
	private void initializeCamera() {
		
		camera = new VideoCapture(IPAddress);
	}

	//private method that reads from a Mat and finds the closest point from the left of the image
	private Point calcClosestPoint(MatOfPoint a) {
		Point closestPoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

		for (Point p : a.toList()) {
			if (Math.abs(p.x) < Math.abs(closestPoint.x)) {
				closestPoint = p;
			}
		}

		return closestPoint;
	}

	//private method that reads from a Mat and finds the farthest point from the left of the image
	private Point calcFarthestPoint(MatOfPoint a) {
		Point farthestPoint = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);

		for (Point p : a.toList()) {
			if (Math.abs(p.x) > Math.abs(farthestPoint.x)) {
				farthestPoint = p;
			}
		}

		return farthestPoint;
	}
	
	//reads image, processes it, calculates result(s) and returns in a double[] array
	public double [] update() {
		camera.read(sourceImg);
		if(!sourceImg.empty()) {
			vsubsystem.process(sourceImg);
		}
		
		else {
			
			double [] errorDouble = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
			
			return errorDouble;
		}
		foundContours = vsubsystem.filterContoursOutput();
		double [] returnedArray = new double[3];

		centerOfImage = new Point(sourceImg.width() / 2, sourceImg.height() / 2);

		// grab all of the rectangles
		ArrayList<Rect> rectangles = new ArrayList<Rect>();

		for (MatOfPoint a : foundContours) {
			rectangles.add(new Rect(calcClosestPoint(a), calcFarthestPoint(a)));
		}
		
		if(rectangles.size() == 0) {
			returnedArray[0] = Double.MIN_VALUE;
			returnedArray[1] = Double.MIN_VALUE;
			returnedArray[2] = Double.MIN_VALUE;
		}

		// check for either one or two rectangles - if one, looking at the boiler, if two, looking at the gears
		if (rectangles.size() == 1) {
			Point centerofRect = new Point(rectangles.get(0).width / 2, rectangles.get(0).height / 2);
			returnedValue = centerofRect.x - centerOfImage.x; //change the name of returned value
			
			returnedArray[0] = returnedValue;
			returnedArray[1] = -5.0; //isFacing error value
			returnedArray[2] = 0.0; //areaRatio error value
			
		}
		else if (rectangles.size() == 2) {
			
			//checking the comaprisons of the area to figure out where we are facing in relation to the gear targets
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
	
	//the value that is sent to the SmartDashboard
	public String tempDouble() {
		Double sentDouble = new Double(returnedValue);
		return sentDouble.toString();
	}
}
