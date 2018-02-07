package co.com.s4n.deliveryDrone.services;

import java.util.List;

public interface ILogger {

	public void addLine(String line);
	public void addListOfLines(List<String> lines);
	public void export(String fileName);
	public List<String> getLog();
}
