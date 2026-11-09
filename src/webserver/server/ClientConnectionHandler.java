package webserver.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import webserver.http.HttpRequest;
import webserver.http.HttpRequestParser;
import webserver.http.HttpResponse;
import webserver.http.HttpResponseWriter;
import webserver.http.HttpStatus;
import webserver.routing.Router;

/**
 * Tek bir istemci bağlantısını baştan sona işler:
 * isteği okur, uygun işleyiciye yönlendirir, cevabı yazar ve soketi kapatır.
 *
 * {@link Runnable} arayüzünü uyguladığı için bir iş parçacığı havuzunda
 * çalıştırılabilir.
 */
final class ClientConnectionHandler implements Runnable {

    private static final int READ_TIMEOUT_MS = 5_000;

    private final Socket client;
    private final Router router;
    private final HttpRequestParser requestParser;
    private final HttpResponseWriter responseWriter;

    ClientConnectionHandler(Socket client, Router router,
                            HttpRequestParser requestParser, HttpResponseWriter responseWriter) {
        this.client = client;
        this.router = router;
        this.requestParser = requestParser;
        this.responseWriter = responseWriter;
    }

    @Override
    public void run() {
        String clientAddress = client.getInetAddress().getHostAddress() + ":" + client.getPort();
        try (Socket socket = client;
             InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream()) {

            socket.setSoTimeout(READ_TIMEOUT_MS);

            HttpResponse response;
            HttpRequest request = null;
            try {
                request = requestParser.parse(in);
                response = router.route(request);
            } catch (IllegalArgumentException e) {
                response = HttpResponse.text(HttpStatus.BAD_REQUEST, "400 - Hatalı istek");
            }

            boolean includeBody = request == null || !"HEAD".equals(request.method());
            responseWriter.write(response, out, includeBody);

            System.out.printf("[%s] %s -> %d%n", clientAddress,
                    request == null ? "(geçersiz istek)" : request.method() + " " + request.path(),
                    response.status().code());
        } catch (IOException e) {
            System.err.printf("[%s] Bağlantı hatası: %s%n", clientAddress, e.getMessage());
        }
    }
}
