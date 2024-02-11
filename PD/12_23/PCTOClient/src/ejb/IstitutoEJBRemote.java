 package ejb;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface IstitutoEJBRemote {
    List<Istituto> findIstituti();
    Istituto createIstituto(Istituto i);
    Istituto updateIstituto(Istituto i);
    void deleteIstituto(Istituto i);
    Istituto searchID(Long id);
    List<Istituto> searchTipologia(String tipoScuola);
    List<Istituto> searchProvincia(String provincia);
    void updateNumeroStudenti(Long id);
    void updateGoldCore(Long id);
}
