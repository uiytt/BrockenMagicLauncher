package fr.uiytt.brokenmagiclauncher.update;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.SwingUtilities;

import fr.uiytt.brokenmagiclauncher.Main;

public class DownloadFile {
	private URL url;
	private File destination;
	public DownloadFile(URL url,File destination) {
		this.url = url;
		this.destination = destination;
	}
	public void download() throws IOException {
		HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
        long completeFileSize = httpConnection.getContentLength();

        java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
        java.io.FileOutputStream fos = new java.io.FileOutputStream(destination);
        java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
        byte[] data = new byte[1024];
        long downloadedFileSize = 0;
        int x = 0;
        while ((x = in.read(data, 0, 1024)) >= 0) {
        	while(PausableSwingWorker.getWorker().isPaused()) {
				if(PausableSwingWorker.getWorker().isDone()) {
					bout.close();
			        in.close();
			        fos.close();
					return;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
        	if(PausableSwingWorker.getWorker().isDone()) {
				bout.close();
		        in.close();
		        fos.close();
				return;
			}
            downloadedFileSize += x;

            // calculate progress
            final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100d);

            // update progress bar
            SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
	                   Main.getFrame().getProgressbar().setValue(currentProgress);
					
				}
			});
            		


            bout.write(data, 0, x);
        }
        bout.close();
        in.close();
        fos.close();
		return;

	}
	
}
