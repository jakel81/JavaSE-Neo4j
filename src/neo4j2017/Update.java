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
public class Update {
    
    public static void main(String[] args) {
        
        Config noSSL = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();

        Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("", ""), noSSL);
        
        try (Session session  = driver.session()) {
            
            String lsRequete = "MATCH (n:Formation { intitule: 'HBase' }) SET n.intitule = 'HBASE' RETURN n"; 
            
            StatementResult curseur  = session.run(lsRequete);
            
            Record enregistrement = curseur.single();
            Value valeur = enregistrement.get(0).get("intitule");
            String lsValeur = valeur.toString();
            System.out.println("La nouvelle valeur est : " + lsValeur);
            
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
