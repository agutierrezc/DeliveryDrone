package co.com.s4n.deliveryDrone.model.commands.impl;

import org.springframework.stereotype.Service;

import co.com.s4n.deliveryDrone.exceptions.BusinessException;
import co.com.s4n.deliveryDrone.model.Drone;
import co.com.s4n.deliveryDrone.model.Position;
import co.com.s4n.deliveryDrone.model.commands.IDroneCommand;

@Service("GoHomeDroneCommand")
public class GoHomeDroneCommand implements IDroneCommand{
	
	@Override
	public void executeCommand(Drone drone) throws BusinessException{
		Position dronePosition = drone.getPosition();
		dronePosition.restarPosicion();
	}
}
