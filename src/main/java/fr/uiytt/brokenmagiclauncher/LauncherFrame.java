package fr.uiytt.brokenmagiclauncher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import fr.uiytt.brokenmagiclauncher.update.PackInfos;
import fr.uiytt.brokenmagiclauncher.update.PausableSwingWorker;

public class LauncherFrame {
	private JFrame guiFrame;
	private JTextField textfieldusername;
	private JTextField pathfield;
	private JLabel unicorn2;
	private JPasswordField textfieldpassword;
	private JButton launchbutton;
	private JButton configbutton;
	private JButton closebutton;
	private JButton filechooser;
	private JButton pausebutton;
	private JButton resumbutton;
	private JButton websitebutton;
	private JButton stopbutton;
	private JButton repairbutton;
	private JButton showpassword;
	private static boolean unicorn = false;
	private JLayeredPane lpane;
	private JLabel background_config;
	private JLabel progressbarimg;
	private JFileChooser path_chooser;
	private int MaxRAM;
	private int MinRAM;
	private int CurrentRAM;
	private JSlider sliderRAM;
	private JTextArea textRAM;
	private JTextPane progresstext;
	private JTextPane speedtext;
	private JProgressBar progressbar;

	public LauncherFrame() {
		Main.setFrame(this);
		this.MaxRAM = 16000;
		this.MinRAM = 500;
		this.CurrentRAM = Config.ram;
	}

	public void InitGui() {
		this.guiFrame = new JFrame();
		this.guiFrame.setDefaultCloseOperation(3);
		this.guiFrame.setTitle("Kaiten Magic");
		this.guiFrame.setSize(1280, 720);
		this.guiFrame.setLocationRelativeTo(null);
		this.guiFrame.setResizable(false);

		try {
			createInterface();
		} catch (IOException e) {

			e.printStackTrace();
		}
		initconfig();

		this.guiFrame.setVisible(true);
	}

	public void createInterface() throws IOException {
		this.lpane = new JLayeredPane();
		this.guiFrame.setLayout(new BorderLayout());
		this.guiFrame.add(this.lpane, "Center");

		try {
			InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/Menu.png");
			BufferedImage img = ImageIO.read(res);
			JLabel label1 = new JLabel(new ImageIcon(img));
			label1.setBounds(0, 0, 1280, 720);
			this.lpane.add(label1, new Integer(0), 0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.textfieldusername = new JTextField("", 10);
		this.textfieldusername.setBounds(506, 269, 333, 55);
		this.textfieldusername.setBorder(null);
		this.textfieldusername.setOpaque(false);
		this.textfieldusername.setCaretColor(Color.WHITE);
		Font font1 = new Font("arial", 0, 20);
		this.textfieldusername.setFont(font1);
		this.textfieldusername.setForeground(Color.WHITE);
		if (!Config.user.isEmpty()) {
			this.textfieldusername.setText(Config.user);
		}
		this.lpane.add(this.textfieldusername, new Integer(1), 0);
		this.textfieldpassword = new JPasswordField("", 10);
		this.textfieldpassword.setEchoChar('\u25CF');
		this.textfieldpassword.setBounds(506, 352, 333, 55);
		this.textfieldpassword.setBorder(BorderFactory.createEmptyBorder());
		this.textfieldpassword.setOpaque(false);
		this.textfieldpassword.setCaretColor(Color.WHITE);
		this.textfieldpassword.setFont(font1);
		this.textfieldpassword.setForeground(Color.WHITE);
		if (!Config.password.isEmpty()) {
			this.textfieldpassword.setText(Config.password);
		}
		this.lpane.add(this.textfieldpassword, new Integer(1), 0);

		JButton showunicorn = new JButton();
		showunicorn.setBounds(1221, 630, 80, 80);
		showunicorn.setOpaque(false);
		showunicorn.setContentAreaFilled(false);
		showunicorn.setBorderPainted(false);

		this.lpane.add(showunicorn, new Integer(1), 0);
		showunicorn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (unicorn == false) {
					unicorn = true;
					Config.writeUnicorn(true);
					unicorn2.setVisible(true);
				} else {
					unicorn = false;
					Config.writeUnicorn(false);
					unicorn2.setVisible(false);
				}

			}
		});

