package co.com.s4n.deliveryDrone.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import co.com.s4n.deliveryDrone.command.IDeliveryCommand;
import co.com.s4n.deliveryDrone.command.impl.DeliveryCommand;
import co.com.s4n.deliveryDrone.services.IDeliveryDrone;
import co.com.s4n.deliveryDrone.services.ILogger;
import co.com.s4n.deliveryDrone.services.IRestaurant;

@Service
public class RestaurantImpl implements IRestaurant{
	
	@Value("${NUMBER_OF_DRONES}")
	private int numberOfDrones;
	
	@Autowired
	private ApplicationContext context;
	
	private ThreadPoolExecutor threadPoolExecutor;
	
	public RestaurantImpl(){
		threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}
	
	public void sendDeliveries(List<List<String>> deliveries){
		List<IDeliveryCommand> commands = createCommands(deliveries);
		
		int numberOfCommands = 0;
		for (IDeliveryCommand iDeliveryCommand : commands) {
			if(numberOfCommands >= numberOfDrones){
				System.out.println("La cantidad de drones(" + numberOfDrones + ") no es suficiente, compre mas");
				break;
			}
			threadPoolExecutor.execute(iDeliveryCommand);
			numberOfCommands ++;
		}
		threadPoolExecutor.shutdown();
	}
	
	private List<IDeliveryCommand> createCommands(List<List<String>> deliveries){
		List<IDeliveryCommand> commands = new ArrayList<>();
		
		for (int i = 0; i < deliveries.size(); i++) {
			IDeliveryCommand iDeliveryCommand = new DeliveryCommand();
			
			IDeliveryDrone iDeliveryDrone = context.getBean(IDeliveryDrone.class);
			ILogger iLogger = context.getBean(ILogger.class);
			
			iDeliveryCommand.createCommand(iDeliveryDrone, iLogger, deliveries.get(i), (i+1));
			commands.add(iDeliveryCommand);
		}
		return commands;
	}
}
