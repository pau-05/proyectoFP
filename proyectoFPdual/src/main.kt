package proyectoFPdual

import proyectoFPdual.eventos.*

fun main() {
    // Inicializo las variables que va a rellenar el usuario durante el uso de los menús:
    var usr = ""
    var contra = ""
    var correo = ""
    var nomEvento = ""
    var ubiEvento = ""
    var fechaEvento = ""
    var cat = Categoria.ACTIVIDAD //Inicializo la variable en cualquier categoria, se cambiará más adelante
    var evento = ""


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
            val comprobarUsr = Usuario(usr, contra, correo).comprobarUsuario(usr, contra, correo)
            val comprobarAmind = Organizador(usr, contra, correo).comprobarAdmin(usr, contra, correo)
            if (comprobarUsr) {
                do {
                println("Seleccione una la acción que desea realizar: \n" +
                        "1. Apuntarse a evento\n" +
                        "2. Cancelar inscripción a evento\n" +
                        "3. Ver mis eventos\n" +
                        "4. Cerrar sesión\n"
                )
                val menuUsr= readln().toInt()
                    when(menuUsr) {
                        1 -> {
                            //Se tienen que poder ver los eventos
                            println("Dime a que evento quieres apuntarte: ")
                            evento = readln()
                            val encuentraEvento= Evento(nomEvento, ubiEvento, fechaEvento, cat).compobarNombreEvento(evento)
                            if (encuentraEvento){
                                val apuntarseEvento = Usuario(usr, contra, correo).agregarEventoAlUsuario(nomEvento)
                            }else{
                                println("El evento no existe y por tanto no te puedes apuntar. Inténtelo de nuevo más tarde.")
                            }
                        }
                        2 -> {
                            println("Lista de eventos a los que estás apuntado")
                            //Debe mostrar la lista de eventos a los que está apuntado
                            println("Dime que evento quieres cancelar: ")
                            val evento = readln()
                            val encuentraEvento= Evento(nomEvento, ubiEvento, fechaEvento, cat).compobarNombreEvento(evento)
                            if (encuentraEvento){
                                val apuntarseEvento = Usuario(usr, contra, correo).agregarEventoAlUsuario(usr)
                            }else{
                                println("El evento no existe y por tanto no te puedes apuntar. Inténtelo de nuevo más tarde.")
                            }
                        }
                        3 -> {
                            println("Eventos de ${Usuario.nombre}:")
                            Usuario.eventosUsr.forEach { evento ->
                                println("- ${evento.nombre} el día ${evento.fecha} (Categoría: ${evento.categoria})")
                            }
                        }
                    }
                }while (menuUsr==4)

            } else if (comprobarAmind){
                //Crear menú de admins
                println("Bienvenido admin, Seleccione una la acción que desea realizar: \n" +
                        "1. Crear evento\n" +
                        "2. Borrar evento\n" +
                        "3. Modificar evento\n" +
                        "4. Cerrar sesión."
                )
                val menuAdmin = readln().toInt()
                do {
                    when(menuAdmin) {
                        1 -> {println(" escriba el nombre del evento que quieras crear: ")
                            nomEvento = readln()
                            println("Dime la fecha: ")
                            fechaEvento = readln()
                            println("Dime la ubicación del evento")
                            ubiEvento= readln()
                            println("Dime la categoría a la que pertenece: ")
                            var elegirCat = readln().toInt()
                            do {
                                when(elegirCat){
                                    1 -> {cat=Categoria.CONFERENCIA}
                                    2 -> {cat=Categoria.TALLER}
                                    3 -> {cat}
                                    else -> {
                                        println("No hay más categorías, elige una de las disponibles.")
                                    }
                                }
                            }while (elegirCat !in 1..4)//Obliga a elegir 1, 2 o 3, de lo contrario no se puede comprobar
                            val comporbarEvento = Evento(nomEvento, ubiEvento, fechaEvento, cat).comprobarEvento(nomEvento,
                                ubiEvento, fechaEvento, cat)
                            if (comporbarEvento){
                                println("Evento ya existente, pruebe a crear otro evento.")
                            }else{
                                val nuevoEvento = Evento(nomEvento, ubiEvento, fechaEvento, cat).crearEvento(nomEvento,
                                    ubiEvento, fechaEvento, cat)
                                println("Evento creado con éxito.")
                            }
                        }
                        2 -> {

                        }
                        3 -> {
                            println("Dime el nombre del evento a modificar: ")
                            evento = readln()
                            val encuentraEvento= Evento(nomEvento, ubiEvento, fechaEvento, cat).compobarNombreEvento(evento)
                            if (encuentraEvento){
                                println("Dime el nuevo nombre:")
                                nomEvento = readln()
                                println("Dime la fecha: ")
                                fechaEvento = readln()
                                println("Dime la ubicación del evento")
                                ubiEvento= readln()
                                println("Dime la categoría a la que pertenece: ")
                                var elegirCat = readln().toInt()
                                do {
                                    when(elegirCat){
                                        1 -> {cat=Categoria.CONFERENCIA}
                                        2 -> {cat=Categoria.TALLER}
                                        3 -> {cat}
                                        else -> {
                                            println("No hay más categorías, elige una de las disponibles.")
                                        }
                                    }
                                }while (elegirCat !in 1..4)
                                val modificacionEvento = Evento(nomEvento, ubiEvento, fechaEvento, cat).modificarEvento(nomEvento,
                                    ubiEvento, fechaEvento, cat)
                            }else{
                                println("El evento no existe y por tanto no lo puedes modificar. Inténtelo de nuevo más tarde.")
                            }
                        }
                    }
                } while (menuAdmin==4)
            } else {
                println("Usuario, contraseña o email incorrectos.")
            }
        }
        2 -> {
            println("Escribe el nombre del usuario: ")
            usr = readln()
            println("Escribe la contraseña: ")
            contra = readln()
            println("Escribe tu correo: ")
            correo = readln()
            val comprobarUsr = Usuario(usr, contra, correo).comprobarUsuario(usr, contra, correo)
            if (comprobarUsr) {
                println("Usuario ya existente, pruebe a iniciar sesión.")
            } else {
                val nuevoUsuario = Usuario(usr, contra, correo).crearUsuario(usr, contra, correo)
                println("Usuario creado con éxito.")
            }
        }
    }
}