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
    private IstitutoEJB istitutoEJB;
    private Istituto i1, i2, i3;
    
    
    @PostConstruct
    private void populateDB() {
        i1 = new Istituto("Scuola1", "Tipo1", "Provincia1", 
                "Responsabile1", "Argomento1", 10, true);
        i2 = new Istituto("Scuola2", "Tipo2", "Provincia2", 
                "Responsabile2", "Argomento2", 30, true);
        i3 = new Istituto("Scuola3", "Tipo3", "Provincia3", 
                "Responsabile3", "Argomento3", 45, false);
        istitutoEJB.createIstituto(i1);
        istitutoEJB.createIstituto(i2);
        istitutoEJB.createIstituto(i3);
    }
    
    @PreDestroy
    private void clearDB() {
        istitutoEJB.deleteIstituto(i1);
        istitutoEJB.deleteIstituto(i2);
        istitutoEJB.deleteIstituto(i3);
    }
}
