
# Gestión de Hospital

**Medical Manager** es un proyecto creado para poner en práctica los conocimientos adquiridos durante el Bootcamp de desarrollo backend en BeTek. El objetivo central de este proyecto es desarrollar un sistema que automatice el proceso de agendar citas médicas para los usuarios, mejorando la eficiencia y facilidad en la gestión de las mismas. A continuación, se describen los lineamientos generales seguidos para lograr este objetivo:

## Requerimientos:

Se solicita crear una API tipo REST la cual permita llevar a cabo las siguientes
funcionalidades:

- Agendar una cita médica.
- Verificar si un usuario tiene agendada una cita.
- Relizar el cálculo de la fecha para la próxima cita de acuerdo al tipo de usuario.
- Almacenar la información en memoria en una base de datos h2.
- Hacer la implementación con JAVA + el ecosistema de Spring.
- Realizar con buenas prácticas de desarrollo.


## Implementación

A partir de los requerimientos identificados, se propone la siguiente implementación para abordar y resolver las necesidades detectadas:

### Diseño del sistema

En esta sección se presenta el diseño estructural del sistema, que incluye la arquitectura de la base de datos. El objetivo es proporcionar una representación clara y detallada de los componentes del sistema, sus interrelaciones, y la manera en que interactúan para cumplir con los requisitos identificados.


#### Diagrama de Base de datos

<div align="center">
      <img src="C:\Users\Estefania Sora\Desktop\Bootcamp\Proyecto-Integrador\ImagesREADME\Diagrama EPS.drawio.png" alt="TicketPDF">
</div>

### Arquitectura del sistema

Para el desarrollo del sistema “Medical Manager”, se plantea una arquitectura por capas, ya que permite separar las responsabilidades y administrar las dependencias, permitiendo que cada capa superior pueda utilizar los servicios de la capa inferior. A partir de lo anterior, se plantea la siguiente arquitectura general del sistema:



Se plantea desarrollar una API para el tratamiento de los datos para almacenar la información correspondiente a la información básica de los pacientes junto con las citas registradas. Por otro lado, los servicios permiten obtener la información correspondiente a las peticiones realizadas por el usuario, las cuales se entregan en formato json para que sea utilizada de diversas maneras ya sea por el frontend o por otro destino.

### Endpoints

### Registro del paciente

Antes del agendamiento de una cita médica deben existir pacientes registrados dentro del sistema. Para ello, se creó un endpoint con la siguiente ruta:

```bash
  http://localhost:8080/api/v1/patient
```

### `Cuerpo de la petición `

```bash
  {
	"identityPatient": "1234567895",
	"typeId": "CC",
	"typePatient": "PARTICULAR",
	"phoneNumber": 31452521,
	"name": "Nombre Prueba",
	"lastName": "Prueba Apellido",
	"address": "Cra 10 N° 87 A 81"
}
```

### `Respuesta 200 de la petición`
```bash
  {
	"mensaje": "El paciente se registró con éxito"
}
```
### Consulta de un paciente en específico

Para la consulta de un paciente, se creó un endpoint con al siguiente ruta:

```bash
 http://localhost:8080/api/v1/patient/1234567895
```
Donde el valor numérico al final de la ruta corresponde al ID del paciente

### `Respuesta 200 de la petición`
```bash
{
	"identityPatient": "1234567895",
	"typeId": "CC",
	"typePatient": "PARTICULAR",
	"phoneNumber": 31452521,
	"name": "Nombre Prueba",
	"lastName": "Prueba Apellido",
	"address": "Cra 10 N° 87 A 81"
}
```

#### Cuando un paciente tiene un tipo de usuario diferente a los permitidos, deberá retornar

### `Respuesta 400 de la petición`
```bash
{
	"mensaje": "Tipo de usuario no permitido en el hospital"
}
```

### Agendamiento de una cita médica

Luego de crear al paciente, se realiza el agendamiento de una cita, para ello, se creó un endpoint con al siguiente ruta:

```bash
 http://localhost:8080/api/v1/medical/agendar
```
Donde el valor numérico al final de la ruta corresponde al ID del paciente

### `Cuerpo de la petición`
```bash
{
	"specialty": "Medicina General",
	"patient":{
		"identityPatient": "1234567895"		
	}
}
```
### `Respuesta 200 de la petición`
```bash
{
	"id": 1,
	"date": "2024-08-23"
}
```
Al momento de realizar el agendamiento se hacer el cálculo de la fecha para la próxima cita con las siguientes reglas de negocio:

- Si el agendamiento lo hace un usuario tipo EPS, la fecha de agendamiento se cuenta la fecha actual más 10 días sin contar sábados y domingos.
- Si el agendamiento lo hace un usuario tipo PÓLIZA, la fecha de agendamiento es la fecha actual más 8 días contando sábados y domingos.
- Si el agendamiento lo hace un usuario tipo PARTICULAR la fecha de agendamiento es la fecha actual más 7 días sin contar sábados y domingos.



Debido a que un usuario particular solo puede tener una cita en el hospital, si un usuario particular intenta agendar más de una cita, se retorna un error HTTP 400 con el siguiente JSON.

### Validación de errores

#### Cuando un paciente agenda más de una cita antes de cumplir con la que ya se tiene agendada

### `Respuesta 400 de la petición`
```bash
{
	"mensaje": "El usuario con identificación 1234567895 ya tiene una cita agendada, por lo cual no podrá realizar más agendamientos."
}
```

#### Consulta de la cita agendada

### `Respuesta 200 de la petición`
```bash
{
	"id": 1,
	"especialidad": "Medicina General",
	"identificaciónUsuario": "1234567895",
	"tipoUsuario": "PARTICULAR",
	"fechaCita": "2024-08-23"
}
```

## Used By

This project is used by the following companies:

- Lenguaje de programación: Java
- Frameworks: Spring Boot
- Bases de Datos: H2

## LinkedIn 🔵

- [Estefanía Sora](https://www.linkedin.com/in/yilma-sora-backend-desarrolloweb/)