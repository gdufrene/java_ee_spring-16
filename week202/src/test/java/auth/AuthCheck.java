package auth;

import static org.junit.Assert.*;
import static utils.HtmlUtils.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import utils.WebTool;


public class AuthCheck {
	
	WebTool web;
	
	@Before
	public void initWebTool() {
		web = new WebTool("localhost:8080", "/user-web/");
	}

	@Test
	public void checkLoginPage() throws IOException {
		// 1. There is a form at "login.jsp" ********************
		String content = asString( web.get("login.jsp") );
		assertTrue( content.contains("html") );
		assertEquals( 200, web.code() );
			
		String action = getTagAttribute( content, "form", "action" );
		assertNotNull( action );
		assertTrue( action.contains("auth") );
	}

	@Test
	public void canAuthValidUser() throws IOException {
		// 2. Servlet auth can valid a user *************************
		String data = 
				WebTool.encode("email", "mark.zuckerberg@facebook.com")
				+ WebTool.encode("password", "f4c3b00k");
		InputStream is = web.post("auth", data);
		is.close();

		assertEquals("Authent should succeed with test user. (redirect expected)",
				302, web.code()
		);
	}
	
	private String postInvalidUser(String mail) throws IOException {
		String data = 
				WebTool.encode("email", mail)
				+ WebTool.encode("password", "another-test");
		InputStream is = web.post("auth", data);
		return asString(is);
	}
	
	@Test
	public void blockInvalidUser_noRedirect() throws IOException {
		postInvalidUser("invalid.user@mail.com");
		assertEquals("Authent should failed with test user. (no-redirect)", 
				200, web.code() 
		);
	}
	
	@Test
	public void blockInvalidUser_noShowErrorMessage() throws IOException {
		String result = postInvalidUser("invalid.user@mail.com");
		assertTrue("Authent show a message on error", 
				result.contains("alert alert-danger")
		);
	}
	
	@Test
	public void blockInvalidUser_keepMailField() throws IOException {
		String result = postInvalidUser("invalid.user@mail.com");
		assertTrue("Authent keep user email in textfield on error.",
				result.contains("value=\"invalid.user@mail.com\"")
		);
	}
	
	
}
