package ejb;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static ejb.Vino.*;

@Stateless
@LocalBean
public class VinoEJB implements VinoEJBRemote {
    @Inject
    private EntityManager em;

    @Override
    public List<Vino> findVini() {
        TypedQuery<Vino> query = em.createNamedQuery(FIND_ALL, Vino.class);
        return query.getResultList();
    }

    @Override
    public Vino createVino(Vino v) {
        em.persist(v);
        return v;
    }

    @Override
    public Vino updateVino(Vino v) {
        return em.merge(v);
    }

    @Override
    public void deleteVino(Vino v) {
        em.remove(em.merge(v));
    }

    @Override
    public List<Vino> searchId(Long id) {
        TypedQuery<Vino> query = em.createNamedQuery(SEARCH_ID, Vino.class);
        query.setParameter(1, id);
        return query.getResultList();
    }

    @Override
    public List<Vino> searchVitigno(String v) {
        TypedQuery<Vino> query = em.createNamedQuery(SEARCH_WIT, Vino.class);
        query.setParameter(1, v);
        return query.getResultList();
    }

    @Override
    public List<Vino> searchPrezzo(float p) {
        TypedQuery<Vino> query = em.createNamedQuery(SEARCH_PRICE, Vino.class);
        query.setParameter(1, p);
        return query.getResultList();        
    }

    @Override
    public List<Vino> searchPrezzoMinoreDi(float p) {
        TypedQuery<Vino> query = em.createNamedQuery(SEARCH_PRICE_LOWER_THAN, Vino.class);
        query.setParameter(1, p);
        return query.getResultList();         
    }

    @Override
    public List<Vino> searchProvenienza(String p) {
        TypedQuery<Vino> query = em.createNamedQuery(SEARCH_BELONGING, Vino.class);
        query.setParameter(1, p);
        return query.getResultList();         
    }

    @Override
    public void updateBottleNumber(Long id) {
        TypedQuery<Vino> query = em.createNamedQuery(UPDATE_BOTTLES, Vino.class);
        query.setParameter(1, id);
        query.executeUpdate();        
    }

    @Override
    public void updatePurchase(Long id) {
        TypedQuery<Vino> query = em.createNamedQuery(UPDATE_PURCHASE, Vino.class);
        query.setParameter(1, id);
        query.executeUpdate();        
    }
}
