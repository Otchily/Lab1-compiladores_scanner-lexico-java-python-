# LAB 1 - Scanner - Analisador Léxico - RegExp - Autômato Finito

## Identificação

- **Faculdade:** Pontifícia Universidade Católica de São Paulo (PUC-SP)
- **Curso:** Ciência da Computação
- **Disciplina:** Compiladores
- **Laboratório:** LAB 1 - Scanner - Analisador Léxico - RegExp - Autômato Finito
- **Integrantes:** Joao Alfredo / Leonardo Souza Silva

---

## 1. Introdução

A análise léxica constitui a primeira fase do processo de compilação. Nessa etapa, o compilador percorre o fluxo de caracteres do programa-fonte, identifica sequências com significado e as transforma em unidades léxicas chamadas *tokens*. Em termos formais, essa fase se apoia em expressões regulares, gramáticas regulares e autômatos finitos, que fornecem a base teórica para o reconhecimento eficiente de padrões textuais. [Source](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)

O presente laboratório teve como objetivo aproximar a formulação teórica estudada nos capítulos iniciais da disciplina de uma experiência prática de implementação. Para isso, foram realizadas atividades envolvendo Bash, expressões regulares, testes em ferramentas online, construção de mini-scanners em Python e Java, experimentação com autômatos finitos no JFLAP/FAT, comparação entre tokenização de compiladores e tokenização de modelos de linguagem, além da tokenização de um texto literário em língua portuguesa. [Source](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)

---

## 2. Objetivos do laboratório

Este laboratório teve como objetivos principais:

- compreender o papel do scanner na fase inicial da compilação;
- relacionar expressões regulares com o reconhecimento de tokens;
- visualizar a implementação operacional de autômatos finitos;
- implementar um analisador léxico simplificado em Python e Java;
- aplicar os conceitos estudados a um texto em língua portuguesa;
- exercitar documentação técnica e testes unitários.

---

## 3. Atividade 1 - Bash no Terminal Ubuntu: simulação do fluxo de entrada do scanner

Na primeira atividade, foi desenvolvido um script em Bash com a finalidade de simular o comportamento inicial de um scanner. O programa lê o conteúdo de entrada linha por linha e exibe o material recebido, permitindo observar de forma concreta a ideia de *character stream* descrita na teoria de compiladores. Além disso, foi incluído um pré-processamento elementar para remoção de espaços e tabulações, comportamento compatível com a função de normalização frequentemente associada ao analisador léxico. [Source](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)

### Arquivo utilizado
- `scanner_simples.sh`

### Código
```bash
#!/bin/bash

while IFS= read -r linha; do
    limpa=$(echo "$linha" | tr -d ' \t\r')
    echo "[SCANNER] Linha original: '$linha'"
    echo "[SCANNER] Linha sem espaços/tabs: '$limpa'"
done
```

### Exemplo de entrada
Arquivo: `exemplo.c`

```c
// Exemplo inspirado no Livro do Dragão
position = initial + rate * 60;
if (position > 100) {
    position = position - 1;
}
```

### Comentário analítico

Essa atividade foi importante para tornar visível uma ideia que, em geral, aparece de forma abstrata nos textos introdutórios de compiladores. Ao observar a leitura sequencial da entrada, percebe-se que o scanner não “entende” o programa em alto nível; ele opera inicialmente sobre caracteres, preparando o caminho para o reconhecimento posterior de lexemas e tokens.

### Evidência
- Inserir print da execução no terminal: `imagens/terminal_scanner.png`

---

## 4. Atividade 2 - Expressões Regulares online

Na segunda atividade, foram utilizadas as plataformas Regexr e Regex101 para experimentar o reconhecimento de padrões léxicos básicos sobre a expressão clássica `position = initial + rate * 60`. O objetivo foi demonstrar que categorias como identificadores, números e operadores podem ser descritas de forma compacta por expressões regulares, reforçando a ligação entre teoria formal e implementação prática do scanner. [Source](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)

### Expressões regulares utilizadas

**Identificador**
```regex
[a-zA-Z_][a-zA-Z0-9_]*
```

**Número inteiro**
```regex
\d+
```

**Operadores**
```regex
[=+\-*]
```

**Espaços em branco**
```regex
\s+
```

**Regex única para capturar os tokens relevantes**
```regex
[a-zA-Z_][a-zA-Z0-9_]*|\d+|[=+\-*]
```

### Comentário analítico

