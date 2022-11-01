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


public class Practice_by_word extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	String character;
	public Practice_by_word() {
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
		String word1 = request.getParameter("word").trim();
		PrintWriter out = response.getWriter();
		System.out.println("get here111!"+word1);
		String word = new String(word1.getBytes("ISO-8859-1"),"utf-8");
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
			String condition = "select distinct qid,qcontent,answer,question_lib.sid,sa,sb,sc,sd from question_lib,question_choice where question_lib.qcontent like '%"+word+"%' and question_choice.sid = question_lib.sid ORDER BY RAND() LIMIT 3";
			System.out.println(condition);
			sql = con.prepareStatement(condition);
			ResultSet rSet = sql.executeQuery(condition);
			System.out.println(rSet);
			int i;
			for(i = 0;i<3;i++) {
				if(rSet.next()) {
					String qid = rSet.getString("qid");//
					String content = rSet.getString("qcontent");//
					String answer = rSet.getString("answer");//
					String sid = rSet.getString("question_lib.sid");//
					String a = rSet.getString("sa");//
					String b = rSet.getString("sb");//
					String c = rSet.getString("sc");//
					String d = rSet.getString("sd");//
					if(sid.equals("17")) {
						out.println("\n qid \n"+qid+"\n"+content+"\n answer \n"+answer+" end \n");
						System.out.println("\n qid \n"+qid+"\n"+content+"\n answer\n"+answer+" end \n");
					}
					else {
						out.println("\n qid \n"+qid+"\n"+content+"\n"+"A."+a+"\n"+"B."+b+"\n"+"C."+c+"\n"+"D."+d+"\n answer \n"+answer+"\n end \n");
						System.out.println("\n qid \n"+qid+"\n"+content+"\n"+"A."+a+"\n"+"B."+b+"\n"+"C."+c+"\n"+"D."+d+"\n answer \n"+answer+"\n end \n");
					}
					
				}
				else {
					out.println("can not get content!"+"\n");
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


