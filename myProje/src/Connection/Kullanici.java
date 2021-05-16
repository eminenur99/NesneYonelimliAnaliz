package Connection;

import java.sql.SQLOutput;

public class Kullanici implements IObserver {
    String username;
    String password;

    public Kullanici(String username, String password){
        this.username = username;
        this.password = password;
    }


    @Override
    public void update(boolean sogutucuDurumu) {
        System.out.println("Soğutucu " + (sogutucuDurumu ? "Açıldı" : "Kapatıldı") );
    }
}
