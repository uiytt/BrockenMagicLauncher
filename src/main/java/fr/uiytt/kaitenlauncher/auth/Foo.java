package fr.uiytt.kaitenlauncher.auth;


import javax.swing.JOptionPane;

import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.model.AuthAgent;
import fr.litarvan.openauth.model.response.AuthResponse;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.uiytt.kaitenlauncher.Main;

public class Foo implements Runnable {
	private String username;
	private boolean auth;

	public Foo(String username, String password) {
		this.auth = false;

		this.username = username;
		this.password = password;
	}

	private String password;

	public void run() {
		Authenticator authenticator = new Authenticator("https://authserver.mojang.com/",AuthPoints.NORMAL_AUTH_POINTS);
		try {
			AuthResponse response = authenticator.authenticate(AuthAgent.MINECRAFT, this.username, this.password,
					"clientToken");
			Auth.setAuthinfos(new AuthInfos(response.getSelectedProfile().getName(), response.getAccessToken(),
					response.getSelectedProfile().getId()));
		} catch (AuthenticationException e) {
			Main.getFrame().getTextfieldpassword().setEnabled(true);
			Main.getFrame().getTextfieldusername().setEnabled(true);
			Main.getFrame().getConfigbutton().setEnabled(true);
			Main.getFrame().getLaunchbutton().setEnabled(true);
			JOptionPane.showMessageDialog(Main.getFrame().getLaunchbutton(),
					"Impossible de se connecter :\n" + e.getMessage(), "Erreur - 2", 0);
			this.auth = false;

			return;
		}
		this.auth = true;
	}

	public boolean getAuth() {
		return this.auth;
	}
}
