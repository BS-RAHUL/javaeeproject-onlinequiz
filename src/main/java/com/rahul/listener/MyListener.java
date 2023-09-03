package com.rahul.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionIdListener;
import jakarta.servlet.http.HttpSessionListener;





public class MyListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent arg0) {
		
		Connection con=null;
	try{
		ResultSet rs;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rahul","RR0114");
		
		PreparedStatement ps1=con.prepareStatement("Select * from QUIZCONTACT");
		
		rs=ps1.executeQuery();
		if(rs.next())
		{System.out.println("your table name already exist");}
		else 
		{System.out.println("else if part table does not exist new table has created");
	
		
		PreparedStatement ps2= con.prepareStatement("CREATE SEQUENCE JAVATPOINT MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE");
		ps2.executeUpdate();
		PreparedStatement ps=con.prepareStatement("CREATE TABLE QUIZCONTACT (NAME VARCHAR2(4000),EMAIL VARCHAR2(4000),PHONE VARCHAR2(4000),MESSAGE VARCHAR2(4000))");
		ps.executeUpdate();
		PreparedStatement ps4=con.prepareStatement("CREATE TABLE QUIZINFO (SUBJECT VARCHAR2(4000),QUIZNAME VARCHAR2(4000))");
		ps4.executeUpdate();		
		
	
		PreparedStatement  ps5=con.prepareStatement("CREATE TABLE  QUIZQ(NAME VARCHAR2(4000),EMAIL VARCHAR2(4000),PHONE VARCHAR2(4000),QUESTION VARCHAR2(4000))");
		ps5.executeUpdate();
		
		
		ps5= con.prepareStatement("CREATE TABLE  QUIZQUES(QUESTION VARCHAR2(4000),OPTION1 VARCHAR2(4000),OPTION2 VARCHAR2(4000),OPTION3 VARCHAR2(4000),OPTION4 VARCHAR2(4000),ANSWER VARCHAR2(4000),QUIZNAME VARCHAR2(4000),QID VARCHAR2(4000),DESCRIPTION VARCHAR2(4000),CONSTRAINT QUIZQUES_PK PRIMARY KEY (QID) ENABLE)");
		ps5.executeUpdate();
		
		
		ps5= con.prepareStatement("CREATE TABLE  QUIZREGISTER (USERNAME VARCHAR2(4000),USERPASS VARCHAR2(4000),CATEGORY VARCHAR2(4000),EMAIL VARCHAR2(4000))");
		ps5.executeUpdate();
		Statement stmt=con.createStatement();
		stmt.executeUpdate("CREATE TRIGGER  BI_QUIZINFO before insert on QUIZINFO for each row begin select JAVATPOINT.nextval into :NEW.QUIZNAME from dual;end");
		stmt.executeUpdate("CREATE TRIGGER  BI_QUIZQUES before insert on QUIZQUES for each row begin select JAVATPOINT.nextval into :NEW.QID from dual;end");
		}	
	}
		
	    catch(Exception e){
	    	e.printStackTrace();
	    	
	    }
	    }
	    
	    public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("project undeployed");
		
	}
}
