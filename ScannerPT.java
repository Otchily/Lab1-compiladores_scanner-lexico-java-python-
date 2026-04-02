import java.util.*;
import java.util.regex.*;

public class ScannerPT {

    private static final String TOK_REGEX =
            "(?<RETICENCIAS>\\.\\.\\.)"
            + "|(?<TRACO>--|—)"
            + "|(?<PALAVRA>[A-Za-zÀ-ÖØ-öø-ÿ]+(?:-[A-Za-zÀ-ÖØ-öø-ÿ]+)*)"
            + "|(?<NUMERO>\\d+(?:[.,]\\d+)?)"
            + "|(?<OPERADOR>==|!=|<=|>=|[=+\\-*/<>])"
            + "|(?<PONTUACAO>[.,;:!?()\"'“”‘’\\[\\]{}])"
            + "|(?<ESPACO>\\s+)"
            + "|(?<OUTRO>.)";

    private static final Set<String> ARTIGOS = new HashSet<>(Arrays.asList(
            "o", "a", "os", "as", "um", "uma", "uns", "umas"
    ));

    private static final Set<String> PRONOMES = new HashSet<>(Arrays.asList(
            "eu", "tu", "ele", "ela", "nós", "nos", "vós", "vos",
            "eles", "elas", "me", "te", "se", "lhe", "lhes", "mim",
            "comigo", "ti", "contigo", "si", "consigo", "isto", "isso",
            "aquilo", "este", "esta", "esse", "essa", "aquele", "aquela",
            "quem", "que", "qual", "quais"
    ));

    private static final Set<String> PREPOSICOES = new HashSet<>(Arrays.asList(
            "de", "do", "da", "dos", "das", "em", "no", "na", "nos", "nas",
            "por", "para", "a", "ao", "à", "às", "até", "com", "sem", "sob",
            "sobre", "entre"
    ));

    private static final Set<String> CONJUNCOES = new HashSet<>(Arrays.asList(
            "e", "ou", "mas", "porém", "todavia", "contudo", "porque", "pois",
            "embora", "quando", "se"
    ));

    public static class Token {
        public final String lexema;
        public final String tipo;

        public Token(String lexema, String tipo) {
            this.lexema = lexema;
            this.tipo = tipo;
        }

        @Override
        public String toString() {
            return "(" + lexema + ", " + tipo + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Token)) return false;
            Token other = (Token) obj;
            return lexema.equals(other.lexema) && tipo.equals(other.tipo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(lexema, tipo);
        }
    }

    public static String classificarPalavra(String palavra) {
        String p = palavra.toLowerCase();

        if (ARTIGOS.contains(p)) return "ARTIGO";
        if (PRONOMES.contains(p)) return "PRONOME";
        if (PREPOSICOES.contains(p)) return "PREPOSICAO";
        if (CONJUNCOES.contains(p)) return "CONJUNCAO";
        if (p.endsWith("ar") || p.endsWith("er") || p.endsWith("ir")
                || p.endsWith("ando") || p.endsWith("endo") || p.endsWith("indo")
                || p.endsWith("ava") || p.endsWith("iam")) {
            return "VERBO?";
        }
        if (p.endsWith("mente")) return "ADV?";
        return "PALAVRA";
    }

    public static List<Token> tokenize(String texto) {
        List<Token> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile(TOK_REGEX);
        Matcher matcher = pattern.matcher(texto);

        while (matcher.find()) {
            if (matcher.group("ESPACO") != null) {
                continue;
            } else if (matcher.group("PALAVRA") != null) {
                String lexema = matcher.group("PALAVRA");
                tokens.add(new Token(lexema, classificarPalavra(lexema)));
            } else if (matcher.group("NUMERO") != null) {
                tokens.add(new Token(matcher.group("NUMERO"), "NUMERO"));
            } else if (matcher.group("OPERADOR") != null) {
                tokens.add(new Token(matcher.group("OPERADOR"), "OPERADOR"));
            } else if (matcher.group("PONTUACAO") != null) {
                tokens.add(new Token(matcher.group("PONTUACAO"), "PONTUACAO"));
            } else if (matcher.group("TRACO") != null) {
                tokens.add(new Token(matcher.group("TRACO"), "TRACO"));
            } else if (matcher.group("RETICENCIAS") != null) {
                tokens.add(new Token(matcher.group("RETICENCIAS"), "RETICENCIAS"));
            } else {
                tokens.add(new Token(matcher.group(), "??"));
            }
        }

        return tokens;
    }

    public static List<String> tokenizeToStrings(String texto) {
        List<String> saida = new ArrayList<>();
        for (Token t : tokenize(texto)) {
            saida.add(t.lexema);
        }
        return saida;
    }

    public static void main(String[] args) {
        String exemplo = "Do título. Uma noite destas, vindo da cidade para o Engenho Novo.";
        List<Token> tokens = tokenize(exemplo);
        for (Token t : tokens) {
            System.out.println(t);
        }
    }
}
