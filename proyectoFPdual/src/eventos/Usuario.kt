package proyectoFPdual.eventos

class Usuario (val nombre: String, val contrasenia: String, val correo: String) {

    //Lista de usuarios dentro de la clase, el nombre de usuario es la clave y la contraseña el valor
    private val arrayUsuarios = ArrayList<Usuario>()
    //Lista especial que guardará los eventos a los que se haya apuntado cada usuario
    private val eventosUsr = ArrayList<Evento>()

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
    fun crearUsuario(nombre: String, contrasenia: String, correo: String): Usuario {
        val nuevoUsuario = Usuario(nombre, contrasenia, correo)
        if (!comprobarUsuario(nombre, contrasenia, correo)) {
            arrayUsuarios.add(nuevoUsuario)
        }
        return nuevoUsuario
    }

    //Método que le permite a los usuarios apuntarse a eventos
    fun agregarEventoAlUsuario(nombre: String, evento: Evento){
        val usuario = arrayUsuarios.find { it.nombre == nombre }
        usuario?.eventosUsr?.add(evento)
    }
}