#!/bin/ksh
cd /home/soporte/PROCESOS/Shell
ENVIO="$HOME/PROCESOS/emailsend"
TIPO_ALARMA="monitor_error"
LOG="/logs"
BLOQUEO="$TIPO_ALARMA.bloqueo"
TEMPFILE="$TIPO_ALARMA.last_count"

# Asegurarse de eliminar el archivo de bloqueo incluso en caso de error
trap 'rm -f "$BLOQUEO"' EXIT

# Leer el valor de la última cuenta de errores 504 del archivo temporal
if [ -f "$TEMPFILE" ]; then
    LAST_COUNT=$(cat "$TEMPFILE")
else
    LAST_COUNT=0
fi

if [ ! -f "$BLOQUEO" ]; then
    : > "$BLOQUEO"

    FECHA=$(date +"%d\/%b\/%Y")
    FOUND_504=0  # Variable para rastrear si se encontró el código 504

    awk -v fecha="$FECHA" '$4 ~ fecha {print substr($4,2,11) " " $9}' "$LOG/autoges_access.log" | sort | uniq -c | sort -nr | while read -r line; do
        CANT=$(echo "$line" | awk '{print $1}')
        CODE=$(echo "$line" | awk '{print $3}')
        echo "CANT: $CANT, CODE: $CODE"

        if [ "$CODE" -eq 504 ]; then
            FOUND_504=1  # Se encontró el código 504
            if [ "$CANT" -ge 5 ] || [ "$CANT" -gt "$LAST_COUNT" ]; then
                ASUNTO="Monitor de Errores en LOG - Tu Movilnet"
                BODY="Se detectan $CANT peticiones con codigo de error $CODE en Tu Movilnet."
                /bin/sh "$ENVIO/send_mail.ksh" "$BODY" "$ASUNTO" "$ENVIO/csagc.mail"
                break
            fi
        fi
    done

    # Guardar la nueva cuenta de errores 504 en el archivo temporal
    if [ $FOUND_504 -eq 1 ]; then
        echo "$CANT" > "$TEMPFILE"
    else
        echo "0" > "$TEMPFILE"
    fi

    rm -f "$BLOQUEO"
fi
