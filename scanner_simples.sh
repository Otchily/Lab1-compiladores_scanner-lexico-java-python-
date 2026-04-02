#!/bin/bash

while IFS= read -r linha; do
    limpa=$(echo "$linha" | tr -d ' \t\r')
    echo "[SCANNER] Linha original: '$linha'"
    echo "[SCANNER] Linha sem espaços/tabs: '$limpa'"
done
