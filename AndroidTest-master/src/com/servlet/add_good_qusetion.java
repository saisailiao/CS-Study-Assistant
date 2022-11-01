package com.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/RegisterTest")
public class add_good_qusetion extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	public add_good_qusetion() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
    	response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Connection con;
		Statement sql;
		String qid = request.getParameter("qid").trim();
		String uid = request.getParameter("uid").trim();
		String btype = request.getParameter("btype").trim();
		String btime = request.getParameter("btime").trim();
		//String exam_time = convertEncodingFormat(exam_time, "iso-8859-1", "UTF-8");
		String uri = "jdbc:mysql://localhost/cshelper";
		try {
			con = DriverManager.getConnection(uri,"root","newpassword");
			//out.println("get here");
			System.out.println("get here!" );
			String condition = "select * from favourite where qid = '"+qid+"' and uid = '"+uid+"'";
			sql = con.prepareStatement(condition);
			ResultSet rSet = sql.executeQuery(condition);
			System.out.println(rSet );	
			if(rSet.next()) {
				out.println("can not add!");
			}
			else {
				//con = DriverManager.getConnection(uri,"root","newpassword");
				condition = "insert into favourite(qid,uid,btype,btime) values ('"+qid+"','"+uid+"','"+btype+"','"+btime+"')";
				sql = con.prepareStatement(condition);
			    int rSet1 = sql.executeUpdate(condition);
				System.out.println(rSet );
				if(rSet1 == 1) {
					out.println("add successfully!");
				}
				else {
					out.println("can not add!");
				}
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


