
![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 🧠 Evaluación Final Transversal – Desarrollo Orientado a Objetos II

## 👨‍💻 Autor del proyecto

**Nombre:** Sergio Sandoval Valenzuela  
**Carrera:** Analista Programador  
**Sede:** Santiago Online  
**Asignatura:** Desarrollo Orientado a Objetos II (PRY2203)  
**Evaluación:** EFT – Semana 9

---

# 📖 Introducción

Este repositorio contiene **BibliotecaEscolar**, una aplicación de escritorio desarrollada en Java para apoyar la administración de una biblioteca escolar.

El proyecto evolucionó durante la asignatura **Desarrollo Orientado a Objetos II**, integrando progresivamente los principios de programación orientada a objetos con una interfaz gráfica, acceso a bases de datos relacionales y operaciones de préstamo y devolución de libros.

La versión correspondiente a la **Evaluación Final Transversal de la Semana 9** consolida estas funcionalidades mediante una organización por paquetes y responsabilidades diferenciadas.

---

# 🎯 Propósito del proyecto

Desarrollar una aplicación que permita administrar información de una biblioteca escolar y aplicar, en un caso práctico, los contenidos de la asignatura.

El sistema busca facilitar el registro y consulta de estudiantes y libros, la gestión de préstamos y devoluciones, y la actualización del stock disponible, manteniendo la información almacenada en MySQL.

---

# 📘 Descripción general del sistema

**BibliotecaEscolar** es una aplicación de escritorio con interfaz gráfica desarrollada en **Java Swing** y persistencia mediante **MySQL y JDBC**.

El proyecto aplica los siguientes conceptos y recursos:

- Encapsulamiento y organización de clases.
- Separación de responsabilidades mediante paquetes.
- Clases de modelo para representar las entidades del sistema.
- Interfaz gráfica con Java Swing.
- Acceso a datos mediante JDBC.
- Patrón DAO para organizar las operaciones de base de datos.
- Patrón Singleton para administrar el acceso a la conexión.
- Consultas y operaciones SQL.
- Manejo de excepciones durante las operaciones.
- Concurrencia mediante `Thread` y actualización segura de la interfaz con `SwingUtilities.invokeLater`.
- Sincronización de operaciones sensibles.
- Transacciones con `commit` y `rollback`.
- Control de versiones con Git y publicación en GitHub.

---

# 🖥️ Interfaz de usuario

La interfaz gráfica permite utilizar las funciones principales de la biblioteca desde ventanas y formularios, sin necesidad de ejecutar consultas SQL manualmente.

**Principales funcionalidades:**

- Consultar la información registrada en el sistema.
- Registrar estudiantes.
- Registrar libros.
- Editar información de libros.
- Registrar préstamos.
- Registrar devoluciones.
- Actualizar la información presentada en pantalla.
- Salir de la aplicación.

---

# 💾 Persistencia de datos

La aplicación utiliza una base de datos **MySQL**, a la que se conecta mediante **JDBC**.

**Base de datos:** `biblioteca`

**Tablas del sistema:**

- `usuarios`
- `estudiantes`
- `categorias`
- `libros`
- `prestamos`

Los scripts SQL incluidos en el repositorio permiten crear la estructura de las tablas y cargar los datos iniciales:

```text
sql/
├── PRY2203_EFT_S9_Script_crea_tablas_biblioteca.sql
└── PRY2203_EFT_S9_Script_poblado_tablas_biblioteca.sql
```

La información se conserva en la base de datos después de cerrar la aplicación.

---

# ⚠️ Validaciones y manejo de errores

El sistema contempla verificaciones durante las operaciones de registro y administración de préstamos, además del manejo de errores relacionados con el acceso a los datos.

En las operaciones que modifican información relacionada, se utilizan transacciones para evitar cambios parciales si ocurre un problema.

---

# 🔄 Gestión de préstamos y devoluciones

El sistema administra el préstamo y la devolución de libros, manteniendo actualizado su stock.

**Registro de préstamo:**

1. Se selecciona la información necesaria para realizar el préstamo.
2. Se verifica la disponibilidad del libro.
3. Se registra el préstamo en la base de datos.
4. Se actualiza el stock dentro de la misma transacción.

**Registro de devolución:**

1. Se identifica el préstamo correspondiente.
2. Se registra la devolución.
3. Se restituye el stock del libro.
4. Se confirma la operación en la base de datos.

Estas operaciones utilizan mecanismos como `synchronized`, `SELECT ... FOR UPDATE`, `commit` y `rollback` para proteger la consistencia de los datos.

---

# 🧵 Concurrencia y actualización de la interfaz

El proyecto incorpora el uso de **hilos (`Thread`)** en el flujo de préstamos.

Para actualizar componentes gráficos desde una operación concurrente se utiliza:

```java
SwingUtilities.invokeLater(() -> {
    // Actualizacion de componentes de la interfaz grafica.
});
```

Esto permite coordinar el trabajo realizado fuera del hilo de interfaz con la actualización de los componentes de Java Swing.

---

# 🔄 Flujo general del sistema

1. Se inicia la aplicación desde su clase principal.
2. Se establece la conexión con MySQL.
3. Se presenta la interfaz gráfica.
4. El usuario consulta o registra información.
5. Las operaciones se gestionan mediante las clases correspondientes.
6. Las clases de acceso a datos ejecutan las consultas SQL.
7. La información se almacena o recupera desde MySQL.
8. La interfaz presenta los resultados actualizados.

---

# 🧱 Estructura general del proyecto

El código se encuentra organizado en los siguientes paquetes:

```text
BibliotecaEscolar/
├── README.md
├── sql/
│   ├── PRY2203_EFT_S9_Script_crea_tablas_biblioteca.sql
│   └── PRY2203_EFT_S9_Script_poblado_tablas_biblioteca.sql
└── [codigo fuente Java]
    ├── main/
    ├── modelo/
    ├── vista/
    ├── controlador/
    ├── dao/
    └── datos/
```

*La ubicación física de los paquetes depende de la estructura de carpetas del proyecto abierto en IntelliJ IDEA.*

---

# 📁 Descripción de paquetes

## 📁 main

Contiene el punto de entrada para iniciar la aplicación.

## 📁 modelo

Agrupa las clases que representan los datos y entidades de la biblioteca.

## 📁 vista

Contiene las ventanas, formularios y componentes gráficos de Java Swing.

## 📁 controlador

Coordina la interacción entre la interfaz y las operaciones del sistema.

## 📁 dao

Organiza las operaciones de consulta y actualización de información en la base de datos.

## 📁 datos

Contiene los elementos asociados a la conexión con MySQL, incluida la clase `ConexionBD`.

## 📁 sql

Contiene los scripts de creación de tablas y carga inicial de datos.

---

# 💻 Tecnologías utilizadas

- Java (JDK 26 en el entorno de desarrollo).
- Java Swing.
- JDBC.
- MySQL.
- IntelliJ IDEA.
- Debian GNU/Linux 12.
- Git.
- GitHub.
- Markdown.

---

# ⚙️ Requisitos

- JDK compatible con el proyecto (desarrollado con JDK 26).
- IntelliJ IDEA o un entorno de desarrollo Java equivalente.
- Servidor MySQL disponible.
- Controlador JDBC de MySQL configurado en el proyecto.
- Base de datos creada mediante los scripts incluidos.
- Variable de entorno `MYSQL_PASSWORD` configurada con la contraseña de conexión correspondiente.

**Seguridad:** no se deben publicar contraseñas ni credenciales de la base de datos en el repositorio.

---

# 🚀 Instrucciones para ejecutar el proyecto

## Clonar el repositorio

Desde una terminal:

```bash
git clone https://github.com/sergiosandovalv/BibliotecaEscolar.git
```

## Preparar la base de datos

1. Iniciar el servidor MySQL.
2. Ejecutar el script `PRY2203_EFT_S9_Script_crea_tablas_biblioteca.sql`.
3. Ejecutar el script `PRY2203_EFT_S9_Script_poblado_tablas_biblioteca.sql`.
4. Verificar la configuración de conexión de la clase `ConexionBD`.
5. Configurar la variable de entorno `MYSQL_PASSWORD` antes de iniciar la aplicación.

## Abrir y ejecutar en IntelliJ IDEA

1. Abrir IntelliJ IDEA.
2. Seleccionar **File → Open** y elegir la carpeta `BibliotecaEscolar`.
3. Comprobar que el proyecto utilice el JDK correspondiente y tenga disponible el controlador JDBC de MySQL.
4. Ejecutar la clase principal del paquete `main`.
5. Utilizar la interfaz para consultar, registrar y administrar los préstamos y devoluciones.

---

# 📄 Funcionalidades implementadas

- Registro y consulta de estudiantes.
- Registro, consulta y edición de libros.
- Registro de préstamos.
- Registro de devoluciones.
- Actualización de stock.
- Persistencia de información en MySQL.
- Interfaz gráfica con Java Swing.
- Acceso a datos mediante JDBC y DAO.
- Conexión centralizada mediante Singleton.
- Operaciones concurrentes y sincronización.
- Transacciones para mantener la consistencia de los datos.
- Organización modular mediante paquetes.
- Versionamiento con Git y publicación en GitHub.

---

# 🎯 Objetivos de aprendizaje alcanzados

- Aplicar la programación orientada a objetos a un sistema de gestión.
- Organizar el código separando responsabilidades.
- Construir una interfaz gráfica funcional.
- Conectar una aplicación Java con una base de datos relacional.
- Ejecutar operaciones de lectura y escritura mediante JDBC.
- Implementar transacciones para operaciones relacionadas.
- Incorporar conceptos de concurrencia y sincronización.
- Gestionar el proyecto mediante Git y GitHub.

---

# ✅ Conclusión

**BibliotecaEscolar** integra los contenidos trabajados durante **Desarrollo Orientado a Objetos II** en una aplicación de escritorio que permite administrar estudiantes, libros, préstamos y devoluciones.

La implementación de **Java Swing, JDBC, MySQL, DAO, Singleton, concurrencia y transacciones** permitió avanzar desde la organización de clases hacia un sistema con interfaz gráfica y persistencia de datos.

La versión de la **EFT de la Semana 9** reúne estas funcionalidades y refuerza la importancia de la separación de responsabilidades, la consistencia de los datos y el control de versiones.

---

# 🔗 Repositorio

**GitHub:** <https://github.com/sergiosandovalv/BibliotecaEscolar>

---

**Proyecto desarrollado para la asignatura Desarrollo Orientado a Objetos II – Duoc UC.**
