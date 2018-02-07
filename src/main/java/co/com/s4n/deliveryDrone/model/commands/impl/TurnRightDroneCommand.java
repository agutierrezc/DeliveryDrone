package co.com.s4n.deliveryDrone.model.commands.impl;

import org.springframework.stereotype.Service;

import co.com.s4n.deliveryDrone.model.Drone;
import co.com.s4n.deliveryDrone.model.Position;
import co.com.s4n.deliveryDrone.model.commands.IDroneCommand;

@Service("D")
public class TurnRightDroneCommand implements IDroneCommand{

	@Override
	public void executeCommand(Drone drone) {
		Position dronePosition = drone.getPosition();
		dronePosition.turnRight();
	}
}
