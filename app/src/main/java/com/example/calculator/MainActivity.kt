package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var operation =""
    private var num1 = ""
    private var num2 = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numButtons = arrayOf(
            binding.btncero, binding.btn1,binding.btn2,binding.btn3,
            binding.btn4, binding.btn5,binding.btn6,binding.btn7,binding.btn8,
            binding.btn9
        )

        val operationesButtons = arrayOf(
            binding.btnsuma, binding.btnresta,
            binding.btnmulti,binding.btndivi,
            binding.btnigual,
        )
        val btnBorrar = binding.btnBorrar
        btnBorrar.setOnClickListener{
            onBorrarButtonClick()
        }

        for( button in numButtons){
            button.setOnClickListener{
                onNumButtonClick(button)
            }
        }


        for (button in  operationesButtons){
            button.setOnClickListener{
                onOperationButtonClick(button)
            }
        }
    }

    private fun onNumButtonClick(button: Button) {
        val buttonText = button.text.toString()
        if (operation.isEmpty()) {
            num1 += buttonText
            binding.tvResul.text = num1
        } else {
            num2 += buttonText
            binding.tvResul.text = num2
        }

    }

    private fun onBorrarButtonClick() {
        if (operation.isEmpty()){
            //
            if (num1.isNotEmpty()){
                num1 = num1.substring(0,num1.length -1)
                binding.tvResul.text =num1
            }
        } else{
            //
            if (num2.isNotEmpty()){
                num2 = num2.substring(0,num2.length -1)
                binding.tvResul.text =num2
            }
        }

    }




    private fun onOperationButtonClick(button: Button) {
        val buttonText = button.text.toString()
        when(buttonText){
            "+" -> operation = "+"
            "-" -> operation = "-"
            "*" -> operation = "*"
            "/" -> operation = "/"
            "=" -> {
                val result = calculateResul()
                binding.tvResul.text = result
                num1 = result
                num2 = ""
                operation = ""
            }

        }

    }

    private fun calculateResul(): String {
        val num1 = num1.toDouble()
        val num2 = num2.toDouble()
        return when (operation) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "*" -> (num1 * num2).toString()
            "/" -> if (num1 != 0.0)(num1 / num2).toString() else "Error"
            else -> "Error"
        }
    }

    }







