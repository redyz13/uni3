package ejb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import static ejb.Istituto.*;

@Entity
@NamedQueries({
    @NamedQuery(name = FIND_ALL, query = "SELECT i FROM Istituto i"),
    @NamedQuery(name = SEARCH_ID, query = "SELECT i FROM Istituto i WHERE i.id = ?1"),
    @NamedQuery(name = SEARCH_TIP, query = "SELECT i FROM Istituto i WHERE i.tipologiaScuola = ?1"),
    @NamedQuery(name = SEARCH_PRO, query = "SELECT i FROM Istituto i WHERE i.provincia = ?1"),
    @NamedQuery(name = UPDATE_NUMSTUDENTI, query = "UPDATE Istituto i SET i.studentiAmmessi = 50 WHERE i.id = ?1"),
    @NamedQuery(name = UPDATE_GOLDCORE, query = "UPDATE Istituto i SET i.goldCore = true WHERE i.id = ?1"),
})
public class Istituto implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Istituto.findAll";
    public static final String SEARCH_ID = "Istituto.searchById";
    public static final String SEARCH_TIP = "Istituto.searchByTip";
    public static final String SEARCH_PRO = "Istituto.searchByPro";
    public static final String UPDATE_NUMSTUDENTI = "Istituto.updateNumStudenti";
    public static final String UPDATE_GOLDCORE = "Istituto.updateGoldCore";
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nomeScuola, tipologiaScuola, provincia, responsabile, argomento;
    private int studentiAmmessi;
    private boolean goldCore;
    
    public Istituto() {}

    public Istituto(String nomeScuola, String tipologiaScuola, String provincia, String responsabile, String argomento, int studentiAmmessi, boolean goldCore) {
        this.nomeScuola = nomeScuola;
        this.tipologiaScuola = tipologiaScuola;
        this.provincia = provincia;
        this.responsabile = responsabile;
        this.argomento = argomento;
        this.studentiAmmessi = studentiAmmessi;
        this.goldCore = goldCore;
    }

    public String getNomeScuola() {
        return nomeScuola;
    }

    public void setNomeScuola(String nomeScuola) {
        this.nomeScuola = nomeScuola;
    }

    public String getTipologiaScuola() {
        return tipologiaScuola;
    }

    public void setTipologiaScuola(String tipologiaScuola) {
        this.tipologiaScuola = tipologiaScuola;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getResponsabile() {
        return responsabile;
    }

    public void setResponsabile(String responsabile) {
        this.responsabile = responsabile;
    }

    public String getArgomento() {
        return argomento;
    }

    public void setArgomento(String argomento) {
        this.argomento = argomento;
    }

    public int getStudentiAmmessi() {
        return studentiAmmessi;
    }

    public void setStudentiAmmessi(int studentiAmmessi) {
        this.studentiAmmessi = studentiAmmessi;
    }

    public boolean isGoldCore() {
        return goldCore;
    }

    public void setGoldCore(boolean goldCore) {
        this.goldCore = goldCore;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Istituto)) {
            return false;
        }
        Istituto other = (Istituto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Istituto{" + "id=" + id + ", nomeScuola=" + nomeScuola + ", tipologiaScuola=" + tipologiaScuola + ", provincia=" + provincia + ", responsabile=" + responsabile + ", argomento=" + argomento + ", studentiAmmessi=" + studentiAmmessi + ", goldCore=" + goldCore + '}';
    }
}
