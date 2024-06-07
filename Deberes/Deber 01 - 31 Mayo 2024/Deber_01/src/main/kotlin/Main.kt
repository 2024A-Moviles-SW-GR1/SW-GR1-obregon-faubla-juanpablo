import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    println("Hola!")
    val arregloArtistas = ArrayList<Artista>()

    while (true) {
        println("1. Agregar artista")
        println("2. Mostrar artistas")
        println("3. Eliminar artista")
        println("4. Actualizar artista")
        println("5. Agregar obra")
        println("6. Eliminar obra")
        println("7. Actualizar obra")
        println("8. Guardar en archivo")
        println("9. Cargar de archivo")
        println("10. Salir")

        var opcion = readlnOrNull()


        when (opcion) {
            "1" -> {


                println("Ingrese el nombre del artista")
                val nombre = readLine()
                println("Ingrese el numero de obras")
                val numeroObras = readLine()
                println("Ingrese el rating")
                val rating = readLine()
                println("Ingrese la fecha de nacimiento")
                val fechaNacimiento = readLine()
                println("Esta vivo? (true/false)")
                val estaVivo = readLine()
                val artista = Artista(nombre!!, numeroObras!!.toInt(), rating!!.toFloat(), Date(), estaVivo!!.toBoolean())
                arregloArtistas.add(artista)
                println("Artista agregado")
                //println(arregloArtistas)



            }
            "2" -> {



                arregloArtistas.forEach {
                    println(it)
                }




            }
            "3" -> {
                println("Seleccione el nombre del artista a eliminar")
                val nombre = readLine()
                val artista = arregloArtistas.find { it.getNombres().equals(nombre) }
                if (artista != null) {
                    arregloArtistas.remove(artista)
                    println("Artista eliminado")
                } else {
                    println("Artista no encontrado")
                }
            }
            "4" -> {



                println("Ingrese el nombre del artista a actualizar")
                val nombre = readLine()
                val artista = arregloArtistas.find { it.getNombres().equals(nombre)}
                if (artista != null) {
                    println("Que desea actualizar?")
                    println("1. Nombre")
                    println("2. Numero de obras")
                    println("3. Rating")
                    println("4. Fecha de nacimiento")
                    println("5. Esta vivo")
                    val opcion = readlnOrNull()
                    when (opcion) {
                        "1" -> {
                            println("Ingrese el nuevo nombre")
                            val nuevoNombre = readLine()
                            artista.setNombres(nuevoNombre!!)
                        }
                        "2" -> {
                            println("Ingrese el nuevo numero de obras")
                            val nuevoNumeroObras = readLine()
                            artista.setNumeroObras(nuevoNumeroObras!!.toInt())
                        }
                        "3" -> {
                            println("Ingrese el nuevo rating")
                            val nuevoRating = readLine()
                            artista.setRating(nuevoRating!!.toFloat())
                        }
                        "4" -> {
                            println("Ingrese la nueva fecha de nacimiento")
                            val nuevaFechaNacimiento = readLine()
                            artista.setFechaNacimiento(Date())
                        }
                        "5" -> {
                            println("Esta vivo? (true/false)")
                            val nuevoEstaVivo = readLine()
                            artista.setEstaVivo(nuevoEstaVivo!!.toBoolean())
                        }
                    }
                } else {
                    println("Artista no encontrado")
                }

            }

            "5" -> {
                println("Ingrese el nombre del artista a quien pertenece la obra")
                val nombre = readLine()
                val artista = arregloArtistas.find { it.getNombres().equals(nombre) }
                if (artista != null) {
                    println("Ingrese el nombre de la obra")
                    val nombreObra = readLine()
                    println("Ingrese el anio de creacion")
                    val anioCreacion = readLine()
                    println("Esta en venta? (true/false)")
                    val estaEnVenta = readLine()
                    println("Copias vendidas")
                    val copiasVendidas = readLine()
                    println("Precio")
                    val precio = readLine()
                    val obra = Obra(nombreObra!!, Date(), estaEnVenta!!.toBoolean(), copiasVendidas!!.toInt(), precio!!.toFloat())
                    artista.setObras(obra)

                    println("Obra agregada")
                } else {
                    println("Artista no encontrado")
                }
            }

            "6" -> {
                println("Ingrese el nombre del artista a quien pertenece la obra")
                val nombre = readLine()
                val artista = arregloArtistas.find { it.getNombres().equals(nombre) }
                if (artista != null) {
                    println("Ingrese el nombre de la obra a eliminar")
                    val nombreObra = readLine()
                    val obra = artista.getObras().find { it.getNombreObra().equals(nombreObra) }
                    if (obra != null) {
                        artista.eliminarObra(obra)
                        println("Obra eliminada")
                    } else {
                        println("Obra no encontrada")
                    }
                } else {
                    println("Artista no encontrado")
                }
            }

            "7" -> {
                println("Ingrese el nombre del artista a quien pertenece la obra")
                val nombre = readLine()
                val artista = arregloArtistas.find { it.getNombres().equals(nombre) }
                if (artista != null){
                    println("Ingrese el nombre de la obra a actualizar")
                    val nombreObra = readLine()
                    val obra = artista.getObras().find { it.getNombreObra().equals(nombreObra) }
                    if (obra != null) {
                        println("Que desea actualizar?")
                        println("1. Nombre")
                        println("2. Anio de creacion")
                        println("3. Esta en venta")
                        println("4. Copias vendidas")
                        println("5. Precio")
                        val opcion = readlnOrNull()
                        when (opcion) {
                            "1" -> {
                                println("Ingrese el nuevo nombre")
                                val nuevoNombre = readLine()
                                obra.setNombreObra(nuevoNombre!!)
                            }
                            "2" -> {
                                println("Ingrese el nuevo anio de creacion")
                                val nuevoAnioCreacion = readLine()
                                obra.setAnioCreacion(Date())
                            }
                            "3" -> {
                                println("Esta en venta? (true/false)")
                                val nuevoEstaEnVenta = readLine()
                                obra.setEstaEnVenta(nuevoEstaEnVenta!!.toBoolean())
                            }
                            "4" -> {
                                println("Ingrese las nuevas copias vendidas")
                                val nuevasCopiasVendidas = readLine()
                                obra.setCopiasVendidas(nuevasCopiasVendidas!!.toInt())
                            }
                            "5" -> {
                                println("Ingrese el nuevo precio")
                                val nuevoPrecio = readLine()
                                obra.setPrecio(nuevoPrecio!!.toFloat())
                            }
                        }
                    } else {
                        println("Obra no encontrada")
                    }


                }

            }

            "8" -> {
                println("Guardando en archivo")
                val archivo = File("artistas.txt")
                val escritor = FileWriter(archivo)

                arregloArtistas.forEach {
                    escritor.write("Artista\n")
                    escritor.write(it.getNombres())
                    escritor.write("\n")
                    escritor.write(it.getNumeroObras().toString())
                    escritor.write("\n")
                    escritor.write(it.getRating().toString())
                    escritor.write("\n")
                    escritor.write(it.getFechaNacimiento().toString())
                    escritor.write("\n")
                    escritor.write(it.getEstaVivo().toString())
                    escritor.write("\n")
                    it.getObras().forEach {
                        escritor.write("Obras\n")
                        escritor.write(it.getNombreObra())
                        escritor.write("\n")
                        escritor.write(it.getAnioCreacion().toString())
                        escritor.write("\n")
                        escritor.write(it.getEstaEnVenta().toString())
                        escritor.write("\n")
                        escritor.write(it.getCopiasVendidas().toString())
                        escritor.write("\n")
                        escritor.write(it.getPrecio().toString())
                        escritor.write("\n")
                    }
                }
                escritor.close()
                println("Guardado en archivo")
            }

            "9" -> {
                println("Cargando de archivo")
                val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
                var archivo = File("artistas.txt")
                val lector = archivo.bufferedReader()
                var linea = lector.readLine()

                var artistaActual: Artista? = null

                while (linea != null) {
                    when (linea) {
                        "Artista" -> {
                            val nombre = lector.readLine() ?: ""
                            val numeroObras = lector.readLine()?.toIntOrNull() ?: 0
                            val rating = lector.readLine()?.toFloatOrNull() ?: 0.0f
                            val fechaNacimientoStr = lector.readLine() ?: ""
                            val fechaNacimiento = dateFormat.parse(fechaNacimientoStr) ?: Date()
                            val estaVivo = lector.readLine()?.toBoolean() ?: false
                            val artista = Artista(nombre, numeroObras, rating, fechaNacimiento, estaVivo)
                            arregloArtistas.add(artista)
                            artistaActual = artista
                        }
                        "Obras" -> {
                            val nombre = lector.readLine() ?: ""
                            val anioCreacionStr = lector.readLine() ?: ""
                            val anioCreacion = dateFormat.parse(anioCreacionStr) ?: Date()
                            val estaEnVenta = lector.readLine()?.toBoolean() ?: false
                            val copiasVendidas = lector.readLine()?.toIntOrNull() ?: 0
                            val precio = lector.readLine()?.toFloatOrNull() ?: 0.0f
                            val obra = Obra(nombre, anioCreacion, estaEnVenta, copiasVendidas, precio)
                            artistaActual?.setObras(obra)
                        }
                    }
                    linea = lector.readLine()
                }
                println("Cargado de archivo")



            }
            "10" -> {
                break
            }


        }
    }

}




