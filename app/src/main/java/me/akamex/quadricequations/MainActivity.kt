package me.akamex.quadricequations

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import me.akamex.quadricequations.equation.*

class MainActivity : AppCompatActivity() {

    var currentEquation: EquationFragment? = null

    private fun checkedHandle(index: Int) {
        currentEquation = when(index) {
            R.id.fullEquationButton -> FullEquation.newInstance()
            R.id.axEquationButton -> AxEquation.newInstance()
            R.id.axbxEquationButton -> AxBxEquation.newInstance()
            R.id.axcEquationButton -> AxCEquation.newInstance()
            else -> FullEquation.newInstance()
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.equationFrame, currentEquation!!, "")
        transaction.commit()
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            findViewById<TextView>(R.id.equationTextForExample).text = Html.fromHtml("ax${headerIndex("2")}+bx+c=0", Html.FROM_HTML_MODE_LEGACY)

            findViewById<RadioButton>(R.id.fullEquationButton).text = Html.fromHtml("ax${headerIndex("2")} + bx + c = 0", Html.FROM_HTML_MODE_LEGACY)
            findViewById<RadioButton>(R.id.axEquationButton).text = Html.fromHtml("ax${headerIndex("2")} = 0", Html.FROM_HTML_MODE_LEGACY)
            findViewById<RadioButton>(R.id.axcEquationButton).text = Html.fromHtml("ax${headerIndex("2")} + c = 0", Html.FROM_HTML_MODE_LEGACY)
            findViewById<RadioButton>(R.id.axbxEquationButton).text = Html.fromHtml("ax${headerIndex("2")} + bx = 0", Html.FROM_HTML_MODE_LEGACY)

            val equationRadio = findViewById<RadioGroup>(R.id.equationTypeGroup)
            equationRadio.setOnCheckedChangeListener { _, index ->
                checkedHandle(index)
            }
            checkedHandle(R.id.fullEquationButton)

            findViewById<Button>(R.id.getResult).setOnClickListener {
                currentFocus?.let { view ->
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(view.windowToken, 0)
                }

                currentEquation?.apply {
                    val result = solve()

                    val error = result.error.run {
                        if(this == null) {
                            return@run false
                        }
                        val dialog = Dialog(this@MainActivity)
                        dialog.setTitle("Ошибка!")
                        dialog.setContentView(Error.newInstance(this).requireView())
                        dialog.show()
                        return@run true
                    }
                    if(error) {
                        return@apply
                    }

                    findViewById<TextView>(R.id.result).apply {
                        visibility = if(result.result!!.isNotEmpty()) View.VISIBLE else View.INVISIBLE
                        text = Html.fromHtml(result.result, Html.FROM_HTML_MODE_LEGACY)
                    }
                    findViewById<TextView>(R.id.steps).apply {
                        visibility = if(result.steps.isNotEmpty()) View.VISIBLE else View.INVISIBLE
                        text = Html.fromHtml(result.steps.joinToString("\n"), Html.FROM_HTML_MODE_LEGACY)
                    }
                }
            }
        }
    }
}