#!/bin/bash

# Dirección y puerto del servicio MySQL (usando el nombre del servicio Docker Compose)
DB_HOST="mysql"
DB_PORT="3306"

echo "Verificando disponibilidad de la base de datos en $DB_HOST:$DB_PORT..."

# Espera a que la base de datos esté disponible usando netcat (nc)
# Reintentará cada 1 segundo.
until nc -z $DB_HOST $DB_PORT; do
  echo "MySQL no está disponible - esperando 1 segundo..."
  sleep 1
done

echo "¡MySQL está activo! Iniciando aplicación Spring Boot..."

# Iniciar la aplicación Spring Boot. 'exec' reemplaza el script de shell
# con el proceso java, manteniendo el PID 1.
exec java -jar /app/app.jar