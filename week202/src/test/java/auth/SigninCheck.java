package auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static utils.HtmlUtils.asString;
import static utils.HtmlUtils.getTagAttribute;
import static utils.HtmlUtils.toNextTag;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utils.WebTool;

public class SigninCheck {
	
	WebTool web;
	
	@Before
	public void initWebTool() {
		web = new WebTool("localhost:8080", "/user-web/");
	}

	@Test
	public void checkRegisterPage() throws IOException {
		// 4. There is a register.jsp page,
		boolean okForm = false;
		boolean pageReachable = false;
		boolean contains4Fields = false;
		List<String> expectedFields = Arrays.asList("firstname", "lastname", "email", "password");
			//  - it is reachable
		try {
			String content = asString( web.get("register.jsp") );
			pageReachable = content.contains("html") && web.code() == 200 ;
			
			String action = getTagAttribute( content, "form", "action" );
			if ( action != null && action.contains("signin") ) okForm = true;
			
			String nextInput = content;
			ArrayList<String> notFound = new ArrayList<>( expectedFields );
			while ( (nextInput = toNextTag(nextInput, "input")) != null ) {
				String inputName = getTagAttribute(nextInput, "input", "name");
				//System.out.println("input name="+inputName);
				if ( inputName != null && expectedFields.contains(inputName) ) notFound.remove(inputName); 
			}
			contains4Fields = notFound.isEmpty();
		} catch (IOException ioe ) { }
		assertTrue( "register page should be reachable", pageReachable );
			//  - it contains a form
		assertTrue( "register action form is signin servlet", okForm );
			//  - it contains 4 fields with firstname, lastname, email, password
		assertTrue( "register contains correct fieldnames", contains4Fields );
	}
		
	@Test
	public void checkSigninServlet() throws IOException {
		// 5. There is a "/signin" servlet
		String content = asString( web.get("register.jsp") );
		assertTrue( content.contains("html") && web.code() == 200 );
		
		String userMail2 = "u_" + (Math.random()*10000) + "@test.com";
		HashMap<String, String> data = new HashMap<>(); 
		data.put("firstname", "Test");
		data.put("lastname", "Test");
		data.put("email", userMail2);
		data.put("password", "test");
		InputStream is = web.post("signin", WebTool.encodeMap(data));
		is.close();
		assertEquals( 
			"POST /sigin should accept new user and redirect to a page with HTTP code 302",
			302, web.code()
		);
	}
	
	private interface MissingFieldHandler {
		public void check(String field, int code, String res, Exception err);
	}
	
	private void doMissingFieldsRequests(MissingFieldHandler cb) {
		List<String> expectedFields = Arrays.asList("firstname", "lastname", "email", "password");

		String content;
		for ( String field : expectedFields ) {
			content = "";
			try {				
				String userMail2 = "u_" + (Math.random()*10000) + "@test.com";
				HashMap<String, String> data = new HashMap<>(); 
				data.put("firstname", "Test");
				data.put("lastname", "Test");
				data.put("email", userMail2);
				data.put("password", "test");
				data.remove(field);
				
				InputStream is = web.post("signin", WebTool.encodeMap(data));
				content = asString( is );
				cb.check(field, web.code(), content, null);
				
			} catch(IOException e) {
				cb.check(field, web.code(), content, e);
			}
		}
		
	}
	
	@Test
	public void checkSigninServlet_checkMissingParam() throws IOException {
		//  - it refuses any attempt with empty or null field
		doMissingFieldsRequests( (field, code, content, err) -> {
			assertEquals("No redirect when field in error",
				200, code
			);
		} );
	}
	
	@Test
	public void checkSigninServlet_showErrorParam() throws IOException {
	//  - it shows error on empty or null field
		doMissingFieldsRequests( (field, code, content, err) -> {
			assertEquals(200, code);
			int i = content.indexOf("name=\""+field+"\"");
			if ( i > 0 ) {
				int j = 0;
				int found = 0;
				while ( j >= 0 && j < i ) {
					j = content.indexOf("<div", j+1);
					if ( j < 0 ) break;
					
					String klass = getTagAttribute(content.substring(j), "div", "class");
					if ( klass != null && klass.contains("form-group") )
						if ( j < i ) found = j;
				}
				int k = found >= 0 ? content.indexOf('>', found+1) : -1;
				String divString = "";
				if ( k > 0 ) divString = content.substring(found, k);
				if ( !divString.contains("has-error") ) {
					fail( "Field '" + field + "' should be in error when empty");
				}
			}
		} );
	}
	
	@Test
	public void checkSigninServlet_ErrorOnExistingUser() throws IOException {
		//  - it refuses existing user (mail)
		boolean okCheckExists = false;
			
		HashMap<String, String> data = new HashMap<>(); 
		data.put("firstname", "Test");
		data.put("lastname", "Test");
		data.put("email", "mark.zuckerberg@facebook.com");
		data.put("password", "test");
		
		InputStream is = web.post("signin", WebTool.encodeMap(data));
		is.close();
		okCheckExists = web.code() == 200;
		assertTrue("signin refuses existing user", okCheckExists);
	}
}
