package webserver.model;

import java.util.List;

/**
 * Öğrenci bilgisini bellekte (kod içinde) tutan {@link ProfileRepository} uygulaması.
 *
 * >>> Kendi bilgilerinizi yalnızca bu dosyada değiştirmeniz yeterlidir. <<<
 */
public final class InMemoryProfileRepository implements ProfileRepository {

    private final StudentProfile profile = new StudentProfile(
            "Çınar Özbakır",
            "Öğrenci No: 1250505057",
            "Kırklareli Üniversitesi · Bilgisayar Mühendisliği",
            List.of(
                    "Merhaba! Ben Çınar. Bilgisayar mühendisliği eğitimime devam ediyorum ve "
                            + "yazılım geliştirmenin her aşamasına büyük bir merak duyuyorum.",
                    "Programlamaya liseli yıllarımda küçük projelerle başladım. Zamanla Java, "
                            + "Python ve JavaScript gibi dillerle masaüstü, web ve mobil uygulamalar geliştirdim.",
                    "Bu ödevde, hiçbir hazır kütüphane kullanmadan yalnızca Java'nın soket "
                            + "sınıflarıyla çalışan bir web sunucusu yazdım. Şu an okuduğunuz sayfa da "
                            + "bu sunucu tarafından 1989 numaralı porttan gönderiliyor."),
            List.of(
                    "Nesne yönelimli programlama ve temiz kod",
                    "Web ve mobil uygulama geliştirme",
                    "Bilgisayar ağları",
                    "Yapay zekâ uygulamaları"));

    @Override
    public StudentProfile getProfile() {
        return profile;
    }
}
