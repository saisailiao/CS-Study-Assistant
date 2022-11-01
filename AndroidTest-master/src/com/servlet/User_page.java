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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;


public class User_page extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	public User_page() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
    	//System.out.println("get here");
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
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Connection con;
		Statement sql;
		String uri = "jdbc:mysql://localhost/cshelper";
		System.out.println("get here11111111!" );
		String nickname1 = request.getParameter("nickname").trim();
		System.out.println(nickname1 );
		String nickname = new String(nickname1.getBytes("ISO-8859-1"),"utf-8");
		//Login get_name =new Login();
		try {
			con = DriverManager.getConnection(uri,"root","newpassword");
			//out.println("get here");
			String condition = "select nicknam,mail,qq,phone,tschool,exam_time from user where nickname = '"+nickname+"'";
			System.out.println(condition);
			sql = con.prepareStatement(condition);
			ResultSet rSet = sql.executeQuery(condition);
			System.out.println(rSet);
			if(rSet.next()) {
				String name = rSet.getString("nickname");//
				String mail = rSet.getString("mail");//
				String qq = rSet.getString("qq");//
				String phone = rSet.getString("phone");//
				String tschool = rSet.getString("tschool");//
				String exam_time = rSet.getString("exam_time");//
				out.println(name+"|"+mail+"|"+qq+"|"+phone+"|"+tschool+"|"+exam_time+"|");
				System.out.println(name+"|"+mail+"|"+qq+"|"+phone+"|"+tschool+"|"+exam_time+"|");
			}
			else {
				out.println("can not get content!");
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


