package net.homecredit.webapp;

import net.homecredit.servletapi.HttpRequest;
import net.homecredit.servletapi.HttpResponse;
import net.homecredit.servletapi.HttpServlet;

import java.io.StringWriter;
import java.util.Random;

public class DefaultHttpServlet implements HttpServlet {

    public void doGet(HttpRequest request, HttpResponse response){
        StringWriter writer = response.getWriter();
        // Send the response
        // Send the headers
        writer.write("HTTP/1.0 200 OK\r\n");
        writer.write("Content-Type: text/html\r\n");
        writer.write("Server: SimpleWebServer\r\n");
        // this blank line signals the end of the headers
        writer.write("\r\n");
        // Send the HTML page
        writer.write("<CENTER>\r\n");
        writer.write("<H1 style='color:red'>Welcome to SimpleWebServer<H1>\r\n");
        writer.write("<H2>This page has been visited <span style='color:blue'>" +
                + new Random().nextInt() +"</span></H2>\r\n");
        writer.write("</CENTER>\r\n");

        response.setPlainTextResponse(writer.toString());
    }
}
