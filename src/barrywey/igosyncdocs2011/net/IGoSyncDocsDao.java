/**
 * @(#)IGoSyncDocsDao.java Jul 16, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.net;

import java.io.IOException;
import java.net.MalformedURLException;

import com.google.gdata.client.GoogleService.AccountDeletedException;
import com.google.gdata.client.GoogleService.AccountDisabledException;
import com.google.gdata.client.GoogleService.CaptchaRequiredException;
import com.google.gdata.client.GoogleService.InvalidCredentialsException;
import com.google.gdata.client.GoogleService.NotVerifiedException;
import com.google.gdata.client.GoogleService.ServiceUnavailableException;
import com.google.gdata.client.GoogleService.SessionExpiredException;
import com.google.gdata.client.GoogleService.TermsNotAgreedException;
import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclFeed;
import com.google.gdata.data.acl.AclRole;
import com.google.gdata.data.acl.AclScope;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.docs.RevisionFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

/**
 * 
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Jul 16, 2010
 * @since JDK1.6
 */
public interface IGoSyncDocsDao {

	/**
	 * Login with google email and password
	 * @param email
	 * @param password
	 * @throws CaptchaRequiredException A CAPTCHA is required. (A response with this error code will also contain
	 * 			 an image URL and a CAPTCHA token.)
	 * @throws InvalidCredentialsException The login request used a username or password that is not recognized
	 * @throws AccountDisabledException The user account has been disabled.
	 * @throws AccountDeletedException The user account has been deleted.
	 * @throws NotVerifiedException The account email address has not been verified. The user will need to 
	 *      	access their Google account directly to resolve the issue before logging in using 
	 *      	a non-Google applicationl
	 * @throws ServiceUnavailableException The service is not available; try again later. 
	 * @throws SessionExpiredException
	 * @throws TermsNotAgreedException The user has not agreed to terms. The user will need to access their 
	 * 			Google account directly to resolve the issue before logging in using a non-Google application. 
	 * @throws AuthenticationException The error is unknown or unspecified; the request contained invalid input or was malformed.
	 */
	public void login(String email, String password)
			throws CaptchaRequiredException, InvalidCredentialsException,
			AccountDisabledException, AccountDeletedException,
			NotVerifiedException, ServiceUnavailableException,
			SessionExpiredException, TermsNotAgreedException,
			AuthenticationException;
	
	/**
	 * Get All available feeds from server for current user.
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListFeed getAllFeeds() throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Get all word processor documents.
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListFeed getAllDocuments() throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Get all spreadsheets.
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListFeed getAllSpreadsheets () throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Get all presentations.
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListFeed getAllPresentations() throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Get all trashed objects.
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListFeed getAllTrashedObjects() throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Get all folders.
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListFeed getAllFolders() throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Title search.title-exact queries are case-insenstive.
	 * @param title
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListFeed exactTitleQuery(String title) throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Search the contents of documents.This is different than searching just the title of every document.
	 * @param text
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListFeed fullTextQuery(String text) throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Create new Document,Spreadsheet,Presentation.
	 * @param title
	 * @param type could be: Document,Spreadsheet,Presentation
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListEntry createNew(String title,String type) throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Upload file. file type must matches Enum DocumentListEntry.MediaType.
	 * @param title
	 * @param filePath
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListEntry upload(String title,String filePath) throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Create a new folder.
	 * @param folderName
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public DocumentListEntry createFolder(String folderName) throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Get entry's acl feed object.
	 * @param entry
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public AclFeed getAclFeed(DocumentListEntry entry) throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Get entry's revisoin feed;
	 * 
	 * @param entry
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public RevisionFeed getRevisionFeed(DocumentListEntry entry) throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Trash the given item.
	 * 
	 * @param entry
	 */
	public void trash(DocumentListEntry entry) throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Untrash the given item
	 * 
	 * @param entry
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public void untrash(DocumentListEntry entry)throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Hide the given item
	 * 
	 * @param entry
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public void hide(DocumentListEntry entry) throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Star the given item
	 * 
	 * @param entry
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public void star(DocumentListEntry entry) throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Add AclEntry to a DocumentListEntry
	 * 
	 * @param role
	 * @param scope
	 * @param entry
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public void addAclEntry(AclRole role,AclScope scope,DocumentListEntry entry) throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Delete AclEntry from a DocumentListEntry
	 * 
	 * @param role
	 * @param scope
	 * @param entry
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public void delAclEntry(AclEntry aclEntry) throws MalformedURLException, IOException, ServiceException;
	
	/**
	 * Delete given Entry
	 * 
	 * @param entry
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public void delEntry(DocumentListEntry entry) throws MalformedURLException, IOException, ServiceException;
	
	public void download(String url,String filePath) throws MalformedURLException, IOException, ServiceException;
	
	public void downloadDocument(DocumentListEntry entry,String filePath,String format) throws MalformedURLException, IOException, ServiceException;
	
	public void downloadPresentation(DocumentListEntry entry,String filePath,String format) throws MalformedURLException, IOException, ServiceException;
	
	public void downloadSpreadsheet(DocumentListEntry entry,String filePath,String format) throws MalformedURLException, IOException, ServiceException;
	
}