class Artista {
    var nombres: String = ""
    var numeroObras: Int = 0
    var rating: Float = 0.0f
    var fechaNacimiento: Date = Date()
    var estaVivo: Boolean = false
    val obras: MutableList<Obra> = mutableListOf()


    constructor(nombres: String, numeroObras: Int, rating: Float, fechaNacimiento: Date, estaVivo: Boolean) {
        this.nombres = nombres
        this.numeroObras = numeroObras
        this.rating = rating
        this.fechaNacimiento = fechaNacimiento
        this.estaVivo = estaVivo
    }
    @JvmName("getAlternativeNombres")
    fun setNombres(nombres: String) {
        this.nombres = nombres
    }
    @JvmName("getAlternativeNombres")
    fun getNombres(): String {
        return this.nombres
    }
    @JvmName("getAlternativeNombres")
    fun setNumeroObras(numeroObras: Int) {
        this.numeroObras = numeroObras
    }
    @JvmName("getAlternativeNombres")
    fun getNumeroObras(): Int {
        return this.numeroObras
    }
    @JvmName("getAlternativeNombres")
    fun setRating(rating: Float) {
        this.rating = rating
    }
    @JvmName("getAlternativeNombres")
    fun getRating(): Float {
        return this.rating
    }
    @JvmName("getAlternativeNombres")
    fun setFechaNacimiento(fechaNacimiento: Date) {
        this.fechaNacimiento = fechaNacimiento
    }
    @JvmName("getAlternativeNombres")
    fun getFechaNacimiento(): Date {
        return this.fechaNacimiento
    }
    @JvmName("getAlternativeNombres")
    fun setEstaVivo(estaVivo: Boolean) {
        this.estaVivo = estaVivo
    }
    @JvmName("getAlternativeNombres")
    fun getEstaVivo(): Boolean {
        return this.estaVivo
    }
    @JvmName("getAlternativeNombres")
    fun setObras(obra: Obra) {
        this.obras.add(obra)
    }
    @JvmName("getAlternativeNombres")
    fun getObras(): List<Obra> {
        obras.forEach {
            var obras = ""
             obras += it.toString()
        }
        return obras
    }

