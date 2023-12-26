package cl.rortegam.trabajo_uno_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.round

class CalculoContratoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo_contrato)

        val btnVolver = findViewById<Button>(R.id.btnvolver)
        val btnCalcular = findViewById<Button>(R.id.btncalcular)
        val etSalarioBruto = findViewById<EditText>(R.id.etsalariobruto)
        val tvResultado = findViewById<TextView>(R.id.tvresultado)

        btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnCalcular.setOnClickListener {
            val salarioBruto = etSalarioBruto.text.toString().toDoubleOrNull()

            if (salarioBruto != null) {
                val retencion = salarioBruto * 0.20
                val salarioLiquido = salarioBruto - retencion

                // Redondear el resultado a dos decimales
                val salarioLiquidoRedondeado = round(salarioLiquido * 100) / 100

                val resultadoText = "Salario Líquido: $salarioLiquidoRedondeado"
                tvResultado.text = resultadoText
            } else {
                // Manejar el caso en el que la entrada no sea un número válido
                tvResultado.text = "Ingrese un salario bruto válido"
            }
        }
    }
}
