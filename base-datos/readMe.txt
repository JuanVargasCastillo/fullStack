# Base de Datos del Proyecto FullStack

## Motor de base de datos

- MySQL
- Usado desde phpMyAdmin

## Archivo

- `ecoMarket.sql` (Reemplaza con el nombre real)

## Instrucciones para importar

1. Abre **phpMyAdmin**.
2. Crea una base de datos vacía (por ejemplo: `fullstack_db`).
3. Haz clic en la base de datos y ve a la pestaña **Importar**.
4. Selecciona el archivo `.sql` desde la carpeta `base-datos/`.
5. Presiona **Continuar**.

---

## Recomendación de conexión en Spring Boot

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fullstack_db
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=none
