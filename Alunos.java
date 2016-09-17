import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Alunos extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException    {
		res.setContentType("text/html");
      	PrintWriter out = res.getWriter();
    	String url = "jdbc:mysql:alunos";
	   	Connection con;
	   	Statement stmt;
		String query = "select * from alunos";
	   	try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
 		} catch(Exception e) {
			out.println("<P>ClassNotFoundException: ");
			out.println("<P>"+e.getMessage());
	   	}	
	   	try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");	
			stmt = con.createStatement();	
			String nome = req.getParameter("nome");
			String cod = req.getParameter("codigo");						
			stmt.executeUpdate("insert into alunos " +
				"values("+cod+", '"+nome+"')");
			ResultSet rs = stmt.executeQuery(query);
			out.println("<P>Códigos e Alunos Cadastrados");
			while (rs.next()) {
				String s = rs.getString("nome");
				int n = rs.getInt("codigo");
					out.println("<P>"+s + "   " + n);
			}
			stmt.close();
			con.close();
		} catch(SQLException ex) {
				out.println("<P>SQLException: " + ex.getMessage());
		}
	    out.println("<html>");
	    out.println("<title>Obrigado!</title>");
       	out.println("Obrigado por participar");
	    out.println("</html>");
        out.close();
    }
}