package ejb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import static ejb.Vino.*;

@Entity
@NamedQueries({
    @NamedQuery(name = FIND_ALL, query = "SELECT v FROM Vino v"),
    @NamedQuery(name = SEARCH_ID, query = "SELECT v FROM Vino v WHERE v.id = ?1"),
    @NamedQuery(name = SEARCH_PRICE, query = "SELECT v FROM Vino v WHERE v.prezzo = ?1"),
    @NamedQuery(name = SEARCH_WIT, query = "SELECT v FROM Vino v WHERE v.vitigno = ?1"),
    @NamedQuery(name = SEARCH_BELONGING, query = "SELECT v FROM Vino v WHERE v.provenienza = ?1"),
    @NamedQuery(name = SEARCH_PRICE_LOWER_THAN, query = "SELECT v FROM Vino v WHERE v.prezzo < ?1"),
    @NamedQuery(name = UPDATE_BOTTLES, query = "UPDATE Vino v SET v.bottiglieDisponibili = 50 WHERE v.id = ?1"),
    @NamedQuery(name = UPDATE_PURCHASE, query = "UPDATE Vino v SET v.acquisto = true WHERE v.id = ?1"),
})
public class Vino implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "Vino.findAll";
    public static final String SEARCH_ID = "Vino.searchById";
    public static final String SEARCH_PRICE = "Vino.searchByPrice";
    public static final String SEARCH_WIT = "Vino.searchByWit";
    public static final String SEARCH_BELONGING = "Vino.searchByBelonging";
    public static final String SEARCH_PRICE_LOWER_THAN = "Vino.searchByPriceLowerThan";
    public static final String UPDATE_BOTTLES = "Vino.updateBottles";
    public static final String UPDATE_PURCHASE = "Vino.updatePurchase";
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nominativo, vitigno, azienda;
    private int bottiglieDisponibili;
    private String provenienza, tipologia;
    private float prezzo;
    private boolean acquisto;

    public Vino() {}

    public Vino(String nominativo, String vitigno, String azienda, int bottiglieDisponibili, String provenienza, String tipologia, float prezzo, boolean acquisto) {
        this.nominativo = nominativo;
        this.vitigno = vitigno;
        this.azienda = azienda;
        this.bottiglieDisponibili = bottiglieDisponibili;
        this.provenienza = provenienza;
        this.tipologia = tipologia;
        this.prezzo = prezzo;
        this.acquisto = acquisto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNominativo() {
        return nominativo;
    }

    public void setNominativo(String nominativo) {
        this.nominativo = nominativo;
    }

    public String getVitigno() {
        return vitigno;
    }

    public void setVitigno(String vitigno) {
        this.vitigno = vitigno;
    }

    public String getAzienda() {
        return azienda;
    }

    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }

    public int getBottiglieDisponibili() {
        return bottiglieDisponibili;
    }

    public void setBottiglieDisponibili(int bottiglieDisponibili) {
        this.bottiglieDisponibili = bottiglieDisponibili;
    }

    public String getProvenienza() {
        return provenienza;
    }

    public void setProvenienza(String provenienza) {
        this.provenienza = provenienza;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public boolean isAcquisto() {
        return acquisto;
    }

    public void setAcquisto(boolean acquisto) {
        this.acquisto = acquisto;
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
        if (!(object instanceof Vino)) {
            return false;
        }
        Vino other = (Vino) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vino{" + "id=" + id + ", nominativo=" + nominativo + ", vitigno=" + vitigno + ", azienda=" + azienda + ", bottiglieDisponibili=" + bottiglieDisponibili + ", provenienza=" + provenienza + ", tipologia=" + tipologia + ", prezzo=" + prezzo + ", acquisto=" + acquisto + '}';
    }
}
