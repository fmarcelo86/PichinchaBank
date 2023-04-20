# Proyecto Base Implementando Clean Architecture
Este proyecto fue desarrollado utilizando los paradigmas de programación Reactiva y programación Funcional con **Spring Framework y Spring WebFlux**

## Que necesita para ejecutar el proyecto
1. Tener instalado el **JDK de Java 11** o superior
2. Tener instalado **Gradle 7.4.2** o superior

## Guía de ejecución
1. Clonar el proyecto
2. Abrir una terminal CMD y ubicarse en la carpeta raiz del proyecto
3. Ejecutar el comando **gradlew build** para compilar
4. Ejecutar el comando **gradlew bootRun** para ejecutar


5. Ejecutar el comando **gradlew jacocoMergedReport** para generar el reporte de coverage en el directorio: **PichinchaBank/build/reports/jacocoMergedReport/html/index.html**

#### Guía para deployment en Docker
1. Ejecutar los pasos anteriores del 1 al 3
2. Abrir una terminal CMD y ubicarse en la carpeta [deployment](deployment) con el **Dockerfile**
4. Ejecutar los siguientes comandos:

   ``` docker build -f Dockerfile -t pichincha/bank:latest ..```

   ```docker run -d --name pichincha -p 8080:8080 pichincha/bank:latest```

#### Documentación OpenAPI con Swagger 3.0
http://localhost:8080/swagger-ui.html

#### Base de datos relacional H2 Server
Esquema de base de datos: [BaseDatos.sql](applications%2Fapp-service%2Fsrc%2Fmain%2Fresources%2FBaseDatos.sql)

http://localhost:8081
```
username: sa
password: pass
```

## Para consumir los servicios
Colección postman: [PichinchaBanck.postman_collection.json](PichinchaBanck.postman_collection.json)

## Implementaciones PLUS
* Arquitectura Hexagonal con Clean Architecture
* Programación funcional
* Expresiones lambda
* Programación reactiva con Spring WebFlux
* Reporte de jacoco con comando en gradle
* Modelo de despliegue en AWS
* Documentación Swagger 3.0 generado desde el Código
* Documentación de JavaDoc
* Sitema de manejo de excepciones
* Escritura de log utilizando log4j

## Modelo de despliegue

Máquina de AWS EC2 con OS Linux y Docker para el despliegue se utilizó DevOps construyendo los Pipeline de CI/CD y RM en Microsoft Azure

## Conocer más sobre la arquitectura

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.
