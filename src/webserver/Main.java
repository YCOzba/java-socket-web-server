package webserver;

import webserver.handler.BiographyPageHandler;
import webserver.handler.NotFoundHandler;
import webserver.http.HttpRequestParser;
import webserver.http.HttpResponseWriter;
import webserver.model.InMemoryProfileRepository;
import webserver.model.ProfileRepository;
import webserver.routing.RequestHandler;
import webserver.routing.Router;
import webserver.server.WebServer;
import webserver.view.BiographyPageRenderer;
import webserver.view.PageRenderer;

/**
 * Uygulamanın giriş noktası (Composition Root).
 *
 * Tüm nesneler burada oluşturulur ve birbirine bağlanır. Diğer sınıflar
 * somut sınıfları değil, arayüzleri (interface) kullanır. Böylece bağımlılıklar
 * tek bir yerden yönetilir (Dependency Inversion Principle).
 */
public final class Main {

    /** Ödevde istenen port numarası. */
    private static final int PORT = 1989;

    private Main() {
    }

    public static void main(String[] args) {
        ProfileRepository profileRepository = new InMemoryProfileRepository();
        PageRenderer pageRenderer = new BiographyPageRenderer();

        RequestHandler biographyPage = new BiographyPageHandler(profileRepository, pageRenderer);

        Router router = new Router(new NotFoundHandler());
        router.register("/", biographyPage);
        router.register("/index.html", biographyPage);

        WebServer server = new WebServer(PORT, router, new HttpRequestParser(), new HttpResponseWriter());
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        server.start();
    }
}
