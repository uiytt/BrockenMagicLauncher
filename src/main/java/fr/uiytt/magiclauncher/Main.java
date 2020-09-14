package fr.uiytt.magiclauncher;

import java.io.File;
import java.io.IOException;

import fr.theshark34.openlauncherlib.minecraft.GameInfos;
import fr.theshark34.openlauncherlib.minecraft.GameType;
import fr.theshark34.openlauncherlib.minecraft.GameVersion;
import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;

public class Main {
	public static final GameVersion KM_VERSION = new GameVersion("1.12.2", GameType.V1_8_HIGHER);

	public static GameInfos KM_INFOS;
	public static File KM_DIR;
	private static LauncherFrame frame;
	public static GuiRunnable staticrunnable;

	public static void main(String[] args) {
		KM_DIR = GameDirGenerator.createGameDir(Config.name);
		try {
			if(Config.debug) {
				Config.updateConfig(new File("/home/uiytt/Dev/Serveurs/modpack"));
			} else {
				Config.updateConfig(KM_DIR);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		GuiRunnable runnable = new GuiRunnable();
		Thread a = new Thread(runnable);
		a.start();
		
		
		
		staticrunnable = runnable;

	}

	public static LauncherFrame getFrame() {
		return frame;
	}

	public static void setFrame(LauncherFrame frame2) {
		frame = frame2;
	}

}
