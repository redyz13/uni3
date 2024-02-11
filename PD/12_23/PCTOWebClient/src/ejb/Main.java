package ejb;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Punto c
        System.out.println("Risultati dell'invocazione del metodo del web service:");
        List<Istituto> istituti = findAll();
        for (Istituto i : istituti)
            System.out.println("---" + printNumStudenti(i));
    }

    private static java.util.List<ejb.Istituto> findAll() {
        ejb.IstitutoEJBService service = new ejb.IstitutoEJBService();
        ejb.IstitutoEJB port = service.getIstitutoEJBPort();
        return port.findAll();
    }

    private static int printNumStudenti(ejb.Istituto istituto) {
        ejb.IstitutoEJBService service = new ejb.IstitutoEJBService();
        ejb.IstitutoEJB port = service.getIstitutoEJBPort();
        return port.printNumStudenti(istituto);
    }
}
