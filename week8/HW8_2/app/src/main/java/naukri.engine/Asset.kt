package naukri.engine

import android.util.Log

object Assert {
    fun log(vararg msg: Any) {
        val sb = StringBuilder()
        for (m in msg) {
            sb.append(m.toString(), " , ")
        }
        Log.println(Log.ASSERT, "Log", sb.dropLast(3).toString())
    }
}