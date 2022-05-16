/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import criptografia.Criptografia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Administrador;


public class AdministradorDAO {
    
    public void cadastra(Administrador administrador) {
        String QUERY = "INSERT INTO administrador (nome,  cpf, email, endereco, senha, pergunta1)" + "VALUES (?,?,?,?,?,?)";
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement (QUERY);
            preparedStatement.setString(1, administrador.getNome());
            preparedStatement.setInt(2, administrador.getCpf());
            preparedStatement.setString(3, administrador.getEmail());
            preparedStatement.setString(4, administrador.getEndereco());
            preparedStatement.setString(5, Criptografia.criptografa(administrador.getSenha()));
            preparedStatement.setString(6, administrador.getPergunta1());
            
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void deletaAdministrador(int id) {
        String QUERY = "UPDATE administrador SET ativo = 0 WHERE administrador.id ="+id;
         Connection connection = null;
         PreparedStatement preparedStatement = null;
            
            try{
                connection = conexao.ConectaBD.createConnectionMySQL();
                preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
                
                preparedStatement.execute();
                System.out.println("Usuario deletado com sucesso");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    public  List<Administrador> getAdministradorByNameAndPassword(String email, String senha) {
        List<Administrador> administradores = new ArrayList<Administrador>();
        String QUERY = "SELECT * FROM administrador WHERE  email='"+email+"' AND senha='"+Criptografia.criptografa(senha)+"' AND ativo != 0";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            
            resultSet = preparedStatement.executeQuery(QUERY);
            
            while (resultSet.next()) {
                
                Administrador administrador = new Administrador();
                
                administrador.setNome(resultSet.getString("nome"));
                administrador.setCpf(resultSet.getInt("cpf"));
                administrador.setEmail(resultSet.getString("email"));
                administrador.setEndereco(resultSet.getString("endereco"));
                administrador.setSenha(resultSet.getString("senha"));
                administrador.setPergunta1(resultSet.getString("pergunta1"));
            
                administradores.add(administrador);
            }
            
            
        }  catch (SQLException ex) {
            System.out.println("Nao foi possivel localizar o usuario");
        } finally {
            try {
                if ( preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return administradores;
    }
    
    public List<Administrador> getAdministrador() {
        List<Administrador> administradores = new ArrayList<Administrador>();
        String QUERY = "SELECT * FROM administrador WHERE ativo != 0";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            
            resultSet = preparedStatement.executeQuery(QUERY);
            
            while (resultSet.next()) {
                
                Administrador administrador = new Administrador();
                
                administrador.setId(resultSet.getInt("id"));
                administrador.setNome(resultSet.getString("nome"));
                administrador.setCpf(resultSet.getInt("cpf"));
                administrador.setEmail(resultSet.getString("email"));
                administrador.setEndereco(resultSet.getString("endereco"));
                administrador.setSenha(resultSet.getString("senha"));
                administrador.setPergunta1(resultSet.getString("pergunta1"));
                administrador.setAtivo(resultSet.getInt("ativo"));
            
                administradores.add(administrador);
            }
            
            
        }  catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return administradores;
    }
    
    public void alteraAdministradorByAtributo(int id, String nome, int cpf, String email, String endereco, String senha, String pergunta1){
        String QUERY = "UPDATE administrador SET nome = '"+nome+"', cpf = "+cpf+", email = '"+email+"', endereco = '"+endereco+"', senha = '"+Criptografia.criptografa(senha)+"', pergunta1 = '"+pergunta1+"' WHERE id="+id;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {   
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            preparedStatement.execute();
            System.out.println("Usuario alterado com sucesso");
        }  catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void alteraAdministrador(Administrador administrador, int id){
        String QUERY = "UPDATE adminstrador SET nome = ?, cpf = ?, email = ?, endereco = ?, senha = ?, pergunta1 = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement.setString(1, administrador.getNome());
            preparedStatement.setInt(2, administrador.getCpf());
            preparedStatement.setString(3, administrador.getEmail());
            preparedStatement.setString(4, administrador.getEndereco());
            preparedStatement.setString(5, Criptografia.criptografa(administrador.getSenha()));
            preparedStatement.setString(6, administrador.getPergunta1());
            
            preparedStatement.execute();
            System.out.println("Administrador alterado com sucesso");
        }  catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void alteraAdministradorSenha(String email, int cpf, String pergunta1){
        String QUERY = "UPDATE administrador SET senha = '"+Criptografia.criptografa("bolo")+"' WHERE email = '"+email+"' AND cpf ='"+cpf+"' AND pergunta1='"+pergunta1+"'";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            preparedStatement.execute();
            System.out.println("Usuario alterado com sucesso");
        }  catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
