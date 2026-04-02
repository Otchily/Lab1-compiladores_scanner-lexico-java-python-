import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GerarTokensJava {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("Uso: java GerarTokensJava <entrada> <saida>");
        }
        String texto = Files.readString(Path.of(args[0]), StandardCharsets.UTF_8);
        List<String> tokens = ScannerPT.tokenizeToStrings(texto);
        Files.writeString(Path.of(args[1]), tokens.toString(), StandardCharsets.UTF_8);
    }
}
