package webserver.http;

import java.util.Map;

/**
 * Tarayıcıdan gelen HTTP isteğini temsil eden değişmez (immutable) veri nesnesi.
 *
 * Örnek istek satırı: {@code GET /index.html HTTP/1.1}
 *
 * @param method  HTTP metodu (GET, POST, HEAD ...)
 * @param path    İstenen kaynağın yolu (ör. "/")
 * @param version HTTP sürümü (ör. "HTTP/1.1")
 * @param headers Başlık alanları (anahtarlar küçük harfe çevrilmiştir)
 */
public record HttpRequest(String method, String path, String version, Map<String, String> headers) {

    public HttpRequest {
        headers = Map.copyOf(headers);
    }

    public String header(String name) {
        return headers.get(name.toLowerCase());
    }
}
