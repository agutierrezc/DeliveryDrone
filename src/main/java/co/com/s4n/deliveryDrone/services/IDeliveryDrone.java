package co.com.s4n.deliveryDrone.services;

import java.util.List;

public interface IDeliveryDrone {
	
	/**
	 * Realiza una serie de moviemientos y retorna un listado de posciones segun los movimientos
	 * @param commands
	 * @return
	 */
	public List<String> startDeliveries(List<String> commands);
	
	public String getDronePosition();
}
