package webserver.http;

import java.nio.charset.StandardCharsets;

/**
 * Sunucunun tarayıcıya göndereceği HTTP cevabı.
 *
 * @param status      Durum kodu (200, 404 ...)
 * @param contentType İçerik türü (ör. "text/html; charset=UTF-8")
 * @param body        Cevap gövdesinin bayt dizisi
 */
public record HttpResponse(HttpStatus status, String contentType, byte[] body) {

    /** UTF-8 kodlamalı bir HTML cevabı oluşturur. */
    public static HttpResponse html(HttpStatus status, String html) {
        return new HttpResponse(status, "text/html; charset=UTF-8", html.getBytes(StandardCharsets.UTF_8));
    }

    /** UTF-8 kodlamalı düz metin cevabı oluşturur. */
    public static HttpResponse text(HttpStatus status, String text) {
        return new HttpResponse(status, "text/plain; charset=UTF-8", text.getBytes(StandardCharsets.UTF_8));
    }
}
