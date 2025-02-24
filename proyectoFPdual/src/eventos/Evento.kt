package proyectoFPdual.eventos

class Evento(private var nombreEvento: String, private var ubicacion: String, private var fecha: String,
             private var categoria: String) {

    companion object{
        private var EventosDisp = ArrayList<Evento>()
        private var EventosActividad = ArrayList<Evento>()
        private var EventosConferencia = ArrayList<Evento>()
        private var EventosTalleres = ArrayList<Evento>()
    /*en este metodo una vez iniciada la sesión se debe poder añadir eventos por tanto las clases deben colaborar,
    * tmb se debe poder añadir al usuario algún evento así que se debe de crear una lista de eventos por cada usuario
    * ahora, solo los admin pueden editar los eventos y añadirlos, por lo tanto si no cumple la clave "admin" (o pedir un código adicional)
    * no puede editar ni crear, solo añadir eventos  a su lista
    *
    * 24/2: lo que necesito hacer es usar la dependencia para que una vez inicie sesión con el admin se lo traiga aquí y pueda modificar
    * los datos en los eventos (y crearlos), en lo sotros casos solo podrá unirse a los eventos.
    *
    * Añadir: que ignore mayus y minus, array con eventos creados por el admin, mirar como hacer perfiles de admins, comprobación
    * de fecha, listas de eventos por cada admin (se tiene que crear automaticamente una lista vacía cuando se crea un admin) y
    * crear una lista para cada categoria d esta manera se imprimirán 3 al solicitar ver los eventos*/

        init {
            //Inicializo por lo menos un evento que puedan agregar los usuarios
            EventosDisp.add(Evento("Recolecta de basura", "Parque del sol, Puertollano", "20/04/2025", "Actividad"))
        }

        fun crearEvento(nombreEvento: String, ubicacion: String, fecha: String, categoria: String){
            val nuevoEvento = Evento(nombreEvento, ubicacion, fecha, categoria)
            if (!comprobarEvento(nombreEvento, ubicacion, fecha, categoria)){
                EventosDisp.add(nuevoEvento)
            }
        }

        //Método que le permite a los admins modificar los eventos
        fun modificarEvento(){

        }

        //Método que le permite a los usuarios apuntarse a eventos
        fun aniadirEvento(){

        }

        //Método que comprueba que el evento que se quiera crear o borrar exista
        fun comprobarEvento(nombreEvento: String, ubicacion: String, fecha: String, categoria: String): Boolean{
            for (evento in EventosDisp) {
                if (evento.nombreEvento == nombreEvento && evento.ubicacion == ubicacion && evento.fecha == fecha
                    && evento.categoria== categoria){
                    return true
                }
            }
            return false
        }
    }
}