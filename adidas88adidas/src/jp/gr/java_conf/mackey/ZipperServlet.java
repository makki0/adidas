package jp.gr.java_conf.mackey;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

import com.google.appengine.api.files.AppEngineFile;

/** Hello World!表示サーブレット **/
public class ZipperServlet extends HttpServlet {
  public void doGet (HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
	  RequestDispatcher rd = req.getRequestDispatcher("input.jsp");
	  rd.forward(req, res);

  }
  public void doPost (HttpServletRequest req, HttpServletResponse res)
		    throws ServletException, IOException {
			 // RequestDispatcher rd = req.getRequestDispatcher("input.jsp");
			  //rd.forward(req, arg1);

		    OutputStream outstream = null;

	        // データ出力
	        byte[] buff = new byte[1024];

		    // アクセスする URL を文字列に設定する。
		    String STR_URL = req.getParameter("inurl");

		    // URLを作成する。
		    URL url = new java.net.URL(STR_URL);

		    String f = url.getFile();

		    String[] parts = f.split("/");

		   String filename;

			  String t = parts[parts.length-1];

			  int idx = t.indexOf("?");
			  if(-1 < idx ){
				  filename = t.substring(0, t.indexOf("?"));
			  }
			  else{
			    filename = t;
			  }

		    // 接続を取得する (接続は new して作るのではなく、openConnection メソッドで取得する)。
		    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		    conn.setRequestMethod("GET"); // ←ここは任意。なくても良い

		    // リーダーを読んでHTTPレスポンスを取得する。
		    // ただし，リクエストした先のURLが画像などの場合は、InputStreamでバイト列として扱う。
		    // 文字エンコーディングは「JISAutoDetect」で自動検出させる。
		    InputStream isr = conn.getInputStream();

	        // コンテントタイプ設定
	        res.setContentType("application/octet-stream");
	        // ヘッダー設定
	        res.setHeader("Content-Disposition","filename=\"" + filename + ".zip\"");

		    outstream = res.getOutputStream();

		    ZipOutputStream zos = new ZipOutputStream(outstream);

		    ZipEntry ze = new ZipEntry(filename);
			zos.putNextEntry(ze);
			for (;;) {
				int len = isr.read(buff);
				if (len < 0) break;
				zos.write(buff, 0, len);
			}
			isr.close();


		    // ストリームならびに接続をクローズ
		    zos.closeEntry();
		    zos.close();
		    conn.disconnect();


		  }
}