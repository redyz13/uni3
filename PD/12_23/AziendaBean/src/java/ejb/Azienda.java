package ejb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "findAll", query = "SELECT a FROM Azienda a"),
    @NamedQuery(name = "searchByID", query = "SELECT a FROM Azienda a WHERE a.id = ?1"),
    @NamedQuery(name = "searchByTipologia", query = "SELECT a FROM Azienda a WHERE a.tipologia = ?1"),
    @NamedQuery(name = "updateAssunzioni", query = "UPDATE Azienda a SET a.assunzioni = false WHERE a.id = ?1")
})
public class Azienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue
    private Long id;
    private String nome, nomeAmministratore, tipologia, provincia;
    private int numeroSedi, personeDaAssumere;
    private String responsabileHR;
    private boolean assunzioni;
    
    public Azienda() {}

    public Azienda(String nome, String nomeAmministratore, String tipologia, String provincia, String responsabileHR, int numeroSedi, int personeDaAssumere, boolean assunzioni) {
        this.nome = nome;
        this.nomeAmministratore = nomeAmministratore;
        this.tipologia = tipologia;
        this.provincia = provincia;
        this.responsabileHR = responsabileHR;
        this.numeroSedi = numeroSedi;
        this.personeDaAssumere = personeDaAssumere;
        this.assunzioni = assunzioni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAmministratore() {
        return nomeAmministratore;
    }

    public void setNomeAmministratore(String nomeAmministratore) {
        this.nomeAmministratore = nomeAmministratore;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getNumeroSedi() {
        return numeroSedi;
    }

    public void setNumeroSedi(int numeroSedi) {
        this.numeroSedi = numeroSedi;
    }

    public int getPersoneDaAssumere() {
        return personeDaAssumere;
    }

    public void setPersoneDaAssumere(int personeDaAssumere) {
        this.personeDaAssumere = personeDaAssumere;
    }

    public String getResponsabileHR() {
        return responsabileHR;
    }

    public void setResponsabileHR(String responsabileHR) {
        this.responsabileHR = responsabileHR;
    }

    public boolean isAssunzioni() {
        return assunzioni;
    }

    public void setAssunzioni(boolean assunzioni) {
        this.assunzioni = assunzioni;
    }

    @Override
    public String toString() {
        return "Azienda{" + "id=" + id + ", nome=" + nome + ", nomeAmministratore=" + nomeAmministratore + ", tipologia=" + tipologia + ", provincia=" + provincia + ", numeroSedi=" + numeroSedi + ", personeDaAssumere=" + personeDaAssumere + ", responsabileHR=" + responsabileHR + ", assunzioni=" + assunzioni + '}';
    }
}
