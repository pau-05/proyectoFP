package proyectoFPdual.eventos

class Evento(var nombreEvento: String,var ubicacion: String,var fecha: String,
             var categoria: Categoria) {

    companion object{
        //Creo 3 listas que me servirá más adelante para imprimir por categoría
        private var eventosActividad = ArrayList<Evento>()
        private var eventosConferencia = ArrayList<Evento>()
        private var eventosTalleres = ArrayList<Evento>()

        init {
            //Inicializo por lo menos un evento de cada categoría
            eventosActividad.add(Evento("Recolecta de basura", "Parque del sol, Puertollano",
                "20/04/2025", Categoria.ACTIVIDAD))
            eventosConferencia.add(Evento("Conferencia sobre el cambio climático",
                "Museo Cristina García Romero, Puertollano", "21/04/2025", Categoria.CONFERENCIA))
            eventosTalleres.add(Evento("Taller de cocina vegana", "Virtual", "22/04/2025",
                Categoria.TALLER))
        }

        //Método que comprueba que la fecha cumpla con el formato dd/mm/yyyy
        fun comprobarFecha(fecha: String):Boolean{
            val regex = Regex("""\d{2}/\d{2}/\d{4}""")
            return regex.matches(fecha)
        }

        //Método que crea los eventos
        fun crearEvento(nombreEvento: String, ubicacion: String, fecha: String, categoria: Categoria){
            val nuevoEvento = Evento(nombreEvento, ubicacion, fecha, categoria)
            if (comprobarFecha(fecha)) {
                if (!comprobarEvento(nombreEvento, ubicacion, fecha, categoria) && categoria == Categoria.TALLER) {
                    eventosTalleres.add(nuevoEvento)
                    println("Evento creado con éxito.")
                } else if (!comprobarEvento(nombreEvento, ubicacion, fecha, categoria) && categoria == Categoria.ACTIVIDAD) {
                    eventosActividad.add(nuevoEvento)
                    println("Evento creado con éxito.")
                } else if (!comprobarEvento(nombreEvento, ubicacion, fecha, categoria) && categoria == Categoria.CONFERENCIA) {
                    eventosConferencia.add(nuevoEvento)
                    println("Evento creado con éxito.")
                }
            }else{
                println("Fecha incorrecta, por favor introduzca un formato válido")
            }
        }

        //Método que le permite a los admins modificar los eventos
        fun modificarEvento(nombreEvento: String, ubicacion: String, fecha: String, categoria: Categoria) {
            val eventoAModificar = Evento(nombreEvento, ubicacion, fecha, categoria)
            //En caso de que cambie la fecha volvemos a comprobar que esté correcta
            if (comprobarFecha(fecha)) {
                // Función para actualizar evento en una lista específica
                fun actualizarEvento(eventos: ArrayList<Evento>) {
                    for (i in eventos.indices) {
                        val evento = eventos[i]
                        if (evento.nombreEvento == nombreEvento && evento.ubicacion == ubicacion &&
                            evento.fecha == fecha && evento.categoria == categoria
                        ) {
                            eventos[i] = eventoAModificar
                            return
                        }
                    }
                }
                // Llamar a la función para actualizar en cada lista de eventos
                actualizarEvento(eventosActividad)
                actualizarEvento(eventosTalleres)
                actualizarEvento(eventosConferencia)
            }else{
                println("Fecha incorrecta, por favor introduzca un formato válido")
            }
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
        fun mostrarEventosPorCategoria(categoria: Categoria) {
            if (categoria==Categoria.ACTIVIDAD){
                for (evento in eventosActividad){
                    println("Nombre: ${evento.nombreEvento}, Ubicación: ${evento.ubicacion}, Fecha: ${evento.fecha}, " +
                            "Categoría: ${evento.categoria}")
                }
            }else if (categoria==Categoria.CONFERENCIA){
                for (evento in eventosConferencia) {
                    println(
                        "Nombre: ${evento.nombreEvento}, Ubicación: ${evento.ubicacion}, Fecha: ${evento.fecha}, " +
                                "Categoría: ${evento.categoria}"
                    )
                }
            }else{
                for (evento in eventosTalleres){
                println("Nombre: ${evento.nombreEvento}, Ubicación: ${evento.ubicacion}, Fecha: ${evento.fecha}, " +
                        "Categoría: ${evento.categoria}")
                }
            }
        }

        //Método que mira de manera superficial si existe para poder apuntarse (funciona para lo usuarios)
        fun compobarNombreEvento(nombreEvento: String): Evento?{
            for (evento in eventosActividad) {//Le añado el lowercase para que no den problema las mayúsculas
                if (evento.nombreEvento.lowercase() == nombreEvento.lowercase()){
                    return evento
                }
            }
            for (evento in eventosTalleres) {
                if (evento.nombreEvento.lowercase() == nombreEvento.lowercase()){
                    return evento
                }
            }
            for (evento in eventosConferencia) {
                if (evento.nombreEvento.lowercase() == nombreEvento.lowercase()){
                    return evento
                }
            }
            return null
        }

        //Método para cancelar eventos
        fun cancelarEvento(nombreEvento: String){
            val evento = compobarNombreEvento(nombreEvento)
            if (evento != null) {
                eventosConferencia.remove(evento)
                eventosTalleres.remove(evento)
                eventosActividad.remove(evento)
                println("\n${nombreEvento} ha sido cancelado.\n")
            }else{
                println("\nEvento no encontrado.\n")
            }
        }

    }
}