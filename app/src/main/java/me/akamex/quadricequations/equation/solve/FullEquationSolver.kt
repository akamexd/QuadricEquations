package me.akamex.quadricequations.equation.solve

import me.akamex.quadricequations.footerIndex
import me.akamex.quadricequations.headerIndex
import me.akamex.quadricequations.toStringValue
import kotlin.math.pow
import kotlin.math.sqrt

object FullEquationSolver : EquationSolver {

    override fun solve(a: Double, b: Double, c: Double): SolveResult {
        val steps = arrayListOf<String>()

        val aRow = a.toStringValue()
        val bRow = b.toStringValue()
        val cRow = c.toStringValue()

        val desc = b.pow(2) + 4 * a * c
        steps.add("D = b${headerIndex("2")}+4ac = $bRow${headerIndex("2")}+4*$aRow*$cRow = ${desc.toStringValue()}")

        if(desc > 0.0) {
            val descSquare = sqrt(desc)

            val x1 = -b + descSquare / (2 * a)
            steps.add("D > 0, то x${footerIndex("1")} = -b+√D/2a = -$bRow+${descSquare.toStringValue()}/2*$aRow = ${x1.toStringValue()}")

            val x2 = -b - descSquare / (2 * a)
            steps.add("D > 0, то x${footerIndex("2")} = -b-√D/2a = -$bRow-${descSquare.toStringValue()}/2*$aRow = ${x2.toStringValue()}")

            return SolveResult(error = null, result = "x${footerIndex("1")} = ${x1.toStringValue()}\nx${footerIndex("2")} = ${x2.toStringValue()}", steps)
        }
        if(desc == 0.0) {
            val x = -b / (2 * a)
            steps.add("D = 0, то x${footerIndex("1")} = -b/2a = -$bRow/2*$aRow = ${x.toStringValue()}")

            return SolveResult(error = null, result = "x${footerIndex("1")} = ${x.toStringValue()}", steps)
        }
        if(desc < 0.0) {
            steps.add("D < 0, то корней нет!")
            return SolveResult(error = null, result = "Корней нет", steps)
        }

        return SolveResult(error = "Дискриминант неопознан!")
    }
}