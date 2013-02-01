package jp.gr.java_conf.mackey;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/** Hello World!表示サーブレット **/
public class DownloadServlet extends HttpServlet {
	String uploadUrl;

  public void doGet (HttpServletRequest req, HttpServletResponse res)
		    throws ServletException, IOException {
	  try{

		  BlobstoreService blobstore = BlobstoreServiceFactory.getBlobstoreService();
		  BlobKey key = new BlobKey(req.getParameter("bkey"));

		  BlobInfo info = new BlobInfoFactory().loadBlobInfo(key);
		  res.setHeader("Content-disposition", "attachment; filename=" + info.getFilename());
		  blobstore.serve(key, res);

	  } finally {

	  }

		//res.sendRedirect("/");

  }
}