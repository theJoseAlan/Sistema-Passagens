package br.com.passagens.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Passagem {

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao = new java.sql.Date(System.currentTimeMillis());

    @Column(name = "nome_Passageiro", nullable = false)
    private String nomePassageiro;


    @Column(nullable = false, unique = true)
    @Id
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String origem;

    @Column(nullable = false)
    private String destino;

    @Column(name = "data_Viagem", nullable = false)
    private String dataViagem;

    @Column(nullable = false)
    private int poltrona;

    @Column
    private Double valor;


    public Passagem(){

    }

    public Passagem(String nomePassageiro, String cpf,
                    String telefone, String origem, String destino,
                    String dataViagem, int poltrona, Double valor) {
        this.nomePassageiro = nomePassageiro;
        this.cpf = cpf;
        this.telefone = telefone;
        this.origem = origem;
        this.destino = destino;
        this.dataViagem = dataViagem;
        this.poltrona = poltrona;
        this.valor = valor;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getNomePassageiro() {
        return nomePassageiro;
    }

    public void setNomePassageiro(String nomePassageiro) {
        this.nomePassageiro = nomePassageiro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(String dataViagem) {
        this.dataViagem = dataViagem;
    }

    public int getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(int poltrona) {
        this.poltrona = poltrona;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "\nData da emissão: " + dataEmissao +
                "\nNOME: " + nomePassageiro +
                "\nCPF: " + cpf +
                "\nTELEFONE: " + telefone +
                "\nOrigem: " + origem +
                " | Destino: " + destino +
                "\nData da Viagem: " + dataViagem +
                "\nPOLTRONA: " + poltrona +
                "\nVALOR: " + valor;
    }
}
