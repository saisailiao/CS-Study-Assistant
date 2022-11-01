package com.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class delete_good_question extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	String character;
	public delete_good_question() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("get here!" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		Connection con;
		Statement sql;
		String uid = request.getParameter("uid").trim();
		String qid = request.getParameter("qid").trim();
		String btype = request.getParameter("btype").trim();
		PrintWriter out = response.getWriter();
		
		//String word = new String(word1.getBytes("ISO-8859-1"),"utf-8");
		//byte[] subject1=str.getBytes("ISO-8859-1");//用tomcat的格式（iso-8859-1）方式去读。
		//String subject =new String(subject1);//采用utf-8去接string
		//System.out.println(str.getBytes());  
		String uri = "jdbc:mysql://localhost/cshelper";
		//System.out.println("get here111!"+subject1);
		//out.print(subject);
		//System.out.println(subject);
		try {
			con = DriverManager.getConnection(uri,"root","newpassword");
			//out.println("get here");
			String condition = "delete from favourite where  favourite.uid = '"+uid+"' and  favourite.qid = '"+qid+"'";
			System.out.println(condition);
			sql = con.prepareStatement(condition);
			int rSet = sql.executeUpdate(condition);
			System.out.println(rSet);
			if(rSet == 1) {
				out.println("delete successfully!");
			}
			else {
				out.println("can not delete!");
			}
			con.close();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}


