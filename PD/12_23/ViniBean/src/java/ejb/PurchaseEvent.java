package ejb;

import javax.enterprise.event.Observes;

public class PurchaseEvent {
    public void onPurchaseUpdate(@Observes @Purchase Vino vino) {
        System.out.println("Il vino [" + vino.getNominativo() + "] deve essere riassortito");
    }    
}
