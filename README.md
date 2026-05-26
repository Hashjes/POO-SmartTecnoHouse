# POO-SmartTecnoHouse

Proyecto de Programación Orientada a Objetos en Java para Smart TecnoHouse SA.

Alumno: Jesús

## Descripción

Aplicación Java con interfaz gráfica Swing para simular un sistema domótico IoT. El proyecto aplica arquitectura MVC, jerarquías polimórficas de sensores y actuadores, reglas de negocio con patrón Strategy, persistencia JSON y documentación automática con Javadoc.

## Requisitos Cubiertos

- Sensores base: temperatura (`temp`), luz (`light`) y presencia (`pir`).
- Sensor añadido para extensibilidad: humedad (`humidity`).
- Actuadores base: bombilla (`bulb`) y ventilador (`fan`).
- Actuador añadido para extensibilidad: enchufe inteligente (`plug`).
- Interfaz `IDispositivo` y clases abstractas `Sensor` y `Actuador`.
- Patrón Strategy en la interfaz `Regla` y sus implementaciones.
- Patrón MVC con paquetes `modelo`, `vista` y `controlador`.
- Patrón Singleton sugerido en `SmartTecnoHouse`.
- Persistencia en `data/state.json` y ejemplo de acciones en `actuators.log`.

## Ejecución

```bash
mvn clean package
mvn exec:java
```

## Entregables

- Código fuente Java se encuentra en `src/main/java`.
- Documentación automática Javadoc en `docs/`.
- Memoria-manual en PDF/DOCX.
- Cheatsheet de Git en PDF.
- Persistencia de ejemplo en `data/state.json`.
- Log de ejemplo en `actuators.log`.