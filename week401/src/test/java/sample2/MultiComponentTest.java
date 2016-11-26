package sample2;


import org.junit.After;
import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.eservices.sample2.api.Greeter;
import fr.eservices.sample2.api.Printer;
import fr.eservices.sample2.api.Welcome;
import fr.eservices.sample2.impl.ConsolePrinter;
import fr.eservices.sample2.impl.ConsoleWelcome;
import fr.eservices.sample2.impl.EnglishGreeter;

public class MultiComponentTest extends AppContextCommon {

	@Before
	public void initContext() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( );
		context.scan( "fr.eservices.sample2" );
		context.refresh();
		context.start();
		
		this.context = context;
	}
	
	@After
	public void stopContext() {
		((AnnotationConfigApplicationContext) context).stop();
	}
	

	
	public void hasBasicComponents() throws Exception {
		hasComponent( EnglishGreeter.class );
		hasComponent( ConsoleWelcome.class );
		hasComponent( ConsolePrinter.class );
	}
	
	public void hasManyComponents() throws Exception {
		hasComponents( Greeter.class, 2 );
		hasComponents( Printer.class, 2 );
		hasComponents( Welcome.class, 2 );
	}
	
	public void hasProperQualifier() throws Exception {
		hasComponentQualified("swing", Welcome.class);
		context.getBean("console", Welcome.class);
		
		context.getBean("english", Greeter.class);
		context.getBean("french", Greeter.class);
		
		context.getBean("swing", Printer.class);
		context.getBean("console", Printer.class);
	}
	
}
