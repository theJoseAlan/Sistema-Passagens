package br.com.passagens.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
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


    @Override
    public String toString() {
        return "\nData da emissão: ................ " + dataEmissao +
                "\nNOME: ........................... " +nomePassageiro+
                "\nCPF: ............................ "+ cpf +
                "\nTELEFONE: ....................... " + telefone +
                "\nVIAGEM .......................... Origem: " + origem +
                " | Destino: " + destino +
                "\nData da Viagem: ................. " + dataViagem +
                "\nPOLTRONA: ....................... " + poltrona +
                "\nVALOR: .......................... " + valor;
    }
}
