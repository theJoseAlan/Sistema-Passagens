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

import static br.com.passagens.Operations.PassagemMethods.criaPassagem;

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
        //File folder = new File("C:\\Users\\user\\Desktop\\Passagens");
        File folder = new File("C:\\Users\\Jose Alan\\Desktop\\Passagens");
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

                if (op > 6 || op < 1) {
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
                        Path pathOfFile1 = Paths.get("C:\\Users\\Jose Alan\\Desktop\\Passagens"+cpfDel+".txt");

                        //Tentando deletar o arquivo
                        try {
                            Files.delete(pathOfFile1);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        poltronasDisponiveis.set(passagemDao.consultaPassagem(cpfDel).getPoltrona()-1,
                                " "+passagemDao.consultaPassagem(cpfDel).getPoltrona());
                        passagemDao.removePassagem(cpfDel);
                        System.out.println("Passagem APAGADA!");
                    } else {
                        System.out.println("Passagem Mantida!");
                    }
                }

                //deletaPassagem(input, passagemDao);

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


}