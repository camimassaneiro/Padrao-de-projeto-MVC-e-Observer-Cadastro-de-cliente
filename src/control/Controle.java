/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import observer.Observador;

/**
 *
 * @author camil
 */
public class Controle implements Observado{
    
    List <Cliente> clientes = new ArrayList<>();
    List <Observador> listObservadores = new ArrayList<>();
    
    public void adicionarCliente(String nome, String sexo){
        Cliente cliente = new Cliente(nome, sexo);
        clientes.add(cliente);        
        
        for(Observador obs: listObservadores){
          obs.notificarAddCliente(clientes.size(), nome, sexo);  
        }        
    }
    
    public void removerCliente(int id){        
        clientes.remove(id);
        
        for(Observador obs: listObservadores){
            obs.notificarRemoverCliente(id);
        }
    }
    
    public void editarCliente(int id, String nome, String sexo){
        Cliente cliente = clientes.get(id);
        Cliente clienteAtualizado = new Cliente(nome, sexo);
        clientes.add(id, clienteAtualizado);
        clientes.remove(cliente);
        
        for(Observador obs: listObservadores){
            obs.notificarEditarCliente(id, nome, sexo);
        }        
    }
    
    public void addObservador(Observador obs){
        listObservadores.add(obs);
    }
    public void removeObservador(Observador obs){
        listObservadores.remove(obs);
    }
    
    
    
    
}
