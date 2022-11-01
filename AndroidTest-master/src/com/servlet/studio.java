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


public class studio extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	String character;
	public studio() {
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
		PrintWriter out = response.getWriter();
		//String subject = new String(subject1.getBytes("ISO-8859-1"),"utf-8");
		//String type = new String(type1.getBytes("ISO-8859-1"),"utf-8");
		//byte[] subject1=str.getBytes("ISO-8859-1");//用tomcat的格式（iso-8859-1）方式去读。
		//String subject =new String(subject1);//采用utf-8去接string
		//System.out.println(str.getBytes());  
		String uri = "jdbc:mysql://localhost/cshelper";
		String time = "";
		//System.out.println("get here111!"+subject1);
		//out.print(subject);
		//System.out.println(subject);
		try {
			con = DriverManager.getConnection(uri,"root","newpassword");
			//out.println("get here");
			System.out.println("get here111! ");
			String condition = "SELECT  time,sum from (SELECT  time,sum(study_time) as sum FROM study_data WHERE uid = '"+uid+"' and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(time) GROUP BY DATE_FORMAT(time, '%Y%m%d'))as li order by time limit 0,7";
			System.out.println(condition);
			sql = con.prepareStatement(condition);
			ResultSet rSet = sql.executeQuery(condition);
			System.out.println(rSet);
			while(rSet.next()) {
				String time1 = rSet.getString("time");//
				String study_time = rSet.getString("sum");//
				time +=study_time+"|";
				System.out.println(study_time);
			}
			condition = "SELECT  time,1-sum2/sum1 from (SELECT  time,sum(num_q) as sum1,sum(num_incorrect) as sum2 FROM study_data WHERE uid = '"+uid+"' and to_days(time) = to_days(now()) GROUP BY DATE_FORMAT(time, '%Y%m%d'))as li order by time";
			System.out.println(condition);
			sql = con.prepareStatement(condition);
			 rSet = sql.executeQuery(condition);
			System.out.println(rSet);
			while(rSet.next()) {
				float rate = rSet.getFloat("1-sum2/sum1");//
				System.out.println("|"+rate);
				int rate1 = (int)(rate * 100);
				time +=rate1+"|";
				time +=rate+"|";
				System.out.println("|"+rate1);
			}
			condition = "SELECT  time,sum1 from (SELECT  time,sum(num_q) as sum1,sum(num_incorrect) as sum2 FROM study_data WHERE uid = '"+uid+"' and to_days(time) = to_days(now()) GROUP BY DATE_FORMAT(time, '%Y%m%d'))as li order by time";
			//System.out.println(condition);
			sql = con.prepareStatement(condition);
			 rSet = sql.executeQuery(condition);
			//System.out.println(rSet);
			while(rSet.next()) {
				String sum1 = rSet.getString("sum1");//
				time +=sum1+"|";
				System.out.println("|"+sum1);
			}
			condition = "SELECT  time,sum1,study from (SELECT  time,sum(num_q) as sum1,sum(num_incorrect) as sum2,sum(study_time) as study FROM study_data WHERE uid = '"+uid+"' and to_days(time) = to_days(now()) GROUP BY DATE_FORMAT(time, '%Y%m%d'))as li order by time";
			//System.out.println(condition);
			sql = con.prepareStatement(condition);
			 rSet = sql.executeQuery(condition);
			//System.out.println(rSet);
			while(rSet.next()) {
				int study = rSet.getInt("study");//
				study = study/60;
				time +=study+"|";
				System.out.println("|"+study);
			}
			out.println(time);
			System.out.println(time);
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


