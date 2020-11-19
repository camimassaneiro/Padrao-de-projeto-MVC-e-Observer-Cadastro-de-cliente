package model;


public class Cliente {

	private String nome, sexo;

	public Cliente(String nome, String sexo) {
		this.nome = nome;
		this.sexo = sexo;
	}
	
	public String getNome() {  return nome;  }
	
	public String getSexo() {  return sexo;  }
	
	public String toString() { 	return nome; }
        

}
