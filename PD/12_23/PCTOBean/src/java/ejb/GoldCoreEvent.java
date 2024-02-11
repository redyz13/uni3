package ejb;

import javax.enterprise.event.Observes;

public class GoldCoreEvent {
    public void onUpdateGoldCore(@Observes @GoldCore Istituto istituto) {
        System.out.println("L'istituto [" + istituto + "]" + " entra nel gold core!");
    }    
}
