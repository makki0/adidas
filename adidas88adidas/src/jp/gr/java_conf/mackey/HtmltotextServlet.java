package jp.gr.java_conf.mackey;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

/** Hello World!表示サーブレット **/
public class HtmltotextServlet extends HttpServlet {
  public void doGet (HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
	  RequestDispatcher rd = req.getRequestDispatcher("input.jsp");
	  rd.forward(req, res);
	  
  }
  public void doPost (HttpServletRequest req, HttpServletResponse res)
		    throws ServletException, IOException {
			 // RequestDispatcher rd = req.getRequestDispatcher("input.jsp");
			  //rd.forward(req, arg1);
			  
		    PrintWriter out;

		    // アクセスする URL を文字列に設定する。
		    String STR_URL = req.getParameter("inurl");

		    // URLを作成する。
		    URL url = new java.net.URL(STR_URL);

		    // 接続を取得する (接続は new して作るのではなく、openConnection メソッドで取得する)。
		    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		    conn.setRequestMethod("GET"); // ←ここは任意。なくても良い

		    // リーダーを読んでHTTPレスポンスを取得する。
		    // ただし，リクエストした先のURLが画像などの場合は、InputStreamでバイト列として扱う。
		    // 文字エンコーディングは「JISAutoDetect」で自動検出させる。
		    InputStreamReader isr = new java.io.InputStreamReader(conn.getInputStream(), "JISAutoDetect");
		    BufferedReader br = new java.io.BufferedReader(isr);


		    res.setContentType("text/html; charset=Shift_JIS");
		    out = res.getWriter();

		    out.println("<html><body>");

		    // 受信したストリームを表示
		    String line = null;
		    while (null != (line = br.readLine())) {
		        out.println(line);
		    }
		    out.println("</body></html>");

		    // ストリームならびに接続をクローズ
		    br.close();
		    conn.disconnect();
		    
		    
		  }
}