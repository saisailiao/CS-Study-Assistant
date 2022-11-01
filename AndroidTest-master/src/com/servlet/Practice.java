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


public class Practice extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	String character;
	public Practice() {
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
		String mode = request.getParameter("mode").trim();
		String qtime = request.getParameter("qtime").trim();
		String subject1 = request.getParameter("subject").trim();
		String qschool1 = request.getParameter("qschool").trim();
		String qtype1 = request.getParameter("qtype").trim();
		String qnum = request.getParameter("qnum").trim();
		String uri = "jdbc:mysql://localhost/cshelper";
		System.out.println(qtype1);
		String qtype = new String(qtype1.getBytes("ISO-8859-1"),"utf-8");
		String subject = new String(subject1.getBytes("ISO-8859-1"),"utf-8");
		String qschool = new String(qschool1.getBytes("ISO-8859-1"),"utf-8");
		int i = 0;
		int flag = 0;
		try {
			con = DriverManager.getConnection(uri,"root","newpassword");
			//out.println("get here");
			String condition = "select viewChoice.qid,qcontent,answer,sa,sb,sc,sd,qtype from viewChoice ";
					//+ "qtime = '"+qtime+"'"+" and subject='"+subject+"' and qschool='"+qschool+"' and qtype='"+qtype+"'";
			if(mode.equals("3"))
			{
				condition += ",favourite where viewChoice.qid=favourite.qid and btype=0 ";
				flag = 1;
			}
			if(!qtime.equals(""))
			{
				if(flag == 0)
					condition += "where ";
				else
					condition += "and ";
				condition += "qtime='"+qtime+"' ";
				flag = 1;
			}
			if(!subject.equals(""))
			{
				if(flag == 0)
					condition += "where ";
				else
					condition += "and ";
				condition += "subject='"+subject+"' ";
				flag = 1;
			}
			if(!qschool.equals(""))
			{
				if(flag == 0)
					condition += "where ";
				else
					condition += "and ";
				condition += "qschool='"+qschool+"' ";
				flag = 1;
			}
			if(!qtype.equals(""))
			{
				if(flag == 0)
					condition += "where ";
				else
					condition += "and ";
				condition += "qtype='"+qtype+"' ";
				flag = 1;
			}
			if(mode.equals("2"))
			{
				condition += "order by rand() desc ";
			}
			condition += "limit "+qnum;
			
			System.out.println(condition);
			sql = con.prepareStatement(condition);
			ResultSet rSet = sql.executeQuery(condition);
			System.out.println(rSet);
			while(rSet.next()) {
				String qid = rSet.getString("qid");//
				String qcontent = rSet.getString("qcontent");//
				String answer = rSet.getString("answer");//
				String sa = rSet.getString("sa");//
				String sb = rSet.getString("sb");//
				String sc = rSet.getString("sc");//
				String sd = rSet.getString("sd");//
				String qtype_ = rSet.getString("qtype");//
				if(qtype_.equals("分析题")) {
					out.println("qid_a\n"+qid+"\ncontent\n"+qcontent+"\nans\n"+answer);
				}
				else if(qtype_.equals("多选题")){
					out.println("qid_m\n"+qid+"\ncontent\n"+qcontent+"\nA."+sa+"\nB."+sb+"\nC."+sc+"\nD."+sd+"\nans\n"+answer);
				}
				else {
					out.println("qid\n"+qid+"\ncontent\n"+qcontent+"\nA."+sa+"\nB."+sb+"\nC."+sc+"\nD."+sd+"\nans\n"+answer);
				}
			}
			out.println("endall\n");
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


