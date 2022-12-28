package br.com.passagens.dao;

import br.com.passagens.entity.Passagem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class PassagemDao {

    private EntityManager manager;


    public PassagemDao(){
        this.manager = Persistence.createEntityManagerFactory("passagens").createEntityManager();
    }

    //Create
    public boolean createPassagem(Passagem passagem){
        this.manager.getTransaction().begin();
        this.manager.persist(passagem);
        this.manager.getTransaction().commit();

        return true;
    }

    //Read

    //Listagem
    public List listaPassagens(){
        Query query = manager.createQuery("SELECT p FROM Passagem as p");
        return query.getResultList();
    }

    //Consulta
    public Passagem consultaPassagem(String cpf){
        return this.manager.find(Passagem.class, cpf);
    }

    //Delete
    public boolean removePassagem(String cpf){
        Passagem passagem = this.manager.find(Passagem.class, cpf);

        if(passagem==null){
            return false;
        }

        this.manager.getTransaction().begin();
        this.manager.remove(passagem);
        this.manager.getTransaction().commit();

        return true;

    }

}