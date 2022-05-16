/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.AdministradorDAO;
import java.util.Iterator;

public class AdministradorV {
    public static void imprime(AdministradorDAO administradorDAO){
    for (model.Administrador a : administradorDAO.getAdministrador()) {
            System.out.println("Nome: " + a.getNome());            
        }
    }
}
