package cl.rortegam.trabajo_uno_mobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.rortegam.trabajo_uno_mobile.ui.theme.Trabajo_Uno_MobileTheme
import kotlin.math.round

class CalculoHonorariosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculoHonorarios()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculoHonorarios() {
    val contexto = LocalContext.current
    var salarioBruto by remember { mutableStateOf("") }
    var resultadoSalario by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Empleados a Honorarios",
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 8.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )



        TextField(
            value = salarioBruto,
            onValueChange = { salarioBruto = it },
            label = { Text("Ingrese salario bruto") },
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = {
            // Realizar cálculo con retención del 13%
            val salarioBrutoDouble = salarioBruto.toDoubleOrNull()

            if (salarioBrutoDouble != null) {
                val retencion = salarioBrutoDouble * 0.13
                val salarioLiquido = salarioBrutoDouble - retencion

                // Redondear el resultado a dos decimales
                val salarioLiquidoRedondeado = round(salarioLiquido * 100) / 100

                // Mostrar el resultado en la variable resultadoSalario
                resultadoSalario = "Salario Líquido: $salarioLiquidoRedondeado"
            } else {
                // Manejar el caso en el que la entrada no sea un número válido
                resultadoSalario = "Ingrese un salario bruto válido"
            }
        }) {
            Text("Calcular Salario")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar el resultado del cálculo
        resultadoSalario?.let {
            Text(it, modifier = Modifier.padding(16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Acción del botón "Volver"
            val intent = Intent(contexto, MainActivity::class.java)
            contexto.startActivity(intent)
        }) {
            Text("Volver")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculoHonorariosPreview() {
    Trabajo_Uno_MobileTheme {
        CalculoHonorarios()
    }
}
