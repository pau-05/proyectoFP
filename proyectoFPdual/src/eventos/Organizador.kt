package proyectoFPdual.eventos


class Organizador(val nombre: String, val contrasenia: String, val correo: String) {

    //Lista especial que guardará los eventos que haya creado cada admin
    private val eventos = ArrayList<Evento>()

    companion object{
        //Lista de los admins
        private val arrayAdmins = ArrayList<Organizador>()

        init {
            //Inicializo por lo menos 1 admin
            arrayAdmins.add(Organizador("admin", "123", "admin@gmail.com"))
        }

        //Método que enviará un booleano para permitirle hacer cambios tanto en los eventos como en los usuarios y admins
        fun comprobarAdmin(nombre: String, contrasenia: String, correo: String): Boolean {
            for (admin in arrayAdmins) {
                if (admin.nombre == nombre && admin.contrasenia == contrasenia && admin.correo == correo) {
                    return true
                }
            }
            return false
        }

        //Método que relaciona al organizador con el evento que ha creado
        fun agregarEventoAlOrganizador(nombre: String, evento: Evento){
            val organizador = arrayAdmins.find { it.nombre == nombre }
            organizador?.eventos?.add(evento)
        }
    }
}