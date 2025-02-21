package proyectoFPdual.eventos

class Usuario private constructor(val nombre: String, val contrasenia: String) {

    companion object {
        //Mapa de usuarios dentro de la clase, el nombre de usuario es la clave y la contraseña el valor
        private val mapaUsuarios = HashMap<String, String>()

        init {
            //Inicializar el mapa de usuarios con algunos datos
            mapaUsuarios["admin"] = "123"
            mapaUsuarios["usr1"] = "456"
            mapaUsuarios["usr2"] = "789"
        }

        // Método para crear instancias de Usuario
        fun crearUsuario(nombre: String, contrasenia: String): Usuario {
            return Usuario(nombre, contrasenia)
        }

        // Método para comprobar si un nombre de usuario y contraseña coinciden en el mapa
        fun comprobarUsuario(nombre: String, contrasenia: String): Boolean {
            return mapaUsuarios[nombre] == contrasenia
        }

        // Método para agregar un usuario al mapa
        fun agregarUsuario(usuario: Usuario) {
            mapaUsuarios[usuario.nombre] = usuario.contrasenia
        }
    }
}

