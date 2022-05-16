/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import conexao.ConectaBD;
import dao.AdministradorDAO;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Administrador;
import model.Usuario;
import static view.AdministradorV.imprime;
import view.TelaLogin;

/**
 *
 * @author Bolofofo
 */
public class AdministradorC {
    public static void iniciarA() throws SQLException{
        Connection connection = ConectaBD.createConnectionMySQL();
        if(connection != null){
            System.out.println("Conexão realizada com sucesso");
            connection.close();
            
        }
        
        
    }
    
    public static void cadastrarAdministrador(String nome, int cpf, String email, String endereco, String senha, String pergunta1) throws SQLException {
        Connection connection = ConectaBD.createConnectionMySQL();
       
        if(connection != null){
            AdministradorDAO administradorDAO = new AdministradorDAO();
            Administrador administrador = new Administrador(nome, cpf, email, endereco, senha, pergunta1);
            administradorDAO.cadastra(administrador);
            connection.close();
        }
    }
    
    public List<Administrador> coletaAdministrador(){
       Connection connection = ConectaBD.createConnectionMySQL();
       List<Administrador> administradores = new ArrayList<Administrador>();
       AdministradorDAO administradorDAO = new AdministradorDAO();
       administradores = administradorDAO.getAdministrador();
       
       return administradores;
   } 
    
   public List<Usuario> coletaUsuario(){
       Connection connection = ConectaBD.createConnectionMySQL();
       List<Usuario> usuarios = new ArrayList<Usuario>();
       UsuarioDAO usuarioDAO = new UsuarioDAO();
       usuarios = usuarioDAO.getUsuario();
       
       return usuarios;
   }
    
   public void editaAdministrador(int id, String nome, int cpf, String email, String endereco, String senha, String pergunta1){
         AdministradorDAO administradorDAO = new AdministradorDAO();
         administradorDAO.alteraAdministradorByAtributo(id, nome, cpf,  email,  endereco, senha, pergunta1);
        
     }
   
   public void apagaAdministrador(int id){
       AdministradorDAO AdministradorDAO = new AdministradorDAO();
       AdministradorDAO.deletaAdministrador(id);
   }
   
   
    public List<Administrador> validaAdministrador(String nome, String senha){
        Connection connection = ConectaBD.createConnectionMySQL();
        List<Administrador> administrador = new ArrayList<Administrador>();
        if(connection != null){
            AdministradorDAO administradorDAO = new AdministradorDAO();        
            administrador = administradorDAO.getAdministradorByNameAndPassword(nome, senha);
            
            if(administrador.size() <= 0){
                new TelaLogin().setVisible(true);
                 
            }
        }
        return administrador;
    }
    
    public static void recuperarSenhaAdmin(String email, int cpf, String pergunta1){
      AdministradorDAO administradorDAO = new AdministradorDAO();
      administradorDAO.alteraAdministradorSenha(email, cpf, pergunta1);
  }
}
