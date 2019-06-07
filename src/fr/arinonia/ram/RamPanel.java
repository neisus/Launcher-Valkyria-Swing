package fr.arinonia.ram;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.arinonia.Main;
import fr.arinonia.utils.LauncherManager;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;

@SuppressWarnings("serial")
public class RamPanel extends JPanel {

	public Image background = Swinger.getResource("ram_1.png");
	private File ramfile = new File(LauncherManager.DIR, "ram.properties");
	private STexturedButton valider;
	public JSlider ramSlider = new JSlider();
	public JLabel ramLabel = new JLabel(ramfile.exists() ? RamManager.getRam().toString() + "Go" : "1Go");
	private RamFrame ramFrame;

	public RamPanel(RamFrame ramFrame) {
		this.ramFrame = ramFrame;
		setLayout(null);
		ramLabel.setFont(new Font("Arial", 1, 60));
		ramLabel.setForeground(Color.black);
		ramLabel.setBounds(620, 232, 175, 48);
		add(ramLabel);

		ramSlider.setMinimum(1);
		ramSlider.setMaximum(10);
		ramSlider.setBackground(Swinger.TRANSPARENT);
		ramSlider.setOpaque(false);
		ramSlider.setBounds(300, 300, 340, 50);
		ramSlider.setFocusable(false);
		ramSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				RamManager.setRam(ramSlider.getValue() + "Go");
				ramLabel.setText(ramSlider.getValue() + "Go");
			}
		});
		ramSlider.setValue(ramfile.exists() ? Integer.parseInt(RamManager.getRam()) : 1);
		valider = new STexturedButton(Swinger.getResource("valid_ram.png"), Swinger.getResource("valid_ram.png"));
		valider.setBounds(350, 350);
		valider.addEventListener(new SwingerEventListener() {
			
			@Override
			public void onEvent(SwingerEvent arg0) {
				Main.getInstance().setVisible(true);
				ramFrame.setVisible(false);
				
			}
		});
		valider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(ramLabel);
		add(ramSlider);
        add(valider);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Swinger.drawFullsizedImage(g, this, this.background);
	}
	
	public RamFrame getRamFrame() {
		return ramFrame;
	}

}
