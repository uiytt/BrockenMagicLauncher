package fr.uiytt.brokenmagiclauncher;

public class GuiRunnable implements Runnable {

	private LauncherFrame frame;

	public synchronized LauncherFrame getLauncherFrame() {
		return this.frame;

	}

	public void run() {
		this.frame = new LauncherFrame();
		this.frame.InitGui();

	}

}
