package proyectoFPdual.eventos

class Evento(private var nombreEvento: String, private var ubicacion: String, private var fecha: String,
             private var categoria: Categoria) {

    private var eventosActividad = ArrayList<Evento>()
    private var eventosConferencia = ArrayList<Evento>()
    private var eventosTalleres = ArrayList<Evento>()
    /*en este metodo una vez iniciada la sesión se debe poder añadir eventos por tanto las clases deben colaborar,
    * tmb se debe poder añadir al usuario algún evento así que se debe de crear una lista de eventos por cada usuario
    * ahora, solo los admin pueden editar los eventos y añadirlos, por lo tanto si no cumple la clave "admin" (o pedir un código adicional)
    * no puede editar ni crear, solo añadir eventos  a su lista
    *
    * 24/2: lo que necesito hacer es usar la dependencia para que una vez inicie sesión con el admin se lo traiga aquí y pueda modificar
    * los datos en los eventos (y crearlos), en los otros casos solo podrá unirse a los eventos.
    *
    * Añadir: que ignore mayus y minus, array con eventos creados por el admin, mirar como hacer perfiles de admins, listas de
    * eventos por cada admin (se tiene que crear automaticamente una lista vacía cuando se crea un admin) y
    * crear una lista para cada categoria de esta manera se imprimirán 3 al solicitar ver los eventos*/

    init {
        //Inicializo por lo menos un evento que puedan agregar los usuarios de cada categoría
        eventosActividad.add(Evento("Recolecta de basura", "Parque del sol, Puertollano",
            "20/04/2025", Categoria.ACTIVIDAD))
        eventosConferencia.add(Evento("Conferencia sobre el cambio climático",
            "Museo Cristina García Romero, Puertollano", "21/04/2025", Categoria.CONFERENCIA))
        eventosTalleres.add(Evento("Taller de cocina vegana", "Virtual", "22/04/2025",
            Categoria.TALLER))
    }

    fun crearEvento(nombreEvento: String, ubicacion: String, fecha: String, categoria: Categoria){
        val nuevoEvento = Evento(nombreEvento, ubicacion, fecha, categoria)
        if (!comprobarEvento(nombreEvento, ubicacion, fecha, categoria) && categoria==Categoria.TALLER){
            eventosTalleres.add(nuevoEvento)
        }else if (!comprobarEvento(nombreEvento, ubicacion, fecha, categoria) && categoria==Categoria.ACTIVIDAD){
            eventosActividad.add(nuevoEvento)
        }else if (!comprobarEvento(nombreEvento, ubicacion, fecha, categoria) && categoria==Categoria.CONFERENCIA){
            eventosConferencia.add(nuevoEvento)
        }
    }

    //Método que le permite a los admins modificar los eventos
    fun modificarEvento(nombreEvento: String, ubicacion: String, fecha: String, categoria: Categoria) {
        val eventoAModificar = Evento(nombreEvento, ubicacion, fecha, categoria)

        // Función para actualizar evento en una lista específica
        fun actualizarEvento(eventos: ArrayList<Evento>) {
            for (i in eventos.indices) {
                val evento = eventos[i]
                if (evento.nombreEvento == nombreEvento && evento.ubicacion == ubicacion && evento.fecha == fecha && evento.categoria == categoria) {
                    eventos[i] = eventoAModificar
                    return
                }
            }
        }

        // Llamar a la función para actualizar en cada lista de eventos
        actualizarEvento(eventosActividad)
        actualizarEvento(eventosTalleres)
        actualizarEvento(eventosConferencia)
    }

    //Método que comprueba que el evento que se quiera crear o borrar exista
    fun comprobarEvento(nombreEvento: String, ubicacion: String, fecha: String, categoria: Categoria): Boolean{
        for (evento in eventosActividad) {
            if (evento.nombreEvento == nombreEvento && evento.ubicacion == ubicacion && evento.fecha == fecha
                && evento.categoria== categoria){
                return true
            }
        }
        for (evento in eventosTalleres) {
            if (evento.nombreEvento == nombreEvento && evento.ubicacion == ubicacion && evento.fecha == fecha
                && evento.categoria== categoria){
                return true
            }
        }
        for (evento in eventosConferencia) {
            if (evento.nombreEvento == nombreEvento && evento.ubicacion == ubicacion && evento.fecha == fecha
                && evento.categoria== categoria){
                return true
            }
        }
        return false
    }

    //Método que muestra los eventos disponibles
    fun mostrarEventosPorCategoria(eventosActividad: List<Evento>, categoria: Categoria): List<Evento> {
        return eventosActividad.filter { it.categoria == categoria }
    }

    //Método que mira de manera superficial si existe para poder apuntarse (funciona para lo usuarios)
    fun compobarNombreEvento(nombreEvento: String): Boolean{
        for (evento in eventosActividad) {
            if (evento.nombreEvento == nombreEvento){
                return true
            }
        }
        for (evento in eventosTalleres) {
            if (evento.nombreEvento == nombreEvento){
                return true
            }
        }
        for (evento in eventosConferencia) {
            if (evento.nombreEvento == nombreEvento){
                return true
            }
        }
        return false
    }
}