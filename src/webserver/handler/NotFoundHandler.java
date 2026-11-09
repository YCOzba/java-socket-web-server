package webserver.handler;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.routing.RequestHandler;

/**
 * Kayıtlı olmayan bir yol istendiğinde 404 sayfası döner.
 */
public final class NotFoundHandler implements RequestHandler {

    @Override
    public HttpResponse handle(HttpRequest request) {
        String html = """
                <!DOCTYPE html>
                <html lang="tr">
                <head><meta charset="UTF-8"><title>404 - Sayfa Bulunamadı</title></head>
                <body style="font-family: Arial, sans-serif; text-align: center; padding-top: 60px; color: #7f1d1d;">
                  <h1>404</h1>
                  <p>Aradığınız sayfa bulunamadı.</p>
                  <a href="/">Ana sayfaya dön</a>
                </body>
                </html>
                """;
        return HttpResponse.html(HttpStatus.NOT_FOUND, html);
    }
}