Os testes evidenciaram que expressões regulares oferecem um mecanismo suficientemente expressivo para descrever classes léxicas simples, como identificadores e literais numéricos. Ao aplicar uma expressão composta em modo global, foi possível reproduzir a separação em lexemas que seria esperada de um analisador léxico elementar, o que demonstra a adequação das linguagens regulares para essa fase do compilador.

### Evidências
- Inserir print do Regexr: `imagens/regexr.png`
- Inserir print do Regex101: `imagens/regex101.png`

---

## 5. Atividade 3 - Find/Replace com regex em editores de texto

A terceira atividade buscou mostrar que expressões regulares têm aplicação prática para além da implementação de compiladores. Foram realizados testes de busca e substituição em arquivos-texto, simulando tarefas de limpeza e transformação de dados semelhantes às operações preliminares que um scanner executa ao ignorar elementos irrelevantes ou adaptar sequências simbólicas segundo regras formais. [Source](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)

### Exemplos utilizados

**Remoção de comentários de linha**
```regex
//.*
```

**Remoção de comentários de bloco**
```regex
/\*[\s\S]*?\*/
```

**Substituição controlada de `=` por `:=`**
```regex
(?<![=!<>])=(?![=])
```

### Comentário analítico

Essa etapa mostrou que o uso de regex não se restringe à teoria de compiladores. A possibilidade de localizar padrões, remover ruído textual e substituir símbolos de maneira controlada revela como os mecanismos formais estudados na disciplina têm aplicações diretas no processamento de dados e no tratamento de arquivos reais.

### Evidência
- Inserir print da operação de busca/substituição: `imagens/find_replace_regex.png`

---

## 6. Atividade 4 - Implementação de scanner em Python e Java

A quarta atividade consistiu na construção de um mini-scanner em Python e Java. A ideia central foi utilizar uma expressão regular composta para reconhecer diferentes classes de token e, a partir disso, produzir uma sequência organizada de saídas léxicas. A atividade aproxima a implementação do comportamento descrito em aula, no qual o scanner reconhece padrões e devolve uma estrutura mais adequada para as fases posteriores do compilador. [Source](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)

### 6.1 Implementação em Python

