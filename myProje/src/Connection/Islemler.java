package Connection;

public class Islemler {
    private static Islemler instance;
    private final SicaklikAlgilayici sensor;
    private final EyleyiciBirim sogutucu;


    private Islemler(){
        sensor = SicaklikAlgilayici.getInstance();
        sogutucu = EyleyiciBirim.getInstance();
    }


    public static Islemler getInstance(IObserver observer) {
        if (instance == null){
            instance = new Islemler();
            instance.sogutucu.addObserve(observer);
        }
        return instance;
    }

   public int sicaklikGonder(){
        return sensor.sicaklikOku();
    }

    public void sogutucuAc(){
        this.sogutucu.sogutucuAc();
    }

    public void sogutucuKapat(){
        this.sogutucu.sogutucuKapat();
    }
}
