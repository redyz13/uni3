package ejb;

import javax.enterprise.event.Observes;

public class AssunzioniUpdate {
    public void onAssunzioniUpdate(@Observes @AssunzioniNo Azienda a) {
        System.out.println("L'azienda " + a.getNome() + " il cui amministratore "
                + "delegatso è " + a.getNomeAmministratore() + " ha completato l'organico");
    }    
}
