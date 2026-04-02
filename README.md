# LAB 1 - Scanner, Analisador Léxico, RegExp e Autômato Finito

![PUC-SP](https://img.shields.io/badge/PUC--SP-Compiladores-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/status-pronto%20para%20entrega-success?style=for-the-badge)
![Python](https://img.shields.io/badge/Python-3.x-yellow?style=for-the-badge&logo=python)
![Java](https://img.shields.io/badge/Java-JDK-red?style=for-the-badge&logo=openjdk)
![Maven](https://img.shields.io/badge/Maven-build-C71A36?style=for-the-badge&logo=apachemaven)
![Tests](https://img.shields.io/badge/tests-Python%20%2B%20JUnit-success?style=for-the-badge)

> Projeto desenvolvido para a disciplina de **Compiladores** da **PUC-SP**  
> **Integrantes:** **Joao Alfredo** / **Leonardo Souza Silva**

---

## Sumário

- [Sobre o projeto](#sobre-o-projeto)
- [Objetivos do laboratório](#objetivos-do-laboratório)
- [Estrutura do repositório](#estrutura-do-repositório)
- [Conteúdo entregue](#conteúdo-entregue)
  - [1. Script Bash](#1-script-bash)
  - [2. Scanner em Python](#2-scanner-em-python)
  - [3. Scanner em Java](#3-scanner-em-java)
  - [4. Testes unitários](#4-testes-unitários)
  - [5. Relatório em Markdown](#5-relatório-em-markdown)
  - [6. Arquivos de entrada e saída](#6-arquivos-de-entrada-e-saída)
- [Como executar](#como-executar)
  - [Execução do script Bash](#execução-do-script-bash)
  - [Execução do scanner em Python](#execução-do-scanner-em-python)
  - [Testes em Python](#testes-em-python)
  - [Execução do scanner em Java](#execução-do-scanner-em-java)
  - [Testes em Java com Maven](#testes-em-java-com-maven)
- [Evidências visuais](#evidências-visuais)
- [Resultados esperados](#resultados-esperados)
- [Observações importantes](#observações-importantes)
- [Tecnologias e ferramentas utilizadas](#tecnologias-e-ferramentas-utilizadas)
- [Referências](#referências)
- [Status da entrega](#status-da-entrega)
- [Autores](#autores)

---

## Sobre o projeto

Este repositório reúne a entrega do **LAB 1** da disciplina de Compiladores, com foco em **análise léxica**, **expressões regulares** e **autômatos finitos**.

O objetivo central do laboratório foi compreender, na prática, como um **scanner** lê um fluxo de caracteres, reconhece **lexemas** e os transforma em **tokens**, estabelecendo uma ligação direta entre os fundamentos teóricos estudados em aula e sua implementação em código.

Ao longo do trabalho, foram realizadas atividades com:

- Bash no terminal Linux;
- expressões regulares em ferramentas online;
- busca e substituição com regex;
- implementação de scanner em **Python**;
- implementação de scanner em **Java**;
- testes unitários com **PyUnit** e **JUnit**;
- modelagem de autômatos no **JFLAP** e **FAT**;
- comparação entre tokens de compiladores e tokenização de LLM;
- tokenização de um texto em língua portuguesa.

---

## Objetivos do laboratório

Este projeto foi desenvolvido com os seguintes objetivos:

- compreender o papel do analisador léxico na compilação;
- relacionar **regex** com reconhecimento de tokens;
- visualizar a implementação formal com **autômatos finitos**;
- construir um mini-scanner funcional em duas linguagens;
- aplicar os conceitos em um texto em português;
- documentar o experimento com clareza técnica e acadêmica.

---

## Estrutura do repositório

```text
LAB1/
├── README.md
├── relatorio_lab1.md
├── scanner_simples.sh
├── exemplo.c
├── imagens/
│   └── LEIA-ME.txt
├── livro/
│   ├── input/
│   │   └── livro.txt
│   └── output/
│       ├── exemplo_python_tuplas.txt
│       ├── saida_scanner_bash.txt
│       ├── tokens_python.txt
│       ├── tokens_java.txt
│       └── tokens_java_tuplas.txt
├── python/
│   ├── scanner_pt.py
│   └── test_scanner_pt.py
└── java/
    ├── ScannerPT.java
    ├── ScannerPTTest.java
    ├── GerarTokensJava.java
    ├── pom.xml
    └── src/
        ├── main/java/
        └── test/java/
```

---

## Conteúdo entregue

### 1. Script Bash

Arquivo responsável por simular o fluxo de entrada do scanner:

- `scanner_simples.sh`

Esse script lê linhas da entrada padrão e mostra uma versão original e uma versão pré-processada, sem espaços e tabulações.

---

### 2. Scanner em Python

Implementação de um mini-analisador léxico para tokenização de texto:

- `python/scanner_pt.py`

Funcionalidades principais:
- reconhecimento de palavras;
- reconhecimento de números;
- reconhecimento de operadores;
- reconhecimento de pontuação;
- suporte a caracteres acentuados do português;
- geração de lista de strings e lista de tuplas `(lexema, tipo)`.

---

### 3. Scanner em Java

Versão equivalente da implementação em Java:

- `java/ScannerPT.java`
- `java/GerarTokensJava.java`

Funcionalidades principais:
- uso de `Pattern` e `Matcher`;
- reconhecimento léxico com regex composta;
- geração de saídas compatíveis com a versão em Python;
- tokenização de texto em arquivo.

---

### 4. Testes unitários

Foram incluídos testes automatizados para validar as implementações:

- **Python:** `python/test_scanner_pt.py`
- **Java:** `java/ScannerPTTest.java`

Os testes cobrem:
- tokenização básica;
- pontuação;
- classificação léxica simples em português.

---

### 5. Relatório em Markdown

Documentação completa do laboratório:

- `relatorio_lab1.md`

O relatório inclui:
- introdução;
- objetivos;
- descrição das atividades;
- discussão teórica;
- comparação com tokenizer de LLM;
- reflexão dos integrantes;
- conclusão;
- referências.

---

### 6. Arquivos de entrada e saída

O projeto inclui um texto de entrada em português e as saídas geradas pelos programas.

**Entrada**
- `livro/input/livro.txt`

**Saídas**
- `livro/output/tokens_python.txt`
- `livro/output/tokens_java.txt`
- `livro/output/exemplo_python_tuplas.txt`
- `livro/output/tokens_java_tuplas.txt`
- `livro/output/saida_scanner_bash.txt`

---

## Como executar

### Requisitos

Para executar o projeto localmente, recomenda-se ter instalado:

- **Python 3**
- **Java JDK**
- **Maven**
- ambiente Linux, WSL ou terminal compatível

---

### Execução do script Bash

```bash
chmod +x scanner_simples.sh
cat exemplo.c | ./scanner_simples.sh
```

---

### Execução do scanner em Python

```bash
cd python
python3 scanner_pt.py
```

Para gerar a saída a partir do arquivo do livro:

```bash
python3 -c "import sys; sys.path.insert(0, '.'); from scanner_pt import processar_arquivo; processar_arquivo('../livro/input/livro.txt', '../livro/output/tokens_python.txt')"
```

---

### Testes em Python

```bash
cd python
python3 -m unittest discover -s . -p 'test_*.py'
```

---

### Execução do scanner em Java

Compilação manual:

```bash
cd java
javac ScannerPT.java GerarTokensJava.java
java ScannerPT
```

Para gerar a saída do arquivo de entrada:

```bash
java GerarTokensJava ../livro/input/livro.txt ../livro/output/tokens_java.txt
```

---

### Testes em Java com Maven

```bash
cd java
mvn test
```

---

## Evidências visuais

A pasta `imagens/` foi reservada para os prints solicitados no enunciado.  
Antes da entrega final no GitHub, devem ser adicionadas as capturas reais das atividades, como:

- execução no terminal;
- regex funcionando no Regexr e Regex101;
- autômatos no JFLAP;
- testes ou execuções em Python e Java;
- tokenizer da OpenAI;
- captura do FAT.

Arquivo de apoio:
- `imagens/LEIA-ME.txt`

---

## Resultados esperados

Ao executar os programas, o projeto deve produzir:

- leitura e pré-processamento de entrada no Bash;
- tokenização de expressões simples;
- tokenização de texto em português;
- listas de tokens em Python e Java;
- validação automática por testes unitários.

---

## Observações importantes

- O projeto está estruturado para facilitar a organização da entrega no GitHub.
- Os códigos foram mantidos de forma didática, para evidenciar a relação entre teoria e implementação.
- A classificação gramatical de palavras do português foi feita de forma **simplificada**, com fins acadêmicos e experimentais.
- Para a entrega final, é recomendável revisar o relatório e complementar a pasta `imagens/` com as evidências produzidas durante a execução real do laboratório.

---

## Tecnologias e ferramentas utilizadas

- **Bash**
- **Python 3**
- **Java**
- **Maven**
- **PyUnit / unittest**
- **JUnit 5**
- [Regexr](https://regexr.com/)
- [Regex101](https://regex101.com/)
- [JFLAP](https://www.jflap.org/tutorial/)
- [FAT - Finite Automata Tool](http://cl-informatik.uibk.ac.at/software/fat/)

---

## Referências

- Enunciado oficial do laboratório: [LAB1 - PUC-SP Compilers](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)
- Documentação oficial de regex em Python: [Python `re`](https://docs.python.org/3/library/re.html)
- Documentação oficial de testes em Python: [Python `unittest`](https://docs.python.org/3/library/unittest.html)
- Documentação oficial de regex em Java: [Java `Pattern`](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html)
- Guia oficial do JUnit 5: [JUnit User Guide](https://junit.org/junit5/docs/current/user-guide/)

---

## Autores

**Joao Alfredo**  
**Leonardo Souza Silva**