    fun eliminarObra(obra: Obra) {
        obras.remove(obra)
    }

    override
    fun toString(): String {
        return "Artista [nombres=" + nombres + ", numeroObras=" + numeroObras + ", rating=" + rating + ", fechaNacimiento="+
                fechaNacimiento + ", estaVivo=" + estaVivo + ", obras=" + obras + "]"
    }




}

class Obra {
    var nombreObra: String = ""
    var anioCreacion: Date = Date()
    var EstaEnVenta: Boolean = false
    var copiasVendidas: Int = 0
    var precio: Float = 0.0f

    constructor(nombreObra: String, anioCreacion: Date, EstaEnVenta: Boolean, copiasVendidas: Int, precio: Float) {
        this.nombreObra = nombreObra
        this.anioCreacion = anioCreacion
        this.EstaEnVenta = EstaEnVenta
        this.copiasVendidas = copiasVendidas
        this.precio = precio
    }
    @JvmName("getAlternativeNombres")
    fun setNombreObra(nombreObra: String) {
        this.nombreObra = nombreObra
    }
    @JvmName("getAlternativeNombres")
    fun getNombreObra(): String {
        return this.nombreObra
    }
    @JvmName("getAlternativeNombres")
    fun setAnioCreacion(anioCreacion: Date) {
        this.anioCreacion = anioCreacion
    }
    @JvmName("getAlternativeNombres")
    fun getAnioCreacion(): Date {
        return this.anioCreacion
    }
    @JvmName("getAlternativeNombres")
    fun setEstaEnVenta(EstaEnVenta: Boolean) {
        this.EstaEnVenta = EstaEnVenta
    }
    @JvmName("getAlternativeNombres")
    fun getEstaEnVenta(): Boolean {
        return this.EstaEnVenta
    }
    @JvmName("getAlternativeNombres")
    fun setCopiasVendidas(copiasVendidas: Int) {
        this.copiasVendidas = copiasVendidas
    }
    @JvmName("getAlternativeNombres")
    fun getCopiasVendidas(): Int {
        return this.copiasVendidas
    }
    @JvmName("getAlternativeNombres")
    fun setPrecio(precio: Float) {
        this.precio = precio
    }
    @JvmName("getAlternativeNombres")
    fun getPrecio(): Float {
        return this.precio
    }

    override
    fun toString(): String {
        return "Obra [nombreObra=" + nombreObra + ", anioCreacion=" + anioCreacion + ", EstaEnVenta=" + EstaEnVenta +
                ", copiasVendidas=" + copiasVendidas + ", precio=" + precio + "]"
    }

}