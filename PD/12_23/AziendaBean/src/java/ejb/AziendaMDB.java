package ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(
    mappedName = "jms/javaee7/Topic",
    activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
    }
)
public class AziendaMDB implements MessageListener {
    @Inject
    AziendaEJB aziendaEJB;
    
    @Inject @PersoneAssumere
    Event<Azienda> onPersonUpdate;
    
    @Inject @AssunzioniNo
    Event<Azienda> onAssunzioniUpdate;
    
    public AziendaMDB() {}
    
    @Override
    public void onMessage(Message message) {
        try {
            AziendaDTO aziendaDTO = message.getBody(AziendaDTO.class);
            Azienda azienda = aziendaEJB.searchByID(aziendaDTO.getId());
            azienda.setPersoneDaAssumere(aziendaDTO.getNumPers());
            azienda = aziendaEJB.updateAzienda(azienda);
            onPersonUpdate.fire(azienda);
            
            if (azienda.getPersoneDaAssumere() == 0) {
                aziendaEJB.updateAssunzioni(azienda.getId());
                onAssunzioniUpdate.fire(azienda);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
