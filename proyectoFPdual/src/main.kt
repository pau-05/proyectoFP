package proyectoFPdual

import proyectoFPdual.eventos.*
import proyectoFPdual.eventos.Usuario.Companion.getAdmin

fun main() {
    // Inicializo las variables que va a rellenar el usuario durante el uso de los menús:
    var usr = ""
    var contra = ""
    var correo = ""
    var nomEvento = ""
    var ubiEvento = ""
    var fechaEvento = ""
    var cat = ""


    println("Bienvenido al portal de sostenibilidad! Para continuar seleccione alguna de las opciones:\n" +
                "1. Iniciar sesión\n" +
                "2. Crear cuenta"
    )
    val primerMenu = readln().toInt()
    when (primerMenu){
        1 -> {
            println("Escribe el nombre del usuario: ")
            usr = readln()
            println("Escribe la contraseña: ")
            contra = readln()
            println("Escribe tu correo: ")
            correo = readln()
            val comprobarUsr = Usuario.comprobarUsuario(usr, contra, correo)
            if (comprobarUsr) {
                println("Inicio de sesión exitoso. Seleccione una la acción que desea realizar: \n" +
                        "1. Apuntarse a evento\n" +
                        "2. Crear evento\n" +
                        "3. Modificar evento\n"
                )
                val segMenu= readln().toInt()
                when(segMenu){
                    1 -> {
                        println("Dime a que evento quieres apuntarte: ")
                        val evento = readln()
                    }
                    2 -> {
                        var permiso = getAdmin()
                        if (permiso){
                            println("Lo sentimos, pero no tienes los permisos necesarios para hacer cambios. " +
                                    "Inicie sesión como admin")
                        }else{
                            println("Bienvenido admin, escriba el nombre del evento que quieras crear: ")
                            nomEvento = readln()
                            println("Dime la fecha: ")
                            fechaEvento = readln()
                            println("Dime la ubicación del evento")
                            ubiEvento= readln()
                            println("Dime la categoría a la que pertenece: ")
                            cat = readln()
                            val comporbarEvento = Evento.comprobarEvento(nomEvento, ubiEvento, fechaEvento, cat)
                            if (comporbarEvento){
                                println("Evento ya existente, pruebe a crear otro evento.")
                            }else{
                                val nuevoEvento = Evento.crearEvento(nomEvento, ubiEvento, fechaEvento, cat)
                                println("Evento creado con éxito.")
                            }
                        }
                    }
                    3 -> {}
                }
            } else {
                println("Usuario o contraseña incorrectos.")
            }
        }

        2 -> {
            println("Escribe el nombre del usuario: ")
            usr = readln()
            println("Escribe la contraseña: ")
            contra = readln()
            println("Escribe tu correo: ")
            correo = readln()
            val comprobarUsr = Usuario.comprobarUsuario(usr, contra, correo)
            if (comprobarUsr) {
                println("Usuario ya existente, pruebe a iniciar sesión.")
            } else {
                val nuevoUsuario = Usuario.crearUsuario(usr, contra, correo)
                println("Usuario creado con éxito.")
            }
        }
    }
}