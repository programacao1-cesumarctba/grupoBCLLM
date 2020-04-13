/**
 * Classe que armazena os Dados do Atual Jogador.
 */

package com.centuri123.jogo;

public class Jogador {
	
	private int RA;
	private String nome;
	private int senha;
	
	public Jogador() {
		
	}
	
	public Jogador(int RA, String nome, int senha) {
		this.setNome(nome);
		this.setRA(RA);
		this.setSenha(senha);
	}
	
	public Jogador(int RA, int senha) {
		this.setRA(RA);
		this.setSenha(senha);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getRA() {
		return RA;
	}
	
	public void setRA(int RA) {
		this.RA = RA;
	}
	
	public int getSenha() {
		return this.senha;
	}
	
	public void setSenha(int senha) {
		this.senha = senha;
	}
	
}
