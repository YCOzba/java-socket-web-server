package webserver.model;

/**
 * Öğrenci bilgisinin nereden geldiğini soyutlayan arayüz.
 *
 * Bilgi bugün kod içinde tutuluyor; ileride dosyadan veya veritabanından
 * okunmak istenirse yalnızca yeni bir uygulama (implementation) yazmak yeterlidir.
 */
public interface ProfileRepository {

    StudentProfile getProfile();
}
