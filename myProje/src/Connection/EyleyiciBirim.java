package Connection;

import java.util.ArrayList;
import java.util.List;

public class EyleyiciBirim implements IObservable{
    private static EyleyiciBirim instance;
    private boolean sogutucuDurumu;

    private EyleyiciBirim(){
        sogutucuDurumu = false;
    }


    public static synchronized EyleyiciBirim getInstance() {
        if (instance == null)
            instance = new EyleyiciBirim();
        return instance;
    }


    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void addObserve(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override

    public void notifyObserver() {
        for (IObserver observer :
                observers) {
            observer.update(sogutucuDurumu);
        }
    }

    public void sogutucuAc(){
        if (!sogutucuDurumu){
            sogutucuDurumu = true;
            notifyObserver();
        } else{
            System.out.println("Soğutucu zaten açık, tekrar açamazsınız!");
        }
    }

    public void sogutucuKapat(){
        if (sogutucuDurumu){
            sogutucuDurumu = false;
            notifyObserver();
        } else{
            System.out.println("Soğutucu zaten kapalı, tekrar kapatamazsınız!");
        }
    }
}
