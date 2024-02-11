package ejb;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface AziendaEJBRemote {
    Azienda createAzienda(Azienda azienda);
    Azienda updateAzienda(Azienda azienda);
    void deleteAzienda(Azienda azienda);
    List<Azienda> findAll();
    Azienda searchByID(Long id);
    List<Azienda> searchByTipologia(String tipologia);
    void updateAssunzioni(Long id);
    int printPersoneDaAssumere(Azienda azienda);
}
