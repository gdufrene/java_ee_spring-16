package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.UserDao;

// register your servlet to a path.

public class AuthControl extends HttpServlet {
	
	UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		// create a userDao
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// get login and password
		
		// check authentication of user
		
		// if Ok : redirect user to a welcome page.
		
		// If error : set an error message and show login page
		
	}
	

}
