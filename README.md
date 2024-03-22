# TALLER 7: Aplicación distribuida segura en todos sus frentes

Se creo una aplicación web usando el micro-framework de Spark java (http://sparkjava.com/). La cual fue desplegada en AWS utilizando EC2 y Docker.

Esta aplicación Web segura permitE un acceso seguro desde el browser a la aplicación así como entre servicios. Es decir, garantiza autenticación, autorización e integridad de usuarios y servicios.


---
### Prerrequisitos

* [Maven](https://maven.apache.org/)

* [Git](https://learn.microsoft.com/es-es/devops/develop/git/what-is-git)

* [Java -17](https://www.cursosaula21.com/que-es-java/)

* [Docker](https://aws.amazon.com/es/docker/)

* [DockerHub](https://flexa.cloud/es/%C2%BFQu%C3%A9-es-el-centro-de-la-ventana-acoplable%3F/)

* [Docker Compose](https://imaginaformacion.com/tutoriales/que-es-docker-compose)

* [AWS](https://aws.amazon.com/es/training/awsacademy/)

---

### Instalación y Ejecución

1.Clonamos el repositorio

```
https://github.com/AREP2024-1/AREP_Taller07.git
```

2.Ingresamos a la carpeta del repositorio que clonamos anteriormente

```
cd AREP_Taller07
```

**Nota :** Este proyecto nos ofrece dos alternativas para la instalción y ejecución  de este, las cuales son:


***A. Trayendo las imagenes de DockerHub***

1.Ejecutamos el docker-compose

```
docker-compose up -d
```

2.Verificamos que los contenedores se esten ejecutando

```
docker ps
```
![](images/Contenedores.JPG)

3.Probamos nuestra aplicación accediendo a la siguiente URL en el navegador

```
 https://localhost:8080/index.html
```
![](images/index.JPG)

***B. Construyendo las imagenes (docker-compose.build)***

Para construir las imagenes de docker usando el archivo **docker-compose.build**, debemos realizar los siguientes pasos:

1.Nos movemos a la carpeta secureLogin

```
cd secureLogin
```

2.Construimos el proyecto

```
mvn package
```

3.Luego no movemos nuevamente a la carpeta raiz

```
cd ..
```

4.Ahora nos movemos a la carpeta secureServer

```
cd secureServer
```

5.Construimos el proyecto

```
mvn package
```

6.Nos movemos nuevamente a la carpeta raiz

```
cd ..
```

7.Ejecutamos el docker-compose.build

```
docker-compose -f docker-compose.build.yml up -d
```

8.Verificamos que los contenedores se esten ejecutando

```
docker ps
```

![](images/Contenedores.JPG)

9.Probamos nuestra aplicación accediendo a la siguiente URL en el navegador

```
 https://localhost:8080/index.html
```
![](images/index.JPG)

---

### Usabilidad

1.Antes de utilizar nuestra aplicación, es necesario crear un contenedor Docker basado en Ubuntu que permita agregar usuarios a nuestra base de datos MongoDB. Para lograr esto, ejecutamos el siguiente comando:

```
docker run -d --name ubuntu ubuntu tail -f /dev/null
```

2.Luego vamos a listar las redes Docker para ver el nombre de la red que estamos usando para este proyecto y así mismo conectarnos a esta, para esto ejecutamos el comando:

```
docker network ls
```
![](images/redDocker.jpg)

**Nota:** Como podemos observar en la imagen nuestra red se llama **arep_taller07_red-taller07**

3.Ahora para conectar nuestro contenedor Ubuntu a la red, ejecutamos el comando:

```
docker network connect arep_taller07_red-taller07 ubuntu
```

4.Luego procederemos a instalar curl para poder agregar usuarios, siguiendo estos pasos:

a.	Nos conectamos al contenedor Ubuntu:
```
docker exec -it ubuntu bash 
```

b.	Actualizamos la lista de paquetes: 
```
apt update
```

c.	Instalamos curl:
```
apt install curl
```

5.Una vez instalado curl, vamos a ejecutar el comando para agregar un usuario de la siguiente manera:

```
curl --location 'http://server:5000/user' \
--header 'Content-Type: application/json' \
--data '{
    "username": "sammuel",
    "password": "jackson"
}'
```

**Nota:** El curl anterior se hizo con ayuda de postman.
![](images/postman.jpg)

En la siguiente imagen, podemos ver el resultado de ejecutar el curl en el Docker Dekstop:
![](images/curlUbuntu.jpg)

**Nota:** Si el usuario se creó correctamente se mostrará un mensaje de Usuario creado exitosamente como se puede observar en la imagen anterior, de lo contrario se envía el mensaje de Usuario no creado.

6.Ahora vamos a nuestro browser en la dirección url indicada anteriormente, para probar la aplicación.

```
https://localhost:8080/index.html
```

Allí vamos a acceder con las credenciales del usuario que agregamos anteriormente, para esto ingresamos en el campo de Usuario sammuel y en el de la Contrasena Jackson y damos click en iniciar sesión.

Si las credenciales ingresadas son correctas se mostrará un mensaje de bienvenida como se puede observar en la imagen:

![](images/loginCorrecto.jpg)

Si por el contrario no son correctas, se mostrará un mensaje de usuario o contraseña incorrectos como se visualiza en la siguiente imagen:

![](images/loginIncorrecto.jpg)


7.También podemos observar los usuarios que han sido agregados a nuestra base de datos, para esto 
a.	Ejecutamos el siguiente comando en el contenedor mongodb desde Docker Dekstop en la opción de exec para conectarnos a la base de datos

```
mongosh mongodb://daniela:daniela2004@mongodb:27017
```
![](images/mongoConnection.jpg)

b.	Luego vamos a acceder a la base de datos con el nombre, utilizando el siguiente comando:
```
use usuarios
```

c.	Ahora vamos a acceder a la tabla donde tenemos la información de los usuarios, para esto ejecutamos el siguiente comando:
```
db.login.find()
```

Como podemos observar en la siguiente imagen tenemos dos usuarios agregados con su respectivo username y contraseña encriptada.

![](images/usuariosMongo.jpg)

---
### Ejecución de Test

1.Desde la raiz nos movemos a la carpeta secureLogin

```
cd secureLogin
```

2.Ejecutamos las pruebas con el siguiente comando

```
mvn test
```

3.Nos devolvemos nuevamente a la carpeta raiz

```
cd ..
```

4.Ahora nos movemos a la carpeta secureServer

```
cd secureServer
```

5.Ejecutamos las pruebas con el siguiente comando

```
mvn test
```
---
### Despliegue en AWS

En el siguiente link encontrara el video donde se evidencia el despliegue en AWS:

```

```

---
### Arquitectura

Los proyectos secureLogin y secureServer forman parte de una arquitectura diseñada para proporcionar una autenticación segura a los usuarios y gestión de su información de manera confiable. La arquitectura consta de tres componentes principales los cuales se describirán más adelante.

Es importante tener en cuenta que se crean dos proyectos debido a que se está trabajando con microservicios.  Esto nos permite conectar varias instancias de una manera más sencilla, eficiente y escalable.

**Componentes**
* **secureaLogin:** Maneja las solicitudes de autenticación de usuarios, para lo cual utiliza un servicio de atenticación en el que se validan las credenciales de inicio de sesión de los usuarios.

* **secureServer:** Se comporta como un servidor el cual recibe las solicitudes de guardado y autenticación de usuarios almacenando las credenciales en una base de datos.

* **MongoDB:** Base de datos utilizada para almacenar la información de las credenciales de los usuarios.

![](images/arquitectura.jpg)

**Estructura Proyecto secureLogin :**

El proyecto está estructurado en diferentes paquetes y clases:

***Main:*** Clase principal que inicia la aplicación secureLogin y configura el controlador Spark.

**1. apiExtenal**
* **HttpClient:** Clase para realizar llamadas HTTP utilizando OkHttp. Así como para realizar llamadas a un servicio se autenticación.

**2. controller**
* **MyController:** Interfaz que define los métodos para los controladores.

* **MySparkController:** Implementación de un controlador Spark para manejar las solicitudes y utilizar el servicio de autenticación para validar las credenciales de inicio de sesión de los usuarios.

**3. services**
* **Authentication:** Clase que representa un servicio de autenticación y gestiona las solicitudes de autenticación de usuarios.

* **DataTransformation:** Clase que contiene un algoritmo de transformacion de datos en diferente formatos, como transformar un string de datos de inicio de sesión en un objeto Login.


**Estructura Proyecto secureServer :**

El proyecto está estructurado en diferentes paquetes y clases:

**Main:** Clase principal que inicia la aplicación secureServer y configura el controlador Spark, la conexión a la base de datos y la configuración de seguridad..

**1. controller**
* **MyController:** Interfaz que define los métodos para los controladores.

* **MySparkController:** Implementación de un controlador Spark para manejar las solicitudes de atenticación y guardado de nuevos usuarios, para lo cual utiliza los servicios de autenticar y guardar usuario.

**2. persistence**
* **CRUDOperations:** Interfaz para operaciones CRUD como save, search, searchAll y delete en la base de datos MongoDB.

* **GestionLogs:** Implementación de operaciones CRUD utilizando MongoDB para gestionar los datos de inicio de sesión de los usuarios.

* **MongoUtil:** Clase para realizar la conexión a la base de datos MongoDB.

**3. service**
* **Authentication:** Verifica la identidad de los usuarios mediante la base de datos, asegurándose de manejar eficientemente las solicitudes de autenticación.

* **Encryption:** Clase que contiene un algoritmo de encriptación SHA-256 para generar un hash de una cadena de texto que en este caso seria el password del usuario.

* **SaveUser:** Almacena la información de los usuarios recién registrados en la base de datos de manera segura y confiable.

---

### Construido con

* [Maven](https://maven.apache.org/): Es una herramienta de comprensión y gestión de proyectos de software. Basado en el concepto de modelo de objetos de proyecto (POM), Maven puede gestionar la construcción, los informes y la documentación de un proyecto desde una pieza de información central.

* [Git](https://learn.microsoft.com/es-es/devops/develop/git/what-is-git): Es un sistema de control de versiones distribuido, lo que significa que un clon local del proyecto es un repositorio de control de versiones completo. Estos repositorios locales plenamente funcionales permiten trabajar sin conexión o de forma remota con facilidad.

* [GitHub](https://platzi.com/blog/que-es-github-como-funciona/): Es una plataforma de alojamiento, propiedad de Microsoft, que ofrece a los desarrolladores la posibilidad de crear repositorios de código y guardarlos en la nube de forma segura, usando un sistema de control de versiones llamado Git.

* [Java -17](https://www.cursosaula21.com/que-es-java/): Es un lenguaje de programación y una plataforma informática que nos permite desarrollar aplicaciones de escritorio, servidores, sistemas operativos y aplicaciones para dispositivos móviles, plataformas IoT basadas en la nube, televisores inteligentes, sistemas empresariales, software industrial, etc.

* [JavaScript](https://universidadeuropea.com/blog/que-es-javascript/): Es un lenguaje de programación de scripts que se utiliza fundamentalmente para añadir funcionalidades interactivas y otros contenidos dinámicos a las páginas web.

* [HTML](https://aulacm.com/que-es/html-significado-definicion/): Es un lenguaje de marcado de etiquetas que se utiliza para crear y estructurar contenido en la web. Este lenguaje permite definir la estructura y el contenido de una página web mediante etiquetas y atributos que indican al navegador cómo mostrar la información.

* [Visual Studio Code](https://openwebinars.net/blog/que-es-visual-studio-code-y-que-ventajas-ofrece/): Es un editor de código fuente desarrollado por Microsoft. Es software libre y multiplataforma, está disponible para Windows, GNU/Linux y macOS.

* [Docker](https://aws.amazon.com/es/docker/): Es una plataforma de software que le permite crear, probar e implementar aplicaciones rápidamente. Docker empaqueta software en unidades estandarizadas llamadas contenedores que incluyen todo lo necesario para que el software se ejecute, incluidas bibliotecas, herramientas de sistema, código y tiempo de ejecución.

* [DockerHub](https://flexa.cloud/es/%C2%BFQu%C3%A9-es-el-centro-de-la-ventana-acoplable%3F/): Es un repositorio público de imágenes de contenedores, donde muchas empresas e individuos pueden publicar imágenes de soluciones prediseñadas.

* [Docker Desktop](https://www.docker.com/products/docker-desktop/): Es una aplicación de escritorio que proporciona una experiencia unificada para desarrollar, implementar y administrar aplicaciones en contenedores Docker en sistemas operativos Windows y macOS.

* [MongoDB](https://www.mongodb.com/es/what-is-mongodb): Es una base de datos de documentos que ofrece una gran escalabilidad y flexibilidad, y un modelo de consultas e indexación avanzado.

* [AWS](https://aws.amazon.com/es/training/awsacademy/): Ofrece cursos y recursos de aprendizaje que permiten a los estudiantes desarrollar diferentes habilidades relacionadas con la nube de AWS.


## Autor

* **[Karol Daniela Ladino Ladino](https://www.linkedin.com/in/karol-daniela-ladino-ladino-55164b272/)** - [20042000](https://github.com/20042000)


## Licencia
**©** Karol Daniela Ladino Ladino. Estudiante de Ingeniería de Sistemas de la Escuela Colombiana de Ingeniería Julio Garavito

