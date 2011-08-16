package barrywey.igosyncdocs2011;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import javax.swing.UIManager;

import barrywey.igosyncdocs2011.bean.SystemRuntime;
import barrywey.igosyncdocs2011.gui.LoginFrame;
import barrywey.igosyncdocs2011.gui.util.FaceUtils;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;

/**
 * 
 * @author Barry Wey
 * @version 1.0 Nov 7, 2010
 * @since JDK1.6
 */
public class IGoSyncDocs {

	public static void main(String[] args) {
		
		try {
			
			loadDefaultSettings();
			
			UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
			SyntheticaStandardLookAndFeel.setFont("SansSerif",12);
			
			LoginFrame frLogin = new LoginFrame();
			frLogin.setLocationRelativeTo(null);
			frLogin.setVisible(true);			
		} catch (Exception ex) {
			FaceUtils.showErrorMessage(null, ex.getMessage() == null ? " " : ex.getMessage());
		}
	}

	private static void loadDefaultSettings() throws IOException {
		File configFile = new File(SystemRuntime.Settings.Config_File_Path);
		if(!configFile.exists()) {
			//copy config file to user.home\iGoSyncDocs-2011 directory
			File appHome = new File(SystemRuntime.Settings.App_Data_Home);
			appHome.mkdir();
			configFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(configFile);
			InputStream is = IGoSyncDocs.class.getResourceAsStream("/barrywey/igosyncdocs2011/resource/config/settings.pro");
			byte[] buffer = new byte[1023*5]; //5k
			int size = is.read(buffer);
			while(size != -1) {
				fos.write(buffer,0,size);
				size = is.read(buffer);
			}
			fos.flush();
			fos.close();
			is.close();
		}//end of if
		
		//load default settings
		Properties pro = new Properties();
		pro.load(new FileInputStream(configFile));
		String defaultLanguage = pro.getProperty("default.language");
		if(defaultLanguage != null && !defaultLanguage.trim().equals("")) {
			if(defaultLanguage.equals("zh_CN"))
				Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
			else if(defaultLanguage.equals("zh_TW"))
				Locale.setDefault(Locale.TRADITIONAL_CHINESE);
			else if(defaultLanguage.equals("en_US")) {
				Locale.setDefault(Locale.UK);
			}else if(defaultLanguage.equals("ja_JP")) {
				Locale.setDefault(Locale.JAPAN);
			}
		}
		else
			Locale.setDefault(Locale.ENGLISH);
		
		String username = pro.getProperty("default.user.name");
		if(username != null && !username.trim().equals("")) 
			SystemRuntime.Settings.UserName = username;
	}//end of method
}
