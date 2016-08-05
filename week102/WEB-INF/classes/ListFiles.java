import java.io.IOException;
import java.io.Writer;
import java.io.File;

import java.nio.file.Files;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/listFiles")
public class ListFiles extends HttpServlet {
  

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String directory = req.getParameter("directory");
    
    if ( directory == null ) {
      resp.sendError( HttpServletResponse.SC_BAD_REQUEST, "You must provide directory parameter." );
      return;
    }
    
    File d = new File( directory );
    if ( !d.exists() ) {
      resp.sendError( HttpServletResponse.SC_NOT_FOUND, "Directory " + directory + " not found." );
      return;
    }
    
    if ( !d.isDirectory() ) {
      resp.sendError( HttpServletResponse.SC_BAD_REQUEST, "This file is not a directory." );
      return;
    }

    Map<String, Map<String, Object>> files = new HashMap<>();    
    Files
      .list( d.toPath() )
      .map( p -> p.toFile() )
      .forEach( f -> {
        String name = f.getName();
        Map<String, Object> fileAttribute = new HashMap<>();
        fileAttribute.put("directory", Boolean.toString(f.isDirectory()) );
        fileAttribute.put("lastModified", f.lastModified() / 1000.0 );
        fileAttribute.put("size", f.length() );
        files.put( name, fileAttribute );
      });
    
    Writer out = resp.getWriter();
    String json = ""; // new JsonEncoderImpl().toJson( files );
    out.write( json );
	}

}