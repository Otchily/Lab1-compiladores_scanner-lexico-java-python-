# LAB 1 - Scanner, Analisador Léxico, RegExp e Autômato Finito

> Projeto desenvolvido para a disciplina de **Compiladores** — **PUC-SP**  
> **Integrantes:** **Joao Alfredo** / **Leonardo Souza Silva**

---

## Sobre o projeto

Este repositório reúne a entrega do **LAB 1** da disciplina de Compiladores, com foco em **análise léxica**, **expressões regulares** e **autômatos finitos**.

O objetivo principal do laboratório foi compreender, na prática, como um **scanner** lê um fluxo de caracteres, reconhece **lexemas** e os transforma em **tokens**, estabelecendo a ponte entre a base teórica estudada em aula e sua implementação em código.

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
