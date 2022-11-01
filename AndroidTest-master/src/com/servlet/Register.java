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
public class Register extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	public Register() {
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
		String nickname1 = request.getParameter("nickname").trim();
		String rtime = request.getParameter("rtime").trim();
		String Phone = request.getParameter("Phone").trim();
		String pwd = request.getParameter("pwd").trim();
		String qq = request.getParameter("qq").trim();
		String mail = request.getParameter("mail").trim();
		String school = request.getParameter("goal_school").trim();
		String exam_time = request.getParameter("exam_time").trim();
		String nickname = new String(nickname1.getBytes("ISO-8859-1"),"utf-8");
		//String exam_time = convertEncodingFormat(exam_time, "iso-8859-1", "UTF-8");
		String uri = "jdbc:mysql://localhost/cshelper";
		try {
			con = DriverManager.getConnection(uri,"root","newpassword");
			//out.println("get here");
			System.out.println("get here!" );
			System.out.println(school);
			String condition = "insert into user(nickname,rtime,pwd,mail,qq,phone,tschool,exam_time) values ('"+nickname+"','"+rtime+"','"+pwd+"','"+mail+"','"+qq+"','"+Phone+"','"+school+"','"+exam_time+"')";
			sql = con.prepareStatement(condition);
			int rSet = sql.executeUpdate(condition);
			System.out.println(rSet );
			if(rSet == 1) {
				out.println("register successfully!");
			}
			else {
				out.println("can not register!");
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


