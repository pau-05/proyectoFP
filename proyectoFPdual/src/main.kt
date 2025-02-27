package proyectoFPdual

import proyectoFPdual.eventos.*

fun main() {
    // Inicializo las variables que va a rellenar el usuario/admin (como usuario, fecha, etc) durante el uso de los menús:
    var usr = ""
    var contra = ""
    var correo = ""
    var nomEvento = ""
    var ubiEvento = ""
    var fechaEvento = ""
    var cat = Categoria.ACTIVIDAD //Inicializo la variable en cualquier categoria, se cambiará más adelante
    var evento = ""
    var elegirCat = 0

    do {
        println("Bienvenido al portal de sostenibilidad! Para continuar seleccione alguna de las opciones:\n" +
                    "1. Iniciar sesión\n" +
                    "2. Crear cuenta\n" +
                    "3. Salir"
        )
        val primerMenu = readln().toInt()
        when (primerMenu){
            1 -> {//En este primer menú lo que hace es separar los admins de los usuarios
                println("Escribe el nombre del usuario: ")
                usr = readln()
                println("Escribe la contraseña: ")
                contra = readln()
                println("Escribe tu correo: ")
                correo = readln()
                //Con estas dos variables comprueba con los métodos si es admin o usuario y lo enviará a su correspondiente menú
                val comprobarUsr = Usuario.comprobarUsuario(usr, contra, correo)
                val comprobarAmind = Organizador.comprobarAdmin(usr, contra, correo)
                if (comprobarUsr) {
                    do {//Opciones de usuario, la idea es que haga todas las operaciones que quiera y luego cierre sesión
                        println("Seleccione la acción que desea realizar: \n" +
                                "1. Apuntarse a evento\n" +
                                "2. Cancelar inscripción a evento\n" +
                                "3. Ver mis eventos\n" +
                                "4. Cerrar sesión\n"
                        )
                        val menuUsr= readln().toInt()
                        when(menuUsr) {
                            1 -> {
                                println("Dime la categoría de los eventos que quieres ver: \n" +
                                        "\n1. Conferencia\n" +
                                        "2. Taller\n" +
                                        "3. Actividad\n")
                                elegirCat = readln().toInt()
                                do {/*Cada vez que quiera apuntarse tendrá que ver los eventos disponibles separados por
                                categoría*/
                                    when(elegirCat){
                                        1 -> {cat=Categoria.CONFERENCIA}
                                        2 -> {cat=Categoria.TALLER}
                                        3 -> {cat}
                                        else -> {
                                            //Comprueba que no puedan poner ningún número fuera de las opciones disponibles
                                            println("No hay más categorías, elige una de las disponibles.")
                                        }
                                    }
                                //Obliga a elegir 1, 2 o 3, de lo contrario no se puede avanzar
                                }while(elegirCat !in 1..4)
                                println("Eventos disponibles: \n")
                                //Muestra todas las categorías y ahora sí el usuario puede inscribirse con la que prefiera
                                Evento.mostrarEventosPorCategoria(cat)
                                println("\nDime a que evento quieres apuntarte: ")
                                evento = readln()
                                Evento.compobarNombreEvento(evento)
                                Usuario.agregarEventoAlUsuario(usr, evento)
                            }
                            2 -> {
                                /*Primero se le despliega una lista de los eventos que ya está apuntado y de ahí puede seleccionar
                                * el que quiera (escribiendo el nombre del evento)*/
                                println("Lista de eventos a los que estás apuntado:\n")
                                Usuario.imprimirEventosPorUsuario(usr)
                                println("Dime cúal de tus eventos quieres cancelar: ")
                                val evento = readln()
                                Evento.compobarNombreEvento(evento)
                                Usuario.quitarInscripcion(usr, evento)
                            }
                            3 -> {
                                /*Si el usuario desea ver la lista de eventos a los que está apuntado en cualquier momento,
                                puede hacerlo*/
                                println("Tus eventos a los que estás incrito:")
                                Usuario.imprimirEventosPorUsuario(usr)
                            }
                        }
                    }while(menuUsr!=4)
                } else if (comprobarAmind){//Menú del admin:
                    println("Bienvenido admin, Seleccione una la acción que desea realizar:\n" +
                            "\n1. Crear evento\n" +
                            "2. Borrar evento\n" +
                            "3. Modificar evento\n" +
                            "4. Ver eventos disponibles.\n" +
                            "5. Cerrar sesión.\n")
                    val menuAdmin = readln().toInt()
                    do {
                        when(menuAdmin) {
                            1 -> {println("Escriba el nombre del evento que quieras crear: ")
                                nomEvento = readln()
                                println("Dime la fecha: ")
                                fechaEvento = readln()
                                println("Dime la ubicación del evento")
                                ubiEvento= readln()
                                println("Dime la categoría a la que pertenece: " +
                                        "\n1. Conferencia\n" +
                                        "2. Taller\n" +
                                        "3. Actividad\n")
                                elegirCat = readln().toInt()
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
                                val comporbarEvento = Evento.comprobarEvento(nomEvento,
                                    ubiEvento, fechaEvento, cat)
                                if (comporbarEvento){
                                    /*Llamo al método comprobarEvento para asegurar que el mismo evento no se cree 2 veces*/
                                    println("Evento ya existente, pruebe a crear otro evento.")
                                }else{//Si todo está correcto, lo crea
                                    Evento.crearEvento(nomEvento, ubiEvento, fechaEvento, cat)
                                }
                            }
                            2 -> {
                                println("Dime el nombre del evento que quieres cancelar: ")
                                evento = readln()
                                Evento.cancelarEvento(evento)
                            }
                            3 -> {
                                println("Dime el nombre del evento a modificar: ")
                                evento = readln()
                                //Comprueba que el evento exista. Si es así se puede modificar
                                val conseguir = Evento.compobarNombreEvento(evento)
                                if (conseguir != null){
                                    println("Dime el nuevo nombre:")
                                    nomEvento = readln()
                                    println("Dime la nueva fecha: ")
                                    fechaEvento = readln()
                                    println("Dime la nueva ubicación del evento")
                                    ubiEvento= readln()
                                    println("Dime la categoría a la que lo quieres cambiar: ")
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
                                    Evento.modificarEvento(nomEvento, ubiEvento, fechaEvento, cat)
                                }else{
                                    println("El evento no existe y por tanto no lo puedes modificar. Intente crear el evebnto")
                                }
                            }
                        }
                    } while (menuAdmin!=5)
                } else {
                    println("Usuario, contraseña o email incorrectos.")
                }
            }
            2 -> {
                //Debido a que tomé el nombre de usuario como clave primaria, no puede haber 2 que se llamen igual
                println("Escribe el nombre del usuario: ")
                usr = readln()
                println("Escribe la contraseña: ")
                contra = readln()
                println("Escribe tu correo: ")
                correo = readln()
                if (Usuario.crearUsuario(usr, contra, correo)==null){
                    println("\nUsuario ya existente, pruebe con otro nombre o inicie sesión.\n")
                }else{
                    println("Usuario creado con éxito.\n")
                }
            }
        }
    }while (primerMenu!=3)
    println("¡Adiós! Vuelva pronto.")
}