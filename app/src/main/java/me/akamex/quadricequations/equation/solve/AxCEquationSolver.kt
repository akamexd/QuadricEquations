package me.akamex.quadricequations.equation.solve

import me.akamex.quadricequations.headerIndex
import me.akamex.quadricequations.toStringValue
import kotlin.math.sqrt

object AxCEquationSolver : EquationSolver {

    override fun solve(a: Double, b: Double, c: Double): SolveResult {
        val steps = arrayListOf<String>()

        val aRow = a.toStringValue()
        val cRow = c.toStringValue()
        steps.add("ax${headerIndex("2")} + c = 0 -> $aRow * x${headerIndex("2")} + $cRow = 0")

        val cOpposite = -c
        steps.add("$aRow * x${headerIndex("2")} = -${cOpposite.toStringValue()} -> $aRow * x${headerIndex("2")} = ${cOpposite.toStringValue()}")

        val x2 = cOpposite / a
        steps.add("x${headerIndex("2")} = ${cOpposite.toStringValue()} / $aRow -> x${headerIndex("2")} = ${x2.toStringValue()}")

        if(x2 < 0) {
            steps.add("Невозможно получить корень из ${x2.toStringValue()}")
            return SolveResult(error = null, result = "Корней нет", steps)
        }
        val x = sqrt(x2)

        steps.add("x = ${x.toStringValue()}")

        return SolveResult(error = null, "x = ${x.toStringValue()}", steps)
    }
}