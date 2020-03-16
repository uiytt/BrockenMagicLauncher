package fr.uiytt.kaitenlauncher.update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;
import java.util.zip.CRC32;

import de.leonhard.storage.Json;
import fr.uiytt.kaitenlauncher.Config;
import fr.uiytt.kaitenlauncher.Main;


public class PackInfos {

	public static void checkforversion() throws IOException {
		Scanner s;

			URL url;
			if(Config.debug) {
				url = new URL("https://raw.githubusercontent.com/uiytt/Kaiten-Magic-Launcher/master/version.txt");
			} else {
				url = new URL(Config.link_VersionModPack);
			}
			s = new Scanner(url.openStream());



		Config.link = s.nextLine();
		@SuppressWarnings("unused")
		String new_version = s.nextLine();
		Config.CRC = s.nextLine();
		
		s.close();
		return;
	}

	public static boolean verify_modpack() {
		Main.getFrame().getProgresstext().setText("Vérification des fichiers");
		File downloadfile = new File(Config.DIR + "/download.json");
		CRC32 crc = new CRC32();
		//WARNING

		if(!Config.debug) {
			
		
		if (downloadfile.exists()) {
			try {
				crc.update(java.nio.file.Files.readAllBytes(downloadfile.toPath()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(!downloadfile.exists() || crc.getValue() != Long.valueOf(Config.CRC)) {
			Main.getFrame().getProgresstext().setText("Téléchargement de la liste des mods");
			try {
				URL website = new URL(Config.link);
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream(downloadfile);
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		} 
		}
		Json jsondownloadlist = new Json("download",Config.DIR);
		ModpackDownload modpack = new ModpackDownload(jsondownloadlist);
		Main.getFrame().getSpeedtext().setVisible(true);
		Main.getFrame().getResumbutton().setVisible(false);
		Main.getFrame().getPausebutton().setVisible(true);
		Main.getFrame().getStopbutton().setVisible(true);
		return modpack.download();
		
		
		

	}

	public static void deleteFolder(File element) {
		if (element.isDirectory()) {
			for (File sub : element.listFiles()) {
				deleteFolder(sub);
			}
		}
		element.delete();
	}

}
