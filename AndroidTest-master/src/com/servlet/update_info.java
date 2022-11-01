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
public class update_info extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	public update_info() {
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
		String uid = request.getParameter("uid").trim();
		String Phone = request.getParameter("Phone").trim();
		String qq = request.getParameter("qq").trim();
		String mail = request.getParameter("mail").trim();
		String school1 = request.getParameter("goal_school").trim();
		String exam_time = request.getParameter("exam_time").trim();
		String school = new String(school1.getBytes("ISO-8859-1"),"utf-8");
		//String exam_time = convertEncodingFormat(exam_time, "iso-8859-1", "UTF-8");
		String uri = "jdbc:mysql://localhost/cshelper";
		try {
			con = DriverManager.getConnection(uri,"root","newpassword");
			//out.println("get here");
			System.out.println("get here!" );
			System.out.println(school);
			String condition = "update user set Phone = '"+Phone+"',qq = '"+qq+"',mail = '"+mail+"', tschool = '"+school+"',exam_time = '"+exam_time+"' where uid = '"+uid+"'";
			sql = con.prepareStatement(condition);
			int rSet = sql.executeUpdate(condition);
			System.out.println(rSet );
			if(rSet == 1) {
				out.println("update successfully!");
			}
			else {
				out.println("can not update!");
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


