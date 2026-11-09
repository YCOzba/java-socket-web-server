package webserver.model;

import java.util.List;

/**
 * Sayfada gösterilecek öğrenci bilgilerini taşıyan değişmez veri nesnesi.
 *
 * @param fullName      Ad Soyad (H1 başlığında gösterilir)
 * @param studentNumber Öğrenci numarası (H2 başlığında gösterilir)
 * @param department    Bölüm / üniversite bilgisi
 * @param biography     Biyografi paragrafları
 * @param interests     İlgi alanları listesi
 */
public record StudentProfile(String fullName,
                             String studentNumber,
                             String department,
                             List<String> biography,
                             List<String> interests) {

    public StudentProfile {
        biography = List.copyOf(biography);
        interests = List.copyOf(interests);
    }
}
