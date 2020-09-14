package fr.uiytt.magiclauncher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	//public static final String version = "1.1";
	public static final String name = "MAGICAL R'LYEH";
	public static String user = "";
	public static String password = "";
	public static String DIR;
	public static int ram = 4000;
	public static String modpack_version = "0.1";
	public static final String link_VersionModPack = "https://raw.githubusercontent.com/uiytt/BrokenMagicLauncher/master/version.txt";
	public static final boolean debug = false;
	public static boolean a2v1 = false;

	private static File JAR_FILE_LOCATION;
	private static Properties properties;

	public static String link;
	public static String CRC;
	public static String new_version;
	
	
	
	public static void updateConfig(File dir) throws IOException {
		if (!(new File(dir.getAbsolutePath())).isDirectory()) {
			(new File(dir.getAbsolutePath())).mkdirs();
		}
		JAR_FILE_LOCATION = new File(dir.getAbsolutePath() + "/parameters.txt");
		JAR_FILE_LOCATION.createNewFile();
		FileInputStream res = new FileInputStream(JAR_FILE_LOCATION);

		properties = new Properties();
		properties.load(res);
		if (properties.get("username") != null) {
			Config.user = (String) properties.get("username");
		}
		if (properties.get("password") != null) {
			Config.password = (String) properties.get("password");
		}
		if (properties.get("ram") == null) {
			writeRam(Config.ram);
		} else {
			Config.ram = Integer.valueOf((String) properties.get("ram")).intValue();
		}
		if (properties.get("path") == null) {
			writePath(dir.getAbsolutePath());
		}
		if (properties.get("modpack_version") == null) {
			writeModPackVersion(Config.modpack_version);
		} else {
			Config.modpack_version = properties.get("modpack_version").toString();
		}
		Config.DIR = properties.get("path").toString();
		if (!(new File((String) properties.get("path"))).exists()) {
			(new File((String) properties.get("path"))).mkdir();
		}
		if (properties.get("a2v1") != null) {
			LauncherFrame.setUnicorn(properties.get("a2v1").toString().equalsIgnoreCase("true"));
		}

	}

	public static void writeUnicorn(Boolean bool) {
		properties.put("a2v1", bool.toString());
		try {
			properties.store(new FileOutputStream(JAR_FILE_LOCATION), "Program parameters");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writePath(String path) {
		properties.put("path", path);
		try {
			properties.store(new FileOutputStream(JAR_FILE_LOCATION), "Program parameters");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeRam(int ram) {
		properties.put("ram", String.valueOf(ram));
		try {
			properties.store(new FileOutputStream(JAR_FILE_LOCATION), "Program parameters");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeModPackVersion(String path) {
		properties.put("modpack_version", path);
		try {
			properties.store(new FileOutputStream(JAR_FILE_LOCATION), "Program parameters");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writePassword(String password) {
		properties.put("password", password);
		try {
			properties.store(new FileOutputStream(JAR_FILE_LOCATION), "Program parameters");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeUsername(String user) {
		properties.put("username", user);
		try {
			properties.store(new FileOutputStream(JAR_FILE_LOCATION), "Program parameters");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
