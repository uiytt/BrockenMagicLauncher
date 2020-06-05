package fr.uiytt.brokenmagiclauncher.update;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import fr.uiytt.brokenmagiclauncher.Launch;
import fr.uiytt.brokenmagiclauncher.Main;
import fr.uiytt.brokenmagiclauncher.auth.Auth;



public class PausableSwingWorker<T, V> extends SwingWorker<T, V> {
	private boolean isPaused;
	private static PausableSwingWorker<?, ?> worker;

	protected T doInBackground() throws Exception {
		worker = this;
		Main.getFrame().getProgressbarimg().setVisible(true);
		if (!Auth.checkAuth()) {
			return null;
		}
		try {
			PackInfos.checkforversion();

			Main.getFrame().getProgressbar().setVisible(true);
			Main.getFrame().getProgresstext().setVisible(true);
			Main.getFrame().getProgressbar().setValue(Main.getFrame().getProgressbar().getMaximum());

			if(PackInfos.verify_modpack()) {

				Launch.launchgame();
			}

		} catch (IOException e1){
			JOptionPane.showMessageDialog(Main.getFrame().getLaunchbutton(),
					"Erreur grave :\n Nous n'arrivons pas à contacter le serveur", "Erreur - 11", 0);
			Main.getFrame().enableAll();
			Main.getFrame().getProgresstext().setVisible(false);
			Main.getFrame().getProgressbar().setVisible(false);
			Main.getFrame().getProgressbarimg().setVisible(false);
			Main.getFrame().getSpeedtext().setVisible(false);
			Main.getFrame().getResumbutton().setVisible(false);
			Main.getFrame().getPausebutton().setVisible(false);
			Main.getFrame().getStopbutton().setVisible(false);
		}
		return null;
	}

	protected void done() {
		super.done();
		Main.getFrame().enableAll();
	}

	public final void pause() {
		if (!isPaused() && !isDone()) {
			this.isPaused = true;
			firePropertyChange("paused", Boolean.valueOf(false), Boolean.valueOf(true));
		}
	}

	public final void resume() {
		if (isPaused() && !isDone()) {
			this.isPaused = false;
			firePropertyChange("paused", Boolean.valueOf(true), Boolean.valueOf(false));
		}
	}

	public final boolean isPaused() {
		return this.isPaused;
	}

	public static PausableSwingWorker<?, ?> getWorker() {
		return worker;
	}
}
