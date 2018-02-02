/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neo4j2017;

import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.summary.*;

/**
 *
 * @author Jo
 */
public class Insert1 {

    public static void main(String[] args) {

        Config noSSL = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();

        Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("", ""), noSSL); // <password>

        try (Session session = driver.session()) {

            String lsRequete = "CREATE (f:Formateur {prenom:'Meadow'}) RETURN id(f)";

            StatementResult curseur = session.run(lsRequete);
            Record enregistrement = curseur.single();
            Value valeur = enregistrement.get(0);
            long id = valeur.asLong();
            System.out.println("ID : " + id);

            // consume() : consomme le résultat de la requête et renvoie un résumé
            ResultSummary resumeDuResultat = curseur.consume();
            SummaryCounters compteur = resumeDuResultat.counters();
            int liAffectes = compteur.nodesCreated();
            System.out.println("Enregistrements insérés ? " + Integer.toString(liAffectes));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("C'est fini");
    } /// main

}
