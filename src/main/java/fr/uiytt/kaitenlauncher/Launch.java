package fr.uiytt.kaitenlauncher;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;

import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.GameFolder;
import fr.theshark34.openlauncherlib.minecraft.GameInfos;
import fr.theshark34.openlauncherlib.minecraft.GameTweak;
import fr.theshark34.openlauncherlib.minecraft.MinecraftLauncher;
import fr.theshark34.openlauncherlib.util.ProcessLogManager;
import fr.uiytt.kaitenlauncher.auth.Auth;
import fr.uiytt.kaitenlauncher.update.PackInfos;

public class Launch {
	public static void launchgame() throws LaunchException {
		Main.getFrame().getProgresstext().setText("Lancement du launcher");
		Main.KM_INFOS = new GameInfos("Kaiten Magic", new File(Config.DIR), Main.KM_VERSION,
				new GameTweak[] { GameTweak.FORGE });
		GameFolder gamefolder = new GameFolder("/assets/", "/libs/", "/natives/", "/bin/minecraft.jar");
		ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(Main.KM_INFOS, /** GameFolder.BASIC **/
				gamefolder, Auth.getAuthinfos());
		profile.getVmArgs().addAll(Arrays.asList(new String[] { "-Xms500M", "-Xmx" + Config.ram + "M" }));

		ExternalLauncher launcher = new ExternalLauncher(profile);
		try {
			Process p = launcher.launch();
			if (!(new File(String.valueOf(Config.DIR) + File.separator + "logs")).isDirectory()) {
				(new File(String.valueOf(Config.DIR) + File.separator + "logs")).mkdirs();
			}
			ProcessLogManager log = new ProcessLogManager(p.getInputStream(),
					new File(String.valueOf(Config.DIR) + File.separator + "logs" + File.separator + "latest.txt"));
			log.start();
			Config.writeModPackVersion("0.1");
			try {
				Thread.sleep(5000L);
				Main.getFrame().getGuiFrame().setVisible(false);
				Main.getFrame().getProgresstext().setVisible(false);
				Main.getFrame().getProgressbar().setVisible(false);
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(0);
		} catch (LaunchException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(Main.getFrame().getLaunchbutton(),
					"Erreur lors du lancement :\n" + e.getMessage(), "Erreur - 8", 0);
			if (JOptionPane.showConfirmDialog(Main.getFrame().getLaunchbutton(),
					"Voulez vous essayer de réparer le launcher ? \n /!\\ Cela supprimera tout vos dossiers du launcher",
					"Réparer le launcher ?", 2) == 0) {
				(new File(Config.DIR)).delete();
				(new File(Config.DIR)).mkdirs();
				try {
					PackInfos.checkforversion();
					Main.getFrame().getProgressbar().setVisible(true);
					Main.getFrame().getProgresstext().setVisible(true);
					Main.getFrame().getProgressbar().setValue(Main.getFrame().getProgressbar().getMaximum());
					PackInfos.verify_modpack();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(Main.getFrame().getLaunchbutton(),
							"Erreur grave :\n Contactez un administrateur", "Erreur - 10", 0);
					Main.getFrame().enableAll();
					Main.getFrame().getProgresstext().setVisible(false);
					Main.getFrame().getProgressbar().setVisible(false);
					Main.getFrame().getProgressbarimg().setVisible(false);
					Main.getFrame().getSpeedtext().setVisible(false);
					Main.getFrame().getResumbutton().setVisible(false);
					Main.getFrame().getPausebutton().setVisible(false);
					Main.getFrame().getStopbutton().setVisible(false);
				}
				
			} else {
				JOptionPane.showMessageDialog(Main.getFrame().getLaunchbutton(),
						"Erreur grave :\n Contactez un administrateur", "Erreur - 10", 0);
				Main.getFrame().enableAll();
				Main.getFrame().getProgresstext().setVisible(false);
				Main.getFrame().getProgressbar().setVisible(false);
				Main.getFrame().getProgressbarimg().setVisible(false);
				Main.getFrame().getSpeedtext().setVisible(false);
				Main.getFrame().getResumbutton().setVisible(false);
				Main.getFrame().getPausebutton().setVisible(false);
				Main.getFrame().getStopbutton().setVisible(false);
			}
		}

	}
}
