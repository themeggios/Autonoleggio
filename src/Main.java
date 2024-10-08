public class Main {
    public static void main(String[] args) {
        GestoreVeicoli gestore = new GestoreVeicoli();

        // Aggiungi alcuni veicoli di esempio
        gestore.aggiungiVeicolo("AA111AA", "Fiat", "Punto", 5);
        gestore.aggiungiVeicolo("BB222BB", "Ford", "Focus", 5);
        gestore.aggiungiVeicolo("CC333CC", "Fiat", "Panda", 4);

        // Stampa l'inventario delle marche
        System.out.println("Inventario delle marche:");
        gestore.stampaInventarioMarche();

        // Salva i veicoli su file
        gestore.salvaVeicoliSuFile("veicoli.txt");

        // Ripristina i veicoli da file
        gestore.ripristinaVeicoliDaFile("veicoli.txt");

        // Stampa l'inventario delle marche dopo il ripristino
        System.out.println("Inventario delle marche dopo il ripristino:");
        gestore.stampaInventarioMarche();
    }
}