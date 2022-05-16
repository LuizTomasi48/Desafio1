/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atividadesaks;

import static controller.AdministradorC.iniciarA;
import java.sql.SQLException;
import view.TelaLogin;

/**
 *
 * @author Bolofofo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        TelaLogin start = new TelaLogin();
        start.setVisible(true);
        
    }
    
}
