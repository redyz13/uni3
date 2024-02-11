package ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
@DataSourceDefinition(
    className = "org.apache.derby.jdbc.EmbeddedDataSource",
    name = "jdbc/EsameDS",
    user = "APP",
    password = "APP",
    databaseName = "EsameDB",
    properties = {"connectionAttribute=;create=true"}
)
public class DatabasePopulator {
    @Inject
    private AziendaEJB aziendaEJB;
    private Azienda a1, a2, a3;
    
    @PostConstruct
    void populateDB() {
        a1 = new Azienda("Accenturo", "Serio Michela", "Consulenza", "Roma", "Mario Rossi", 300, 40, true);
        a2 = new Azienda("Capgemino", "Faceto Carmine", "Sviluppo", "Milano", "Guido Verdi", 200, 50, true);
        a3 = new Azienda("Replio", "Felice Maria", "Sviluppo", "Napoli", "Serena Bianchi", 100, 60, true);
        
        aziendaEJB.createAzienda(a1);
        aziendaEJB.createAzienda(a2);
        aziendaEJB.createAzienda(a3);
    }
    
    @PreDestroy
    void clearDB() {
        aziendaEJB.deleteAzienda(a1);
        aziendaEJB.deleteAzienda(a2);
        aziendaEJB.deleteAzienda(a3);
    }
}
