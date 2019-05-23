package net.homecredit.servletapi;

import java.io.StringWriter;

public class HttpResponse {
    private String plainTextResponse;

    private StringWriter writer = new StringWriter();

    public StringWriter getWriter() {
        return writer;
    }

    public void setWriter(StringWriter writer) {
        this.writer = writer;
    }

    public String getPlainTextResponse() {
        return plainTextResponse;
    }

    public void setPlainTextResponse(String plainTextResponse) {
        this.plainTextResponse = plainTextResponse;
    }
}
