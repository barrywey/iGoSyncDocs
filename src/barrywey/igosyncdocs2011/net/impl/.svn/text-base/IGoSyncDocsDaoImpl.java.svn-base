/**
 * @(#)IGoSyncDocsDaoImpl.java Oct 14, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.net.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import barrywey.igosyncdocs2011.net.IGoSyncDocsDao;


import com.google.gdata.client.DocumentQuery;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.GoogleAuthTokenFactory.UserToken;
import com.google.gdata.client.GoogleService.AccountDeletedException;
import com.google.gdata.client.GoogleService.AccountDisabledException;
import com.google.gdata.client.GoogleService.CaptchaRequiredException;
import com.google.gdata.client.GoogleService.InvalidCredentialsException;
import com.google.gdata.client.GoogleService.NotVerifiedException;
import com.google.gdata.client.GoogleService.ServiceUnavailableException;
import com.google.gdata.client.GoogleService.SessionExpiredException;
import com.google.gdata.client.GoogleService.TermsNotAgreedException;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.acl.AclRole;
import com.google.gdata.data.acl.AclScope;
import com.google.gdata.data.docs.DocumentEntry;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.docs.FolderEntry;
import com.google.gdata.data.docs.PresentationEntry;
import com.google.gdata.data.docs.RevisionFeed;
import com.google.gdata.data.docs.SpreadsheetEntry;
import com.google.gdata.data.media.MediaSource;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 14, 2010
 * @since JDK1.6
 */
public class IGoSyncDocsDaoImpl implements IGoSyncDocsDao{

	private DocsService service;
	private GoogleService spreadsheetsService;
	private static final String appName = "Barry Wey-iGoSyncDocs-2011";
	private UserToken token;

