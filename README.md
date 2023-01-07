# Sistema gerenciador de passagem de ônibus

- Sistema desenvolvido em Java
- Produzido para testar meu próprio nível de resolução de problemas. 
- Implementei o máximo que pude em tratativas de erros e exessões que possam ser geradas pelo sistema ou pelo usuário

## ✒️ Autor

* 🤖 José Alan Vieira Sales
 - ✉ E-mail: salesalan854@gmail.com
 - 📞 Whatsapp: (88) 988695336

## 🛠️ Construído com

* P.O.O
* [Hibernate](https://hibernate.org) 
* [Maven](https://maven.apache.org/) 
* [Postgresql](https://www.postgresql.org)

## ⭐ Uma breve descrição
* O sistema possui uma entidade: Passagem
* Com os seguintes atributos:
- 📃 Passagem -> dataEmissao (Date); nomePassageiro (String); cpf (String/id); telefone (String); origem (String); destino (String)
                 dataViagem (String); poltrona (int); valor (double);
                 
- Há um menu principal numerado de 1 a 6, onde há as seguintes opções: (Criar, Exibir todas, Colsultar, Apagar, Poltronas disponíveis, Sair)

- Os valores dataEmissao, origem e valor não são inseridos pelo user. Mas origem e valor podem ser facilmente alterados para inserção
(o sistema está apto para realizar essa mudança, alterando pouca coisa no código fonte)

- Os seguintes atributos passam por uma validação (antes de cadastrados no banco): Nome, CPF, Número, Data da viagem

## ⚙️ Operações
* Criar, Listar todos os cadastros, Colsultar a partir do Id (cpf), remover;
* Operação adcional: listar poltronas disponíveis
---
### 👀 Observações:
- Não é possível:

1. Criar um nome inválido (ex: 4lan ou Al$n)
2. Inserir o ano (o sistema funciona apenas para 2023 (predefinição que pode ser alterada facilmente)
3. Cadastrar o mesmo CPF em duas passagens
4. Escolher uma poltrona que já está reservada

Se algum dos casos anteriores for disparado, o sistema irá identificar o erro e pedir novamente a inserção daquele campo

Os dados são salvos no bd e em um arquivo de texto na área de trabalho, dentro de uma pasta (passagens)

Se desejar, teste o código e fale comigo sobre possíveis melhorias/adaptações ou correções :-)
