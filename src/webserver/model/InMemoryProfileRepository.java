package webserver.model;

import java.util.List;

/**
 * Öğrenci bilgisini bellekte (kod içinde) tutan {@link ProfileRepository} uygulaması.
 *
 * >>> Kendi bilgilerinizi yalnızca bu dosyada değiştirmeniz yeterlidir. <<<
 */
public final class InMemoryProfileRepository implements ProfileRepository {

    private final StudentProfile profile = new StudentProfile(
            "Yağız Çınar Özbakır",
            "Öğrenci No: 1250505057",
            "Kırklareli Üniversitesi · Yazılım Mühendisliği",
            List.of(
                    "Merhaba! Ben Yağız Çınar. Kırklareli Üniversitesi'nde yazılım mühendisliği "
                            + "okuyorum ve bilgisayar biliminin temellerine büyük bir merak duyuyorum.",
                    "Programlamaya liseli yıllarımda küçük projelerle başladım. Zamanla Go diliyle "
                            + "tanıştım. Beni en çok işin soyut ve teorik yönü çekiyor: bir problemin "
                            + "nasıl modellendiği, bir algoritmanın neden doğru çalıştığı ve iyi bir "
                            + "tasarımın arkasındaki fikirler.",
                    "Bu ödevde, hiçbir hazır kütüphane kullanmadan yalnızca Java'nın soket "
                            + "sınıflarıyla çalışan bir web sunucusu yazdım. Şu an okuduğunuz sayfa da "
                            + "bu sunucu tarafından 1989 numaralı porttan gönderiliyor."),
            List.of(
                    "Algoritmalar ve veri yapıları",
                    "Programlama dillerinin teorisi",
                    "Nesne yönelimli tasarım ve SOLID prensipleri",
                    "Go ile programlama"));

    @Override
    public StudentProfile getProfile() {
        return profile;
    }
}