No caso de Python, foi adotada a estratégia de compor uma expressão regular mestre com grupos nomeados e percorrer o texto com `re.finditer()`. Essa abordagem é coerente com a documentação oficial da linguagem, que ilustra o uso de regex para construção de tokenizadores simples. [Source](https://docs.python.org/3/library/re.html)

**Arquivo principal**
- `python/scanner_pt.py`

**Descrição resumida da implementação**
- reconhecimento de palavras, números, operadores, pontuação, traços e reticências;
- descarte de espaços em branco;
- classificação léxica simplificada de algumas palavras do português;
- geração de lista de tuplas `(lexema, tipo)` e também de lista simples de strings.

### 6.2 Implementação em Java

Na versão em Java, foi utilizada a infraestrutura da biblioteca `java.util.regex`, com `Pattern` para compilação da expressão regular e `Matcher` para percorrer as ocorrências encontradas no texto. Esse fluxo segue o padrão documentado oficialmente pela linguagem para processamento de expressões regulares. [Source](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html)

**Arquivo principal**
- `java/ScannerPT.java`

**Descrição resumida da implementação**
- definição de expressão regular composta;
- varredura sequencial do texto por meio de `Matcher`;
- classificação simplificada de palavras e sinais;
- saída em formato de lista de tokens.

### Comentário analítico

A implementação nas duas linguagens permitiu observar que a lógica léxica fundamental permanece a mesma, embora os mecanismos sintáticos das bibliotecas sejam distintos. Em ambos os casos, a etapa essencial consiste em definir padrões formais, percorrer o texto e converter trechos reconhecidos em unidades significativas. Esse paralelismo entre Python e Java reforça a natureza conceitual do scanner como componente da arquitetura de compiladores, independentemente da linguagem de programação escolhida.

### Evidências
- Inserir print da execução em Python: `imagens/execucao_python.png`
- Inserir print da execução em Java: `imagens/execucao_java.png`

---

## 7. Atividade 5 - Autômatos finitos com JFLAP e FAT

A quinta atividade foi dedicada à visualização da base formal do scanner por meio de autômatos finitos. Foram construídos autômatos para reconhecer números inteiros, identificadores e a distinção entre os operadores `=` e `==`. Também foi observada a conversão de NFA para DFA, o que evidencia como a teoria de linguagens formais é operacionalizada em ferramentas concretas. [Source](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)

### Modelos construídos
- DFA para números inteiros;
- DFA para identificadores;
- NFA para os operadores `=` e `==`;
- conversão de NFA para DFA;
- simulação de reconhecimento de cadeias.

### Comentário analítico

A atividade com o JFLAP/FAT foi particularmente relevante porque traduziu uma definição abstrata em estrutura visual e executável. O autômato de inteiros mostrou uma forma simples de reconhecimento por repetição de dígitos, enquanto o autômato para identificadores exigiu o tratamento diferenciado do primeiro símbolo em relação aos seguintes. Já o caso de `=` e `==` tornou clara a utilidade do não determinismo como modelo intermediário antes da determinização.

### Evidências
- `imagens/jflap_dfa_inteiros.png`
- `imagens/jflap_dfa_identificadores.png`
- `imagens/jflap_nfa_igual_igualigual.png`
- `imagens/fat.png`

---

## 8. Atividade 6 - OpenAI Tokenizer versus tokens de compilador

Nesta atividade, foi realizada uma comparação entre a tokenização obtida por um tokenizer de modelo de linguagem e a tokenização produzida no contexto de compiladores. O objetivo foi discutir as diferenças conceituais entre os dois processos, ainda que ambos utilizem a palavra “token”. [Source](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)

### Discussão

O tokenizer utilizado por modelos de linguagem opera com finalidade estatística e representacional. Em geral, ele fragmenta sequências textuais em subunidades que favorecem compressão, reuso frequente e eficiência de modelagem. Por isso, uma palavra como `position` pode ser segmentada em partes menores, como `pos` e `ition`, sem que isso constitua erro do ponto de vista do modelo.

Já no caso de compiladores, um token léxico deve corresponder a uma categoria formal prevista pela linguagem, como identificador, operador, palavra reservada ou literal numérico. O reconhecimento precisa ser preciso, estável e consistente com a gramática regular adotada pela linguagem-fonte. Em outras palavras, enquanto o token de LLM atende a uma lógica estatística de codificação textual, o token léxico do compilador atende a uma lógica estrutural e sintática.

### Resposta objetiva às perguntas propostas

**Por que o tokenizer da OpenAI pode quebrar `position` em partes?**  
Porque sua finalidade não é respeitar categorias léxicas de uma linguagem formal, mas sim segmentar o texto em subunidades frequentes e eficientes para representação estatística.

**Qual é a diferença conceitual entre token léxico e token de LLM?**  
O token léxico é definido por regras formais da linguagem e precisa servir às etapas de análise sintática e semântica. Já o token de LLM é uma unidade de codificação textual orientada à modelagem probabilística da linguagem natural.

### Evidência
- Inserir print da ferramenta de tokenização: `imagens/openai_tokenizer.png`

---

## 9. Atividade 7 - Tokenização de livro em português

Na sétima atividade, foi realizada a tokenização de um texto literário em língua portuguesa, conforme solicitado no enunciado. O objetivo foi adaptar o scanner para operar não sobre uma expressão curta de linguagem de programação, mas sobre um texto mais extenso e linguisticamente variado, contendo palavras acentuadas, pontuação, contrações e estruturas típicas do português. [Source](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)

### Arquivos utilizados

**Entrada**
- `livro/input/livro.txt`

**Saídas geradas**
- `livro/output/tokens_python.txt`
- `livro/output/tokens_java.txt`

### Exemplo de saída esperada

```python
["Do", "título", ".", "Uma", "noite", "destas", ",", "vindo", "da", "cidade", "para", "o", "Engenho", "Novo", ","]
```

### Comentário analítico

A tokenização de texto em português evidenciou tanto a utilidade quanto os limites de um scanner baseado exclusivamente em expressões regulares. Por um lado, foi possível separar adequadamente palavras, sinais de pontuação e números. Por outro, a classificação gramatical detalhada de itens lexicais da língua natural mostrou-se mais complexa, especialmente quando se consideram ambiguidades morfológicas e contextuais. Assim, a atividade confirmou que o uso de expressões regulares é bastante eficaz para a segmentação lexical, mas insuficiente para uma análise linguística completa.

---

## 10. Testes unitários

O laboratório também exigiu a construção de testes unitários em Python e Java, com uso de PyUnit (`unittest`) e JUnit. Essa etapa foi importante para validar o comportamento do scanner, garantindo maior confiabilidade às implementações. [Source](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)

### 10.1 Testes em Python

A suíte de testes em Python foi implementada com a biblioteca `unittest`, que organiza verificações em classes derivadas de `TestCase` e permite a definição de cenários de validação específicos. Essa estrutura segue a documentação oficial da linguagem. [Source](https://docs.python.org/3/library/unittest.html)

**Arquivo**
- `python/test_scanner_pt.py`

**Cenários verificados**
- tokenização básica de expressão simples;
- reconhecimento de pontuação;
- classificação simplificada de alguns itens da língua portuguesa.

### 10.2 Testes em Java

Na implementação em Java, os testes foram escritos com JUnit, biblioteca consolidada para testes automatizados no ecossistema da linguagem. O uso de anotações e asserções facilitou a verificação objetiva das saídas esperadas. [Source](https://junit.org/junit5/docs/current/user-guide/)

**Arquivo**
- `java/ScannerPTTest.java`

**Cenários verificados**
- tokenização básica;
- reconhecimento de pontuação;
- comparação entre listas esperadas e listas efetivamente produzidas.

### Comentário analítico

A incorporação de testes unitários contribuiu para elevar a qualidade técnica do laboratório, uma vez que permitiu verificar não apenas a execução dos programas, mas também a correção de comportamentos específicos. Em atividades relacionadas a compiladores, esse cuidado é especialmente importante, pois pequenos desvios no reconhecimento léxico podem comprometer toda a cadeia de processamento posterior.

---

## 11. Reflexão dos integrantes

### Joao Alfredo

O aspecto mais interessante deste laboratório foi perceber que o analisador léxico, apesar de frequentemente ser apresentado como uma etapa introdutória do compilador, já concentra uma base formal bastante rigorosa. A relação entre expressão regular, autômato finito e reconhecimento de tokens tornou-se mais clara quando passamos da teoria para a implementação concreta. Também considerei relevante observar que decisões aparentemente simples, como separar palavras e símbolos, exigem critérios formais bem definidos para que o processo de compilação permaneça consistente.

### Leonardo Souza Silva

Para mim, o ponto mais significativo foi entender que o scanner não interpreta o significado global do texto, mas organiza o fluxo de caracteres em unidades reconhecíveis segundo regras precisas. O uso de regex e autômatos finitos mostrou que há uma estrutura matemática por trás de uma etapa que, à primeira vista, poderia parecer apenas operacional. Além disso, a comparação com o tokenizer de modelos de linguagem foi útil para distinguir entre tokenização estatística e tokenização formal, evidenciando finalidades completamente diferentes.

---

## 12. Conclusão

O desenvolvimento deste laboratório permitiu consolidar, de forma prática, conceitos fundamentais da análise léxica estudados na disciplina de Compiladores. As atividades mostraram que o scanner não é apenas um mecanismo auxiliar, mas um componente formalmente estruturado, baseado em expressões regulares, gramáticas regulares e autômatos finitos. [Source](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)

Além disso, a implementação em Python e Java, acompanhada de testes unitários e experimentos com ferramentas visuais, reforçou a ligação entre teoria e prática. A tokenização de texto em língua portuguesa também ampliou a compreensão sobre o alcance e as limitações do paradigma léxico quando aplicado fora do contexto estritamente das linguagens de programação. De modo geral, o laboratório contribuiu para uma compreensão mais madura do papel da análise léxica dentro da arquitetura de um compilador.

---

## 13. Estrutura dos arquivos do projeto

```text
LAB1/
├── README.md
├── relatorio_lab1.md
├── scanner_simples.sh
├── exemplo.c
├── imagens/
├── livro/
│   ├── input/
│   │   └── livro.txt
│   └── output/
│       ├── tokens_python.txt
│       └── tokens_java.txt
├── python/
│   ├── scanner_pt.py
│   └── test_scanner_pt.py
└── java/
    ├── ScannerPT.java
    ├── ScannerPTTest.java
    └── pom.xml
```

---

## 14. Referências

- Enunciado oficial do laboratório: [PUC-SP Compilers - LAB1](https://raw.githubusercontent.com/danielscarvalho/PUC-SP-Compilers/main/LAB1.md)
- Documentação oficial de regex em Python: [Python `re`](https://docs.python.org/3/library/re.html)
- Documentação oficial de testes em Python: [Python `unittest`](https://docs.python.org/3/library/unittest.html)
- Documentação oficial de regex em Java: [Java Pattern](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html)
- Guia oficial do JUnit 5: [JUnit User Guide](https://junit.org/junit5/docs/current/user-guide/)
- Tutorial do JFLAP: [JFLAP](https://www.jflap.org/tutorial/)
- FAT - Finite Automata Tool: [FAT](http://cl-informatik.uibk.ac.at/software/fat/)
