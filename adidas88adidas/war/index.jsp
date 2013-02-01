<%@ page contentType="text/html;charset=Shift_JIS" language="java" %>
<!doctype html>
<!-- The DOCTYPE declaration above will set the     -->
<!-- browser's rendering engine into                -->
<!-- "Standards Mode". Replacing this declaration   -->
<!-- with a "Quirks Mode" doctype is not supported. -->
<%@page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@page import="com.google.appengine.api.blobstore.BlobKey" %>
<%@page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@page import="com.google.appengine.api.datastore.Query" %>
<%@page import="com.google.appengine.api.datastore.PreparedQuery" %>
<%@page import="com.google.appengine.api.datastore.Entity" %>
<%@page import="java.util.List" %>
<%
     BlobstoreService blobstoreService
          = BlobstoreServiceFactory.getBlobstoreService();
     String uploadUrl
          = blobstoreService.createUploadUrl("/uploadurl");

%>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=Shift_JIS">

    <title>Web Application Starter Project</title>

  </head>

  <body>

    <h1>HTMLtoTEXT</h1>

    <form action="/htmltotxt" method="post">
	    <table align="center">
	      <tr>
	        <td colspan="2" style="font-weight:bold;">Please enter URL:</td>
	      </tr>
	      <tr>
	      </tr>
	      <tr>
		      <td>
		        <input type="text" name="inurl">
		        <input type="submit" value="HTMLtoTexT">
		      </td>
	      </tr>
	    </table>
    </form>


    <h1>Zipper</h1>

    <form action="/zipper" method="post">
	    <table align="center">
	      <tr>
	        <td colspan="2" style="font-weight:bold;">Please enter URL:</td>
	      </tr>
	      <tr>
	      </tr>
	      <tr>
		      <td>
		        <input type="text" name="inurl">
		        <input type="submit" value="Zip">
		      </td>
	      </tr>
	    </table>
    </form>

  <h1>Uploader</h1>

    <table align="center">
      <tr>
      </tr>
      <tr>
	      <td>
			<form
			     action="<%= uploadUrl %>"
			     method="POST"
			     enctype="multipart/form-data">
			<input type="file" name="file1">
			<input type="submit" value="Upload">
			</form>

	      </td>
      </tr>
      <tr>
      <td>
      	<%
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
      		Query q = new Query("blobent");
      		q.addSort(Entity.KEY_RESERVED_PROPERTY, Query.SortDirection.ASCENDING);
      		PreparedQuery pq = ds.prepare(q);
      		List<Entity> blist= pq.asList(FetchOptions.Builder.withLimit(10));
      	%>
	      	<% for(Entity e : blist) {
	      			out.println("<a href=\"/downloader?bkey=" + e.getProperty("blobkey") + "\" >" + e.getProperty("filename") + "</a>");
	      			out.println("<a href=\"/deleter?id=" + e.getKey().getId() + "&bkey=\"" + e.getProperty("blobkey") +"\" >Delete</a>");
	      			out.println("<br>");
	      	};
	      	%>
		</td>
      </tr>
    </table>

  </body>
</html>
