package fr.uiytt.brokenmagiclauncher.update;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import de.leonhard.storage.Json;
import fr.uiytt.brokenmagiclauncher.Config;
import fr.uiytt.brokenmagiclauncher.Main;

public class ModpackDownload {
	private int nbr_mods;

	private ArrayList<HashMap<?, ?>> mods;
	private ArrayList<HashMap<?, ?>> configs;
	private ArrayList<HashMap<?, ?>> other;
	private ArrayList<HashMap<?, ?>> remove;
	private String type;
	@SuppressWarnings("unchecked")
	public ModpackDownload(Json json) {

		this.mods = (ArrayList<HashMap<?, ?>>) json.get("mods");
		this.configs = (ArrayList<HashMap<?, ?>>) json.get("configs");
		this.other = (ArrayList<HashMap<?, ?>>) json.get("other");
		this.remove = (ArrayList<HashMap<?, ?>>) json.get("remove");
		this.nbr_mods = mods.size();
		this.type = (String) json.get("rule");
	}
	
	public boolean download() {
		File modsfolder = new File(Config.DIR + File.separator + "mods"+ File.separator);
		if(!modsfolder.isDirectory()) {
			if(modsfolder.exists()) {
				modsfolder.delete();
			} 
			modsfolder.mkdir();
		}

		List<Long> hashinmods = new ArrayList<Long>();
		List<Long> hashinJSON = new ArrayList<Long>();
		HashMap<Long, File> hashremove = new HashMap<Long, File>();

		CRC32 crc = new CRC32();
		for (File child : modsfolder.listFiles()) {
			if(!child.isDirectory()) {
				try {
					crc.reset();
					crc.update(java.nio.file.Files.readAllBytes(child.toPath()));
					hashinmods.add(crc.getValue());
					hashremove.put(crc.getValue(), child);
				} catch (IOException e) {
					e.printStackTrace();	
				}
			}
		}

		this.nbr_mods = 0;
		for(HashMap<?, ?> mod : mods) {

			Long hash = Long.valueOf((String) mod.get("hash"));

			hashinJSON.add(hash);

			if(!hashinmods.contains(hash)) {

				nbr_mods++;
			}

		}

		
		if(!this.type.equals("normal")) {
			for (File child : modsfolder.listFiles()) {
				if(!child.isDirectory()) {
						try {
							crc.reset();
							crc.update(java.nio.file.Files.readAllBytes(child.toPath()));
							Long hash = crc.getValue();
							if(!hashinJSON.contains(hash)) {
								child.delete();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		
				}
			}
		}

		int index = 1;
		Main.getFrame().getProgresstext().setText("Lancement du téléchargement");
		for(HashMap<?, ?> mod : mods) {
			
			while(PausableSwingWorker.getWorker().isPaused()) {
				if(PausableSwingWorker.getWorker().isDone()) {
					return false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(PausableSwingWorker.getWorker().isDone()) {
				return false;
			}
			
			

			String name = (String) mod.get("name"); 
			File modfile = new File(Config.DIR + File.separator + "mods"+ File.separator  + name + ".jar");
			crc.reset();
			if (modfile.exists()) {
				try {
					crc.update(java.nio.file.Files.readAllBytes(modfile.toPath()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} 

			Long crcfromhash = Long.valueOf((String) mod.get("hash"));
			if(!hashinmods.contains(crcfromhash)) {
				Main.getFrame().getProgresstext().setText("Téléchargement du mod " + String.valueOf(index) +"/"+String.valueOf(nbr_mods));
				System.out.println("[LAUNCHER] Téléchargement de " + name);
				
				
				try {
					URL website = new URL((String) mod.get("url"));
					if(!modfile.exists()) {
						modfile.createNewFile();
					}
					(new DownloadFile(website, modfile)).download();
				} catch (IOException e) {
					e.printStackTrace();
				}

				index++;
			}
			
			
		}
		System.out.println(this.configs.size());
		if(this.configs.size() > 0) {
			for(HashMap<?, ?> config : this.configs) {

				while(PausableSwingWorker.getWorker().isPaused()) {
					if(PausableSwingWorker.getWorker().isDone()) {
						return false;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(PausableSwingWorker.getWorker().isDone()) {
					return false;
				}

				String path = (String) config.get("path");
				File fileconf = new File(Config.DIR + File.separator +path);
				System.out.println("test5.3");

				if(!fileconf.exists()) {
					Main.getFrame().getProgresstext().setText("Téléchargement des Configs");
					String name = (String) config.get("name"); 
					System.out.println("[LAUNCHER] Téléchargement de la config \"" + name + "\"");
					fileconf.mkdirs();
					fileconf.delete();
					try {
						URL website = new URL((String) config.get("url"));
						fileconf.createNewFile();
						(new DownloadFile(website, fileconf)).download();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
			}
		}
		

		for(HashMap<?, ?> other : this.other) {
			while(PausableSwingWorker.getWorker().isPaused()) {
				if(PausableSwingWorker.getWorker().isDone()) {
					return false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(PausableSwingWorker.getWorker().isDone()) {
				return false;
			}
			String type = (String) other.get("type");
			String path = (String) other.get("path");
			File fileother;
			if(type.equalsIgnoreCase("zip")) {
				
				String futurepath = (String) other.get("futurepath");
				fileother = new File(Config.DIR + File.separator +futurepath);
			} else {
				fileother = new File(Config.DIR + File.separator +path);
				
			}
			
			if(!fileother.exists()) {
				
				fileother = new File(Config.DIR + File.separator +path);
				String name = (String) other.get("name");
				Main.getFrame().getProgresstext().setText("Téléchargement de(s) " + name);
				System.out.println("[LAUNCHER] Téléchargement de(s) \"" + name + "\"");
				fileother.mkdirs();
				fileother.delete();
				try {
					URL website = new URL((String) other.get("url"));
					fileother.createNewFile();
					(new DownloadFile(website, fileother)).download();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				if(type.equalsIgnoreCase("zip")) {
					
					try {
						unzip(fileother);
						fileother.delete();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
				}
				
			}
			
		}

		crc.reset();
		if(this.remove != null) {
			for(HashMap<?, ?> remove : this.remove) {
				Long hash = Long.valueOf((String) remove.get("hash"));
				if(hashremove.containsKey(hash)) {
					File todelete = hashremove.get(hash);
					todelete.delete();
					System.out.println("Deleting " + todelete.getAbsolutePath());
				}
			}
		}
		
		return true;
	}
	//CODE FOUND IN https://www.thejavaprogrammer.com/java-zip-unzip-files/
	private void unzip(File file) throws IOException {
		byte[] buffer = new byte[1024];
		int len;
		FileInputStream fis = new FileInputStream(file);
		
		//zipinputstream will be useful for reading zipped contents.
		ZipInputStream zis = new ZipInputStream(fis);
		
		//each file in zip file we can get by getNextEntry() method.
		ZipEntry zen = zis.getNextEntry();
		
		//we should check whether it is corrupted zip file or not. if corrupted then zip entry will be null
		//we can't extract that corrupted files.
		while (zen != null) {
			String fileName = zen.getName();
			
			//files should be unzipped according their paths only.
			File newFile = new File(file.getParent() + File.separator + fileName);
			
			//if any parent files are not present then we should create them also.
			File parents = new File(newFile.getParent());
			parents.mkdirs();
			

			if(zen.isDirectory()) {
				newFile.mkdir();
			} else {
				FileOutputStream fos = new FileOutputStream(newFile);
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
			}
			
			System.out.println("unziping " + fileName);
			//we should close io streams.
			
			zis.closeEntry();
			zen = zis.getNextEntry();
		}
		
		zis.closeEntry();
		zis.close();
		fis.close();
		file.delete();
	}
}
