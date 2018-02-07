package co.com.s4n.deliveryDrone.command.impl;

import java.util.List;

import co.com.s4n.deliveryDrone.command.IDeliveryCommand;
import co.com.s4n.deliveryDrone.services.IDeliveryDrone;
import co.com.s4n.deliveryDrone.services.ILogger;

public class DeliveryCommand implements IDeliveryCommand{
	
	private final static String REPORT_MESSAGE = "== Reporte de entregas ==";
	
	private IDeliveryDrone iDeliveryDrone;
	private ILogger iLogger;
	private List<String> commands;
	private int executeNumber;
	
	public void createCommand(IDeliveryDrone iDeliveryDrone, ILogger iLogger, List<String> commands, int executeNumber){
		this.iDeliveryDrone = iDeliveryDrone;
		this.iLogger = iLogger;
		this.commands = commands;
		this.executeNumber = executeNumber;
	}

	@Override
	public void run() {
		List<String> dronePosicion = iDeliveryDrone.startDeliveries(commands);
		iLogger.addLine(REPORT_MESSAGE);
		iLogger.addListOfLines(dronePosicion);
		iLogger.export(getOutputFileName());
	}
	
	private String getOutputFileName(){
		String outputFileName;
		if(executeNumber<10){
			outputFileName = "out0" + executeNumber + ".txt";
		} else {
			outputFileName = "out" + executeNumber + ".txt";
		}
		return outputFileName;
	}
}
