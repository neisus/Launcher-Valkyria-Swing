package fr.arinonia.utils;

import java.io.File;
import java.util.Arrays;

import fr.arinonia.home.HomeFrame;
import fr.arinonia.ram.RamManager;
import fr.northenflo.auth.mineweb.mc.MinewebGameType;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.minecraft.GameFolder;
import fr.theshark34.openlauncherlib.minecraft.GameInfos;
import fr.theshark34.openlauncherlib.minecraft.GameTweak;
import fr.theshark34.openlauncherlib.minecraft.GameVersion;
import fr.theshark34.openlauncherlib.minecraft.MinecraftLauncher;
import fr.theshark34.openlauncherlib.util.ProcessLogManager;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.application.integrated.FileDeleter;
import fr.theshark34.swinger.Swinger;

public class LauncherManager {

	public static final GameVersion VERSION = new GameVersion("1.7.10", MinewebGameType.V1_7_10);
    public static final GameInfos INFOS = new GameInfos("valkyria", VERSION, new GameTweak[0]);
    public static final File DIR = INFOS.getGameDir();
    public static final File CRASH_FOLDER = new File(DIR, "crash");
	private static AuthInfos authInfos = new AuthInfos("null", "null", "null");
	private static Thread updateThread;
	private static File ram = new File(DIR, "ram.properties");

    
    public static void update()throws Exception {
        SUpdate su = new SUpdate("https://valkyria.fr/launcher/launcher/", DIR);
        
        su.getServerRequester().setRewriteEnabled(true);
        su.addApplication(new FileDeleter());

        updateThread = new Thread() {
            private int val;
            private int max;

            public void run() {
                while (!isInterrupted()) {
                    if (BarAPI.getNumberOfFileToDownload() == 0) {
                        ProgressBarLoading.loadingPoint();
                    }
                    else {
                    	HomeFrame.getFrame().getLauncherPanel().getProgressBar().setVisible(true);
                        this.val = ((int)(BarAPI.getNumberOfTotalDownloadedBytes() / 1000L));
                        this.max = ((int)(BarAPI.getNumberOfTotalBytesToDownload() / 1000L));
                        HomeFrame.getFrame().getLauncherPanel().getProgressBar().setMaximum(this.max);
                        HomeFrame.getFrame().getLauncherPanel().getProgressBar().setValue(this.val);
                        HomeFrame.getFrame().getLauncherPanel().setInfosText("");
                        HomeFrame.getFrame().getLauncherPanel().setTelechargementText("Telechargement en cours.. (" + Swinger.percentage(this.val, this.max) + "%)");
                    
                    }
                }
            }
        };
        updateThread.start();
        su.start();
        updateThread.interrupt();
    }
    
    
    
    public static void launch() throws LaunchException {
        ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(INFOS, GameFolder.BASIC, authInfos);
		//profile.getVmArgs().addAll(ram.exists() ? Integer.parseInt(RamManager.getRam()) > 0 ? Arrays.asList("-Xms1G","-Xmx"+RamManager.getRam()+"G") : Arrays.asList("-Xms1G","-Xmx2G") : Arrays.asList("-Xms1G","-Xmx2G"));
        profile.getVmArgs().addAll(Utils.hasJava64Bits() ? ram.exists() ? Integer.parseInt(RamManager.getRam()) > 0 ? Arrays.asList("-Xms1G","-Xmx"+RamManager.getRam()+"G") : Arrays.asList("-Xms1G","-Xmx2G") : Arrays.asList("-Xms1G","-Xmx2G") : Arrays.asList("-Xms1G","-Xmx1G"));
        ExternalLauncher launcher = new ExternalLauncher(profile);
        Process p = launcher.launch();
        ProcessLogManager manager = new ProcessLogManager(p.getInputStream(), new File(DIR, "logs.txt"));
        manager.start();
        try {
            Thread.sleep(5000L);
           System.exit(0);
            p.waitFor();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    
}
