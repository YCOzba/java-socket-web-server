package webserver.view;

import java.util.stream.Collectors;

import webserver.model.StudentProfile;

/**
 * Öğrenci bilgisini renkli ve biçimlendirilmiş bir biyografi sayfasına dönüştürür.
 *
 * Sayfada: H1 ile Ad Soyad, H2 ile öğrenci numarası, CSS ile renkler ve
 * farklı yazı tipleri (serif, sans-serif, monospace, cursive) kullanılır.
 */
public final class BiographyPageRenderer implements PageRenderer {

    @Override
    public String render(StudentProfile profile) {
        String paragraphs = profile.biography().stream()
                .map(p -> "        <p>" + escape(p) + "</p>")
                .collect(Collectors.joining("\n"));

        String interests = profile.interests().stream()
                .map(i -> "          <li>" + escape(i) + "</li>")
                .collect(Collectors.joining("\n"));

        return """
                <!DOCTYPE html>
                <html lang="tr">
                <head>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <title>%1$s - Biyografi</title>
                  <style>
                    body {
                      margin: 0;
                      padding: 40px 16px;
                      min-height: 100vh;
                      box-sizing: border-box;
                      background: linear-gradient(135deg, #1e3a8a 0%%, #7c3aed 100%%) fixed;
                      font-family: Georgia, 'Times New Roman', serif;
                      color: #1f2937;
                    }
                    .card {
                      max-width: 760px;
                      margin: 0 auto;
                      background: #ffffff;
                      border-radius: 16px;
                      box-shadow: 0 20px 50px rgba(0, 0, 0, 0.35);
                      overflow: hidden;
                    }
                    header {
                      background: #0f172a;
                      color: #f8fafc;
                      text-align: center;
                      padding: 36px 24px;
                    }
                    h1 {
                      margin: 0;
                      font-family: 'Trebuchet MS', Verdana, sans-serif;
                      font-size: 2.6em;
                      color: #facc15;
                      letter-spacing: 1px;
                    }
                    h2 {
                      margin: 10px 0 4px;
                      font-family: 'Courier New', Courier, monospace;
                      color: #38bdf8;
                    }
                    .department {
                      font-style: italic;
                      color: #cbd5e1;
                    }
                    main { padding: 28px 36px; }
                    h3 {
                      font-family: 'Trebuchet MS', Verdana, sans-serif;
                      color: #7c3aed;
                      border-bottom: 3px solid #facc15;
                      padding-bottom: 6px;
                    }
                    p {
                      font-size: 1.08em;
                      line-height: 1.7;
                      text-align: justify;
                    }
                    p:first-of-type::first-letter {
                      font-size: 2.4em;
                      float: left;
                      line-height: 1;
                      margin-right: 6px;
                      color: #dc2626;
                      font-weight: bold;
                    }
                    ul { padding-left: 22px; }
                    li {
                      margin: 6px 0;
                      color: #065f46;
                      font-family: Verdana, Arial, sans-serif;
                    }
                    .highlight { color: #dc2626; font-weight: bold; }
                    footer {
                      background: #f1f5f9;
                      text-align: center;
                      padding: 16px;
                      font-family: 'Comic Sans MS', 'Segoe Print', cursive;
                      color: #475569;
                      font-size: 0.95em;
                    }
                  </style>
                </head>
                <body>
                  <div class="card">
                    <header>
                      <h1>%1$s</h1>
                      <h2>%2$s</h2>
                      <div class="department">%3$s</div>
                    </header>
                    <main>
                      <section>
                        <h3>Hakkımda</h3>
                %4$s
                      </section>
                      <section>
                        <h3>İlgi Alanlarım</h3>
                        <ul>
                %5$s
                        </ul>
                      </section>
                    </main>
                    <footer>
                      Bu sayfa <span class="highlight">Java Socket</span> ile yazılmış bir web sunucusu
                      tarafından <b>1989</b> numaralı porttan sunulmaktadır.
                    </footer>
                  </div>
                </body>
                </html>
                """.formatted(escape(profile.fullName()), escape(profile.studentNumber()),
                escape(profile.department()), paragraphs, interests);
    }

    /** HTML'de özel anlamı olan karakterleri güvenli hale getirir. */
    private static String escape(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
