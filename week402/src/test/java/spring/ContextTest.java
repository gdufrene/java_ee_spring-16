package spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eservices.week402.app.AppConfig;
import fr.eservices.week402.ctrl.HelloController;
import fr.eservices.week402.ctrl.SimpleController;
import fr.eservices.week402.model.TimeObject;

public class ContextTest {
	
	AnnotationConfigApplicationContext ctx;
	
	@Before
	public void init() {
		ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppTestConfig.class);
		ctx.refresh();
	}
	
	@After
	public void close() {
		ctx.close();
	}
	
	@Test
	public void testAppAnnotations() {
		List<String> annotationNames = new ArrayList<>();
		for ( Annotation ann : AppConfig.class.getAnnotations() ) {
			annotationNames.add( ann.annotationType().getName() );
		}
		assertTrue( annotationNames.contains("org.springframework.context.annotation.Configuration") );
		assertTrue( annotationNames.contains("org.springframework.context.annotation.ComponentScan") );
		assertTrue( annotationNames.contains("org.springframework.web.servlet.config.annotation.EnableWebMvc") );
	}
	
	@Test
	public void testHelloController() {
		HelloController ctrl = ctx.getBean(HelloController.class);
		assertTrue( ctrl.sayHello(new ExtendedModelMap(), "world").contains("sample") );
	}

	@Test
	public void testHelloControllerView() {
		HelloController ctrl = ctx.getBean(HelloController.class);
		assertEquals( "sample", ctrl.sayHello(new ExtendedModelMap(), "world") );
	}
	
	@Test
	public void testSimpleController() throws Exception {
		SimpleController ctrl = ctx.getBean(SimpleController.class);
		assertTrue(
				Arrays.asList(
					ctrl.getClass()
					.getAnnotation(RequestMapping.class)
					.value()
				).contains("/simple")
			);
	}
	
	@Test
	public void testSimpleController_add() throws Exception {
		SimpleController ctrl = ctx.getBean(SimpleController.class);
		assertEquals( "5", ctrl.add(2, 3) );
		
		assertTrue(
			Arrays.asList(
				ctrl.getClass()
				.getDeclaredMethod("add", new Class[]{int.class, int.class})
				.getAnnotation(RequestMapping.class)
				.value()
			).contains("/add")
		);
	}
	
	@Test
	public void testSimpleController_time() throws Exception {
		SimpleController ctrl = ctx.getBean(SimpleController.class);
		TimeObject time = ctrl.getTime();
		assertNotNull( time );
		
		assertTrue(
				Arrays.asList(
					ctrl.getClass()
					.getDeclaredMethod("getTime", new Class[]{})
					.getAnnotation(RequestMapping.class)
					.value()
				).contains("/time")
			);
	}
}
