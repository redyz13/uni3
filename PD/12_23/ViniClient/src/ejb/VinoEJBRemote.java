package ejb;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface VinoEJBRemote {
    List<Vino> findVini();
    Vino createVino(Vino v);
    Vino updateVino(Vino v);
    void deleteVino(Vino v);
    List<Vino> searchId(Long id);
    List<Vino> searchVitigno(String v);
    List<Vino> searchPrezzo(float p);
    List<Vino> searchPrezzoMinoreDi(float p);
    List<Vino> searchProvenienza(String p);
    void updateBottleNumber(Long id);
    void updatePurchase(Long id);
}
