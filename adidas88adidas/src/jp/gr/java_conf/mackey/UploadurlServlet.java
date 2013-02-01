package jp.gr.java_conf.mackey;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.files.AppEngineFile;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

/** Hello World!表示サーブレット **/
public class UploadurlServlet extends HttpServlet {
    private BlobstoreService blobstoreService
    	= BlobstoreServiceFactory.getBlobstoreService();
  public void doGet (HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {



  }
  public void doPost (HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
	  Map<String, BlobKey> blobs
      	= blobstoreService.getUploadedBlobs(req);
	 BlobKey blobKey = blobs.get("file1");
	 BlobInfo info = new BlobInfoFactory().loadBlobInfo(blobKey);

	 DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	 Entity blobent = new Entity("blobent");
	 blobent.setProperty("filename", info.getFilename());
	 blobent.setProperty("blobkey", blobKey.getKeyString());
	 ds.put(blobent);
	res.sendRedirect("/");
  }
}