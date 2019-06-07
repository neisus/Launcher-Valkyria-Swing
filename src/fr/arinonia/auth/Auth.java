package fr.arinonia.auth;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.jasypt.util.text.BasicTextEncryptor;

import fr.arinonia.Main;
import fr.arinonia.VPanel;
import fr.arinonia.home.HomeFrame;
import fr.northenflo.auth.exception.AccountSuspendException;
import fr.northenflo.auth.exception.DataEmptyException;
import fr.northenflo.auth.exception.DataWrongException;
import fr.northenflo.auth.exception.RequireGAuthException;
import fr.northenflo.auth.exception.ServerNotFoundException;
import fr.northenflo.auth.mineweb.AuthMineweb;
import fr.northenflo.auth.mineweb.utils.TypeConnection;

public class Auth {

	private VPanel panel;
	BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

	public Auth(VPanel panel) {
		this.panel = panel;
	}
	
	public void start() {
		panel.setFieldsEnabled(false);
		if (panel.usernameField.getText().replaceAll(" ", "").length() < 3) {
			JOptionPane.showMessageDialog(panel, "Veuillez entrer un pseudo valide", "Valkyria Error", 0);
			panel.setFieldsEnabled(true);
			return;
		}
		Thread t = new Thread() {
			@SuppressWarnings("deprecation")
			public void run() {
                AuthMineweb.setTypeConnection(TypeConnection.launcher);
                AuthMineweb.setUrlRoot("https://valkyria.fr");
                AuthMineweb.setUsername(panel.usernameField.getText());
                AuthMineweb.setPassword(panel.passwordField.getText());
                try {
					try {
						AuthMineweb.start();
					} catch (DataWrongException e) {
						panel.setFieldsEnabled(true);
						e.printStackTrace();
					} catch (DataEmptyException e) {
						panel.setFieldsEnabled(true);
						e.printStackTrace();
					} catch (IOException e) {
						panel.setFieldsEnabled(true);
						e.printStackTrace();
					} catch (ServerNotFoundException e) {
						panel.setFieldsEnabled(true);
						e.printStackTrace();
					}
				} catch (AccountSuspendException | RequireGAuthException e) {
					panel.setFieldsEnabled(true);
					e.printStackTrace();
				}
                if(AuthMineweb.isConnected()) {
                	basicTextEncryptor.setPasswordCharArray("dhnsjf45".toCharArray());
                	panel.getConfig().put("username", basicTextEncryptor.encrypt(panel.usernameField.getText()));
                	panel.getConfig().put("password", basicTextEncryptor.encrypt(panel.passwordField.getText()));
                	
                	Main.getInstance().setVisible(false);
                	new HomeFrame();
                }
			}
		};
		t.start();
	}
	
}
