package dao;


import criptografia.Criptografia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;

public class UsuarioDAO {

    
    public void cadastra(Usuario usuario) {
        String QUERY = "INSERT INTO usuario (nome,  cpf, email, endereco, senha, pergunta1)" + "VALUES (?,?,?,?,?,?)";
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement (QUERY);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setInt(2, usuario.getCpf());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getEndereco());
            preparedStatement.setString(5, Criptografia.criptografa(usuario.getSenha()));
            preparedStatement.setString(6, usuario.getPergunta1());
            
            preparedStatement.execute();
            System.out.println("Novo usuario cadastrado");
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
    
    public void deletaUsuario(int id) {
        String QUERY = "UPDATE usuario SET ativo = 0 WHERE usuario.id ="+id;
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
   
   
 
    
    public  List<Usuario> getUsuarioByNameAndPassword(String email, String senha) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        String QUERY = "SELECT * FROM usuario WHERE  email='"+email+"' AND senha='"+Criptografia.criptografa(senha)+"' AND ativo != 0";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            
            resultSet = preparedStatement.executeQuery(QUERY);
            
            while (resultSet.next()) {
                
                Usuario usuario = new Usuario();
                
                usuario.setId(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setCpf(resultSet.getInt("cpf"));
                usuario.setEndereco(resultSet.getString("endereco"));
                usuario.setPergunta1(resultSet.getString("pergunta1"));
                usuario.setSenha(resultSet.getString("senha"));
            
                usuarios.add(usuario);
               
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
        return usuarios;
    }
    
    public List<Usuario> getUsuario() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        String QUERY = "SELECT * FROM usuario WHERE ativo != 0";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            
            resultSet = preparedStatement.executeQuery(QUERY);
            
            while (resultSet.next()) {
                
                Usuario usuario = new Usuario();
                
                usuario.setId(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setCpf(resultSet.getInt("cpf"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setEndereco(resultSet.getString("endereco"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setPergunta1(resultSet.getString("pergunta1"));
                usuario.setAtivo(resultSet.getInt("ativo"));
                
                usuarios.add(usuario);
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
        return usuarios;
    }
    
    public void alteraUsuarioByAtributo(int id, String nome, int cpf, String email, String endereco, String senha, String pergunta1){
        senha = Criptografia.criptografa(senha);
        String QUERY = "UPDATE usuario SET nome = '"+nome+"', cpf = "+cpf+", email = '"+email+"', endereco = '"+endereco+"', senha = '"+senha+"', pergunta1 = '"+pergunta1+"' WHERE id="+id;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {   
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY);
            preparedStatement.execute(QUERY);
          
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
        
    public void alteraUsuario(Usuario usuario, int id){
        String QUERY = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, endereco = ?, senha = ?, pergunta1= ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = conexao.ConectaBD.createConnectionMySQL();
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setInt(2, usuario.getCpf());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getEndereco());
            preparedStatement.setString(5, Criptografia.criptografa(usuario.getSenha()));
            preparedStatement.setString(7, usuario.getPergunta1());
            
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
    
    public void alteraUsuarioSenha(String email, int cpf, String pergunta1){
        
        String QUERY = "UPDATE usuario SET senha = '"+Criptografia.criptografa("bolo")+"' WHERE email = '"+email+"' AND cpf ='"+cpf+"' AND pergunta1='"+pergunta1+"'";
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
