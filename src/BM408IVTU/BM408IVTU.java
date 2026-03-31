package BM408IVTU;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class BM408IVTU {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, UnsupportedEncodingException {
        
        // Alıştırma 2 için çıktı dosyası oluşturuyoruz
        PrintWriter writer = new PrintWriter("BM408_Alistirma2_Join_Raporu.txt", "UTF-8");
        
        // Veritabanı bağlantımızı kuruyoruz
        Connection baglanti = DBDataAccess.dbBaglanti();
        Statement ifade = baglanti.createStatement();
        
        System.out.println("=== Alistirma 2: Personel ve Departman Eslesme Raporu (Dolu Liste) ===");
        System.out.println("---------------------------------------------------------");
        
        /* 
         * ÖNEMLİ NOT: Personel tablosunda ID'ler 8, 9, 10 diye gidiyor.
         * Departman tablosunda ise ID'ler 1, 2, 3 diye gidiyor.
         * Bu yüzden p.id = d.id + 7 yaparak personelleri departmanlarla eşleştiriyoruz.
         */
        String SQLifade = "SELECT p.ad, p.soyad, d.ad AS departman_adi, d.odano " +
                          "FROM personel p, departman d " +
                          "WHERE p.id = (d.id + 7)"; 
                          
        ResultSet sonucKumesi = ifade.executeQuery(SQLifade);
        
        // Başlıkları yazdıralım
        String baslik = "AD SOYAD\t\tDEPARTMAN\t\tODA NO";
        System.out.println(baslik);
        writer.println(baslik);
        writer.println("---------------------------------------------------------");
        
        int sayac = 0;
        while (sonucKumesi.next()) {
            String satir = sonucKumesi.getString("ad") + " " + 
                           sonucKumesi.getString("soyad") + "\t\t" + 
                           sonucKumesi.getString("departman_adi") + "\t\t" + 
                           sonucKumesi.getString("odano");
            
            System.out.println(satir);
            writer.println(satir);
            sayac++;
        }
        
        writer.close();
        
        System.out.println("---------------------------------------------------------");
        System.out.println("İşlem tamam! Toplam " + sayac + " eşleşme bulundu.");
        System.out.println("'BM408_Alistirma2_Join_Raporu.txt' dosyasını kontrol edebilirsin.");
    }
}