import os
import sys
import unittest

sys.path.insert(0, os.path.dirname(__file__))

from scanner_pt import tokenize, tokenize_to_strings


class TestScannerPT(unittest.TestCase):

    def test_tokenizacao_basica(self):
        texto = "position = initial + rate * 60"
        tokens = tokenize_to_strings(texto)
        self.assertEqual(tokens, ["position", "=", "initial", "+", "rate", "*", "60"])

    def test_pontuacao_portugues(self):
        texto = "Olá, mundo!"
        tokens = tokenize(texto)
        self.assertEqual(tokens[0], ("Olá", "PALAVRA"))
        self.assertEqual(tokens[1], (",", "PONTUACAO"))
        self.assertEqual(tokens[2], ("mundo", "PALAVRA"))
        self.assertEqual(tokens[3], ("!", "PONTUACAO"))

    def test_classificacao_simples(self):
        texto = "Eu fui para a escola."
        tokens = tokenize(texto)
        esperado = [
            ("Eu", "PRONOME"),
            ("fui", "PALAVRA"),
            ("para", "PREPOSICAO"),
            ("a", "ARTIGO"),
            ("escola", "PALAVRA"),
            (".", "PONTUACAO")
        ]
        self.assertEqual(tokens, esperado)


if __name__ == "__main__":
    unittest.main()
