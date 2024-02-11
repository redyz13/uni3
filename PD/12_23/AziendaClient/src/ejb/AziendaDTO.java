package ejb;

import java.io.Serializable;

public class AziendaDTO implements Serializable {
    private Long id;
    private int numPers;
    
    public AziendaDTO() {}

    public AziendaDTO(Long id, int numPers) {
        this.id = id;
        this.numPers = numPers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumPers() {
        return numPers;
    }

    public void setNumPers(int numPers) {
        this.numPers = numPers;
    }
}
