package com.example.programmercalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.programmercalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var buttonsFromTowToNine:Array<Button>
    lateinit var buttonPressed:MutableMap<String, Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addCallbacks()

        buttonsFromTowToNine = arrayOf(binding.btnDigitTwo, binding.btnDigitThree,
            binding.btnDigitFour, binding.btnDigitFive,
            binding.btnDigitSix, binding.btnDigitSeven,
            binding.btnDigitEight,binding.btnDigitNine)


        buttonPressed = mutableMapOf(BIN_BUTTON to false, OCT_BUTTON to false,
            DEC_BUTTON to false, HEX_BUTTON to true)

    }

    fun onClickNumber(v: View){
        val newDigit = (v as Button).text.toString()
        val oldDigits = binding.tvResultNumber.text.toString()
        val newTextNumber = oldDigits + newDigit
        binding.tvResultNumber.text = newTextNumber
    }
    private fun addCallbacks(){
        //Operations buttons
        binding.btnBin.setOnClickListener {
            changeButtonColorOnClick(binding.btnBin, binding.btnOct, binding.btnDec, binding.btnHex)
            clearInput()
            changButtonPressed(BIN_BUTTON)
        }
        binding.btnOct.setOnClickListener {
            changeButtonColorOnClick(binding.btnOct, binding.btnBin, binding.btnDec, binding.btnHex)
            clearInput()
            changButtonPressed(OCT_BUTTON)
        }
        binding.btnDec.setOnClickListener {
            changeButtonColorOnClick(binding.btnDec, binding.btnOct, binding.btnBin, binding.btnHex)
            clearInput()
            changButtonPressed(DEC_BUTTON)
        }
        binding.btnHex.setOnClickListener {
            changeButtonColorOnClick(binding.btnHex, binding.btnDec, binding.btnOct, binding.btnBin)
            changButtonPressed(HEX_BUTTON)
        }

        binding.btnDeleteAll.setOnClickListener {
            clearInput()
        }
        binding.btnRetry.setOnClickListener {
            clearInput()
        }

        binding.btnResult.setOnClickListener {
            if (binding.tvResultNumber.text.isNotEmpty()){
                val currentNumber = binding.tvResultNumber.text.toString()
                for ((key, value) in buttonPressed) {
                    if(key == BIN_BUTTON && value){
                        val result = handleConversionButtons(binding.btnBin, currentNumber)
                        binding.tvResultNumber.text = result
                    }else if (key == OCT_BUTTON && value){
                        val result = handleConversionButtons(binding.btnOct, currentNumber)
                        binding.tvResultNumber.text = result
                    }else if (key == DEC_BUTTON && value){
                        val result = handleConversionButtons(binding.btnDec, currentNumber)
                        binding.tvResultNumber.text = result
                    }else if (key == HEX_BUTTON && value){
                        val result = handleConversionButtons(binding.btnHex, currentNumber)
                        binding.tvResultNumber.text = result
                    }
                }

            }else{
                Toast.makeText(this, "Please Enter a Number", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun clearInput(){
        binding.tvResultNumber.text = ""
    }

    private fun changeButtonColorOnClick(selectedButton: Button, button1: Button, button2: Button, button3: Button) {
        val defaultButtonDrawable = ContextCompat.getDrawable(selectedButton.context, R.drawable.rounded_button)
        val selectedButtonDrawable = ContextCompat.getDrawable(selectedButton.context, R.drawable.selected_rounded_button)

          selectedButton.background = selectedButtonDrawable
          button1.background = defaultButtonDrawable
          button2.background = defaultButtonDrawable
          button3.background = defaultButtonDrawable

        disableIrrelevantButtons(selectedButton)
    }

    private fun handleConversionButtons(selectedButton: Button, number:String): String {
        return when (selectedButton) {
            binding.btnBin -> {
                val calculationResult = NumberConverter.fromBinary(number)
                val toOct = calculationResult.value1
                val toDec = calculationResult.value2
                val toHex = calculationResult.value3

                "OCT: $toOct \nDEC: $toDec\nHEX: $toHex"
            }
            binding.btnOct -> {
                val calculationResult = NumberConverter.fromOctal(number)
                val toBin = calculationResult.value1
                val toDec = calculationResult.value2
                val toHex = calculationResult.value3

                "BIN: $toBin \nDEC: $toDec\nHEX: $toHex"
            }
            binding.btnDec -> {
                val calculationResult = NumberConverter.fromDecimal(number)
                val toBin = calculationResult.value1
                val toOct = calculationResult.value2
                val toHex = calculationResult.value3

                "BIN: $toBin \nOCT: $toOct\nHEX: $toHex"
            }
            binding.btnHex -> {
                val calculationResult = NumberConverter.fromHexadecimal(number)
                val toBin = calculationResult.value1
                val toOct = calculationResult.value2
                val toDec = calculationResult.value3

                "BIN: $toBin \nOCT: $toOct\nDEC: $toDec"
            }
            else -> {
                "BIN: 0 \nOCT: 0\nDEC: 0"
            }
        }
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
                for (i in buttonsFromTowToNine.indices){
                    if(!buttonsFromTowToNine[i].isClickable){
                        buttonsFromTowToNine[i].isClickable = true
                    }
                }
            }
        }
    }
    private fun changButtonPressed(buttonName:String){
        when(buttonName){
            BIN_BUTTON -> buttonPressed = mutableMapOf(BIN_BUTTON to true, OCT_BUTTON to false, DEC_BUTTON to false, HEX_BUTTON to false)
            OCT_BUTTON -> buttonPressed = mutableMapOf(BIN_BUTTON to false, OCT_BUTTON to true, DEC_BUTTON to false, HEX_BUTTON to false)
            DEC_BUTTON -> buttonPressed = mutableMapOf(BIN_BUTTON to false, OCT_BUTTON to false, DEC_BUTTON to true, HEX_BUTTON to false)
            HEX_BUTTON -> buttonPressed = mutableMapOf(BIN_BUTTON to false, OCT_BUTTON to false, DEC_BUTTON to false, HEX_BUTTON to true)
        }

    }
    companion object{
        val BIN_BUTTON = "btnBin"
        val OCT_BUTTON = "btnOct"
        val DEC_BUTTON = "btnDec"
        val HEX_BUTTON = "btnHex"
    }

}
