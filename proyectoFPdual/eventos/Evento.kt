package proyectoFPdual.eventos

class Evento(private var nombre: String, private var ubicacion: String, private var fecha: String) {
    //private var EventosDisp = ArrayList<Evento>
    private var categoria = arrayListOf("taller", "actividad", "conferencia")
/*en este metodo una vez iniciada la sesión se debe poder añadir eventos por tanto las clases deben colaborar,
* tmb se debe poder añadir al usuario algún evento así que se debe de crear una lista de eventos por cada usuario
* ahora, solo los admin pueden editar los eventos y añadirlos, por lo tanto si no cumple la clave "admin" (o pedir un código adicional)
* no puede editar ni crear, solo añadir eventos  a su lista*/
    fun crearEvento(nombre: String, ubicacion: String, fecha: String){

    }
}