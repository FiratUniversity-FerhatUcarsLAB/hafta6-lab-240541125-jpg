/**
 * Ad Soyad: [Şevval Bulut]
 * Öğrenci No: [240541125]
 * Proje: [Proje1_NotSistemi.java]
 * Tarih: [15.11.2025]
 */
import java.util.*;
public class Main { public static void main(String[] args) {
    Scanner in = new Scanner(System.in); 
    int vizeNot, finalNot, odevNot;
    System.out.println("Vize notunu giriniz");
    vizeNot = in.nextInt();
    System.out.println("Final notunu giriniz");
    finalNot = in.nextInt();
    System.out.println("Odev notunu giriniz");
    odevNot = in.nextInt();
    System.out.println("=== OGRENCI NOT RAPORU ===");
    System.out.println("Vize: "+vizeNot); 
    System.out.println("Final: "+finalNot);
    System.out.println("Odev: "+odevNot); 
    double ort = calculateAverage(vizeNot, finalNot, odevNot); System.out.println("------------------------------"); 
    System.out.println("Ortalama: "+ort); 
    System.out.println("Harf Notu: "+getLetterGrade(ort)); 
    System.out.println("Durum: "+((isPassingGrade(ort)?"Gecti":"Kaldı")));
    System.out.println("Onur Listesi: "+((isHonorList(ort, vizeNot, finalNot,odevNot)?"Evet":"Hayır"))); 
    System.out.println("Butunleme: "+((hasRetakeRight(ort)?"Var":"Yok"))); }
    public static double calculateAverage(int vizeNot, int finalNot, int odevNot){
        return vizeNot * 0.3 + finalNot * 0.4 + odevNot * 0.3; 
         }
         public static boolean isPassingGrade( double ort){ 
             if (ort>=50){
                 return true;
             }else{ 
                 return false; 
                 } 
             
         }
             public static String getLetterGrade(double ort){ 
                 String harfNot ="" ; if(ort>=90 && ort<100){
                     harfNot="A";
                 }else if (ort>=80 && ort<=89) {
                     harfNot="B";
                 }else if (ort>=70 && ort<=79){
                     harfNot="C";
                }else if (ort>=60 && ort<=69){
                    harfNot="D";
                }else {
                    harfNot="F";
                }
                return harfNot;
                 }
                 public static boolean isHonorList(double ort,int vizeNot,int finalNot, int odevNot){
                     if(ort>=85 && vizeNot>=70 && finalNot>=70 && odevNot>=70 ){
                         return true;
                     }else{
                         return false;
                     }
                 }
                 public static boolean hasRetakeRight(double ort){
                     return(ort>=40 && ort<50)? true: false;
                 }
}

