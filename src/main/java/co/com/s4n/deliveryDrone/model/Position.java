package co.com.s4n.deliveryDrone.model;

import java.awt.Point;

public class Position {
	
	private Point point;
	
	private Orientation orientation;
	
	public Position() {
		this.point = new Point(0, 0);
		this.orientation = Orientation.NORTH;
	}
	
	public void turnLeft(){
		this.orientation = orientation.getLeftSide();
	}
	
	public void turnRight(){
		this.orientation = orientation.getRightSide();
	}
	
	public void forward(){
		if(this.orientation.equals(Orientation.NORTH)){
			point.y ++;
		} else if(this.orientation.equals(Orientation.EAST)) {
			point.x ++;
		} else if(this.orientation.equals(Orientation.SOUTH)) {
			point.y --;
		} else {
			point.x --;
		}
	}
	
	public Point simulateForward(){
		Point simulatePoint = new Point(point.x, point.y);
		
		if(this.orientation.equals(Orientation.NORTH)){
			simulatePoint.y ++;
		} else if(this.orientation.equals(Orientation.EAST)) {
			simulatePoint.x ++;
		} else if(this.orientation.equals(Orientation.SOUTH)) {
			simulatePoint.y --;
		} else {
			simulatePoint.x --;
		}
		
		return simulatePoint;
	}
	
	public void restarPosicion(){
		this.point = new Point(0, 0);
		this.orientation = Orientation.NORTH;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	@Override
	public String toString() {
		return "(" + point.x + ", " + point.y + ") " + orientation.toString();
	}
}
