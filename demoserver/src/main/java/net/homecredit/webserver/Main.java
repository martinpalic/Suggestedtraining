package net.homecredit.webserver;


import net.homecredit.servletapi.HttpRequest;
import net.homecredit.servletapi.HttpResponse;
import net.homecredit.servletapi.HttpServlet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = new URLClassLoader(new URL[]{new URL("file:samplewebapp/target/samplewebapp.jar")});
        Class<?> loadedServletClass = loader.loadClass("net.homecredit.webapp.DefaultHttpServlet");


        ServerSocket server = new ServerSocket(8080);
        while (true) {
            Socket conn = server.accept();
            InputStream inputStream = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            HttpRequest request = new HttpRequest();
            HttpResponse response = new HttpResponse();
            HttpServlet servlet = (HttpServlet) loadedServletClass.newInstance();

            request.setPlainTextRequest(readRequest(reader));
            servlet.doGet(request, response);

            sendResponse(response, conn.getOutputStream());
            
            System.out.println("End of connection");
            conn.close();
        }
    }

    private static void sendResponse(HttpResponse response, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        writer.write(response.getPlainTextResponse());
        writer.flush();
        writer.close();
    }

    private static String readRequest(InputStreamReader reader) throws IOException {
        StringBuilder buffer = new StringBuilder();
        while (true) {
            String requestLine = dumpInputStream(reader);
            if (requestLine.equals("\r\n")) break;
            buffer.append(requestLine.substring(0, requestLine.length() > 1 ? requestLine.length()-2 : 0));
        }
        return buffer.toString();
    }

    private static String dumpInputStream(Reader reader) throws IOException {
        StringWriter buffer = new StringWriter();
        do {
            int read = reader.read();
            if (read < 0) break;
            buffer.append((char) read);
        } while (!buffer.getBuffer().toString().endsWith("\r\n"));
        return buffer.getBuffer().toString();
    }
}
