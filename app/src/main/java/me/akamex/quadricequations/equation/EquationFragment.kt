package me.akamex.quadricequations.equation

import android.widget.EditText
import androidx.fragment.app.Fragment
import me.akamex.quadricequations.R
import me.akamex.quadricequations.equation.solve.EquationSolver
import me.akamex.quadricequations.equation.solve.SolveResult

abstract class EquationFragment : Fragment(), EquationSolver {

    abstract fun getEquationSolver(): EquationSolver

    fun solve(): SolveResult {
        view?.apply {
            val aEdit = findViewById<EditText>(R.id.aValue)
            var a = 1.0
            if(aEdit != null) {
                if (aEdit.text.toString().isEmpty()) {
                    aEdit.setText("1")
                }
                a = aEdit.text.toString().toDoubleOrNull() ?: 1.0
            }

            val bEdit = findViewById<EditText>(R.id.bValue)
            var b = 1.0
            if(bEdit != null) {
                if (bEdit.text.toString().isEmpty()) {
                    bEdit.setText("1")
                }
                b = bEdit.text.toString().toDoubleOrNull() ?: 1.0
            }

            val cEdit = findViewById<EditText>(R.id.cValue)
            var c = 0.0
            if (cEdit != null) {
                if (cEdit.text.toString().isEmpty()) {
                    cEdit.setText("0")
                }
                c = cEdit.text.toString().toDoubleOrNull() ?: 0.0
            }

            return solve(a, b, c)
        }
        return SolveResult(error = "Ошибка приложения")
    }

    override fun solve(a: Double, b: Double, c: Double): SolveResult = getEquationSolver().solve(a, b, c)
}