<div align="center">
  <h1>📚 Inspire Library</h1>
</div>
<div>
    <h2>Descripción del proyecto</h2>
    Este proyecto busca optimizar la gestión de libros y préstamos en una librería, facilitando tanto la administración interna como la experiencia de los usuarios. Su propósito es ofrecer una solución moderna y eficiente que automatice procesos como el seguimiento de préstamos y la disponibilidad de libros.  
</div>

<div>
    <p className='mt-3'>
        <strong>
        Tecnologías usadas
        </strong>
    </p>  
</div>

**Front-end:**
- React
- Bootstrap
- JSX

**Back-end:**
- Java
- Spring Framework

**Base de datos:**
- Base de datos en la nube (Azure).

**Cómo instalar y usar el proyecto**

1. **Clonar el repositorio**  
   a- Abrir terminal o bash
   b- Navegar al directorio donde guardarás el archivo:
   cd  DirectiorioParaProyecto
   c- Clonar el repositorio:
   git clone <URL del repositorio>

2. **Verificar la conexión con el repositorio remoto**  
   a- Entrar al directorio del proyecto
   cd  DirectiorioParaProyecto
   b- Ejecutar el comando para verificar la conexión correcta
   git remote -v

3. **Verificar la rama activa**  
   a- Despues de clonar, Git te ubicará en la rama principal, para comprobarlo:
   git branch
   b- Para cambiar a una rama específica (main o develop), ejecutá:
   git checkout nombre-de-la-rama
   ó también:
   git switch nombre-de-la-rama

4. **Instalar dependencias de la rama DEVELOP**  
   a- Una vez en la rama develop que usa Node.js, para instalar dependencias antes de ejecutar el proyecto:    
   npm install ó npm i

5. **Ejecutar el proyecto**  
   Para ejecutar el proyecto, necesitamos realizar varias tareas:
   a- PARA EL FRONTEND (RAMA DEVELOP)
   npm run start
   b- PARA EL BACKEND (RAMA MAIN)
   Compilar el proyecto --> mvn clean package
   Ejecutar el proyecto --> java -jar target/nombre-del-archivo.jar

Con estos pasos, el proyecto se ejecutará correctamente, y desde donde estemos ejecutando el frontend (rama develop) se abrirá la aplicación en el navegador por defecto de su computador, ¡ya puede conocer todas las extraordinarias funcionalidades  y ventajas del proyecto!. Esperamos sea de su agrado.

**En caso de nuevas actualizaciones del repositorio**
Para nuevas versiones subidas al repositorio, usted podrá disponer de las mismas de la siguiente manera:
a- Abrir terminal o bash
b- Navegar al directorio donde guardarás el archivo:
cd  DirectiorioParaProyecto
c- Ubicarse en la rama donde se realizó la actualización:
git switch nombreRama
d- Ejecutar comando para obtener los cambios:
git pull
e- Ejecutar comando para verificar el estado de la rama:
git status
