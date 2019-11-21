package naukri.engine

import java.io.*

object SerializationManager {

    fun <T : Serializable> serialize(obj: T?): String {
        if (obj == null) {
            return ""
        }
        val baos = ByteArrayOutputStream()
        val oos = ObjectOutputStream(baos)
        oos.writeObject(obj)
        oos.close()

        return baos.toString("ISO-8859-1")
    }

    fun <T : Serializable> deserialize(string: String): T? {
        if (string.isEmpty()) {
            return null
        }

        var bais = ByteArrayInputStream(string.toByteArray(charset("ISO-8859-1")))
        var ois = ObjectInputStream(bais)

        return ois.readObject() as T
    }

    fun <T : Serializable> deserialize(string: String, clazz: Class<T>): T? = deserialize<T>(string)

}

fun <T : Serializable> T.deepCopy() : T? {
    val baos = ByteArrayOutputStream()
    val oos = ObjectOutputStream(baos)
    oos.writeObject(this)
    oos.close()
    val bais = ByteArrayInputStream(baos.toByteArray())
    val ois = ObjectInputStream(bais)
    return ois.readObject() as T
}