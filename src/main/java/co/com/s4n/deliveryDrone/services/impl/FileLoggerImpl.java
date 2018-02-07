package co.com.s4n.deliveryDrone.services.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.com.s4n.deliveryDrone.services.ILogger;

@Service
@Scope("prototype")
public class FileLoggerImpl implements ILogger{
	
	private List<String> log;
	
	public FileLoggerImpl(){
		log = new ArrayList<String>();
	}

	@Override
	public void addLine(String line) {
		log.add(line);
	}
	
	@Override
	public void addListOfLines(List<String> lines) {
		log.addAll(lines);
	}

	@Override
	public void export(String fileName) {
		boolean firstLine = true;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
			for (String string : log) {
				if(firstLine){
					bw.write(string);
					firstLine = false;
				}
				else{
					bw.write("\n");
					bw.write(string);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getLog() {
		return log;
	}
	
	@Override
	public String toString() {
		String logAsString = "";
		for (String string : log) {
			logAsString += string + "\n";
		}
		return logAsString;
	}
}
