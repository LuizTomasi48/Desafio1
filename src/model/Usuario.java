/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 7915772
 */
public class Usuario extends Pessoa {
    
   

    public Usuario(String nome, int cpf, String email, String endereco, String senha, String pergunta1) {
        super(nome, cpf, email, endereco, senha, pergunta1);
    }

    public Usuario() {
        
    }
    
}
