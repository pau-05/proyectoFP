package proyectoFPdual

import proyectoFPdual.eventos.Usuario

fun main() {
    // Inicializo las variables que va a rellenar el usuario durante el uso de los menús:
    var usr = ""
    var contra = ""
    val nomEvento = ""
    var inicioUsr = false

    println("Bienvenido al portal de sostenibilidad! Para continuar seleccione alguna de las opciones:\n" +
                "1. Iniciar sesión\n" +
                "2. Crear cuenta"
    )
    val primerMenu = readln().toInt()
    when (primerMenu) {
        1 -> {
            println("Escribe el nombre del usuario: ")
            usr = readln()
            println("Escribe la contraseña: ")
            contra = readln()
            val comprobarUsr = Usuario.comprobarUsuario(usr, contra)
            if (comprobarUsr) {
                println("Inicio de sesión exitoso.")
                inicioUsr = true
            } else {
                println("Usuario o contraseña incorrectos.")
            }
        }

        2 -> {
            println("Escribe el nombre del usuario: ")
            usr = readln()
            println("Escribe la contraseña: ")
            contra = readln()
            val comprobarUsr = Usuario.comprobarUsuario(usr, contra)
            if (comprobarUsr) {
                println("Usuario ya existente, pruebe a iniciar sesión o cambie la contraseña si desea.")
            } else {
                val nuevoUsuario = Usuario.crearUsuario(usr, contra)
                Usuario.agregarUsuario(nuevoUsuario)
                println("Usuario creado con éxito.")
            }
        }
    }
}