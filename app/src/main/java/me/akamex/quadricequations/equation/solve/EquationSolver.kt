package me.akamex.quadricequations.equation.solve

interface EquationSolver {

    fun solve(a: Double, b: Double, c: Double): SolveResult

}