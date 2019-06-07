package fr.arinonia;


import javax.swing.JFrame;


import fr.arinonia.utils.LauncherManager;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.animation.Animator;
import fr.theshark34.swinger.util.WindowMover;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private static Main instance;
	private VPanel panel;

	public Main() {
		setTitle("Valkyria Alpha");
		setSize(962, 581);
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setBackground(Swinger.TRANSPARENT);
		setIconImage(Swinger.getResource("valkyria.png"));
		setContentPane(this.panel = new VPanel());

		WindowMover mover = new WindowMover(this);
		addMouseListener(mover);
		addMouseMotionListener(mover);
		Animator.fadeInFrame(this, 10);
		setVisible(true);
	}

	public static void main(String[] args) {
		Swinger.setSystemLookNFeel();
		System.setProperty("http.agent", "Chrome");
		Swinger.setResourcePath("/fr/arinonia/res");
		if (!LauncherManager.DIR.exists())
			LauncherManager.DIR.mkdirs();
		instance = new Main();
		
		
	}

	public static Main getInstance() {
		return instance;
	}

	public VPanel getPanel() {
		return panel;
	}

}
