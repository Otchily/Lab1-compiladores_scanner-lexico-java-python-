import re
from typing import List, Tuple

Token = Tuple[str, str]

TOKEN_SPECIFICATION = [
    ("RETICENCIAS", r"\.\.\."),
    ("TRACO", r"--|—"),
    ("PALAVRA", r"[A-Za-zÀ-ÖØ-öø-ÿ]+(?:-[A-Za-zÀ-ÖØ-öø-ÿ]+)*"),
    ("NUMERO", r"\d+(?:[.,]\d+)?"),
    ("OPERADOR", r"==|!=|<=|>=|[=+\-*/<>]"),
    ("PONTUACAO", r"[.,;:!?()\"'“”‘’\[\]{}]"),
    ("ESPACO", r"\s+"),
    ("OUTRO", r".")
]

TOK_REGEX = "|".join(f"(?P<{name}>{pattern})" for name, pattern in TOKEN_SPECIFICATION)

ARTIGOS = {
    "o", "a", "os", "as", "um", "uma", "uns", "umas"
}

PRONOMES = {
    "eu", "tu", "ele", "ela", "nós", "nos", "vós", "vos",
    "eles", "elas", "me", "te", "se", "lhe", "lhes", "mim",
    "comigo", "ti", "contigo", "si", "consigo", "isto", "isso",
    "aquilo", "este", "esta", "esse", "essa", "aquele", "aquela",
    "quem", "que", "qual", "quais"
}

PREPOSICOES = {
    "de", "do", "da", "dos", "das", "em", "no", "na", "nos", "nas",
    "por", "para", "a", "ao", "à", "às", "até", "com", "sem", "sob",
    "sobre", "entre"
}

CONJUNCOES = {
    "e", "ou", "mas", "porém", "todavia", "contudo", "porque", "pois",
    "embora", "quando", "se"
}


def classificar_palavra(palavra: str) -> str:
    p = palavra.lower()

    if p in ARTIGOS:
        return "ARTIGO"
    if p in PRONOMES:
        return "PRONOME"
    if p in PREPOSICOES:
        return "PREPOSICAO"
    if p in CONJUNCOES:
        return "CONJUNCAO"
    if p.endswith(("ar", "er", "ir", "ando", "endo", "indo", "ava", "iam")):
        return "VERBO?"
    if p.endswith("mente"):
        return "ADV?"
    return "PALAVRA"


def tokenize(texto: str) -> List[Token]:
    tokens: List[Token] = []

    for mo in re.finditer(TOK_REGEX, texto):
        tipo = mo.lastgroup
        lexema = mo.group()

        if tipo == "ESPACO":
            continue
        if tipo == "PALAVRA":
            tokens.append((lexema, classificar_palavra(lexema)))
        elif tipo == "NUMERO":
            tokens.append((lexema, "NUMERO"))
        elif tipo == "OPERADOR":
            tokens.append((lexema, "OPERADOR"))
        elif tipo == "PONTUACAO":
            tokens.append((lexema, "PONTUACAO"))
        elif tipo == "TRACO":
            tokens.append((lexema, "TRACO"))
        elif tipo == "RETICENCIAS":
            tokens.append((lexema, "RETICENCIAS"))
        else:
            tokens.append((lexema, "??"))

    return tokens


def tokenize_to_strings(texto: str) -> List[str]:
    return [lexema for lexema, _ in tokenize(texto)]


def processar_arquivo(caminho_entrada: str, caminho_saida: str) -> None:
    with open(caminho_entrada, "r", encoding="utf-8") as f:
        conteudo = f.read()

    tokens = tokenize_to_strings(conteudo)

    with open(caminho_saida, "w", encoding="utf-8") as f:
        f.write(str(tokens))


if __name__ == "__main__":
    exemplo = 'Do título. Uma noite destas, vindo da cidade para o Engenho Novo, encontrei no trem.'
    resultado = tokenize(exemplo)

    for token in resultado:
        print(token)
