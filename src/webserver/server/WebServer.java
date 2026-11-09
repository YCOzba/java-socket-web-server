package webserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import webserver.http.HttpRequestParser;
import webserver.http.HttpResponseWriter;
import webserver.routing.Router;

/**
 * Belirtilen portu dinleyen ve gelen her bağlantıyı ayrı bir iş parçacığında
 * (thread) işleyen TCP sunucusu.
 *
 * Tek sorumluluğu: bağlantıları kabul etmek ve işlemeleri için
 * {@link ClientConnectionHandler} nesnesine devretmek (Single Responsibility).
 */
public final class WebServer {

    private static final int THREAD_POOL_SIZE = 10;

    private final int port;
    private final Router router;
    private final HttpRequestParser requestParser;
    private final HttpResponseWriter responseWriter;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    private volatile boolean running;
    private ServerSocket serverSocket;

    public WebServer(int port, Router router, HttpRequestParser requestParser, HttpResponseWriter responseWriter) {
        this.port = port;
        this.router = router;
        this.requestParser = requestParser;
        this.responseWriter = responseWriter;
    }

    /** Sunucuyu başlatır ve bağlantı beklemeye başlar (bloklayan çağrı). */
    public void start() {
        try (ServerSocket socket = new ServerSocket(port)) {
            this.serverSocket = socket;
            this.running = true;
            System.out.println("Sunucu çalışıyor: http://localhost:" + port);
            System.out.println("Durdurmak için Ctrl+C tuşlarına basın.");

            while (running) {
                Socket client = socket.accept();
                threadPool.submit(new ClientConnectionHandler(client, router, requestParser, responseWriter));
            }
        } catch (SocketException e) {
            if (running) {
                System.err.println("Soket hatası: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Sunucu " + port + " portunda başlatılamadı: " + e.getMessage());
        } finally {
            stop();
        }
    }

    /** Sunucuyu ve iş parçacığı havuzunu kapatır. */
    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException ignored) {
            // Kapatma sırasında oluşan hata önemsizdir.
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Sunucu durduruldu.");
    }
}
