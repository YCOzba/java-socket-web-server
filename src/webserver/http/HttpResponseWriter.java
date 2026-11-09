package webserver.http;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * {@link HttpResponse} nesnesini HTTP/1.1 protokolüne uygun biçimde
 * soketin çıkış akışına (OutputStream) yazar.
 */
public final class HttpResponseWriter {

    private static final String CRLF = "\r\n";
    private static final String SERVER_NAME = "JavaSocketServer/1.0";

    public void write(HttpResponse response, OutputStream out, boolean includeBody) throws IOException {
        String head = "HTTP/1.1 " + response.status().code() + " " + response.status().reason() + CRLF
                + "Date: " + DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneOffset.UTC)) + CRLF
                + "Server: " + SERVER_NAME + CRLF
                + "Content-Type: " + response.contentType() + CRLF
                + "Content-Length: " + response.body().length + CRLF
                + "Connection: close" + CRLF
                + CRLF;

        out.write(head.getBytes(StandardCharsets.ISO_8859_1));
        if (includeBody) {
            out.write(response.body());
        }
        out.flush();
    }
}
