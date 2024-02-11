package ejb;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserire l'ID: ");
        Long id = sc.nextLong();
        Azienda a = searchByID(id);
        int pers = printPersoneDaAssumere(a);
        System.out.println(pers);
    }
    
    private static Azienda searchByID(Long id) {
        AziendaEJBService service = new AziendaEJBService();
        AziendaEJB port = service.getAziendaEJBPort();
        return port.searchByID(id);
    }
    
    private static int printPersoneDaAssumere(Azienda a) {
        AziendaEJBService service = new AziendaEJBService();
        AziendaEJB port = service.getAziendaEJBPort();
        // Aggiunto il parametro da passare alla funzione
        return port.printPersoneDaAssumere(a);
    }
}
