package me.akamex.quadricequations.equation.solve

data class SolveResult(
    val error: String? = null,
    val result: String? = null,
    val steps: List<String> = arrayListOf()
)