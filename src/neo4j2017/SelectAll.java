/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j2017;

import org.neo4j.driver.v1.*;

/**
 *
 * @author Jo
 */
public class SelectAll {

    public static void main(String[] args) {

        // Configuration sans SSL
        Config noSSL = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();
        // Connexion via une authentification "basic"
        Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("", ""), noSSL); // <password>

        // Ouverture de la session (with resource)
        try (Session session = driver.session()) {
            // Requête : du pur Neo4j- Cypher
            String lsRequete = "MATCH (f:Formation) RETURN f";

            // Renvoie un curseur (0, 1 ou n enregistrements)
            StatementResult curseur = session.run(lsRequete);

            Record enregistrement;
            while (curseur.hasNext()) {
                // Avance sur l'enregistrement suivant
                enregistrement = curseur.next();
                System.out.println("record");
                System.out.println(enregistrement);

                // Recupere l'ID
                System.out.println("record.get(0) : l'ID");
                Value valeur = enregistrement.get(0);
                System.out.println(valeur);

                // Recupere l'attribut intitule
                System.out.println("record.get(0).get('intitule')");
                valeur = enregistrement.get(0).get("intitule");
                String lsValeur = valeur.toString();
                System.out.println(lsValeur);

                // Recupere l'attribut duree
                System.out.println("record.get(0).get('duree')");
                valeur = enregistrement.get(0).get("duree");
                lsValeur = valeur.toString();
                System.out.println(lsValeur);

                // Recupere l'attribut tarif
                System.out.println("record.get(0).get('tarif')");
                valeur = enregistrement.get(0).get("tarif");
                lsValeur = valeur.toString();
                System.out.println(lsValeur);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("C'est fini");
    } /// main

}
