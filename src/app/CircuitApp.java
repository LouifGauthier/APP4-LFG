package app;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import electronique.Circuit;
import electronique.CircuitSerie;
import electronique.Composant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CircuitApp {
    private static final String pathIn = System.getProperty("user.dir") + File.separator + "src" + File.separator + "donnees" + File.separator + "fichiers_json";

    public CircuitApp() {
        interfaceUtilisateur1();
    }

    public void interfaceUtilisateur1() {
        String[] fichiers = new File(pathIn).list((fichiers_json, name) -> name.endsWith(".json"));
        System.out.println("Bonjour et bienvenue!\nVeuillez sélectionner un fichier en inscrivant son numéro correspondant.");
        for (int i = 0; i < fichiers.length; i++) {
            System.out.println("[" + (i + 1) + "]" + " : " + fichiers[i]);
        }
        Scanner interfaceLecteur = new Scanner(System.in);
        int choix = interfaceLecteur.nextInt();
        interfaceLecteur.nextLine();
        while (choix <= 0 || choix > fichiers.length + 1) {
            System.out.println("Choix Invalide.");
            choix = interfaceLecteur.nextInt();
            interfaceLecteur.nextLine();
        }
        CircuitBuilder builder = new CircuitBuilder(fichiers[choix - 1]);
        Composant composant = builder.construireCircuit(fichiers[choix - 1]);
        System.out.printf("Résistance totale du circuit: %.2f Ω", composant.calculerResistance());
        System.out.println("\n\nMerci d'avoir utilisé l'application! Voulez-vous:\n[R] Réutiliser l'application\n[Q] Quitter");
        String choix1;
        while (true) {
            choix1 = interfaceLecteur.nextLine();
            if (choix1.equalsIgnoreCase("R") || choix1.equalsIgnoreCase("Q")) {
                break;
            } else {
                System.out.println("Choix invalide.");
            }
        }
        if (choix1.equalsIgnoreCase("R")) {
            interfaceUtilisateur1();
        } else if (choix1.equalsIgnoreCase("Q")) {
            System.out.println("Au revoir!");

        }
    }

    public static void main(String[] args) {
        new CircuitApp();
    }

}
