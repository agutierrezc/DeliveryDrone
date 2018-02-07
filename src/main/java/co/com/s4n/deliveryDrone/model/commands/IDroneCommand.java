package co.com.s4n.deliveryDrone.model.commands;

import co.com.s4n.deliveryDrone.exceptions.BusinessException;
import co.com.s4n.deliveryDrone.model.Drone;

public interface IDroneCommand {
	
	public void executeCommand(Drone drone) throws BusinessException;
}
