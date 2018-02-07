package co.com.s4n.deliveryDrone.command;

import java.util.List;

import co.com.s4n.deliveryDrone.services.IDeliveryDrone;
import co.com.s4n.deliveryDrone.services.ILogger;

public interface IDeliveryCommand extends Runnable {
	
	public void createCommand(IDeliveryDrone iDeliveryDrone, ILogger iLogger, List<String> commands, int executeNumber);
}
