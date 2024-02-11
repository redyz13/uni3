package ejb;

import javax.enterprise.event.Observes;


public class BottleEvent {
    public void onBottleNumberUpdate(@Observes @Bottle Vino vino) {
        System.out.println("Aggiornamento effettuato [" + vino + "]");
    }    
}
