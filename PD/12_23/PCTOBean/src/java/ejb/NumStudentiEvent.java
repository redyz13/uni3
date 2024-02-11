package ejb;

import javax.enterprise.event.Observes;

public class NumStudentiEvent {
    public void onUpdateNumStudenti(@Observes @Studenti Istituto istituto) {
        System.out.println("Aggiornamento effettuato [" + istituto + "]");
    }
}
