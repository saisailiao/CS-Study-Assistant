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

import com.sun.tools.sjavac.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * Servlet implementation class LoginTest
 */
//@WebServlet("/mustLogin")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String name;
    String uid;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
    
    public static String getPastDate(int past) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);  
        Date today = calendar.getTime();  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String result = format.format(today);  
        return result;  
    }  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		Connection con;
		Statement sql;
		String logname1 = request.getParameter("logname").trim();
		String password = request.getParameter("password").trim();
		PrintWriter out = response.getWriter();
		System.out.println("get here111!"+logname1);
		String logname = new String(logname1.getBytes("ISO-8859-1"),"utf-8");
		name = logname;
		String uri = "jdbc:mysql://localhost/cshelper";
		try {
			con = DriverManager.getConnection(uri,"root","newpassword");
			String condition = "select * from user where nickname = '"+logname+"' and pwd = '"+password+"'";
			sql = con.prepareStatement(condition);
			ResultSet rSet = sql.executeQuery(condition);
			if(rSet.next()) {
				 uid = rSet.getString("uid");//
				out.println(uid+"\nlogin successfully!");
			}
			else {
				out.println("can not login!");
			}
			String num_q = "0";
			String num_incorrect = "0";
			String study_time = "0";
			int i;
			for(i = 0;i < 7;i++) {
				System.out.println("!!!!!!!"+i);
				String Date = getPastDate(i);
				System.out.println("!!!!!!!"+i+Date);
		     condition = "insert into study_data(uid,time,num_q,num_incorrect,study_time) values("+uid+",'"+Date+"',"+num_q+","+num_incorrect+","+study_time+")";
			System.out.println(condition);
			sql = con.prepareStatement(condition);
			sql.execute(condition);}
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
