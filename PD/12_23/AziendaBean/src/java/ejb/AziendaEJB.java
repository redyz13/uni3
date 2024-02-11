package ejb;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

// Modifica: Aggiunta di public alle firme dei metodi

@WebService
@LocalBean
@Stateless
public class AziendaEJB implements AziendaEJBRemote {
    @Inject
    private EntityManager em;
    
    @Override
    public Azienda createAzienda(Azienda azienda) {
        em.persist(azienda);
        return azienda;
    }
    
    @Override
    public Azienda updateAzienda(Azienda azienda) {
        return em.merge(azienda);
    }
    
    @Override
    public void deleteAzienda(Azienda azienda) {
        em.remove(em.merge(azienda));
    }
    
    @Override
    public List<Azienda> findAll() {
        TypedQuery<Azienda> query = em.createNamedQuery("findAll", Azienda.class);
        return query.getResultList();
    }
    
    @Override @WebMethod(operationName = "searchByID")
    public Azienda searchByID(@WebParam(name = "id") Long id) {
        TypedQuery<Azienda> query = em.createNamedQuery("searchByID", Azienda.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }
    
    @Override
    public List<Azienda> searchByTipologia(String tipologia) {
        TypedQuery<Azienda> query = em.createNamedQuery("searchByTipologia", Azienda.class);
        query.setParameter(1, tipologia);
        return query.getResultList();
    }
    
    @Override
    public void updateAssunzioni(Long id) {
        TypedQuery<Azienda> query = em.createNamedQuery("updateAssunzioni", Azienda.class);
        query.setParameter(1, id);
        query.executeUpdate();
    }
    
    @Override @WebMethod(operationName = "printPersoneDaAssumere")
    public int printPersoneDaAssumere(@WebParam(name = "azienda") Azienda azienda) {
        return azienda.getPersoneDaAssumere();
    }
}
