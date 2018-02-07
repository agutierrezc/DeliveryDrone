package co.com.s4n.deliveryDrone.model.commands.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.com.s4n.deliveryDrone.exceptions.BusinessException;
import co.com.s4n.deliveryDrone.model.Drone;
import co.com.s4n.deliveryDrone.model.NeighborhoodMap;
import co.com.s4n.deliveryDrone.model.Position;
import co.com.s4n.deliveryDrone.model.commands.IDroneCommand;

@Service("A")
public class ForwardDroneCommand implements IDroneCommand{

	@Value("${OUT_OF_LIMITS_MESSAGE}")
	private String outOfLimitsMessage;
	
	@Override
	public void executeCommand(Drone drone) throws BusinessException{
		NeighborhoodMap droneMap = drone.getNeighborhoodMap();
		Position dronePosition = drone.getPosition();
		
		if (droneMap.isValidPosition(dronePosition.simulateForward())){
			dronePosition.forward();
		} else
			throw new BusinessException(outOfLimitsMessage);
	}
}
