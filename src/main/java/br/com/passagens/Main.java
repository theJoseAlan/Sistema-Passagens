package br.com.passagens;

import br.com.passagens.Validacoes.Validacoes;
import br.com.passagens.dao.PassagemDao;
import br.com.passagens.entity.Passagem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Validacoes validacoes = new Validacoes();

        PassagemDao passagemDao = new PassagemDao();

        ArrayList<String> poltronasDisponiveis = new ArrayList();

        int cont =0;
        int  op = 0;

        //Adicionando as poltronas na lista

        for(int i = 1; i<=50; i++){
            poltronasDisponiveis.add(" "+i);
        }

        //Já que no persistence.xml está como create, essa função apaga todos os aquivos criados durante uma execução
        //Caso queira que os dados sejam sempre mantidos, o persistence deve ser alterado de create para update
        // e deletar o código abaixo
        //inicio delete all archives
        File folder = new File("C:\\Users\\user\\Desktop\\Passagens");
        if (folder.isDirectory()) {
            File[] sun = folder.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }
        }
        //fim delete all archives

        //Oculta os warnings do hibernate
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        while (true) {
            cont = 0;
            try {
                System.out.println();
                System.out.println("   _____________\n" +
                        " _/_|[][][][][] | - -\n" +
                        "(      Devs Bus | - -\n" +
                        "=--OO-------OO--=dwb");

                System.out.println("\nReserve aqui sua passagem para 2023! \n");
                System.out.println("========= MENU =========");
                System.out.println("[1] CRIAR\n[2] EXIBIR TODAS\n[3] CONSULTAR\n[4] APAGAR\n[5] POLTRONAS DISPONÍVEIS\n[6] SAIR");
                System.out.print("R: ");

                op = input.nextInt();
                input.nextLine();

                if (op > 5 || op < 1) {
                    System.out.println("Opção inválida");
                }

            } catch (Exception e) {
                System.out.println("ERRO! Digite apenas números!");
                input.nextLine();
            }

            if(op==1) {

                criaPassagem(input, validacoes, passagemDao, cont, poltronasDisponiveis);

            } else if (op==2) {

                listaPassagens(passagemDao);

            }else if(op==3) {

                buscaPassagem(input, passagemDao);

            } else if (op==4) {

                deletaPassagem(input, passagemDao);

            } else if (op==5) {
                System.out.println("Poltronas disponíveis: ");
                for(String poltras : poltronasDisponiveis){
                    System.out.print(poltras+" | ");
                    cont ++;
                    if(cont==26){
                        System.out.println();
                    }
                }

            }else if(op==6){
                break;
            }
        }

    }


    //Métodos (extraídos)

    private static void deletaPassagem(Scanner input, PassagemDao passagemDao) {
        System.out.print("Digite o CPF do passageiro (apenas números): ");
        String cpfDel = input.nextLine();

        if(passagemDao.consultaPassagem(cpfDel)==null){
            System.out.println("Não há nenhuma passagem cadastrada com o cpf: "+cpfDel);
        }else{
            System.out.println("Tem certeza que deseja remover a passagem de " +
                    passagemDao.consultaPassagem(cpfDel).getNomePassageiro() + "?");
            System.out.print("R(S/N): ");
            String confirma = input.nextLine();

            if (confirma.equals("S") || confirma.equals("s")) {

                //Procurando o arquivo
                Path pathOfFile1 = Paths.get("C:\\Users\\user\\Desktop\\Passagens\\Passagem"+cpfDel+".txt");

                //Tentando deletar o arquivo
                try {
                    Files.delete(pathOfFile1);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                passagemDao.removePassagem(cpfDel);
                System.out.println("Passagem APAGADA!");
            } else {
                System.out.println("Passagem Mantida!");
            }
        }
    }

    private static void buscaPassagem(Scanner input, PassagemDao passagemDao) {
        System.out.print("Digite o CPF do passageiro (apenas números): ");
        String cpfFind = input.nextLine();
        Passagem passagemBd = passagemDao.consultaPassagem(cpfFind);

        if(passagemBd == null){
            System.out.println("\nNenhum passageiro encontrado com o cpf: "+cpfFind+"\n");
        }else{
            System.out.println("Passageiro encontrado: "+"\n"+passagemBd);
        }

    }

    private static void listaPassagens(PassagemDao passagemDao) {
        List<Passagem> passagensNoBd = passagemDao.listaPassagens();

        if(passagensNoBd.isEmpty()){
            System.out.println("\nNão há nenhuma passagem cadastrada\n");
        }else{
            System.out.println("===== TODAS AS PASSAGENS =====");
            for (Passagem passagensEncontradas : passagensNoBd){
                System.out.println(passagensEncontradas);
                System.out.println("-----------------------------------");
            }
        }
    }

    private static void criaPassagem(Scanner input, Validacoes validacoes,
                                     PassagemDao passagemDao, int cont,
                                     ArrayList<String> poltronasDisponiveis) {
        int poltrona;
        String telefone;
        int op2;
        String cpf;
        System.out.println("===== CADASTRO =====");
        Passagem passagem = new Passagem();

        while (true){
            System.out.print("Nome: ");
            String nome = input.nextLine();

            if(validacoes.validaNome(nome)){
                passagem.setNomePassageiro(nome);
                break;
            }else{
                System.out.println("Nome inválido! Tente novamente!");
            }
        }

        while (true){

            System.out.print("CPF(apenas números): ");
            cpf = input.nextLine();

            if(passagemDao.cpfCadastrado(cpf)){
                System.out.println("CPF já cadastrado");
            } else if (!validacoes.isCPF(cpf)){
                System.out.print("Erro, CPF invalido!!!\n");
            } else {
                passagem.setCpf(cpf);
                break;

            }
        }

        while (true){

            System.out.print("Telefone: ");
            telefone = input.nextLine();

            if(validacoes.validarTelefone(telefone)){
                passagem.setTelefone(telefone);
                break;
            }else{
                System.out.println("Algo deu errado! Tente novamente");
            }
        }


        while (true){
            try {
                System.out.println("Escolha seu destino: ");
                System.out.println("    DESTINO --------- VALOR");
                System.out.println("1 - São Paulo         (690.00)");
                System.out.println("2 - Rio de Janeiro    (875.00)");
                System.out.println("3 - Brasília          (376.42)");
                System.out.println("4 - Goiás             (640.00)");
                System.out.println("5 - Bahia             (489.00)");
                System.out.print("R: ");
                op2 = input.nextInt();
                input.nextLine();

                if(op2>5||op2<1){
                    System.out.println("Opção inválida!");
                }else{
                    break;
                }
            }catch (Exception e){
                System.out.println("Digite apenas números");
                input.nextLine();
            }
        }

        switch (op2){
            case 1:
                passagem.setOrigem("Piaui");
                passagem.setDestino("São Paulo");
                passagem.setValor(690.00);
                break;

            case 2:
                passagem.setOrigem("Piaui");
                passagem.setDestino("Rio de Janeiro");
                passagem.setValor(875.00);
                break;

            case 3:
                passagem.setOrigem("Piaui");
                passagem.setDestino("Brasília");
                passagem.setValor(376.42);
                break;

            case 4:
                passagem.setOrigem("Piaui");
                passagem.setDestino("Goiás");
                passagem.setValor(640.00);
                break;

            case 5:
                passagem.setOrigem("Piaui");
                passagem.setDestino("Bahia");
                passagem.setValor(489.00);
                break;

            default:
                break;
        }

        //Data da viagem
        while (true) {

            try {


                System.out.println("Data da viagem");

                System.out.print("Dia (apenas números): ");
                int dia = input.nextInt();
                input.nextLine();
                System.out.print("Mês (apenas números): ");
                int mes = input.nextInt();
                input.nextLine();

                if (validacoes.validaData(dia, mes)) {
                    String dataViagem = dia+"/"+mes+"/2023";
                    passagem.setDataViagem(dataViagem);
                    break;
                } else {
                    System.out.println("Data inválida! Tente novamente!");
                }
            }catch (Exception e){
                System.out.println("Algo deu errado. Tente novamente!");
                input.nextLine();
            }
        }


        //fim data viagem

        while (true){
            cont = 0;
            try {
                System.out.println("Poltronas disponíveis: ");
                for(String poltras : poltronasDisponiveis){
                    System.out.print(poltras+" | ");
                    cont ++;
                    if(cont==26){
                        System.out.println();
                    }
                }

                System.out.println();
                System.out.print("Escolha uma poltrona (1 - 50): ");
                poltrona = input.nextInt();
                input.nextLine();

                if(poltrona<1 || poltrona>50){

                    System.out.println("Inválido");

                }else if (!poltronasDisponiveis.contains(" "+poltrona)){

                    System.out.println("Essa poltrona já está reservada");

                }else{
                    poltronasDisponiveis.set(poltrona-1, "X");
                    passagem.setPoltrona(poltrona);
                    break;

                }

            }catch (Exception e){
                System.out.println("Digite apenas números inteiros");
                input.nextLine();
            }
        }

        passagemDao.createPassagem(passagem);

        //Inicio de salvamento dos dados em arquivo txt
        FileWriter arq;
        try {
            String nomeArq = "C:\\Users\\user\\Desktop\\Passagens\\Passagem"+passagem.getCpf()+".txt";
            arq = new FileWriter(nomeArq);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("REGISTRO DE PASSAGEM\n");

        gravarArq.printf(String.valueOf(passagem));

        try {
            arq.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Fim do salvamento em arquivo local

        System.out.println("Passagem criada");
    }
}