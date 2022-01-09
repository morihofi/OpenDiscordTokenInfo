package application.web;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import com.google.common.io.Resources;

import fi.iki.elonen.NanoHTTPD;


public class WebApp extends NanoHTTPD {
	
    public WebApp(int port) throws IOException {
        super(port);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        
    }


    @Override
    public Response serve(IHTTPSession session) {
        if (session.getMethod() == Method.GET) {
        	String path = session.getUri();
        	
        	try{
        		if (path.startsWith("/cgi-bin/dti-controlpanel/")) {
            		
            		if (path.equals("/cgi-bin/dti-controlpanel/filemanager.cgi")) {
            			String filepath = "";
            			
            			try {
            				filepath = session.getParameters().get("path").get(0);
            				
            				String html = "<html><head></head><body><h1>File Manager</h1>"
            						//+ "Current directory: " + new File(filepath).getName() + "<br>"
            						+ "<table>"
            						+ "  <tr>"
            						+ "    <th>Name</th>"
            						+ "    <th>Type</th>"
            						+ "    <th>Size</th>"
            						+ "  </tr>";
            						
            				 File f = new File(filepath);

            				 List<String> pathnames = new ArrayList<String>();
            			     // Populates the array with names of files and directories
            			     
            				 pathnames.add(".");
            				 pathnames.add("..");
            				 
            				  for (String i : f.list()) {
            					  pathnames.add(i);
            				  }
            			     
            			     
            			     

            			     // For each pathname in the pathnames array
            			     for (String pathname : pathnames) {
            			         // Print the names of files and directories
            			         //System.out.println(pathname);
            			         String filename = new File(pathname).getName();
            			         
            			         MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
            			         // only by file name
            			         String mimeType = "";
            			        
            			         String sizestr = "";
            			      
            			         

            			         if(new File(pathname).getAbsoluteFile().isFile()) {
            			        	 mimeType = mimeTypesMap.getContentType(pathname);
            			        	 //long size = Files.size(path1);
              			        	 //sizestr = readableFileSize(size);
            			         }else {
            			        	 mimeType = "Directory";
            			         }
            			        	 //mimeType = mimeTypesMap.getContentType(pathname);
            			        /*	 
            			        	 
            			         */
            			         
            			    	
            			         
            			         html += "  <tr>"
                 						+ "    <td>" + filename + "</td>"
                						+ "    <td>" + mimeType + "</td>"
                						+ "    <td>" + sizestr + "</td>"
                						+ "  </tr>";
            			         
            			         System.out.println(filename + ";" + mimeType + ";" + sizestr + ";" + new File(pathname).getAbsoluteFile().isFile());
            			     }
            						
            				
            			     
            			     html += "</table></body></html>";
            				
            				
            				
            				
            				return newFixedLengthResponse(Response.Status.REDIRECT, MIME_HTML, html);
            			}catch (Exception ex){

            				Response r = newFixedLengthResponse(Response.Status.REDIRECT, MIME_HTML, "");
            	            r.addHeader("Location", "/cgi-bin/dti-controlpanel/filemanager.cgi?path=/");
            	            return r;
            	            
            			}
            			
            			
            		}
            		
            		
            		return newFixedLengthResponse("");
            		
            	}
            	
            	
            	if (path.startsWith("/cgi-bin/data")) {
            		
            		if (path.equals("/cgi-bin/data/stylesheet.css")) {
            		
    	        		URL url = getClass().getResource("/application/web/files/stylesheet.css"); //.getResource("/application/web/files/stylesheet.css");
    	        		String text = null;
    					try {
    						text = Resources.toString(url, StandardCharsets.UTF_8);
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	        			
    	        		return newFixedLengthResponse(Response.Status.OK, "text/css", text);
    	        		
            		}
            		
            	}
            	
            	
            	
            	if (path.startsWith("/cgi-bin/dti-gui/")) {
            	
            		
            	
            	}
            	
            	
                //String itemIdRequestParameter = session.getParameters().get("itemId").get(0);
            	
                
                
        	}catch(Exception ex) {
        		return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, MIME_HTML, "<html><head><title>Internal Server Error</title></head>"
        				+ "<body><h1>Internal Server Error</h1><b>Exception:&nbsp;</b>" + ex.getMessage() +"<br>Stack Trace:<pre>" + ex.getStackTrace()+"</pre></body></html>");
        	}
        	
        	
            
            
            
            
        }
        return newFixedLengthResponse(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "The requested resource does not exist");
    }
    
    public static String readableFileSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
    
}