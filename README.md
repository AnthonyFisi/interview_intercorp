# Login facebook y firebase

**Índice**
1. Prerrequisitos
2. Instalación
   2.1 Configuración de Facebook Developer
   2.2.Configuración de Firebase
   2.3.Patrón de arquitectura
3. Ejecución de pruebas
4. Despliegue


## 1. Prerrequisitos
- Contar con una cuenta de GMAIL
- Crear una cuenta en Firebase (https://firebase.google.com/?hl=es)
- Habilitar la cuenta Facebook Developers (https://developers.facebook.com/)
- Contar con una cuenta de PlayStore (https://play.google.com/console/developers)
- Contar con una cuenta en Github guardar el repositorio (https://github.com/)

## 2. Instalación
##### 2.1 Configuración de Facebook Developer
- Habilitar la cuenta de facebook developer y luego proceder a crear una App
- Crear una App tipo Noinguno que esta al final de las opciones que da facebook.
- Luego de tener la cuenta debemos dirigirnos **Inicio de sesión con Facebook > Inicio rápido**
- Extraemos 2 datos importantes para configurar en Firebase Auhtentication **AppId y la clave secreta** de la siguiente parte
  **Configurar > Basica**

Recomendación

- Deben de agregar un usuario de prueba en **Roles en App > Roles** para que la app deje que realizen pruebas ya que esta en modo desarrollador, de caso contrario si desean poner en activo la App deben de agregar una política de seguridad en el siguiente lugar **Configurar > Basica > URL de la Política de privacidad** para que pase a modo activo.

##### 2.2.Configuración de Firebase
- Crear un propyecto en la consola de firebase
- Luego ir a **Compilación > Authentication > Sign-in method** y seleccionar facebook como modo de inicio de sesión.
- Ingresar los dos datos que mencionamos en Facebook Developer **AppId y la Clave secreta**

##### 2.3.Patrón de arquitectura
Use el patrón de arquitectura MVVM (Model-View-ViewModel) debido a la facilidad que proporciona para separa la logica de la parte del UI dando un mejor orden en la parte del codigo ademas de proporcionar un desacople entre cada capa.

![alt text](https://androidwave.com/wp-content/uploads/2019/05/mvvm-architecture-app-in-android.png)
##### 2.4 Estructura de carpetas
```
|__com.example.intercopr
    |__ model
        |__ UserModel.java
    |__ repository
        |__ LoginRepository.java
        |__ RegisterRepository.java
    |__ view
        |__ dialog
            |__ DatePickerFragment.java
        |__ HomeActivity.java
        |__ RegisterActivity.java
    |__ viewmodel
        |__ LoginViewModel
        |__ RegisterViewModel
```
## 3. Ejecución de pruebas

##### Pruebas de usuario
###### 3.1 Primero abrir la app e iniciar sesión
![alt text](https://firebasestorage.googleapis.com/v0/b/prueba2022-6b57f.appspot.com/o/WhatsApp%20Image%202023-03-31%20at%2012.57.59%20PM.jpeg?alt=media&token=332bb07b-174f-4430-b47f-75ebe236a090)
##### 3.2 Luego abrir la aplicación de facebook instalada en el celular para logearse
![alt text](https://firebasestorage.googleapis.com/v0/b/prueba2022-6b57f.appspot.com/o/WhatsApp%20Image%202023-03-31%20at%201.09.22%20PM.jpeg?alt=media&token=7637df70-8b9d-48cd-a421-809fd2e51f65)
##### 3.3 El Firebase Authentication va registrarse el nuevo inicio de sesión
![alt text](https://firebasestorage.googleapis.com/v0/b/prueba2022-6b57f.appspot.com/o/authenticationfirebase.PNG?alt=media&token=3a3253c1-57bc-41b5-af12-3b2912c728e9)
##### 3.4 Luego de ingresar corectamente las credenciales nos vamos a dirigir una siguiente pantalla para llenar el formulario del usuario que consta de Nombre , apellidos , edad y fecha de nacimiento.
![alt text](https://firebasestorage.googleapis.com/v0/b/prueba2022-6b57f.appspot.com/o/WhatsApp%20Image%202023-03-31%20at%2012.57.59%20PM%20(2).jpeg?alt=media&token=0bfbb6e6-0df6-4de4-904e-6c4d052c0b19)
##### 3.5 Una vez registrado en la base de datos de Firebase Realtime va insertarse un nuevo usuario.
![alt text](https://firebasestorage.googleapis.com/v0/b/prueba2022-6b57f.appspot.com/o/databseuser.PNG?alt=media&token=b7a6946c-8e05-4e43-abae-b54ad9f4fa87)
##### 3.6 La última pantalla que veremos sera de los datos del usuario registrado
![alt text](https://firebasestorage.googleapis.com/v0/b/prueba2022-6b57f.appspot.com/o/WhatsApp%20Image%202023-03-31%20at%2012.57.59%20PM%20(3).jpeg?alt=media&token=f0df1123-fb7e-4436-93b7-9f1340d07752)

## 4. Despliegue

