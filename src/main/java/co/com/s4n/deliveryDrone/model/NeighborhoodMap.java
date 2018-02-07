package co.com.s4n.deliveryDrone.model;

import java.awt.Point;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import co.com.s4n.deliveryDrone.exceptions.BusinessException;

@Repository
public class NeighborhoodMap {
	
	@Value("${INVALID_MAP_DIMENSIONS_MESSAGE}")
	private String invalidMapDimansions;
	
	@Value("${MAP_DIMENSIONS}")
	private String mapDimansions;
	
	private int axisY;
	private int axisX;
	
	public NeighborhoodMap(){
		this.axisX = 0;
		this.axisY = 0;
	}
	
	@PostConstruct
	public void init() throws BusinessException{
		String[] dimansions = mapDimansions.split(",");
		
		if (dimansions.length != 2) {
			throw new BusinessException(invalidMapDimansions);
		}
		try {
			int axisX = Integer.parseInt(dimansions[0]);
			int axisY = Integer.parseInt(dimansions[1]);
			this.setAxisY(axisY);
			this.setAxisX(axisX);
		} catch (Exception e) {
			throw new BusinessException(invalidMapDimansions);
		}
	}

	public int getAxisY() {
		return axisY;
	}

	public void setAxisY(int axisY) {
		this.axisY = axisY;
	}

	public int getAxisX() {
		return axisX;
	}

	public void setAxisX(int axisX) {
		this.axisX = axisX;
	}
	
	//Determina si una posicion es validad de acuerdo a sus ejes
	public boolean isValidPosition(Point position){
		if (position.x >= this.axisX)
			return false;
		if (position.y >= this.axisY)
			return false;
		if (position.x <= this.axisX * -1)
			return false;
		if (position.y <= this.axisY * -1)
			return false;
		return true;
	}

}
