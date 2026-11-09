package webserver.handler;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.model.ProfileRepository;
import webserver.model.StudentProfile;
import webserver.routing.RequestHandler;
import webserver.view.PageRenderer;

/**
 * Ana sayfa isteğini karşılar: öğrenci bilgisini depodan alır,
 * HTML'e dönüştürür ve cevap olarak döner.
 *
 * Somut sınıflara değil, {@link ProfileRepository} ve {@link PageRenderer}
 * arayüzlerine bağımlıdır (Dependency Inversion Principle).
 */
public final class BiographyPageHandler implements RequestHandler {

    private final ProfileRepository profileRepository;
    private final PageRenderer pageRenderer;

    public BiographyPageHandler(ProfileRepository profileRepository, PageRenderer pageRenderer) {
        this.profileRepository = profileRepository;
        this.pageRenderer = pageRenderer;
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        StudentProfile profile = profileRepository.getProfile();
        return HttpResponse.html(HttpStatus.OK, pageRenderer.render(profile));
    }
}
