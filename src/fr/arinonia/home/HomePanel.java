package fr.arinonia.home;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.arinonia.utils.LauncherManager;
import fr.arinonia.utils.Utils;
import fr.northenflo.auth.mineweb.utils.Get;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;
import fr.theshark34.swinger.textured.STexturedProgressBar;

@SuppressWarnings("serial")
public class HomePanel extends JPanel implements SwingerEventListener {

	private Image base = Swinger.getResource("base.png");
	String str_money = Get.getSession.getUser("money");
	String str_name = Get.getSession.getUser("pseudo");
	String str_date = Get.getSession.getUser("created");
	String str_grade = Get.getSession.getUser("rank");
	private JLabel name = new JLabel("Nom: " + this.str_name);
	private JLabel money = new JLabel("Points boutique: " + this.str_money);
	private JLabel date = new JLabel("Creation: " + this.str_date);
	private JLabel grade = new JLabel("Grade: " + this.str_grade);
	Font font = new Font("SegoeUI", 1, 18);
	private static STexturedProgressBar progressBar = new STexturedProgressBar(Swinger.getResource("air.png"),
			Swinger.getResource("barfull.png"));
	private static STexturedButton play = new STexturedButton(Swinger.getResource("play.png"),
			Swinger.getResource("play.png"));
	private static STexturedButton close = new STexturedButton(Swinger.getResource("close.png"),
			Swinger.getResource("close.png"));
	private static JLabel telechargement = new JLabel("");
	private static JLabel info = new JLabel();
	private static JLabel admin = new JLabel("admin");
	private STexturedButton userSkin = new STexturedButton(Swinger.getResource("air.png"));

	URL url;

	{
		try {
			url = new URL("https://valkyria.fr/API/get_head_skin/" + str_name + "/256");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	Image skin;

	{
		try {
			skin = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HomePanel() {
		setLayout(null);
		setBackground(Swinger.TRANSPARENT);
		name.setBounds(100, 160, 300, 20);
		name.setFont(font);
		name.setForeground(Color.WHITE);
		name.setFont(name.getFont().deriveFont(20F));
		admin.setBounds(730, 10, 70, 100);
		admin.setFont(admin.getFont().deriveFont(25.0f));
		admin.setVisible(false);
		admin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if (grade.getText().equalsIgnoreCase("Grade: Fondateur")) {
			admin.setVisible(true);
		}
		money.setBounds(100, 260, 300, 20);
		money.setFont(font);
		money.setForeground(Color.WHITE);
		money.setFont(money.getFont().deriveFont(20F));
		grade.setBounds(100, 350, 300, 20);
		grade.setFont(font);
		grade.setForeground(Color.WHITE);
		grade.setFont(grade.getFont().deriveFont(20F));
		date.setBounds(100, 440, 300, 20);
		date.setFont(font);
		date.setForeground(Color.WHITE);
		date.setFont(date.getFont().deriveFont(20F));
		progressBar.setBounds(164, 574, 610, 46);
		play.addEventListener(this);
		play.setBounds(540, 450, 300, 90);
		play.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		close.addEventListener(this);
		close.setBounds(800, 30);
		close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		telechargement.setBounds(340, 440, 400, 400);
		telechargement.setFont(new Font("SegoeUI", 0, 20));
		telechargement.setFocusable(true);
		info.setBounds(340, 400, 400, 400);
		info.setFont(new Font("SegoeUI", 0, 20));
		info.setText("Bienvenue sur valkyria");
		userSkin.setBounds(561, 186, 255, 255);
		userSkin.setTextureHover(skin);
		userSkin.setTexture(skin);
		
		
		add(name);
		add(admin);
		add(money);
		add(grade);
		add(date);
		add(progressBar);
		add(play);
		add(close);
		add(telechargement);
		add(info);
		add(userSkin);


	}

	@Override
	public void onEvent(SwingerEvent e) {
		if (e.getSource() == close) {
			Utils.tryToExit();
		} else if (e.getSource() == play) {
			setEnabeled(false);
			Thread t = new Thread() {
				@Override
				public void run() {
					try {
						LauncherManager.update();
					} catch (Exception e1) {
						e1.printStackTrace();
						setEnabeled(true);
					}
					try {
						LauncherManager.launch();
					} catch (LaunchException e1) {
						e1.printStackTrace();
						setEnabeled(true);
					}
				}
			};
			t.start();
		}
	}

	static void setEnabeled(boolean enable) {
		play.setEnabled(enable);
	}

	public STexturedProgressBar getProgressBar() {
		return progressBar;
	}

	public void setTelechargementText(String text) {
		telechargement.setText(text);
	}

	public void setInfosText(String text) {
		info.setText(text);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Swinger.drawFullsizedImage(g, this, base);

	}

}
