/**
 * Ad Soyad: [Şevval Bulut]
 * Öğrenci No: [240541125]
 * Proje: [Sinema Bileti]
 * Tarih: [23.11 2025]
 */
import java.util.Scanner;

public class SinemaBileti {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("----- SİNEMA BİLET SİSTEMİ -----");

        System.out.println("Gün seçiniz (1=Pazartesi, 2=Salı, 3=Carsamba, 4=Persembe, 5=Cuma, 6=Cumartesi, 7=Pazar): ");
        int gun = in.nextInt();

        System.out.println("Saat giriniz (0-23 arası): ");
        int saat = in.nextInt();

        System.out.println("Yaşınızı giriniz: ");
        int yas = in.nextInt();

        System.out.println("Meslek seçiniz (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = in.nextInt();

        System.out.println("Film türü seçiniz (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuru = in.nextInt();

        double finalFiyat = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);
        generateTicketInfo(gun, saat, yas, meslek, filmTuru, finalFiyat);
    }


    // ---------------------------------------------------------
    // 1) Hafta sonu kontrolü
    public static boolean isWeekend(int gun) {
        return gun == 6 || gun == 7;
    }

    // ---------------------------------------------------------
    // 2) Matine kontrolü (12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // ---------------------------------------------------------
    // 3) Temel fiyat hesaplama
    public static double calculateBasePrice(int gun, int saat) {
        boolean haftaSonu = isWeekend(gun);
        boolean matine = isMatinee(saat);

        if (!haftaSonu && matine) return 45;    
        if (!haftaSonu && !matine) return 65;  
        if (haftaSonu && matine) return 55;     
        return 85;                      
    }

    // ---------------------------------------------------------
    // 4) İndirim hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun) {
        double indirim = 0;

        // Yaş indirimleri
        if (yas >= 65) {
            indirim = 0.30;
        } else if (yas < 12) {
            indirim = 0.25;
        }

        // Meslek indirimleri
        switch (meslek) {
            case 1:  // Öğrenci
                if (gun >= 1 && gun <= 4) {
                    indirim = Math.max(indirim, 0.20);
                } else {
                    indirim = Math.max(indirim, 0.15);
                }
                break;

            case 2:  
                if (gun == 3) {  
                    indirim = Math.max(indirim, 0.35);
                }
                break;

            default:
                break;
        }

        return indirim;
    }

    // ---------------------------------------------------------
    // 5) Film formatı fiyat ekstraları
    public static double getFormatExtra(int tur) {
        switch (tur) {
            case 1: return 0;   
            case 2: return 25;  
            case 3: return 35;  
            case 4: return 50;  
            default: return 0;
        }
    }

    // ---------------------------------------------------------
    // 6) Final fiyat hesaplama
    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        double base = calculateBasePrice(gun, saat);
        double extra = getFormatExtra(filmTuru);
        double indirim = calculateDiscount(yas, meslek, gun);

        double fiyat = base + extra;
        fiyat -= fiyat * indirim;

        return fiyat;
    }

    // ---------------------------------------------------------
    // 7) Bilet bilgisi yazdırma
    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru, double fiyat) {
        System.out.println("\n====== BİLET ÖZETİ ======");

        // Gün
        String gunAdi = "";
        switch (gun) {
            case 1: gunAdi = "Pazartesi"; break;
            case 2: gunAdi = "Salı"; break;
            case 3: gunAdi = "Çarşamba"; break;
            case 4: gunAdi = "Perşembe"; break;
            case 5: gunAdi = "Cuma"; break;
            case 6: gunAdi = "Cumartesi"; break;
            case 7: gunAdi = "Pazar"; break;
        }

        // Meslek
        String meslekAdi = "";
        switch (meslek) {
            case 1: meslekAdi = "Öğrenci"; break;
            case 2: meslekAdi = "Öğretmen"; break;
            case 3: meslekAdi = "Diğer"; break;
        }

        // Film Türü
        String format = "";
        switch (filmTuru) {
            case 1: format = "2D"; break;
            case 2: format = "3D"; break;
            case 3: format = "IMAX"; break;
            case 4: format = "4DX"; break;
        }

        System.out.println("Gün: " + gunAdi);
        System.out.println("Saat: " + saat + ":00");
        System.out.println("Yaş: " + yas);
        System.out.println("Meslek: " + meslekAdi);
        System.out.println("Film Türü: " + format);

        System.out.printf("Ödenecek Tutar: %.2f TL\n", fiyat);
        System.out.println("==========================");
    }
}

