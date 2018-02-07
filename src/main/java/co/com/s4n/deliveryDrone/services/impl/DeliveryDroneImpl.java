package co.com.s4n.deliveryDrone.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.com.s4n.deliveryDrone.exceptions.BusinessException;
import co.com.s4n.deliveryDrone.model.Drone;
import co.com.s4n.deliveryDrone.model.commands.IDroneCommand;
import co.com.s4n.deliveryDrone.services.IDeliveryDrone;

@Service
@Scope("prototype")
public class DeliveryDroneImpl implements IDeliveryDrone {

	@Value("${NOT_IMPLEMENTED_COMMAND_MESSAGE}")
	private String notImplementedCommand;
	
	@Value("${NUMBER_OF_PACKAGES}")
	private int numberOfPackage;
	
	@Autowired
	private Drone drone;
	
	@Autowired
	private Map<String, IDroneCommand> droneCommands = new ConcurrentHashMap<String, IDroneCommand>();

	/**
	 * inicia un serie de entregas, si una entrega no es posible por que se sale del mapa,
	 * muestra un mensaje de error pero continua con las entregas.
	 * La cantidad de entregas esta determinada por el parametro NUMBER_OF_PACKAGES,
	 * Cada vez que supere ese numero el dron debe volver al origen a recoger mas paquetes
	 * @param commands
	 * @throws BusinessException
	 */
	public List<String> startDeliveries(List<String> commands) {
		int numberOfDeliveries = 0;
		List<String> dronePosition = new ArrayList<>();
		
		for (String command : commands) {
			try {
				if(numberOfDeliveries >= numberOfPackage){
					goHome();
					numberOfDeliveries = 0;
				}
				if(isValidCommand(command)){
					executeDelivery(command);
					dronePosition.add(drone.getPositionAsString());
				} else {
					dronePosition.add(notImplementedCommand);
				}
			} catch (BusinessException e) {
				dronePosition.add(e.getMessage());
			}
			numberOfDeliveries++;
		}
		return dronePosition;
	}
	
	public String getDronePosition(){
		return drone.getPositionAsString();
	}
	
	private void executeDelivery(String route) throws BusinessException{
		for (int i = 0; i < route.length(); i++) {
			String command = route.charAt(i) + "";
			IDroneCommand droneCommand = droneCommands.get(command);
			if (droneCommand == null){
				throw new BusinessException(notImplementedCommand);
			}
			droneCommand.executeCommand(drone);
		}
	}
	
	private boolean isValidCommand(String command){
		if (command.isEmpty())
			return false;
		if (!command.matches("[AID]*"))
			return false;
		return true;
	}
	
	private void goHome() throws BusinessException{
		IDroneCommand droneCommand = droneCommands.get("GoHomeDroneCommand");
		droneCommand.executeCommand(drone);
	}
}
