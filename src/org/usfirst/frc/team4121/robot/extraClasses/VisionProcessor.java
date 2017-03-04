package org.usfirst.frc.team4121.robot.extraClasses;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;

public class VisionProcessor {
	private double returnedValue;
	private VisionRead vsubsystem;
	private ArrayList<MatOfPoint> foundContours;
	private Point centerOfImage;
	private double isFacing = 0;
	private Mat mat = new Mat();

	public VisionProcessor() {
	}

	//private method that reads from a Mat and finds the closest point from the left of the image

private Point calcClosestPoint(MatOfPoint a) {
		Point closestPoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

		ArrayList<Point> pointList = new ArrayList<Point>();

		for (Point p : a.toList()) {
			if (Math.abs(p.x) < Math.abs(closestPoint.x)) {
				closestPoint = p;
			}
		}

		pointList.add(closestPoint);

		for (Point p : a.toList()) {
			if (Math.abs(p.x) == Math.abs(closestPoint.x)) {
				pointList.add(p);
			}
		}

		for (Point p : pointList) {
			if (Math.abs(p.y) < Math.abs(closestPoint.y)) {
				closestPoint = p;
			}
		}

		return closestPoint;
	}

	private Point calcFarthestPoint(MatOfPoint a) {
		Point farthestPoint = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);

		ArrayList<Point> pointList = new ArrayList<Point>();

		for (Point p : a.toList()) {
			if (Math.abs(p.x) > Math.abs(farthestPoint.x)) {
				farthestPoint = p;
			}
		}

		pointList.add(farthestPoint);

		for (Point p : a.toList()) {
			if (Math.abs(farthestPoint.x) == Math.abs(p.x)) {
				pointList.add(p);
			}
		}

		for (Point p : pointList) {
			if (Math.abs(p.y) < Math.abs(farthestPoint.y)) {
				farthestPoint = p;
			}
		}

		return farthestPoint;
	}

	//reads image, processes it, calculates result(s) and returns in a double[] array
	public double[] update(VisionRead reader) {
		
		double [] returnedArray = new double[3];
		
		if(!mat.empty()) {
			reader.process(mat);
		}
		else { //Integer values account for errors
			Robot.visionArray=null;
		}
		foundContours = reader.filterContoursOutput();
		
		if(!foundContours.isEmpty())
		{
		Rect leftRect = Imgproc.boundingRect(reader.filterContoursOutput().get(0));
		Rect rightRect = Imgproc.boundingRect(reader.filterContoursOutput().get(1));
		
		double centerLeft= leftRect.x+(leftRect.width/2);
		double centerRight= rightRect.x+(rightRect.width/2);
		double averageCenter = (centerRight+centerLeft)/2;
		double centerError = averageCenter-(RobotMap.IMG_WIDTH/2);
		
		returnedArray[0]=centerError;
		returnedArray[1]=centerLeft;
		returnedArray[2]=centerRight;
		
		}
		
		else
		{
			returnedArray[0]=0.0;
			returnedArray[1]=-9999.0;
			returnedArray[2]=-9999.0;
		}
		/*centerOfImage = new Point(mat.width() / 2, mat.height() / 2);

		// grab all of the rectangles
		ArrayList<Rect> rectangles = new ArrayList<Rect>();

		for (MatOfPoint a : foundContours) {
			rectangles.add(new Rect(calcClosestPoint(a), calcFarthestPoint(a)));
		}
		if(rectangles.size() == 0) { //returns Double.MIN_VALUE if there are no rectangles to account for
			returnedArray[0] = -9999.0;
			returnedArray[1] = -9999.0;
			returnedArray[2] = -9999.0;
		}
		// check for either one or two rectangles - if one, looking at the boiler, if two, looking at the gears
		if (rectangles.size() == 1) {
			Point centerofRect = new Point(rectangles.get(0).width / 2, rectangles.get(0).height / 2);
			returnedValue = centerofRect.x - centerOfImage.x; //change the name of returned value

			returnedArray[0] = returnedValue;
			returnedArray[1] = -5.0; //isFacing error value - returned for the one rectangle only
			returnedArray[2] = 0.0; //areaRatio error value - returned for the one rectangle only
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
		else if(rectangles.size() < 2) { //if there are more than two rectangles, it returns Double.MAX_VALUE
			returnedArray[0] = -9999.0;
			returnedArray[1] = -9999.0;
			returnedArray[2] = -9999.0;
		}*/
		return returnedArray;
	}

//the value that is sent to the SmartDashboard
public String tempDouble() {
	Double sentDouble = new Double(returnedValue);
	//return camera.retrieve(sourceImg);
	return sentDouble.toString();
}
}
