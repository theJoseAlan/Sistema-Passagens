package br.com.passagens;

import br.com.passagens.Validacoes.Validacoes;
import br.com.passagens.dao.PassagemDao;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static br.com.passagens.Operations.PassagemMethods.*;

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
        File folder = new File("C:\\Users\\Jose Alan\\Desktop\\Passagens\\Passagem");
        if (folder.isDirectory()) {
            File[] sun = folder.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }
        }
        //fim delete all archives


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

                deletaPassagem(input, passagemDao, poltronasDisponiveis);

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

}