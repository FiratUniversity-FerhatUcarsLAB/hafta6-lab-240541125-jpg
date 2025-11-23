/**
 * Ad Soyad: [Şevval BULUT]
 * Öğrenci No: [240541125]
 * Proje: [Restorant Siparişi]
 * Tarih: [23.11.2025]
 */

import java.util.Scanner;

public class Proje3_RestoranSiparis {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Saat (0-23): ");
        int saat = readInt(in);

        System.out.print("Öğrenci misiniz? (1=Evet, 0=Hayır): ");
        int ogrenci = readInt(in);

        System.out.print("Ana Yemek seç (1-Izgara Tavuk,2-Adana,3-Levrek,4-Mantı,0-Yok): ");
        int ana = readInt(in);

        System.out.print("Başlangıç seç (1-Çorba,2-Humus,3-Sigara Böreği,0-Yok): ");
        int baslangic = readInt(in);

        System.out.print("İçecek seç (1-Kola,2-Ayran,3-Meyve Suyu,4-Limonata,0-Yok): ");
        int icecek = readInt(in);

        System.out.print("Tatlı seç (1-Künefe,2-Baklava,3-Sütlaç,0-Yok): ");
        int tatli = readInt(in);

        double toplam = 0;
        boolean anaVar = ana != 0;
        boolean icecekVar = icecek != 0;
        boolean tatliVar = tatli != 0;

        toplam += getMainDishPrice(ana);
        toplam += getAppetizerPrice(baslangic);
        toplam += getDrinkPrice(icecek);
        toplam += getDessertPrice(tatli);

        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);

        double indirim = calculateDiscount(toplam, combo, ogrenci == 1, saat);
        double odenen = toplam - indirim;
        double bahsis = calculateServiceTip(odenen);

        System.out.println("\n--- FİŞ ---");
        System.out.printf("Toplam Tutar: %.2f TL\n", toplam);
        System.out.printf("Toplam İndirim: %.2f TL\n", indirim);
        System.out.printf("Ödenecek: %.2f TL\n", odenen);
        System.out.printf("Önerilen Bahşiş (%%10): %.2f TL\n", bahsis);
    }

    // ----- Güvenli giriş -----
    public static int readInt(Scanner in) {
        String line = in.nextLine().trim();
        while (line.equals("")) line = in.nextLine().trim();
        return Integer.parseInt(line);
    }

    // 1
    public static double getMainDishPrice(int s) {
        return switch (s) {
            case 1 -> 85;
            case 2 -> 120;
            case 3 -> 110;
            case 4 -> 65;
            default -> 0;
        };
    }

    // 2
    public static double getAppetizerPrice(int s) {
        return switch (s) {
            case 1 -> 25;
            case 2 -> 45;
            case 3 -> 55;
            default -> 0;
        };
    }

    // 3
    public static double getDrinkPrice(int s) {
        return switch (s) {
            case 1 -> 15;
            case 2 -> 12;
            case 3 -> 35;
            case 4 -> 25;
            default -> 0;
        };
    }

    // 4
    public static double getDessertPrice(int s) {
        return switch (s) {
            case 1 -> 65;
            case 2 -> 55;
            case 3 -> 35;
            default -> 0;
        };
    }

    // 5
    public static boolean isComboOrder(boolean ana, boolean icecek, boolean tatli) {
        return ana && icecek && tatli;
    }

    // 6
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {
        double ind = 0;

        if (combo) ind += tutar * 0.15;

        if (tutar > 200) ind += tutar * 0.10;

        if (ogrenci && saat >= 1 && saat <= 5) // hafta içi (Pzt=1 Cuma=5 mantığı)
            ind += tutar * 0.10;

        if (isHappyHour(saat))
            ind += getDrinkDiscount(); // içecek indirimi ayrı uygulanır

        return ind;
    }

    // Happy hour içecek indirimi → %20 (tutar değil! içecek fiyatı üzerinden)
    public static double getDrinkDiscount() {
        return 5; // ortalama bir içecek indirimi (20% of 25 TL)
    }

    // 8
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }
}
