package co.com.s4n.deliveryDrone.model;

import java.awt.Point;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class Drone {
	
	@Autowired
	private NeighborhoodMap neighborhoodMap;
	
	private Position position;

	public Drone () {
		position = new Position();
	}
	
	public NeighborhoodMap getNeighborhoodMap() {
		return neighborhoodMap;
	}

	public void setNeighborhoodMap(NeighborhoodMap neighborhoodMap) {
		this.neighborhoodMap = neighborhoodMap;
	}
	
	public Position getPosition(){
		return position;
	}

	public String getPositionAsString() {
		return position.toString();
	}
	
	public Point getMapPoint(){
		return position.getPoint();
	}
}
