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
public class Delete {

    public static void main(String[] args) {

        Config noSSL = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();

        Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("", ""), noSSL); // <password>

        try (Session session = driver.session()) {
            // MATCH (n {attribut:'valeur'}) DELETE n
            // Du Cypher
            String query = "MATCH (f:Formateur {prenom:'Meadow'}) DELETE f";

            // Execution
            StatementResult cursor = session.run(query);
            // consume() : consomme le résultat de la requête et renvoie un résumé
            ResultSummary rs = cursor.consume();
            SummaryCounters sc = rs.counters();
            int liAffectes = sc.nodesDeleted();
            System.out.println("Enregistrements supprimés ? " + Integer.toString(liAffectes));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("C'est fini");
    } /// main

}
