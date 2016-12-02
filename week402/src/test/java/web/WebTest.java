package web;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WebTest {
	
	WebTool wt;
	
	@Before
	public void init() {
		wt = new WebTool("localhost:8080", "/week402");
	}
	
	@Test
	public void testRunTomcat() throws Exception {
		Assert.assertTrue(
			WebTool.toString(
				wt.get("/test.txt")
			).contains("test")
		);
	}
	
	@Test
	public void testHello() throws Exception {
		String name = "me" + Math.random();
		Assert.assertTrue(
			WebTool.toString(
				wt.get("/app/hello/world?name="+name)
			).contains("Hello "+name)
		);
	}
	
	@Test
	public void testHello_noParam() throws Exception {
		Assert.assertTrue(
			WebTool.toString(
				wt.get("/app/hello/world")
			).contains("Hello ")
		);
	}
	
	@Test
	public void testSimple_add() throws Exception {
		Assert.assertTrue(
			WebTool.toString(
				wt.get("/app/simple/add?a=2&b=3")
			).equals("5")
		);
	}
	
	@Test
	public void testSimple_time() throws Exception {
		String res = WebTool.toString( wt.get("/app/simple/time") );
		Assert.assertTrue(res.startsWith("{"));
		Assert.assertTrue(res.endsWith("}"));
		for ( String param : Arrays.asList("day", "time", "locale", "timestamp") )
			Assert.assertTrue( res.contains("\""+param+"\":") );
	}

}
