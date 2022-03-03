package me.akamex.quadricequations.equation

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import me.akamex.quadricequations.R
import me.akamex.quadricequations.equation.solve.AxCEquationSolver
import me.akamex.quadricequations.equation.solve.EquationSolver
import me.akamex.quadricequations.headerIndex

class AxCEquation : EquationFragment() {

    override fun getEquationSolver(): EquationSolver = AxCEquationSolver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ax_c_equation, container, false)

        view.findViewById<TextView>(R.id.x2).text = Html.fromHtml("x${headerIndex("2")} + ", Html.FROM_HTML_MODE_LEGACY)

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = AxCEquation()

    }
}