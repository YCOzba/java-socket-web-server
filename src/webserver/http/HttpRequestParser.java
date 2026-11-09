package webserver.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Soketten okunan ham metni {@link HttpRequest} nesnesine dönüştürür.
 *
 * Bir HTTP isteği şu yapıdadır:
 * <pre>
 * GET / HTTP/1.1            &lt;- istek satırı
 * Host: localhost:1989      &lt;- başlıklar
 * User-Agent: ...
 *                           &lt;- boş satır (başlıkların sonu)
 * </pre>
 */
public final class HttpRequestParser {

    /**
     * @throws IllegalArgumentException istek satırı HTTP formatına uymuyorsa
     * @throws IOException              soketten okuma başarısız olursa
     */
    public HttpRequest parse(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.ISO_8859_1));

        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.isBlank()) {
            throw new IllegalArgumentException("Boş istek");
        }

        String[] parts = requestLine.trim().split("\\s+");
        if (parts.length != 3 || !parts[2].startsWith("HTTP/")) {
            throw new IllegalArgumentException("Geçersiz istek satırı: " + requestLine);
        }

        Map<String, String> headers = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            int colon = line.indexOf(':');
            if (colon > 0) {
                headers.put(line.substring(0, colon).trim().toLowerCase(), line.substring(colon + 1).trim());
            }
        }

        return new HttpRequest(parts[0].toUpperCase(), stripQuery(parts[1]), parts[2], headers);
    }

    private static String stripQuery(String target) {
        int q = target.indexOf('?');
        return q >= 0 ? target.substring(0, q) : target;
    }
}
