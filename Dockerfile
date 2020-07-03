FROM mysql:8.0

COPY src/main/resources/scripts/vehicle-fleet.sql /docker-entrypoint-initdb.d/vehicle-fleet.sql
