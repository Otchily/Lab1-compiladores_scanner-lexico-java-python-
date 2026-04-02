import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

public class ScannerPTTest {

    @Test
    public void testTokenizacaoBasica() {
        List<String> tokens = ScannerPT.tokenizeToStrings("position = initial + rate * 60");
        assertEquals(Arrays.asList("position", "=", "initial", "+", "rate", "*", "60"), tokens);
    }

    @Test
    public void testPontuacao() {
        List<ScannerPT.Token> tokens = ScannerPT.tokenize("Olá, mundo!");
        assertEquals(new ScannerPT.Token("Olá", "PALAVRA"), tokens.get(0));
        assertEquals(new ScannerPT.Token(",", "PONTUACAO"), tokens.get(1));
        assertEquals(new ScannerPT.Token("mundo", "PALAVRA"), tokens.get(2));
        assertEquals(new ScannerPT.Token("!", "PONTUACAO"), tokens.get(3));
    }

    @Test
    public void testClassificacao() {
        List<ScannerPT.Token> tokens = ScannerPT.tokenize("Eu fui para a escola.");
        List<ScannerPT.Token> esperado = Arrays.asList(
                new ScannerPT.Token("Eu", "PRONOME"),
                new ScannerPT.Token("fui", "PALAVRA"),
                new ScannerPT.Token("para", "PREPOSICAO"),
                new ScannerPT.Token("a", "ARTIGO"),
                new ScannerPT.Token("escola", "PALAVRA"),
                new ScannerPT.Token(".", "PONTUACAO")
        );
        assertEquals(esperado, tokens);
    }
}
