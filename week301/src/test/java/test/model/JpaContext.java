package test.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class JpaContext {
	
	private static Class<?> 
		persistanceClass;
		
	@BeforeClass
	public void setJpaClasses() throws ClassNotFoundException {
		persistanceClass = Class.forName("javax.persistence.Persistence");
	}

	@Test
	public void getPersistanceContext() throws Exception {
		Object emf = callStatic( persistanceClass, "createEntityManagerFactory", new Object[]{"myApp"} );
		Object em = call(emf, "createEntityManager");
		Object tx = call(em, "getTransaction");
		assertNotNull( tx );
	}

	private Object callStatic(Class<?> k, String methodName, Object[] params) throws Exception {
		Method method = null;
		for ( Method m : k.getDeclaredMethods() ) {
			if ( m.getName().equals(methodName) ) {
				method = m ;
				break;
			}
		}
		if ( method == null ) fail("No such method "+ methodName + " on " + k.getName() );
		try {
			return method.invoke(null, params);
		} catch( InvocationTargetException e ) {
			throw (Exception) e.getTargetException();
		} catch( IllegalAccessException iae ) {
			fail( "IllegalAccessException " + k.getName() + "." + methodName + "()" );
			return null;
		}
	}
	
	private Object call(Object o, String methodName) throws Exception {
		Method method = null;
		Class<?> k = o.getClass();
		for ( Method m : k.getDeclaredMethods() ) {
			if ( m.getName().equals(methodName) ) {
				method = m ;
				break;
			}
		}
		if ( method == null ) fail("No such method "+ methodName + " on " + k.getName() );
		
		try {
			return method.invoke(o, (Object[]) null);
		} catch( InvocationTargetException e ) {
			throw (Exception) e.getTargetException();
		} catch( IllegalAccessException iae ) {
			fail( "IllegalAccessException " + k.getName() + "." + methodName + "()" );
			return null;
		}
	}
	
	
}
