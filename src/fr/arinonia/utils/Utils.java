package fr.arinonia.utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import fr.arinonia.Main;
import fr.theshark34.supdate.BarAPI;

public class Utils {

	public static final String APPNAME = "SAO launcher by Arinonia";

	
	public static void openURL(String url) throws IOException, URISyntaxException {
		Desktop.getDesktop().browse(new URI(url));
	}
	
    public static void sendMessageInConsole(Object message, boolean error) {
    	LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String str = dateTime.format(timeFormatter);
    	 if(error) {
     		System.err.println("["+str+"] " + "[" + APPNAME + "] " + message);
     	}else {
     		System.out.println("["+str+"] " + "[" + APPNAME + "] " + message);
     	}
    }
    
    public static boolean hasJava64Bits() {
    	if(System.getProperty("sun.arch.data.model").contains("64")) {
    		return true;
    	}else{
        	return false;
    	}
    }
    
    public static void tryToExit() {
		if (BarAPI.getNumberOfDownloadedFiles() != BarAPI.getNumberOfFileToDownload() &&
				JOptionPane.showConfirmDialog(Main.getInstance(),
						"Voulez-vous vraiment interrompre le téléchargement en cours ?", "Téléchargement en cour",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
				) != JOptionPane.YES_OPTION)
			return;
		System.exit(0);
	}
}
