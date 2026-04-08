package app;

import electronique.CircuitParallele;
import electronique.CircuitSerie;
import electronique.Composant;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import electronique.Resistance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {
    private static final String pathIn = System.getProperty("user.dir") + File.separator + "src" + File.separator + "donnees" + File.separator + "fichiers_json";

    public CircuitBuilder(String fichier) {
        construireCircuit(fichier);

    }

    public Composant construireCircuit(String fichier) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode circuitBrut = mapper.readTree(new File(pathIn + File.separator + fichier));
            JsonNode circuit = circuitBrut.get("circuit");
            return lireComposant(circuit);
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
            return null;
        }
    }


    private Composant lireComposant(JsonNode node) {
        String type = node.get("type").asText();
        if ("resistance".equals(type)) {
            return new Resistance(node.get("valeur").asDouble());
        } else if ("parallele".equals(type)) {
            List<Composant> composants = new ArrayList<>();
            for (JsonNode composantNode : node.get("composants")) {
                composants.add(lireComposant(composantNode));
            }
            return new CircuitParallele(composants);
        } else if ("serie".equals(type)) {
            List<Composant> composants = new ArrayList<>();
            for (JsonNode resistanceNode : node.get("composants")) {
                composants.add(lireComposant(resistanceNode));
            }
            return new CircuitSerie(composants);
        }
        throw new IllegalArgumentException("Type de circuit inconnu : " + type);
    }
}
