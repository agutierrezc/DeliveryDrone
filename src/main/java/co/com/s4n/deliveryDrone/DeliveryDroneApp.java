package co.com.s4n.deliveryDrone;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.com.s4n.deliveryDrone.exceptions.BusinessException;
import co.com.s4n.deliveryDrone.services.IRestaurant;

public class DeliveryDroneApp {
	private final static String BEANS_FILE = "DeliveryDrone-beans.xml";
	private static ApplicationContext context;
	
	public static void main(String[] args) throws BusinessException, IOException {
		
		context = new ClassPathXmlApplicationContext(BEANS_FILE);
		
		IRestaurant resturant = context.getBean(IRestaurant.class);
		
		if(args.length == 1){
			String folderPath = args[0];
			
			List<File> inputFiles = getInputFiles(folderPath);
			resturant.sendDeliveries(getAllCommands(inputFiles));
			System.out.println("Envios entregados!!");
		} else {
			System.out.println("Es necesario ingresar la ruta del directorio con los archivos de entrda");
		}
	}
	
	private static List<File> getInputFiles(String folderPath) throws BusinessException{
		File folder = validateFolderPath(folderPath);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if(name.lastIndexOf('.')>0) {
					int lastIndex = name.lastIndexOf('.');
					String str = name.substring(lastIndex);
					if(str.equals(".txt")) {
						return true;
					}
				}
				return false;
			}
		};
		File[] filesInFolder = folder.listFiles(filter);
		return getInputFilesOrderByName(filesInFolder);
	}
	
	private static List<File> getInputFilesOrderByName(File[] files){
		List<File> inputFilesSorted = new ArrayList<>();
		
		for (File file : files) {
			inputFilesSorted.add(file);
		}
		Collections.sort(inputFilesSorted);
		return inputFilesSorted;
	}
	
	private static File validateFolderPath(String folderPath) throws BusinessException{
		File folder = new File(folderPath);
		if (!(folder.exists() && folder.isDirectory())){
			throw new BusinessException("El directorio " + folderPath + " no es valido");
		}
		return folder;
	}
	
	private static List<List<String>> getAllCommands(List<File> files){
		List<List<String>> allCommands = new ArrayList<>();
		for (File file : files) {
			allCommands.add(getCommandsFile(file));
		}
		return allCommands;
	}
	
	private static List<String> getCommandsFile(File file){
		List<String> commands = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line;
			
			while ((line = br.readLine()) != null) {
				commands.add(line);
			}
			return commands;
		} catch (IOException e) {
			System.out.println("No se encuentro el archivo " + file.getAbsolutePath());
		}
		return null;
	}
}
