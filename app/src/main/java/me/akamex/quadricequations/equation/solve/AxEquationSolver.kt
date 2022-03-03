package me.akamex.quadricequations.equation.solve

import me.akamex.quadricequations.headerIndex
import me.akamex.quadricequations.toStringValue
import kotlin.math.sqrt

object AxEquationSolver : EquationSolver {

    override fun solve(a: Double, b: Double, c: Double): SolveResult {
        val steps = arrayListOf<String>()

        val aRow = a.toStringValue()
        steps.add("ax${headerIndex("2")} = 0 -> $aRow * x${headerIndex("2")} = 0")

        val aOpposite = -a
        steps.add("x${headerIndex("2")} = -$aRow -> x = √${aOpposite.toStringValue()}")

        if(aOpposite < 0) {
            steps.add("Невозможно получить корень из ${aOpposite.toStringValue()}")
            return SolveResult(error = null, result = "Корней нет", steps)
        }

        val result = sqrt(aOpposite)
        val resultRow = result.toStringValue()
        steps.add("x = $resultRow")

        return SolveResult(error = null, "x = $resultRow", steps)
    }
}