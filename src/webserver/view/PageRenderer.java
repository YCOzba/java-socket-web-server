package webserver.view;

import webserver.model.StudentProfile;

/**
 * Öğrenci bilgisinden HTML sayfası üreten nesnelerin sözleşmesi.
 *
 * Farklı bir tasarım istenirse bu arayüzü uygulayan yeni bir sınıf yazılır;
 * sunucu ve işleyici kodu değişmez.
 */
public interface PageRenderer {

    String render(StudentProfile profile);
}
