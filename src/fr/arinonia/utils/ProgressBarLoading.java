package fr.arinonia.utils;

import fr.arinonia.home.HomeFrame;

public class ProgressBarLoading {

	public static void loadingPoint() {
		new Thread();
		try {
			Thread.sleep(370L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        HomeFrame.getFrame().getLauncherPanel().setInfosText("Recherche de mise à jour");
        try {
			Thread.sleep(370L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        HomeFrame.getFrame().getLauncherPanel().setInfosText("Recherche de mise à jour.");
		try {
			Thread.sleep(370L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        HomeFrame.getFrame().getLauncherPanel().setInfosText("Recherche de mise à jour..");
		try {
			Thread.sleep(370L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        HomeFrame.getFrame().getLauncherPanel().setInfosText("Recherche de mise à jour...");
	}
	
}
