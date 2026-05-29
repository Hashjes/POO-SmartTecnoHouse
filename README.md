# POO-SmartTecnoHouse

Este repositorio contiene un Proyecto de Programación Orientada a Objetos en Java para Smart TecnoHouse SA.

Alumno: Jesús

## Descripción

Se trata de una aplicación Java con interfaz gráfica en Swing que simula un sistema domótico IoT. El proyecto, debido a los requisitos, aplica la arquitectura MVC, las jerarquías polimórficas de sensores y actuadores, patrón Strategy para las reglas de negocio, persistencia JSON y documentación automática con Javadoc.

## Qué incluye el código

- Sensores base (presentes en el primer proyecto): temperatura (temp), luz (light) y presencia (pir).
- Sensor extra añadido: humedad (humidity).
- Actuadores base (presentes en el primer proyecto): bombilla (bulb) y ventilador (fan).
- Actuador extra añadido: enchufe inteligente (plug).
- Interfaz "IDispositivo" y clases abstractas Sensor y Actuador.
- El Patrón Strategy se emplea en la interfaz "Regla" y sus implementaciones.
- Se emplea el Patrón MVC con los paquetes denominados: modelo, vista y controlador.
- Se ha empleado el patrón Singleton como se recomienda en el apartado de diseño para el modelo principal "SmartTecnoHouse".
- Finalmente, se ha creado persistencia tanto al guardar como al cerrar el programa mediante "data/state.json", y el log de acciones realizadas se guarda en "actuators.log".

## Ejecución

Para ejecutar el programa es necesario cumplir previamente con los requisitos de tener instalado Java JDK 17 o superior y Apache Maven instalado y disponible mediante la terminal.

Una vez cumplido esto solo es necesario ejecutar los siguientes comandos:

```bash
mvn clean package
mvn exec:java
```
Opcionalmente, también se puede ejecutar el programa desde un IDE ejecutando la clase app.Main

## Contenido del repositorio

- Código fuente de Java localizado en "src/main/java".
- La documentación automática de Javadoc se encuentra en "docs/".
- La persistencia y el ejemplo de persistencia se localiza en "data/state.json".
- El log de ejemplo y el log en general se encuentra en "actuators.log".