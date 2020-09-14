package fr.uiytt.magiclauncher.auth;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.uiytt.magiclauncher.Config;
import fr.uiytt.magiclauncher.Main;

public class Auth {
	private static AuthInfos authinfos;

	public static boolean checkAuth() {
		Main.getFrame().getTextfieldpassword().setEnabled(false);
		Main.getFrame().getTextfieldusername().setEnabled(false);
		Main.getFrame().getLaunchbutton().setEnabled(false);
		Main.getFrame().getConfigbutton().setEnabled(false);
		Main.getFrame().getShowpassword().setEnabled(false);

		if (Main.getFrame().getTextfieldusername().getText().replace(" ", "").length() == 0
				|| String.valueOf(Main.getFrame().getTextfieldpassword().getPassword()).length() == 0) {
			InputStream res = Main.class.getResourceAsStream("/fr/uiytt/magiclauncher/img/KAWAI.png");
			try {
				BufferedImage img = ImageIO.read(res);
				JOptionPane.showMessageDialog(Main.getFrame().getLaunchbutton(),
						"Merci de remplir le mot de passe et le nom d'utilisateur", "Erreur - 1", 0,
						new ImageIcon(img));
				Main.getFrame().enableAll();
				return false;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(Main.getFrame().getLaunchbutton(),
						"Merci de remplir le mot de passe et le nom d'utilisateur", "Erreur - 1", 0);
				e.printStackTrace();
				Main.getFrame().enableAll();
				return false;
			}
		}
		if (!requestauth(Main.getFrame().getTextfieldusername().getText(),
				String.valueOf(Main.getFrame().getTextfieldpassword().getPassword()))) {
			return false;
		}
		Config.writeUsername(Main.getFrame().getTextfieldusername().getText());
		Config.writePassword(String.valueOf(Main.getFrame().getTextfieldpassword().getPassword()));
		return true;
	}

	protected static boolean requestauth(String username, String password) {
		Foo foo = new Foo(username, password);
		Thread t = new Thread(foo);
		t.run();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return foo.getAuth();
	}

	public static AuthInfos getAuthinfos() {
		return authinfos;
	}

	public static void setAuthinfos(AuthInfos authinfos) {
		Auth.authinfos = authinfos;
	}

}
