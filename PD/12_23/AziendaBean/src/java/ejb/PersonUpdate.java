package ejb;

import javax.enterprise.event.Observes;

public class PersonUpdate {
    public void onPersonUpdate(@Observes @PersoneAssumere Azienda a) {
        System.out.println("Aggiornamento Effettuato");
    }
}
