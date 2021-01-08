package homework.postbox

fun main(args: Array<String>) {
    val sides = arrayOf("length", "width", "height")
    while (true) {
        try {
            val map = HashMap<String, Float>()
            for (s in sides) map[s] = BoxTester().input(s)
            BoxTester().getBox(map[sides[0]], map[sides[1]], map[sides[2]])
            break
        } catch (e: Exception) {
            println("Error. Please try again.")
            continue
        }
    }
}

class BoxTester {
    fun input(side: String): Float {
        println("Please enter object's $side: ")
        var value = readLine()!!.toFloat()
        if (value < 0) throw Exception()
        return value
    }

    fun getBox(length: Float?, width: Float?, height: Float?) {
        val boxes = arrayOf(Box3(), Box5())
        for (box in boxes) {
            if (box.validate(length, width, height)) {
                box.printBox()
                return
            }
        }
        println("No suitable box.")
    }
}

open class Box(val length: Float, val width: Float, val height: Float) {

    fun validate(length: Float?, width: Float?, height: Float?): Boolean {
        return this.length >= length!! && this.width >= width!! && this.height >= height!!
    }

    fun printBox() {
        println(this.javaClass.simpleName)
    }
}

class Box3 : Box(23f, 14f, 13f)

class Box5 : Box(39.5f, 27.5f, 23f)