		try {
			InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/unicorn.png");
			BufferedImage img = ImageIO.read(res);
			unicorn2 = new JLabel(new ImageIcon(img));
			unicorn2.setBounds(0, 0, 300, 250);
			unicorn2.setVisible(LauncherFrame.unicorn);
			this.lpane.add(unicorn2, new Integer(0), 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.showpassword = new JButton();
		this.showpassword.setBounds(851, 366, 27, 27);
		this.showpassword.setOpaque(false);
		this.showpassword.setContentAreaFilled(false);
		this.showpassword.setBorderPainted(false);
		this.showpassword.setFocusPainted(false);
		this.lpane.add(this.showpassword, new Integer(1), 0);
		final ButtonModel bModel = this.showpassword.getModel();
		bModel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (bModel.isPressed()) {

					Main.getFrame().getTextfieldpassword().setEchoChar((char) 0);
				} else {
					Main.getFrame().getTextfieldpassword().setEchoChar('\u25CF');
				}
			}
		});

		InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/launchbutton1.png");
		BufferedImage img = ImageIO.read(res);
		this.launchbutton = new JButton(new ImageIcon(img));
		this.launchbutton.setBounds(590, 511, 267, 60); 
		
		this.launchbutton.setContentAreaFilled(false);
		this.launchbutton.setBorderPainted(false);
		this.launchbutton.setFocusPainted(false);
		this.launchbutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (LauncherFrame.this.launchbutton.isEnabled()) {
					InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/launchbutton2.png");

					try {
						BufferedImage img = ImageIO.read(res);
						LauncherFrame.this.launchbutton.setIcon(new ImageIcon(img));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			public void mouseExited(MouseEvent evt) {
				if (LauncherFrame.this.launchbutton.isEnabled()) {
					InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/launchbutton1.png");

					try {
						BufferedImage img = ImageIO.read(res);
						LauncherFrame.this.launchbutton.setIcon(new ImageIcon(img));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		this.guiFrame.getRootPane().setDefaultButton(this.launchbutton);
		launchbutton.requestFocus();
		this.lpane.add(this.launchbutton, new Integer(1), 0);
		this.launchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(System.getProperty("sun.arch.data.model").equalsIgnoreCase("64")) {
					PausableSwingWorker<?, ?> worker = new PausableSwingWorker<String, String>();
					worker.execute();
				} else {
					JOptionPane.showMessageDialog(Main.getFrame().getLaunchbutton(),
							"Vous n'avez pas Java 64 bit", "Erreur - 9", 0);
					Main.getFrame().enableAll();
				}
				
			}
		});

		this.configbutton = new JButton();
		this.configbutton.setBounds(506, 511, 60, 60);
		this.configbutton.setContentAreaFilled(false);
		this.configbutton.setBorderPainted(false);
		this.configbutton.setFocusPainted(false);
		this.configbutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				if (LauncherFrame.this.configbutton.isEnabled()) {
					InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/configbutton2.png");

					try {
						BufferedImage img = ImageIO.read(res);
						LauncherFrame.this.configbutton.setIcon(new ImageIcon(img));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			public void mouseExited(MouseEvent evt) {
				if (LauncherFrame.this.configbutton.isEnabled()) {
					LauncherFrame.this.configbutton.setIcon(null);
				}
			}
		});
		this.lpane.add(this.configbutton, new Integer(1), 0);
		this.configbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LauncherFrame.this.openConfig();
			}
		});

		this.lpane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mouseReleased(e);
				LauncherFrame.this.lpane.grabFocus();
			}
		});

		res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/pausebutton1.png");
		img = ImageIO.read(res);
		this.pausebutton = new JButton(new ImageIcon(img));
		this.pausebutton.setBounds(905, 617, 28, 29);
		this.pausebutton.setVisible(false);
		this.pausebutton.setContentAreaFilled(false);
		this.pausebutton.setBorderPainted(false);
		this.pausebutton.setFocusPainted(false);
		this.lpane.add(this.pausebutton, new Integer(1), 0);
		this.pausebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!PausableSwingWorker.getWorker().isPaused()) {
					PausableSwingWorker.getWorker().pause();
					Main.getFrame().getPausebutton().setVisible(false);
					Main.getFrame().getResumbutton().setVisible(true);
				}
			}
		});
		this.pausebutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/pausebutton2.png");

				try {
					BufferedImage img = ImageIO.read(res);
					LauncherFrame.this.pausebutton.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void mouseExited(MouseEvent evt) {
				InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/pausebutton1.png");

				try {
					BufferedImage img = ImageIO.read(res);
					LauncherFrame.this.pausebutton.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/playbutton1.png");
		img = ImageIO.read(res);
		this.resumbutton = new JButton(new ImageIcon(img));
		this.resumbutton.setBounds(905, 617, 28, 29);
		this.resumbutton.setVisible(false);
		this.resumbutton.setContentAreaFilled(false);
		this.resumbutton.setBorderPainted(false);
		this.resumbutton.setFocusPainted(false);
		this.lpane.add(this.resumbutton, new Integer(1), 0);
		this.resumbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PausableSwingWorker.getWorker().isPaused()) {
					PausableSwingWorker.getWorker().resume();
					Main.getFrame().getResumbutton().setVisible(false);
					Main.getFrame().getPausebutton().setVisible(true);
				}
			}
		});

		this.pausebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!PausableSwingWorker.getWorker().isPaused()) {
					PausableSwingWorker.getWorker().pause();
					Main.getFrame().getPausebutton().setVisible(false);
					Main.getFrame().getResumbutton().setVisible(true);
				}
			}
		});
		this.resumbutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/playbutton2.png");

				try {
					BufferedImage img = ImageIO.read(res);
					LauncherFrame.this.resumbutton.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void mouseExited(MouseEvent evt) {
				InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/playbutton1.png");

				try {
					BufferedImage img = ImageIO.read(res);
					LauncherFrame.this.resumbutton.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/closebutton1.png");
		img = ImageIO.read(res);
		this.stopbutton = new JButton(new ImageIcon(img));
		this.stopbutton.setBounds(941, 617, 29, 29);
		this.stopbutton.setContentAreaFilled(false);
		this.stopbutton.setBorderPainted(false);
		this.stopbutton.setFocusPainted(false);
		this.stopbutton.setVisible(false);
		this.lpane.add(this.stopbutton, new Integer(1), 0);
		this.stopbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!PausableSwingWorker.getWorker().isCancelled()) {
					PausableSwingWorker.getWorker().cancel(false);
					Main.getFrame().getResumbutton().setVisible(false);
					Main.getFrame().getPausebutton().setVisible(false);
					Main.getFrame().getStopbutton().setVisible(false);
					Main.getFrame().getProgressbar().setVisible(false);
					Main.getFrame().getProgresstext().setVisible(false);
					Main.getFrame().getProgressbarimg().setVisible(false);
					Main.getFrame().getSpeedtext().setVisible(false);
					Main.getFrame().enableAll();
				}
			}
		});

		this.stopbutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/closebutton2.png");

				try {
					BufferedImage img = ImageIO.read(res);
					LauncherFrame.this.stopbutton.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void mouseExited(MouseEvent evt) {
				InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/closebutton1.png");

				try {
					BufferedImage img = ImageIO.read(res);
					LauncherFrame.this.stopbutton.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/progressbar.png");
		img = ImageIO.read(res);
		this.progressbarimg = new JLabel(new ImageIcon(img));
		this.progressbarimg.setBounds(311, 620, 584, 22);
		this.progressbarimg.setVisible(false);
		this.lpane.add(this.progressbarimg, new Integer(1), 0);

		this.progressbar = new JProgressBar(0, 100);
		this.progressbar.setStringPainted(false);
		this.progressbar.setBounds(313, 622, 580, 18);
		this.progressbar.setOpaque(false);
		this.progressbar.setBorder(null);
		this.progressbar.setForeground(Color.WHITE);
		this.progressbar.setBorderPainted(false);

		this.progressbar.setVisible(false);
		this.progressbar.setBorder(BorderFactory.createEmptyBorder());
		this.progressbar.setCursor(Cursor.getPredefinedCursor(3));
		this.lpane.add(this.progressbar, new Integer(1), 0);

		this.progresstext = new JTextPane();
		this.progresstext.setText("T�l�chargement");
		this.progresstext.setBounds(313, 590, 580, 620);
		this.progresstext.setVisible(false);
		this.progresstext.setOpaque(false);
		this.progresstext.setFocusable(false);
		Font font = new Font("Arial", 1, 17);
		this.progresstext.setFont(font);
		this.progresstext.setForeground(Color.WHITE);
		this.lpane.add(this.progresstext, new Integer(1), 0);

		this.speedtext = new JTextPane();
		this.speedtext.setText("");
		this.speedtext.setBounds(152, 621, 158, 22);
		this.speedtext.setVisible(false);
		this.speedtext.setOpaque(false);
		this.speedtext.setFocusable(false);
		this.speedtext.setFont(font);
		this.speedtext.setForeground(Color.WHITE);
		this.lpane.add(this.speedtext, new Integer(1), 0);

		StyledDocument doc = this.progresstext.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, 1);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/websitebutton.png");
		img = ImageIO.read(res);
		this.websitebutton = new JButton(new ImageIcon(img));
		this.websitebutton.setBounds(424, 511, 60, 60); //def = 375 511
		this.websitebutton.setContentAreaFilled(false);
		this.websitebutton.setBorderPainted(false);
		this.websitebutton.setFocusPainted(false);

		this.lpane.add(this.websitebutton, new Integer(1), 0);
		this.websitebutton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
				if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {

					try {
						desktop.browse(new URI("https://broken-magic.mtxserv.com/"));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		this.websitebutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/websitebutton2.png");

				try {
					BufferedImage img = ImageIO.read(res);
					LauncherFrame.this.websitebutton.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void mouseExited(MouseEvent evt) {
				InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/websitebutton.png");

				try {
					BufferedImage img = ImageIO.read(res);
					LauncherFrame.this.websitebutton.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public void initconfig() {
		try {
			InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/configmenu.png");
			BufferedImage img = ImageIO.read(res);
			this.background_config = new JLabel(new ImageIcon(img));
			this.background_config.setBounds(280, 60, 720, 481);
			this.background_config.setVisible(false);
			this.lpane.add(this.background_config, new Integer(2), 0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.closebutton = new JButton();
		this.closebutton.setBounds(961, 71, 29, 29);
		this.closebutton.setContentAreaFilled(false);
		this.closebutton.setBorderPainted(false);
		this.closebutton.setFocusPainted(false);
		this.closebutton.setVisible(false);
		this.lpane.add(this.closebutton, new Integer(2), 0);
		this.closebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LauncherFrame.this.closeConfig();
			}
		});
		this.closebutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/closebutton2.png");

				try {
					BufferedImage img = ImageIO.read(res);
					LauncherFrame.this.closebutton.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void mouseExited(MouseEvent evt) {
				LauncherFrame.this.closebutton.setIcon(null);
			}
		});

		this.filechooser = new JButton();
		this.filechooser.setBounds(736, 239, 223, 58);
		this.filechooser.setVisible(false);
		this.filechooser.setContentAreaFilled(false);
		this.filechooser.setBorderPainted(false);
		this.filechooser.setFocusPainted(false);
		this.lpane.add(this.filechooser, new Integer(2), 0);
		this.filechooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LauncherFrame.this.path_chooser = new JFileChooser(
						FileSystemView.getFileSystemView().getHomeDirectory());
				LauncherFrame.this.path_chooser.setCurrentDirectory(new File(Config.DIR));
				LauncherFrame.this.path_chooser.setFileSelectionMode(1);
				LauncherFrame.this.path_chooser.setAcceptAllFileFilterUsed(false);
				LauncherFrame.this.path_chooser.setDialogTitle("Choisissez un dossier");
				int returnValue = LauncherFrame.this.path_chooser.showOpenDialog(null);
				if (returnValue == 0) {
					LauncherFrame.this.pathfield
							.setText(LauncherFrame.this.path_chooser.getSelectedFile().getAbsolutePath());
					Config.DIR = LauncherFrame.this.path_chooser.getSelectedFile().getAbsolutePath();
				}
			}
		});
		this.filechooser.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/filechooserbutton.png");

				try {
					BufferedImage img = ImageIO.read(res);
					LauncherFrame.this.filechooser.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void mouseExited(MouseEvent evt) {
				LauncherFrame.this.filechooser.setIcon(null);
			}
		});
		Font font1 = new Font("arial", 0, 20);

		this.pathfield = new JTextField(Config.DIR, 10);
		this.pathfield.setBounds(331, 239, 388, 58);
		this.pathfield.setBorder(BorderFactory.createEmptyBorder());
		this.pathfield.setOpaque(false);
		this.pathfield.setFont(font1);
		this.pathfield.setForeground(Color.WHITE);
		this.pathfield.setVisible(false);
		this.lpane.add(this.pathfield, new Integer(2), 0);

		this.sliderRAM = new JSlider(0, this.MinRAM, this.MaxRAM, this.CurrentRAM);
		this.sliderRAM.setVisible(false);

		this.sliderRAM.setBounds(334, 322, 424, 65);
		this.sliderRAM.setOpaque(false);
		this.lpane.add(this.sliderRAM, new Integer(2), 0);
		this.sliderRAM.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					int ram = source.getValue();
					Main.getFrame().getTextRAM().setText(String.valueOf(String.valueOf(ram)) + " MB");
					Config.ram = ram;
					Config.writeRam(ram);
				}
			}
		});

		this.textRAM = new JTextArea(String.valueOf(this.CurrentRAM) + " MB");
		this.textRAM.setVisible(false);
		this.textRAM.setFocusable(false);
		this.textRAM.setBounds(804, 342, 158, 33);
		this.textRAM.setFont(font1);
		this.textRAM.setForeground(Color.WHITE);
		this.textRAM.setOpaque(false);
		this.lpane.add(this.textRAM, new Integer(2), 0);

		this.repairbutton = new JButton();
		this.repairbutton.setBounds(322, 414, 352, 57);
		this.repairbutton.setVisible(false);
		this.repairbutton.setContentAreaFilled(false);
		this.repairbutton.setBorderPainted(false);
		this.repairbutton.setFocusPainted(false);
		this.lpane.add(this.repairbutton, new Integer(2), 0);
		this.repairbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(Main.getFrame().configbutton,
						"Voulez vous essayer de réparer le launcher ? \n /!\\ Cela supprimera tout vos dossiers du launcher",
						"Réparer le launcher ?", 2) == 0) {
					Main.getFrame().enableAll();
					if ((new File(Config.DIR)).isDirectory()) {
						PackInfos.deleteFolder(new File(Config.DIR));
						(new File(Config.DIR)).mkdir();
					}
					Config.writeModPackVersion("0.1");
					Config.modpack_version = "0.1";
					JOptionPane.showMessageDialog(Main.getFrame().configbutton, "Launcher réparé");

					return;
				}
			}
		});

		this.repairbutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/repairbutton.png");

				try {
					BufferedImage img = ImageIO.read(res);
					LauncherFrame.this.repairbutton.setIcon(new ImageIcon(img));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void mouseExited(MouseEvent evt) {
				LauncherFrame.this.repairbutton.setIcon(null);
			}
		});
	}

	public void openConfig() {
		this.launchbutton.setEnabled(false);
		this.closebutton.setVisible(true);
		this.background_config.setVisible(true);
		this.filechooser.setVisible(true);
		this.pathfield.setText(Config.DIR);
		this.pathfield.setVisible(true);
		this.repairbutton.setVisible(true);
		this.sliderRAM.setVisible(true);
		this.textRAM.setVisible(true);
	}

	public void closeConfig() {
		this.launchbutton.setEnabled(true);
		this.closebutton.setVisible(false);
		this.background_config.setVisible(false);
		this.filechooser.setVisible(false);
		this.pathfield.setVisible(false);
		this.repairbutton.setVisible(false);
		this.sliderRAM.setVisible(false);
		this.textRAM.setVisible(false);

		if (!(new File(this.pathfield.getText())).isDirectory()) {
			JOptionPane.showMessageDialog(this.filechooser,
					"Le dossier \"" + this.pathfield.getText() + "\" n'existe pas.", "Erreur - 3", 0);

			if (!(new File(Config.DIR)).isDirectory()) {
				Config.DIR = Main.KM_DIR.getAbsolutePath();
			}
		} else {

			Config.DIR = this.pathfield.getText();
			Config.writeModPackVersion("0.1");
		}
		Config.writePath(Config.DIR);
	}

	public void enableAll() {
		this.launchbutton.setEnabled(true);
		InputStream res = Main.class.getResourceAsStream("/fr/uiytt/kaitenlauncher/img/launchbutton1.png");

		try {
			BufferedImage img = ImageIO.read(res);
			this.launchbutton.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.configbutton.setEnabled(true);
		this.textfieldusername.setEnabled(true);
		this.textfieldpassword.setEnabled(true);
		this.showpassword.setEnabled(true);
	}

	public JPasswordField getTextfieldpassword() {
		return textfieldpassword;
	}

	public void setTextfieldpassword(JPasswordField textfieldpassword) {
		this.textfieldpassword = textfieldpassword;
	}

	public JButton getPausebutton() {
		return pausebutton;
	}

	public void setPausebutton(JButton pausebutton) {
		this.pausebutton = pausebutton;
	}

	public JButton getResumbutton() {
		return resumbutton;
	}

	public void setResumbutton(JButton resumbutton) {
		this.resumbutton = resumbutton;
	}

	public JButton getStopbutton() {
		return stopbutton;
	}

	public void setStopbutton(JButton stopbutton) {
		this.stopbutton = stopbutton;
	}

	public JLabel getProgressbarimg() {
		return progressbarimg;
	}

	public void setProgressbarimg(JLabel progressbarimg) {
		this.progressbarimg = progressbarimg;
	}

	public JTextPane getProgresstext() {
		return progresstext;
	}

	public void setProgresstext(JTextPane progresstext) {
		this.progresstext = progresstext;
	}

	public JTextPane getSpeedtext() {
		return speedtext;
	}

	public void setSpeedtext(JTextPane speedtext) {
		this.speedtext = speedtext;
	}

	public JProgressBar getProgressbar() {
		return progressbar;
	}

	public void setProgressbar(JProgressBar progressbar) {
		this.progressbar = progressbar;
	}

	public JTextArea getTextRAM() {
		return textRAM;
	}

	public void setTextRAM(JTextArea textRAM) {
		this.textRAM = textRAM;
	}

	public JButton getLaunchbutton() {
		return launchbutton;
	}

	public void setLaunchbutton(JButton launchbutton) {
		this.launchbutton = launchbutton;
	}

	public JButton getConfigbutton() {
		return configbutton;
	}

	public void setConfigbutton(JButton configbutton) {
		this.configbutton = configbutton;
	}

	public JTextField getTextfieldusername() {
		return textfieldusername;
	}

	public void setTextfieldusername(JTextField textfieldusername) {
		this.textfieldusername = textfieldusername;
	}

	public JButton getShowpassword() {
		return showpassword;
	}

	public void setShowpassword(JButton showpassword) {
		this.showpassword = showpassword;
	}

	public JFrame getGuiFrame() {
		return guiFrame;
	}

	public void setGuiFrame(JFrame guiFrame) {
		this.guiFrame = guiFrame;
	}

	public static boolean isUnicorn() {
		return unicorn;
	}

	public static void setUnicorn(boolean unicorn) {
		LauncherFrame.unicorn = unicorn;
	}

}
