package Connection;

import java.sql.*;
import java.util.Scanner;

public class Arayuz {
    public static void main(String[] args) {
        try {

            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Users",
                    "postgres", "123456");
            if (conn == null) {
                System.out.println("Bağlantı girişi başarısız!");
            }
            else{
                System.out.println("Bağlantı girişi başarılı!");
            }

            Islemler islemBirimi;
            Scanner klavye = new Scanner(System.in);

            while(true){
                System.out.println("Kullanıcı Adınızı Giriniz: ");
                String kullaniciAdi = klavye.nextLine();

                String sql = "SELECT *  FROM \"users\" WHERE username='" + kullaniciAdi + "'";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);


                conn.close();

                if (rs.next() == false){
                    System.out.println("Böyle bir kullanıcı bulunamadı. Lütfen tekrar deneyiniz.");
                }else{
                    System.out.println("Şifrenizi Giriniz:");
                    String sifre = klavye.nextLine();
                    String kullaniciSifre = rs.getString("password");
                    if (sifre.equals(kullaniciSifre)){
                        System.out.println("Giriş başarılı.");

                        islemBirimi = Islemler.getInstance(new Kullanici(kullaniciAdi, sifre));
                        break;
                    }
                    System.out.println("Giriş işlemi başarısız. Lütfen tekrar deneyiniz.");
                }
            }

            while (true){
                System.out.println("***İşlemler***");
                System.out.println("1-Sıcaklık Oku");
                System.out.println("2-Soğutucu Aç");
                System.out.println("3-Soğutucu Kapat");
                System.out.println("4-Çıkış");

                int menu = klavye.nextInt();
                switch (menu){
                    case 1:
                       System.out.println("Ortam Sıcaklığı: " + islemBirimi.sicaklikGonder());
                        break;
                    case 2:
                        islemBirimi.sogutucuAc();
                        break;
                    case 3:
                        islemBirimi.sogutucuKapat();
                        break;
                }
                if (menu == 4) break;
            }

        } catch (SQLException ex) {
            System.out.println("error - "+ex.getMessage());
        }
    }
}
