import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestoreVeicoli {
    private List<Veicolo> veicoli;
    private int codiceSequenziale;
    private Map<String, Integer> inventarioMarche;

    public GestoreVeicoli() {
        veicoli = new ArrayList<>();
        codiceSequenziale = 1;
        inventarioMarche = new HashMap<>();
    }

    // Funzionalità per aggiungere un nuovo veicolo
    public void aggiungiVeicolo(String targa, String marca, String modello, int numeroPosti) {
        Veicolo veicolo = new Veicolo(targa, marca, modello, numeroPosti);
        veicolo.setCodice(codiceSequenziale++);
        veicoli.add(veicolo);
        aggiornaInventarioMarche(marca);
    }

    // Funzionalità per eliminare un veicolo
    public void eliminaVeicolo(int codice) {
        for (Veicolo veicolo : veicoli) {
            if (veicolo.getCodice() == codice) {
                veicoli.remove(veicolo);
                return;
            }
        }
    }

    public void eliminaVeicolo(String targa) {
        for (Veicolo veicolo : veicoli) {
            if (veicolo.getTarga().equals(targa)) {
                veicoli.remove(veicolo);
                return;
            }
        }
    }

    // Funzionalità per cercare un veicolo
    public Veicolo cercaVeicolo(int codice) {
        for (Veicolo veicolo : veicoli) {
            if (veicolo.getCodice() == codice) {
                return veicolo;
            }
        }
        return null;
    }

    public Veicolo cercaVeicolo(String targa) {
        for (Veicolo veicolo : veicoli) {
            if (veicolo.getTarga().equals(targa)) {
                return veicolo;
            }
        }
        return null;
    }

    // Funzionalità per cercare veicoli con un dato numero di posti
    public List<Veicolo> cercaVeicoliConPosti(int numeroPosti) {
        List<Veicolo> risultati = new ArrayList<>();
        for (Veicolo veicolo : veicoli) {
            if (veicolo.getNumeroPosti() == numeroPosti) {
                risultati.add(veicolo);
            }
        }
        return risultati;
    }

    // Funzionalità per salvare e ripristinare l'insieme di veicoli su file
    public void salvaVeicoliSuFile(String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Veicolo veicolo : veicoli) {
                writer.println(veicolo.getCodice() + "," + veicolo.getTarga() + "," + veicolo.getMarca() + "," + veicolo.getModello() + "," + veicolo.getNumeroPosti());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Errore durante il salvataggio del file: " + e.getMessage());
        }
    }

    public void ripristinaVeicoliDaFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Veicolo veicolo = new Veicolo(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
                veicolo.setCodice(Integer.parseInt(parts[0]));
                veicoli.add(veicolo);
                aggiornaInventarioMarche(parts[2]);
            }
        } catch (IOException e) {
            System.err.println("Errore durante il ripristino del file: " + e.getMessage());
        }
    }

    // Funzionalità per aggiornare l'inventario delle marche
    private void aggiornaInventarioMarche(String marca) {
        if (inventarioMarche.containsKey(marca)) {
            inventarioMarche.put(marca, inventarioMarche.get(marca) + 1);
        } else {
            inventarioMarche.put(marca, 1);
        }
    }

    // Funzionalità per stampare l'inventario delle marche
    public void stampaInventarioMarche() {
        for (Map.Entry<String, Integer> entry : inventarioMarche.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}