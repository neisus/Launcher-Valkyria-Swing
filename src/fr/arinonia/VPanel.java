package fr.arinonia;

import static fr.theshark34.swinger.Swinger.getResource;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jasypt.util.text.BasicTextEncryptor;

import fr.arinonia.auth.Auth;
import fr.arinonia.ram.RamFrame;
import fr.arinonia.utils.Config;
import fr.arinonia.utils.Utils;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;

@SuppressWarnings("serial")
public class VPanel extends JPanel implements SwingerEventListener {

	
	
	BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
	
	private Image image = getResource("overlay.png");
	Config config = new Config("config");
	public JTextField usernameField; 
	public JPasswordField passwordField;
	private STexturedButton quitButton = new STexturedButton(getResource("close.png"), getResource("close.png"));
	private STexturedButton discordButton = new STexturedButton(getResource("discord.png"), getResource("discord.png"));
	private STexturedButton teamspeakButton = new STexturedButton(getResource("teamspeak.png"),
			getResource("teamspeak.png"));
	private STexturedButton siteButton = new STexturedButton(getResource("site.png"), getResource("site.png"));
	private STexturedButton forumButton = new STexturedButton(getResource("forum.png"), getResource("forum.png"));
	private STexturedButton connexionButton = new STexturedButton(getResource("connexion.png"),
			getResource("connexion.png"));
	private STexturedButton ramButton = new STexturedButton(getResource("option.png"), getResource("option.png"));

	public VPanel() {
    	basicTextEncryptor.setPasswordCharArray("dhnsjf45".toCharArray());
    	usernameField = new JTextField(basicTextEncryptor.decrypt(config.get("username")));
    	passwordField = new JPasswordField(basicTextEncryptor.decrypt(config.get("password")));
		setLayout(null);
		setBackground(new Color(255, 255, 255, 0));
		usernameField.setForeground(new Color(0, 0, 0));
		usernameField.setFont(new Font("Arial", 1, 20));
		usernameField.setCaretColor(new Color(0, 0, 0));
		usernameField.setOpaque(false);
		usernameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		usernameField.setBounds(420, 340, 262, 32);
		usernameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					Auth auth = new Auth(Main.getInstance().getPanel());
					auth.start();
				}
			}
		});
		passwordField.setForeground(new Color(0, 0, 0));
		passwordField.setFont(new Font("Arial", 1, 20));
		passwordField.setCaretColor(new Color(0, 0, 0));
		passwordField.setOpaque(false);
		passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		passwordField.setBounds(420, 390, 262, 32);
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					Auth auth = new Auth(Main.getInstance().getPanel());
					auth.start();
				}
			}
		});
		discordButton.addEventListener(this);
		discordButton.setOpaque(true);
		discordButton.setBounds(670, 250, 75, 75);
		discordButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		teamspeakButton.addEventListener(this);
		teamspeakButton.setOpaque(true);
		teamspeakButton.setBounds(716, 326, 60, 60);
		teamspeakButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		siteButton.addEventListener(this);
		siteButton.setOpaque(true);
		siteButton.setBounds(696, 400, 60, 60);
		siteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forumButton.addEventListener(this);
		forumButton.setOpaque(true);
		forumButton.setBounds(660, 470, 75, 75);
		forumButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		connexionButton.addEventListener(this);
		connexionButton.setOpaque(true);
		connexionButton.setBounds(355, 445, 150, 30);
		connexionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		quitButton.addEventListener(this);
		quitButton.setBounds(610, 256, 28, 28);
		quitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ramButton.addEventListener(this);
		ramButton.setBounds(240, 250, 75, 75);
		ramButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		add(usernameField);
		add(passwordField);
		add(discordButton);
		add(teamspeakButton);
		add(siteButton);
		add(forumButton);
		add(connexionButton);
		add(quitButton);
		add(ramButton);

	}

	@Override
	public void onEvent(SwingerEvent e) {
		if (e.getSource() == ramButton) {
			if (Utils.hasJava64Bits()) {
				Main.getInstance().setVisible(false);
				new RamFrame();
			} else {
				Utils.sendMessageInConsole("Vous n'avez pas java 64 bits " + System.getProperty("sun.arch.data.model"), false);
			}
		} else if (e.getSource() == quitButton) {
			System.exit(0);
		} else if (e.getSource() == discordButton) {
			try {
				Utils.openURL("https://discord.gg/Fbq9X2w");
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == teamspeakButton) {

		} else if (e.getSource() == siteButton) {
			try {
				Utils.openURL("http://valkyria.fr/");
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == forumButton) {
			try {
				Utils.openURL("https://valkyria.fr/forum/index.php");
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == connexionButton) {
			Auth auth = new Auth(this);
			auth.start();
		}
	}

	public void setFieldsEnabled(boolean enabled) {
		usernameField.setEnabled(enabled);
		passwordField.setEnabled(enabled);
		connexionButton.setEnabled(enabled);
		ramButton.setEnabled(enabled);
	}
	
	public Config getConfig() {
		return config;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Swinger.drawFullsizedImage(g, this, image);
	}

}
