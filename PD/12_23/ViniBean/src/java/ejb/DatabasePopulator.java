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
    user = "app",
    password = "APP",
    databaseName = "EsameDB",
    properties = {
        "connectionAttributes=;create=true"
    }
)
public class DatabasePopulator {
    @Inject
    private VinoEJB vinoEJB;
    private Vino v1, v2, v3;
    
    @PostConstruct
    private void populateDB() {
        v1 = new Vino("Vino1", "Vitigno1", "Azienda1", 
                5, "Salerno", "Buono", 20, true);
        v2 = new Vino("Vino2", "Vitigno2", "Azienda2", 
                10, "Salerno", "Buonoo", 30, true);
        v3 = new Vino("Vino3", "Vitigno3", "Azienda3", 
                7, "Napoli", "Buonooo", 40, false);  
        
        vinoEJB.createVino(v1);
        vinoEJB.createVino(v2);
        vinoEJB.createVino(v3);
    }
    
    @PreDestroy
    private void clearDB() {
        vinoEJB.deleteVino(v1);
        vinoEJB.deleteVino(v2);
        vinoEJB.deleteVino(v3);
    }
}
