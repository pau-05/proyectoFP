package proyectoFPdual.eventos

class Usuario (val nombre: String, val contrasenia: String, val correo: String) {

    //Lista especial que guardará los eventos a los que se haya apuntado cada usuario
    private val eventosUsr = ArrayList<Evento>()

    companion object{
        //Lista de usuarios dentro de la clase, el nombre de usuario es la clave y la contraseña el valor
        private val arrayUsuarios = ArrayList<Usuario>()

        init {
            //Inicializo la lista de usuarios con algunos datos
            arrayUsuarios.add(Usuario("usr1", "456", "usr1@gmail.com"))
            arrayUsuarios.add(Usuario("usr2", "789", "usr2@gmail.com"))
        }

        // Método para comprobar si un nombre de usuario, contraseña y correo coinciden en el array
        fun comprobarUsuario(nombre: String, contrasenia: String, correo: String): Boolean {
            for (usuario in arrayUsuarios) {
                if (usuario.nombre == nombre && usuario.contrasenia == contrasenia && usuario.correo == correo) {
                    return true
                }
            }
            return false
        }

        // Método para crear instancias de Usuario
        fun crearUsuario(nombre: String, contrasenia: String, correo: String): Usuario? {
            val nuevoUsuario = Usuario(nombre, contrasenia, correo)
            if (!comprobarUsuario(nombre, contrasenia, correo)) {
                arrayUsuarios.add(nuevoUsuario)
            }
            //Verifico si el nombre de usuario ya está en uso
            for (usuario in arrayUsuarios) {
                if (usuario.nombre == nuevoUsuario.nombre) {
                    return null
                }
            }
            return nuevoUsuario
        }

        //Método que le permite a los usuarios apuntarse a eventos
        fun agregarEventoAlUsuario(nombre: String, nombreEvento: String) {
            val usuario = arrayUsuarios.find { it.nombre == nombre }
            val evento = Evento.compobarNombreEvento(nombreEvento)
            if (usuario != null && evento != null) {
                usuario.eventosUsr.add(evento)
                println("\nEvento '${evento.nombreEvento}' agregado a ${usuario.nombre}.\n")
            }else{
                println("\nEvento no encontrado. Vuelva a intentarlo más tarde.\n")
            }
        }

        //Método para imprimir los eventos según el usuario
        fun imprimirEventosPorUsuario(nombre: String) {
            val usuario = arrayUsuarios.find { it.nombre == nombre }
            if (usuario != null) {
                println("Eventos de ${usuario.nombre}:")
                for (evento in usuario.eventosUsr) {
                    println("\nNombre: ${evento.nombreEvento}, Ubicación: ${evento.ubicacion}, Fecha: ${evento.fecha}, " +
                            "Categoría: ${evento.categoria}\n")
                }
            } else {
                println("Usuario no encontrado.")
            }
        }

        //Método para cancelar incripción del usuario
        fun quitarInscripcion(nombre: String, nombreEvento: String) {
            val usuario = arrayUsuarios.find { it.nombre == nombre }
            val evento = Evento.compobarNombreEvento(nombreEvento)
            if (usuario != null && evento != null) {
                usuario.eventosUsr.remove(evento)
                println("\nEvento '${evento.nombreEvento}' ha sido borrado de ${usuario.nombre}.\n")
            }else{
                println("\nEvento no encontrado. Vuelva a intentarlo más tarde.\n")
            }
        }
    }
}