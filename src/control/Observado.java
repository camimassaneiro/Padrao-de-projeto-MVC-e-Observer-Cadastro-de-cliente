/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import observer.Observador;

/**
 *
 * @author camil
 */
public interface Observado {
    
    void adicionarCliente(String nome, String sexo);
    public void removerCliente(int id);
    public void editarCliente(int id, String nome, String sexo);
    public void addObservador(Observador obs);
}
