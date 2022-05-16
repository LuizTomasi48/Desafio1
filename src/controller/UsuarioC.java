/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import conexao.ConectaBD;
import criptografia.Criptografia;
import java.sql.Connection;
import java.sql.SQLException;
import dao.UsuarioDAO;

import dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import view.TelaLogin;
import view.TelaLoginAdmin;
public class UsuarioC {
    
    
    public static void cadastrarUsuario(String nome, int cpf, String email, String endereco, String senha, String pergunta1) throws SQLException{
        Connection connection = ConectaBD.createConnectionMySQL();
        if(connection != null){
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = new Usuario(nome, cpf, email, endereco, senha, pergunta1);
            usuarioDAO.cadastra(usuario);
            connection.close();
        }
        
    }
    
  
   
  public static void recuperarSenha(String email, int cpf, String pergunta1){
      UsuarioDAO usuarioDAO = new UsuarioDAO();
      usuarioDAO.alteraUsuarioSenha(email, cpf, pergunta1);
  }
    
    public List<Usuario> validaUsuario(String email, String senha){
        Connection connection = ConectaBD.createConnectionMySQL();
        List<Usuario> usuario = new ArrayList<Usuario>();
        if(connection != null){
            UsuarioDAO usuarioDAO = new UsuarioDAO();        
            usuario = usuarioDAO.getUsuarioByNameAndPassword(email, senha);
            
        }
        return usuario;
    }
    
     public void apagaUsuario(int id){
       UsuarioDAO usuarioDAO = new UsuarioDAO();
       usuarioDAO.deletaUsuario(id);
    }  
     
     public void editaUsuario(int id, String nome, int cpf, String email, String endereco, String senha, String pergunta1){
         UsuarioDAO usuarioDAO = new UsuarioDAO();      
         usuarioDAO.alteraUsuarioByAtributo(id, nome, cpf,  email,  endereco, senha, pergunta1);
        
     }
    
    
   
}