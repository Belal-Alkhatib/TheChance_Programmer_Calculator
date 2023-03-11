package com.example.programmercalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.programmercalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var firstNumber:Double = 0.0
    var currentOperation: Operation? = null
    lateinit var buttonsFromTowToNine:Array<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addCallbacks()
        buttonsFromTowToNine = arrayOf(binding.btnDigitTwo, binding.btnDigitThree,
            binding.btnDigitFour, binding.btnDigitFive,
            binding.btnDigitSix, binding.btnDigitSeven,
            binding.btnDigitEight,binding.btnDigitNine)
    }

    fun onClickNumber(v: View){
        val newDigit = (v as Button).text.toString()
        val oldDigits = binding.tvResultNumber.text.toString()
        val newTextNumber = oldDigits + newDigit
        binding.tvResultNumber.text = newTextNumber
    }
    private fun addCallbacks(){
        binding.btnOperationAdd.setOnClickListener {
            prepareOperation(Operation.Plus)
        }
        binding.btnOperationSubtract.setOnClickListener {
            prepareOperation(Operation.Minus)
        }
        binding.btnOperationMultiply.setOnClickListener {
            prepareOperation(Operation.Mul)
        }

        binding.btnOperationDivide.setOnClickListener {
            prepareOperation(Operation.Div)
        }

        //Operations buttons
        binding.btnBin.setOnClickListener {
            selectButton(binding.btnBin, binding.btnOct, binding.btnDec, binding.btnHex)
        }
        binding.btnOct.setOnClickListener {
            selectButton(binding.btnOct, binding.btnBin, binding.btnDec, binding.btnHex)
        }
        binding.btnDec.setOnClickListener {
            selectButton(binding.btnDec, binding.btnOct, binding.btnBin, binding.btnHex)
        }
        binding.btnHex.setOnClickListener {
            selectButton(binding.btnHex, binding.btnDec, binding.btnOct, binding.btnBin)
        }

        binding.btnDeleteAll.setOnClickListener {
            clearInput()
        }

        binding.btnResult.setOnClickListener {
            val result = doCurrentOperation()
            binding.tvResultNumber.text = result.toString()
        }
    }

    private fun clearInput(){
        binding.tvResultNumber.text = ""
    }
    private fun prepareOperation(operation: Operation){
        firstNumber = binding.tvResultNumber.text.toString().toDouble()
        clearInput()
        currentOperation = operation
    }

    private fun doCurrentOperation(): Double{
        val secondNumber = binding.tvResultNumber.text.toString().toDouble()
        return when(currentOperation){
            Operation.Plus -> firstNumber + secondNumber
            Operation.Minus -> firstNumber - secondNumber
            Operation.Mul -> firstNumber * secondNumber
            Operation.Div ->  firstNumber / secondNumber
            null -> 0.0
        }
    }

    private fun selectButton(selectedButton: Button, button1: Button, button2: Button, button3: Button) {
        val defaultButtonDrawable = ContextCompat.getDrawable(selectedButton.context, R.drawable.rounded_button)
        val selectedButtonDrawable = ContextCompat.getDrawable(selectedButton.context, R.drawable.selected_rounded_button)

          selectedButton.background = selectedButtonDrawable
          button1.background = defaultButtonDrawable
          button2.background = defaultButtonDrawable
          button3.background = defaultButtonDrawable

        disableIrrelevantButtons(selectedButton)
    }
    private fun disableIrrelevantButtons(selectedButton: Button){
        when (selectedButton) {
            binding.btnBin -> {
                binding.hexButtonsLayout.visibility = View.INVISIBLE
                for (i in buttonsFromTowToNine.indices){
                    buttonsFromTowToNine[i].isClickable = false
                }
            }
            binding.btnOct -> {
                binding.hexButtonsLayout.visibility = View.INVISIBLE

                for (i in buttonsFromTowToNine.indices){
                    buttonsFromTowToNine[i].isClickable =
                        !(buttonsFromTowToNine[i] == binding.btnDigitNine || buttonsFromTowToNine[i] == binding.btnDigitEight)
                }

            }
            binding.btnDec -> {
                binding.hexButtonsLayout.visibility = View.INVISIBLE
                for (i in buttonsFromTowToNine.indices){
                    if(!buttonsFromTowToNine[i].isClickable){
                        buttonsFromTowToNine[i].isClickable = true
                    }
                }

            }
            binding.btnHex -> {
                if(binding.hexButtonsLayout.visibility == View.INVISIBLE){
                    binding.hexButtonsLayout.visibility = View.VISIBLE
                }
            }
        }
    }

}
