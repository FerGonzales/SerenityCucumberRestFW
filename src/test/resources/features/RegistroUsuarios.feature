#language: es
Característica: Registro de usuarios

  @Test1
  Escenario: Registro exitoso de usuario
    Dado Julian es un cliente que quiere administrar sus productos bancarios
    Cuando el envia la informacion requerida para el registro
    Entonces el debe obtener una cuenta virtual para poder ingresar cuando lo requiera

  @Test2
  Escenario: Registro Fallido de usuario
    Dado Julian es un cliente que quiere administrar sus productos bancarios
    Cuando el envia la informacion requerida para el registro
    Entonces el debe obtener una cuenta virtual para poder ingresar cuando lo requiera