/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

/**
 *
 * @author camil
 */
public interface Observador  {
    
    void notificarAddCliente(int id, String nome,String sexo);
    void notificarRemoverCliente(int id);
    void notificarEditarCliente(int id, String nome, String sexo);
    
}
