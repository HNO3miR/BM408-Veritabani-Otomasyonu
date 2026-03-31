package BM408IVTU;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDataAccess {
    public static Connection dbBaglanti() throws ClassNotFoundException, SQLException {
        // SQL Server sürücüsünü yüklüyoruz [cite: 586]
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        // Bağlantı adresi: Burada "sa" kullanıcısı ve belirlediğin şifreyi (örneğin "1") kullanıyoruz [cite: 587, 588]
     // Eski satırı silip yerine bunu yapıştır:
        Connection dbBaglanti = DriverManager.getConnection("jdbc:sqlserver://LOCALHOST;"
                + "databaseName=BM408IVTU;encrypt=true;trustServerCertificate=true;", "sa", "1907");
        
        return dbBaglanti;
    }
}