package me.akamex.quadricequations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val ERROR_ARG = "errorText"

class Error : Fragment() {

    private var errorText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            errorText = it.getString(ERROR_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_error, container, false)
        view.findViewById<TextView>(R.id.errorText).text = errorText
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(errorText: String) = Error().apply {
            arguments = Bundle().apply {
                putString(ERROR_ARG, errorText)
            }
        }

    }
}