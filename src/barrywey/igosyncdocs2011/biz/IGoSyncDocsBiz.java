/**
 * @(#)IGoSyncDocsBiz.java Oct 14, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.biz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.gdata.client.GoogleService.AccountDeletedException;
import com.google.gdata.client.GoogleService.AccountDisabledException;
import com.google.gdata.client.GoogleService.CaptchaRequiredException;
import com.google.gdata.client.GoogleService.InvalidCredentialsException;
import com.google.gdata.client.GoogleService.NotVerifiedException;
import com.google.gdata.client.GoogleService.ServiceUnavailableException;
import com.google.gdata.client.GoogleService.SessionExpiredException;
import com.google.gdata.client.GoogleService.TermsNotAgreedException;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.acl.AclRole;
import com.google.gdata.data.acl.AclScope;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import barrywey.igosyncdocs2011.IGoSyncDocs;
import barrywey.igosyncdocs2011.bean.MyDocumentListEntry;
import barrywey.igosyncdocs2011.bean.SystemRuntime;
import barrywey.igosyncdocs2011.net.IGoSyncDocsDao;
import barrywey.igosyncdocs2011.net.impl.IGoSyncDocsDaoImpl;
import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Oct 14, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsBiz {

	public static String captcha_url = null;
	private static IGoSyncDocsDao dao = new IGoSyncDocsDaoImpl();
	
	/**
	 * Login with email & password
	 * @param email
	 * @param password
	 * @return 'success' if only login is successfully , or the message points out why login has failed.	
	 */
	public static String login(String email,String password) {
		String result = "success";
		if(dao != null) {
			try {
				dao.login(email, password);
			} catch (CaptchaRequiredException e) {
				return LanguageResource.getStringValue("app.login.validate_login_captcha");
			} catch (InvalidCredentialsException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_InvalidCredentials");
			} catch (AccountDisabledException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_AccountDisabled");
			} catch (AccountDeletedException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_AccountDeleted");
			} catch (NotVerifiedException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_NotVerified");
			} catch (ServiceUnavailableException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_ServiceUnavailable");
			} catch (SessionExpiredException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_SessionExpired");
			} catch (TermsNotAgreedException e) {
				result = LanguageResource.getStringValue("app.login.validate_login_TermsNotAgreed");
			} catch (AuthenticationException e) {
				String message = LanguageResource.getStringValue("app.login.validate_login_AuthenticationException");
				result = message.replace("{1}", e.getMessage());
			}
		}//end of if
		return result;
	}//end of login
	
	/**
	 * Cache all items into memory in order to deal with it
	 * @throws IGoSyncDocsException
	 */
	public static void cacheAllItem() throws IGoSyncDocsException {
	
		try {		
			DocumentListFeed allFeeds = dao.getAllFeeds();
			SystemRuntime.CachedEntries.clear();
			for (int i = 0; i < allFeeds.getEntries().size(); i++) {
				MyDocumentListEntry entry = new MyDocumentListEntry();
				entry.setEntry(allFeeds.getEntries().get(i));
				entry.setAclFeeds(dao.getAclFeed(entry.getEntry()));
				SystemRuntime.CachedEntries.add(entry);
			}
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}
	}//end of method
	
	public static List<MyDocumentListEntry> getAllItems() {
		List<MyDocumentListEntry> list = new ArrayList<MyDocumentListEntry>();
		for(MyDocumentListEntry entry : SystemRuntime.CachedEntries) {
			if(!entry.getEntry().isHidden() && !entry.getEntry().isTrashed())
				list.add(entry);
		}
		return list;
	}
	
	public static List<MyDocumentListEntry> getAllDocuments() {
		List<MyDocumentListEntry> list = new ArrayList<MyDocumentListEntry>();
		for (MyDocumentListEntry entry : SystemRuntime.CachedEntries) {
			if (entry.getEntry().getType().equals("document")
					&& !entry.getEntry().isHidden()
					&& !entry.getEntry().isTrashed())
				list.add(entry);
		}
		return list;
	}//end of method
	
	public static List<MyDocumentListEntry> getAllPresentations() {
		List<MyDocumentListEntry> list = new ArrayList<MyDocumentListEntry>();
		for (MyDocumentListEntry entry : SystemRuntime.CachedEntries) {
			if (entry.getEntry().getType().equals("presentation")
					&& !entry.getEntry().isHidden()
					&& !entry.getEntry().isTrashed())
				list.add(entry);
		}
		return list;
	}//end of method
	
	public static List<MyDocumentListEntry> getAllSpreadsheets() {
		List<MyDocumentListEntry> list = new ArrayList<MyDocumentListEntry>();
		for (MyDocumentListEntry entry : SystemRuntime.CachedEntries) {
			if (entry.getEntry().getType().equals("spreadsheet")
					&& !entry.isHidden() && !entry.getEntry().isTrashed())
				list.add(entry);
		}
		return list;
	}//end of method
	
	public static List<MyDocumentListEntry> getAllOthers() {
		List<MyDocumentListEntry> list = new ArrayList<MyDocumentListEntry>();
		for (MyDocumentListEntry entry : SystemRuntime.CachedEntries) {
			if (!entry.getEntry().getType().equals("document")
					&& !entry.getEntry().getType().equals("presentation")
					&& !entry.getEntry().getType().equals("spreadsheet")
					&& !entry.getEntry().isHidden()
					&& entry.getEntry().isTrashed())
				list.add(entry);
		}
		return list;
	}//end of method
	
	public static List<MyDocumentListEntry> getHiddenObjects() {
		List<MyDocumentListEntry> list = new ArrayList<MyDocumentListEntry>();
		for (MyDocumentListEntry entry : SystemRuntime.CachedEntries) {
			if (entry.getEntry().isHidden() && !entry.getEntry().isTrashed())
				list.add(entry);
		}
		return list;
	}
	
	public static List<MyDocumentListEntry> getStaredObjects() {
		List<MyDocumentListEntry> list = new ArrayList<MyDocumentListEntry>();
		for (MyDocumentListEntry entry : SystemRuntime.CachedEntries) {
			if (entry.getEntry().isStarred() && !entry.getEntry().isHidden())
				list.add(entry);
		}
		return list;
	}
	
	public static List<MyDocumentListEntry> getTrashedObjects() {
		List<MyDocumentListEntry> list = new ArrayList<MyDocumentListEntry>();
		for (MyDocumentListEntry entry : SystemRuntime.CachedEntries) {
			if (entry.getEntry().isTrashed() && !entry.getEntry().isHidden())
				list.add(entry);
		}	
		return list;
	}
	
	public static List<MyDocumentListEntry> getSharedWithMeObjects() {
		List<MyDocumentListEntry> list = new ArrayList<MyDocumentListEntry>();
		List<MyDocumentListEntry> allEntries = SystemRuntime.CachedEntries;
		for(int i=0;i<allEntries.size();i++) {
			AclFeed feed = allEntries.get(i).getAclFeeds();
			for (AclEntry entry : feed.getEntries()) {
				if (entry.getRole().getValue().equalsIgnoreCase("owner")
						&& !entry.getScope().getValue().equals(
								SystemRuntime.Settings.UserName)) {
					list.add(allEntries.get(i));
				}
			}// end of for
		}//end of for
		return list;		
	}

	public static String getOwner(MyDocumentListEntry entry) {
		String owner = "";
		List<AclEntry> enties = entry.getAclFeeds().getEntries();
		for (int i = 0; i < enties.size(); i++) {
			AclEntry acl = enties.get(i);
			if(acl.getRole().getValue().equals("owner")) {
				owner = acl.getScope().getValue();
			}
		}// end of for
		return owner;
	}// end of method
	
	public static void trashItem(MyDocumentListEntry entry) throws IGoSyncDocsException {
		try {
			dao.trash(entry.getEntry());
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}
	}
	
	public static void unTrashItem(MyDocumentListEntry entry) throws IGoSyncDocsException {
		try {
			dao.untrash(entry.getEntry());
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}
	}	
	
	public static void hideItem(MyDocumentListEntry entry) throws IGoSyncDocsException{
		try {
			dao.hide(entry.getEntry());
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}
	}
	
	public static void unHideItem (MyDocumentListEntry entry)  throws IGoSyncDocsException{
		try {
			entry.getEntry().setHidden(false);
			entry.getEntry().update();
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}		
	}	
	
	public static void starItem(MyDocumentListEntry entry) throws IGoSyncDocsException {
		try {
			dao.star(entry.getEntry());
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}		
	}
	
	public static void unStarItem(MyDocumentListEntry entry) throws IGoSyncDocsException {
		try {
			entry.getEntry().setStarred(false);
			entry.getEntry().update();
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}		
	}
	
	public static void shareItem(String email,boolean canWrite,MyDocumentListEntry entry)  throws IGoSyncDocsException{
		try {
			AclRole role ;
			if(canWrite)
				role = new AclRole("writer");
			else
				role = new AclRole("reader");
			AclScope scope = new AclScope(AclScope.Type.USER,email.trim());
			dao.addAclEntry(role, scope, entry.getEntry());
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}
	}
	
	public static void upload(File file)  throws IGoSyncDocsException {
		try {
			dao.upload(file.getName().substring(0,file.getName().lastIndexOf('.')), file.getPath());
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}		
	}
	
	public static void delete(MyDocumentListEntry entry) throws IGoSyncDocsException {
		try {
			dao.delEntry(entry.getEntry());
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}		
	}
	
	public static void downloadDocument(MyDocumentListEntry entry,String filePath,
			String format) throws IGoSyncDocsException {
		try {
			if(format == null || format.trim().equals(""))
				format = "doc";
			dao.downloadDocument(entry.getEntry(), filePath+"."+format, format);
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}		
	}
	
	public static void downloadSpreadsheet(MyDocumentListEntry entry, String filePath,
			String format) throws IGoSyncDocsException {
		try {
			if(format == null || format.trim().equals(""))
				format = "xls";
			dao.downloadSpreadsheet(entry.getEntry(), filePath+"."+format, format);
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}		
	}
	
	public static void downloadPresentation(MyDocumentListEntry entry, String filePath,
			String format) throws IGoSyncDocsException {
		try {
			if(format == null || format.trim().equals(""))
				format = "ppt";
			dao.downloadPresentation(entry.getEntry(), filePath+"."+format, format);
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}
	}
	
	public static void download(MyDocumentListEntry entry, String filePath) throws IGoSyncDocsException {
		try {
			dao.download(((MediaContent) entry.getEntry().getContent())
					.getUri(), filePath
					+ entry.getEntry().getTitle().getPlainText());
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}		
	}
	
	public static void createNewDocument(String title) throws IGoSyncDocsException {
		try {
			dao.createNew(title, "document");
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}
	}
	
	public static void createNewPresentation(String title) throws IGoSyncDocsException {
		try {
			dao.createNew(title, "presentation");
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}
	}
	
	public static void createNewSpreadsheet(String title) throws IGoSyncDocsException {
		try {
			dao.createNew(title, "spreadsheet");
		} catch (MalformedURLException e) {
			String message = LanguageResource.getStringValue("main.data.exception_MalformedURL");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (IOException e) {
			String message = LanguageResource.getStringValue("main.data.exception_IO");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (ServiceException e) {
			String message = LanguageResource.getStringValue("main.data.exception_Service");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage()), e);
		} catch (Exception e) {
			String message = LanguageResource.getStringValue("main.data.exception_Other");
			throw new IGoSyncDocsException(message.replace("{1}", e.getMessage() == null ? " " : e.getMessage()), e);
		}
	}
	
	public static List<MyDocumentListEntry> search(String title) {
		List<MyDocumentListEntry> list = new ArrayList<MyDocumentListEntry>();
		List<MyDocumentListEntry> allItems = getAllItems(); //only search in cached list
		for(MyDocumentListEntry entry : allItems) {
			if(entry.getEntry().getTitle().getPlainText().contains(title)) {				
				list.add(entry);
			}
		}
		return list;
	}
	
	public static void changeLanguage(String language) throws IOException {
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
		pro.setProperty("default.language", language);
		pro.store(new FileOutputStream(configFile), "Language changed");
	}
}
