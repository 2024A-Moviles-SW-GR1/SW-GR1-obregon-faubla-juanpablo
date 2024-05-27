import java.util.*

fun main() {
    println("Hola mundo! Yessir")


//variables inmutable
    val inmutable: String = "Juan"


//variables mutables
    var mutable: String = "Carlos"
    mutable = "Carlos2"
// Duck typing
    val nombre = "Juan"
    val apellido = "Perez"
    val edad = 30
    var sueldo: Double = 1.0
    var casado = false
    var estado: Char = 'A'
    val fechaNacimiento: Date = Date()



    //wHEN
    val estadoCivil = "SOLTERO"
    when (estadoCivil) {
        "SOLTERO" -> {
            println("Acá puede aplicar a la universidad")
        }
        "CASADO" -> {
            println("Acá ya no puede aplicar")
        }
        "DIVORCIADO" -> {
            println("Acá puede aplicar a la universidad")
        }
        "VIUDO" -> {
            println("Acá puede aplicar a la universidad")
        }
        else -> {
            println("No reconocido")
        }
    }
    val esSoltero = estadoCivil == "CASADO"
    val coqueteo = if (esSoltero) "Si" else "No"


    calcularSueldo(1000.00, 14.00)
    calcularSueldo(calculoEspecial = 15, sueldo = 1000.00, tasa = 14.00)

    //Uso de clases
    val suma = Suma(4, 2)
    println(suma.sumar())
    val suma3 = Suma(7, null)
    println(suma3.sumar())
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)
    Suma.agregarHistorial(5)
    println(Suma.historialSumas)
    println(Suma.pi)

    //Arreglos
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    println(arregloEstatico)
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)


    //For each
    val respuestaArregloForEach = arregloDinamico
        .forEach {
            println("Valor actual: $it")
        }

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActualIteracion ->
            return@map valorActualIteracion.toDouble() + 100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico
        .map { it -> it + 15 }
    println(respuestaMapDos)

    val respuestaFilter = arregloDinamico
        .filter {
            valorActualIteracion -> valorActualIteracion > 5
        }
    println(respuestaFilter)

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilterDos)

    val respuestaAny: Boolean = arregloDinamico
        .any {
            valorActualIteracion -> valorActualIteracion > 5
        }
    println(respuestaAny)

    val respuestaAll: Boolean = arregloDinamico
        .all {
            valorActualIteracion -> valorActualIteracion > 5
        }
    println(respuestaAll)

    val respuestaReduce = arregloDinamico
        .reduce { acumulado, valorActualIteracion ->
            return@reduce acumulado + valorActualIteracion
        }
    println(respuestaReduce)

}


fun holaMundo(mensaje: String): Unit {
    println("Mensaje: $mensaje.")
}

fun calcularSueldo(
    sueldo: Double, //Requeridos
    tasa: Double = 12.00, //Opcionales
    calculoEspecial: Int? = null //Nulos
): Double {
    if (calculoEspecial != null) {
        return sueldo * tasa * calculoEspecial
    } else {
        return sueldo * tasa
    }
}

abstract class NumerosJava(
    protected val numeroUno: Int,
    protected val numeroDos: Int

    ) {
    init {
        this.numeroUno
        this.numeroDos
        println("Inicializar el objeto")

    }
}

class Suma(
    uno: Int,
    dos: Int
) : NumerosJava(uno, dos) {
    constructor(
        uno: Int?,
        dos: Int,
        tres: Int
    ) : this(if (uno == null) 0 else uno, dos) {
    }
    //constructor secundario
    constructor(
        uno: Int,
        dos: Int?
    ) : this(uno, if (dos == null) 0 else dos) {
    }
    fun sumar(): Int {
        val total = this.numeroUno + this.numeroDos
        // this.numeroUno
        // this.numeroDos
        agregarHistorial(total)
        return total
    }

    companion object {
        val pi = 3.1416

        fun elevarAlCuadrado(numero: Int): Int {
            return numero * numero
        }
        val historialSumas = arrayListOf<Int>()

        fun agregarHistorial(nuevaSuma: Int) {
            this.historialSumas.add(nuevaSuma)
        }
    }

}       