	public IGoSyncDocsDaoImpl() {
		service = new DocsService(appName);
		spreadsheetsService = new GoogleService("wise", appName);
	}

	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#login(java.lang.String, java.lang.String)
	 */
	public void login(String email, String password)
			throws CaptchaRequiredException, InvalidCredentialsException,
			AccountDisabledException, AccountDeletedException,
			NotVerifiedException, ServiceUnavailableException,
			SessionExpiredException, TermsNotAgreedException,
			AuthenticationException {
		service.setUserCredentials(email, password);
		spreadsheetsService.setUserCredentials(email, password);
		token = (UserToken) service.getAuthTokenFactory().getAuthToken();
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#getAllFeeds()
	 */
	public DocumentListFeed getAllFeeds() throws MalformedURLException, IOException, ServiceException {
		service.setUserToken(token.getValue());
		return service.getFeed(new URL(URLManager.Get_All_Feeds), DocumentListFeed.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#getAllDocuments()
	 */
	public DocumentListFeed getAllDocuments() throws MalformedURLException, IOException, ServiceException {
		service.setUserToken(token.getValue());
		return service.getFeed(new URL(URLManager.Get_All_Documents), DocumentListFeed.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#getAllSpreadsheets()
	 */
	public DocumentListFeed getAllSpreadsheets () throws MalformedURLException, IOException, ServiceException {
		service.setUserToken(token.getValue());
		return service.getFeed(new URL(URLManager.Get_All_Spreadsheets), DocumentListFeed.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#getAllPresentations()
	 */
	public DocumentListFeed getAllPresentations() throws MalformedURLException, IOException, ServiceException {
		service.setUserToken(token.getValue());
		return service.getFeed(new URL(URLManager.Get_All_Presentations), DocumentListFeed.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#getAllTrashedObjects()
	 */
	public DocumentListFeed getAllTrashedObjects() throws MalformedURLException, IOException, ServiceException {
		service.setUserToken(token.getValue());
		return service.getFeed(new URL(URLManager.Get_All_TrashedObjects), DocumentListFeed.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#getAllFolders()
	 */
	public DocumentListFeed getAllFolders() throws MalformedURLException, IOException, ServiceException {
		service.setUserToken(token.getValue());
		return service.getFeed(new URL(URLManager.Get_All_Folders), DocumentListFeed.class);		
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#exactTitleQuery(java.lang.String)
	 */
	public DocumentListFeed exactTitleQuery(String title) throws MalformedURLException, IOException, ServiceException {
		DocumentQuery query = new DocumentQuery(new URL(URLManager.Exact_Title_Search));
		query.setTitleQuery(title);
		query.setMaxResults(20);
		service.setUserToken(token.getValue());
		return service.getFeed(query, DocumentListFeed.class);		
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#fullTextQuery(java.lang.String)
	 */
	public DocumentListFeed fullTextQuery(String text) throws MalformedURLException, IOException, ServiceException {
		DocumentQuery query = new DocumentQuery(new URL(URLManager.Exact_Title_Search));
		query.setFullTextQuery(text);
		service.setUserToken(token.getValue());
		return service.getFeed(query, DocumentListFeed.class);			
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#createNew(java.lang.String, java.lang.String)
	 */
	public DocumentListEntry createNew(String title, String type) throws MalformedURLException, IOException, ServiceException {
		DocumentListEntry entry = null;
		if(type.trim().equalsIgnoreCase("document")) {
			entry = new DocumentEntry();
		}else if(type.trim().equalsIgnoreCase("spreadsheet")) {
			entry = new SpreadsheetEntry();
		}else if(type.trim().equalsIgnoreCase("presentation")) {
			entry = new PresentationEntry();
		}
		entry.setTitle(new PlainTextConstruct(title));
		service.setUserToken(token.getValue());
		return service.insert(new URL(URLManager.Exact_Title_Search),entry);
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#upload(java.lang.String, java.lang.String)
	 */
	public DocumentListEntry upload(String title, String filePath)
			throws MalformedURLException, IOException, ServiceException {
		File file = new File(filePath);
		String mimeType = DocumentListEntry.MediaType.fromFileName(file.getName()).getMimeType();
		DocumentListEntry entry = new DocumentListEntry();
		entry.setFile(file,mimeType);
		entry.setTitle(new PlainTextConstruct(title));
		service.setUserToken(token.getValue());
		return service.insert(new URL(URLManager.Exact_Title_Search),entry);
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#createFolder(java.lang.String)
	 */
	public DocumentListEntry createFolder(String folderName)
			throws MalformedURLException, IOException, ServiceException {
		DocumentListEntry folder = new FolderEntry();
		folder.setTitle(new PlainTextConstruct(folderName));
		service.setUserToken(token.getValue());
		return service.insert(new URL(URLManager.Exact_Title_Search),folder);		
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#getAclFeed(com.google.gdata.data.docs.DocumentListEntry)
	 */
	public AclFeed getAclFeed(DocumentListEntry entry)
			throws MalformedURLException, IOException, ServiceException {
		return service.getFeed(new URL(entry.getAclFeedLink().getHref()),
				AclFeed.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#getRevisionFeed(com.google.gdata.data.docs.DocumentListEntry)
	 */
	public RevisionFeed getRevisionFeed(DocumentListEntry entry)
			throws MalformedURLException, IOException, ServiceException {
		return service.getFeed(new URL(entry.getSelfLink().getHref()+"/revisions"),
				RevisionFeed.class);
	}

	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#trash(com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void trash(DocumentListEntry entry) throws MalformedURLException,
			IOException, ServiceException {
		entry.delete();
	}
	
	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#untrash(com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void untrash(DocumentListEntry entry) throws MalformedURLException,
			IOException, ServiceException {
		entry.setTrashed(false);
		service.update(new URL(entry.getEditLink().getHref()), entry);
	}
	
	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#hide(com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void hide(DocumentListEntry entry) throws MalformedURLException,
			IOException, ServiceException {
		entry.setHidden(true);
		service.update(new URL(entry.getEditLink().getHref()), entry);
	}
	
	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#star(com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void star(DocumentListEntry entry) throws MalformedURLException,
			IOException, ServiceException {
		entry.setStarred(true);
		service.update(new URL(entry.getEditLink().getHref()), entry);
	}
	
	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#addAclEntry(com.google.gdata.data.acl.AclRole, com.google.gdata.data.acl.AclScope, com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void addAclEntry(AclRole role, AclScope scope,
			DocumentListEntry entry) throws MalformedURLException, IOException,
			ServiceException {
		AclEntry aclEntry = new AclEntry();
		aclEntry.setRole(role);
		aclEntry.setScope(scope);
		service.insert(new URL(entry.getAclFeedLink().getHref()),aclEntry);
	}

	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#delAclEntry(com.google.gdata.data.acl.AclEntry, com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void delAclEntry(AclEntry entry)
			throws MalformedURLException, IOException, ServiceException {
		entry.delete();
	}
	
	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#delEntry(com.google.gdata.data.docs.DocumentListEntry)
	 */
	@Override
	public void delEntry(DocumentListEntry entry) throws MalformedURLException,
			IOException, ServiceException {
		service.delete(new URL(entry.getEditLink().getHref()+"?delete=true"),entry.getEtag());
	}
	
	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#download(java.lang.String, java.lang.String)
	 */
	@Override
	public void download(String url, String filePath)
			throws MalformedURLException, IOException, ServiceException {
		MediaContent mc = new MediaContent();
		mc.setUri(url);
		MediaSource source = service.getMedia(mc);
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			is = source.getInputStream();
			fos = new FileOutputStream(filePath);
			int c;
			while( (c = is.read()) != -1) {
				fos.write(c);
			}
		}finally {
			if(is != null) 
				is.close();
			if(fos != null) {
				fos.flush();
				fos.close();
			}
		}//end of try-finally
	}

	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#downloadDocument(com.google.gdata.data.docs.DocumentListEntry, java.lang.String, java.lang.String)
	 */
	@Override
	public void downloadDocument(DocumentListEntry entry, String filePath,
			String format) throws MalformedURLException, IOException,
			ServiceException {
		String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
		String exportUrl = ((MediaContent)entry.getContent()).getUri() + "&exportFormat="+fileExtension;
		download(exportUrl, filePath);
	}

	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#downloadPresentation(com.google.gdata.data.docs.DocumentListEntry, java.lang.String, java.lang.String)
	 */
	@Override
	public void downloadPresentation(DocumentListEntry entry, String filePath,
			String format) throws MalformedURLException, IOException,
			ServiceException {
		String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
		String exportUrl = ((MediaContent)entry.getContent()).getUri() + "&exportFormat="+fileExtension;
		download(exportUrl, filePath);		
	}

	
	/* (non-Javadoc)
	 * @see barrywey.igosyncdocs2011.net.IGoSyncDocsDao#downloadSpreadsheet(com.google.gdata.data.docs.DocumentListEntry, java.lang.String, java.lang.String)
	 */
	@Override
	public void downloadSpreadsheet(DocumentListEntry entry, String filePath,
			String format) throws MalformedURLException, IOException,
			ServiceException {
		UserToken docsToken = (UserToken) service.getAuthTokenFactory().getAuthToken();
		UserToken spreadsheetsToken = (UserToken) spreadsheetsService.getAuthTokenFactory().getAuthToken();
		service.setUserToken(spreadsheetsToken.getValue());
		
		String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
		String exportUrl = ((MediaContent)entry.getContent()).getUri() + "&exportFormat="+fileExtension;
		// If exporting to .csv or .tsv, add the gid parameter to specify which sheet to export
		if (fileExtension.equals("csv") || fileExtension.equals("tsv")) {
			exportUrl += "&gid=0"; // gid=0 will download only the first sheet
		}	
		download(exportUrl, filePath);
		service.setUserToken(docsToken.getValue()); // Restore docs token for our DocList service
	}
}
