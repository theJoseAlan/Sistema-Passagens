package br.com.passagens;

import br.com.passagens.ValidaCPF.ValidaCPF;
import br.com.passagens.dao.PassagemDao;
import br.com.passagens.entity.Passagem;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        PassagemDao passagemDao = new PassagemDao();

        String cpf;

        System.out.println("========= MENU =========");
        System.out.println("[1] CRIAR\n[2] EXIBIR TODAS\n[3] CONSULTAR\n[4] APAGAR\n[5] SAIR");
        System.out.print("R: ");
        int op = input.nextInt();
        input.nextLine();

        switch(op) {
            case 1:
                Passagem passagem = new Passagem();

                System.out.print("Nome: ");
                String nome = input.nextLine();

                while (true){

                    System.out.print("CPF(apenas números): ");
                    cpf = input.nextLine();
                    if (ValidaCPF.isCPF(cpf) == true){
                        passagem.setCpf(cpf);
                        break;
                    } else {
                        System.out.printf("Erro, CPF invalido !!!\n");
                    }
                }

                System.out.print("Telefone: ");
                String telefone = input.nextLine();

                System.out.println("Escolha seu destino: ");
                System.out.println("    DESTINO --------- VALOR");
                System.out.println("1 - São Paulo         (690.00)");
                System.out.println("2 - Rio de Janeiro    (875.00)");
                System.out.println("3 - Brasília          (376.42)");
                System.out.println("4 - Goiás             (640.00)");
                System.out.println("5 - Bahia             (489.00)");
                System.out.println("R: ");
                int op2 = input.nextInt();
                input.nextLine();

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

                System.out.print("Qual a data da sua viagem (dd/mm/aaaa)? ");
                String dataViagem = input.nextLine();

                System.out.print("Escolha uma poltrona (1 - 50): ");
                int poltrona = input.nextInt();
                input.nextLine();

                passagem.setNomePassageiro(nome);
                passagem.setTelefone(telefone);
                passagem.setDataViagem(dataViagem);
                passagem.setPoltrona(poltrona);


                passagemDao.createPassagem(passagem);

                System.out.println("Passagem criada");

                break;

            case 2:
                List<Passagem> passagensNoBd = passagemDao.listaPassagens();
                System.out.println("===== TODAS AS PASSAGENS =====");
                for (Passagem passagensEncontradas : passagensNoBd){
                    System.out.println(passagensEncontradas);
                }
                break;
            case 3:
                System.out.print("Digite o CPF do passageiro: ");
                String cpfFind = input.nextLine();
                Passagem passagemBd = passagemDao.consultaPassagem(cpfFind);
                System.out.println("Passageiro encontrado: "+"\n"+passagemBd);
                break;

            case 4:
                System.out.print("Digite o CPF do passageiro: ");
                String cpfDel = input.nextLine();
                System.out.println("Tem certeza que deseja remover a passagem de "+passagemDao.consultaPassagem(cpfDel).getNomePassageiro()+"?");
                System.out.print("R(S/N): ");
                String confirma = input.nextLine();

                if(confirma.equals("S")){
                    passagemDao.removePassagem(cpfDel);
                    System.out.println("PAssagem APAGADA!");
                }else{
                    System.out.println("PAssagem Mantida!");
                }

                break;
        }

    }
}