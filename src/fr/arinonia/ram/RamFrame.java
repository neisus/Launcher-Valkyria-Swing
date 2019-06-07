package fr.arinonia.ram;

import javax.swing.JFrame;

import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;

@SuppressWarnings("serial")
public class RamFrame extends JFrame {

	private RamPanel ramPanel;
	private RamFrame instance;
	
	public RamFrame() {
		setTitle("Valkyria | Ram");
		setSize(963, 542);
		setDefaultCloseOperation(2);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setIconImage(Swinger.getResource("valkyria.png"));
		setContentPane(ramPanel = new RamPanel(this));
		WindowMover mover = new WindowMover(this);
		addMouseListener(mover);
		addMouseMotionListener(mover);
		setBackground(Swinger.TRANSPARENT);
		setVisible(true);
		instance = this;
	}

	public RamPanel getRamPanel() {
		return ramPanel;
	}
	
	public RamFrame getInstance() {
		return instance;
	}
	
}
