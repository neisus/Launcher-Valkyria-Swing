package fr.arinonia.home;

import javax.swing.JFrame;

import fr.northenflo.auth.mineweb.utils.Get;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;

@SuppressWarnings("serial")
public class HomeFrame extends JFrame{

	private HomePanel panel;
	private static HomeFrame frame;

	public HomeFrame() {
		frame = this;
		setTitle(Get.getUSERNAME()+ " Valkyria Alpha");
		setSize(941, 732);
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
        setBackground(Swinger.TRANSPARENT);
		setContentPane(panel = new HomePanel()); 
		setIconImage(Swinger.getResource("valkyria.png"));
		WindowMover mover = new WindowMover(this);
		addMouseListener(mover);
		addMouseMotionListener(mover);
		setVisible(true);
	}
	
	public HomePanel getLauncherPanel() {
		return this.panel;
	}
	
	public static HomeFrame getFrame() {
		return frame;
	}
	
}
