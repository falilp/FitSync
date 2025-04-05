#!/bin/bash

#Forzar entrada por terminal
exec < /dev/tty

#Lista de tags
TAGS=("🚀 Feature" "🐛 Fix" "📝 Docs" "🎨 Style" "🔧 Refactor" "⚡ Performance"  "✅  Test" "🔧 Chore" "🔥 Hotfix" "🔀 Merge" "📦 Build" "✨ UI/UX")

#Mostrar las opciones y pedir un número al usuario
echo "Seleccione un tag para el commit:"
for i in "${!TAGS[@]}"; do
    echo "$((i+1)). ${TAGS[i]}"
done

#Leer el número ingresado
read -p "Ingresa el número del tag: " choice

#Validación
if [[ "$choice" -ge 1 && "$choice" -le ${#TAGS[@]} ]]; then
  TAG="${TAGS[$((choice-1))]}"
else
  echo "Opción no válida. Abortando."
  exit 1
fi

COMMIT_MSG_FILE=$1
COMMIT_MSG=$(cat "$COMMIT_MSG_FILE")
echo "$TAG: $COMMIT_MSG" > "$COMMIT_MSG_FILE"