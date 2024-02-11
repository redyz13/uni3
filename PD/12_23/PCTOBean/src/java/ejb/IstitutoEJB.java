package ejb;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static ejb.Istituto.*;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Stateless
@LocalBean
@WebService
public class IstitutoEJB implements IstitutoEJBRemote {
    @Inject
    EntityManager em;
    
    @Override
    public List<Istituto> findIstituti() {
        TypedQuery<Istituto> query = em.createNamedQuery(FIND_ALL, Istituto.class);
        return query.getResultList();
    }
    
    @Override
    public Istituto createIstituto(Istituto i) {
        em.persist(i);
        return i;
    }
    
    @Override
    public Istituto updateIstituto(Istituto i) {
        return em.merge(i);
    }
    
    @Override
    public void deleteIstituto(Istituto i) {
       em.remove(em.merge(i));
    }

    @Override
    public Istituto searchID(Long id) {
        TypedQuery<Istituto> query = em.createNamedQuery(SEARCH_ID, Istituto.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    @Override
    public List<Istituto> searchTipologia(String tipoScuola) {
        TypedQuery<Istituto> query = em.createNamedQuery(SEARCH_TIP, Istituto.class);
        query.setParameter(1, tipoScuola);
        return query.getResultList();
    }

    @Override
    public List<Istituto> searchProvincia(String provincia) {
        TypedQuery<Istituto> query = em.createNamedQuery(SEARCH_PRO, Istituto.class);
        query.setParameter(1, provincia);
        return query.getResultList();
    }
    
    @Override
    public void updateNumeroStudenti(Long id) {
        TypedQuery<Istituto> query = em.createNamedQuery(UPDATE_NUMSTUDENTI, Istituto.class);
        query.setParameter(1, id);
        query.executeUpdate();        
    }

    @Override
    public void updateGoldCore(Long id) {
        TypedQuery<Istituto> query = em.createNamedQuery(UPDATE_GOLDCORE, Istituto.class);
        query.setParameter(1, id);
        query.executeUpdate();        
    }
    
    @WebMethod(operationName = "printNumStudenti")
    public int printNumStudenti(@WebParam(name = "istituto") Istituto istituto) {
        return istituto.getStudentiAmmessi();
    }
    
    @WebMethod(operationName = "findAll")
    public List<Istituto> findAll() {
        TypedQuery<Istituto> query = em.createNamedQuery(FIND_ALL, Istituto.class);
        return query.getResultList();
    }
}
