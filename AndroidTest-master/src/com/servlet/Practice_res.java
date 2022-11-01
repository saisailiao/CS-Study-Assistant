package com.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Practice_res extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	String character;
	public Practice_res() {
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
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Connection con;
		Statement sql;
		String [] mistakes_qid;
		String mistakes_qid_string = request.getParameter("mistakes_qid").trim();
		String uid = request.getParameter("uid").trim();
		String num_q = request.getParameter("num_q").trim();
		String num_incorrect = request.getParameter("num_incorrect").trim();
		String study_time = request.getParameter("study_time").trim();
		String uri = "jdbc:mysql://localhost/cshelper";
		int i = 0;
		mistakes_qid = mistakes_qid_string.split(",");
		try {
			con = DriverManager.getConnection(uri,"root","newpassword");
			//out.println("get here");
			String condition = "insert into study_data(uid,time,num_q,num_incorrect,study_time) values("+uid+", curdate(),"+num_q+","+num_incorrect+","+study_time+")";
			System.out.println(condition);
			sql = con.prepareStatement(condition);
			sql.execute(condition);
			
			for(i=0;i<Integer.valueOf(num_incorrect);i++)
			{
				condition = "select * from favourite where qid="+mistakes_qid[i]+" and uid="+uid+" and btype=0";
				System.out.println(condition);
				sql = con.prepareStatement(condition);
				ResultSet rSet = sql.executeQuery(condition);
				if(rSet.next())//集合非空，该题目已经收藏过
				{
					System.out.println("Wrong question already exists");
					continue;
				}
				condition = "insert into favourite(qid,uid,btype,btime) values("+mistakes_qid[i]+","+uid+",0,now())";
				System.out.println(condition);
				sql = con.prepareStatement(condition);
				sql.execute(condition);
			}
			
			
			out.println("write data successfully!");
			
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
