package webserver.routing;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

/**
 * Bir isteği işleyip cevap üreten her sınıfın uyması gereken sözleşme.
 *
 * Yeni bir sayfa eklemek için mevcut kodu değiştirmek gerekmez; bu arayüzü
 * uygulayan yeni bir sınıf yazıp {@link Router}'a kaydetmek yeterlidir
 * (Open/Closed Principle).
 */
@FunctionalInterface
public interface RequestHandler {

    HttpResponse handle(HttpRequest request);
}
