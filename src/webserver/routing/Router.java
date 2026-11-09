package webserver.routing;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;

/**
 * İstenen yola (path) göre isteği ilgili {@link RequestHandler} nesnesine yönlendirir.
 * Kayıtlı olmayan yollar için dışarıdan verilen "bulunamadı" işleyicisini kullanır.
 */
public final class Router {

    private final Map<String, RequestHandler> routes = new ConcurrentHashMap<>();
    private final RequestHandler notFoundHandler;

    public Router(RequestHandler notFoundHandler) {
        this.notFoundHandler = notFoundHandler;
    }

    public void register(String path, RequestHandler handler) {
        routes.put(path, handler);
    }

    public HttpResponse route(HttpRequest request) {
        if (!"GET".equals(request.method()) && !"HEAD".equals(request.method())) {
            return HttpResponse.text(HttpStatus.METHOD_NOT_ALLOWED,
                    "405 - Bu sunucu yalnızca GET ve HEAD isteklerini destekler.");
        }
        return routes.getOrDefault(request.path(), notFoundHandler).handle(request);
    }
}
