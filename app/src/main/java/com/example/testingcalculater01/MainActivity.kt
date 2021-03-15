package com.example.testingcalculater01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

private lateinit var resultTextView: TextView
private lateinit var operationTextView: TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //textView
        resultTextView = findViewById(R.id.textView1)
        operationTextView = findViewById(R.id.textView2)
//        signTextView = findViewById(R.id.textView3)
        //numbers
        var oneButton: Button = findViewById(R.id.button1)
        var twoButton: Button = findViewById(R.id.button2)
        var threeButton: Button = findViewById(R.id.button3)
        var fourButton: Button = findViewById(R.id.button4)
        var fiveButton: Button = findViewById(R.id.button5)
        var sixButton: Button = findViewById(R.id.button6)
        var sevenButton: Button = findViewById(R.id.button7)
        var eightButton: Button = findViewById(R.id.button8)
        var nineButton: Button = findViewById(R.id.button9)
        var zeroButton: Button = findViewById(R.id.buttonZero)

        //operation Button
        var clearButton: Button = findViewById(R.id.buttonClearText)
        var bracketButton: Button = findViewById(R.id.buttonBracket)
        var persentageButton: Button = findViewById(R.id.buttonPercentage)
        var divideButton: Button = findViewById(R.id.buttonDivide)
        var addButton: TextView = findViewById(R.id.buttonAdd)
        var subtractionButton: Button = findViewById(R.id.buttonSubtraction)
        var multiplyButton: Button = findViewById(R.id.buttonMultiply)
        var equalButton: Button = findViewById(R.id.buttonEqual)
        var pointButton: Button = findViewById(R.id.buttonPoint)
        var backButton: Button = findViewById(R.id.buttonBack)


        //numbers
        oneButton.setOnClickListener {
            appendonExpresstion("1", true)
        }
        twoButton.setOnClickListener {
            appendonExpresstion("2", true)
        }
        threeButton.setOnClickListener {
            appendonExpresstion("3", true)
        }
        fourButton.setOnClickListener {
            appendonExpresstion("4", true)
        }
        fiveButton.setOnClickListener {
            appendonExpresstion("5", true)
        }
        sixButton.setOnClickListener {
            appendonExpresstion("6", true)
        }
        sevenButton.setOnClickListener {
            appendonExpresstion("7", true)
        }
        eightButton.setOnClickListener {
            appendonExpresstion("8", true)
        }
        nineButton.setOnClickListener {
            appendonExpresstion("9", true)
        }
        zeroButton.setOnClickListener {
            appendonExpresstion("0", true)
        }
        pointButton.setOnClickListener {
            appendonExpresstion(".", true)
        }
        //operation
        persentageButton.setOnClickListener {
            appendonExpresstion("%", false)
        }
        divideButton.setOnClickListener {
            appendonExpresstion("/", false)
        }
        addButton.setOnClickListener {
            Log.d("add button","working")
            appendonExpresstion("+", true)
        }
        subtractionButton.setOnClickListener {
            appendonExpresstion("-", false)
        }
        multiplyButton.setOnClickListener {
            appendonExpresstion("*", false)
        }
        equalButton.setOnClickListener {
            appendonExpresstion("=", false)
        }

        clearButton.setOnClickListener {
            resultTextView.text = ""
            operationTextView.text = ""
        }
        backButton.setOnClickListener {
            var string = operationTextView.text.toString()
            if (string.isNotEmpty()) {
                operationTextView.text = string.substring(0, string.length - 1)
            }
            resultTextView.text = ""
        }
        equalButton.setOnClickListener {
            try {
                val exception = ExpressionBuilder(operationTextView.text.toString()).build()
                val result = exception.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    resultTextView.text = longResult.toString()
                else
                    resultTextView.text = result.toString()
            } catch (e: Exception) {
                Log.d("Exception", "message: " + e.message)
            }
        }
    }

    fun appendonExpresstion(string: String, canClear: Boolean) {

        if (resultTextView.text.isNotEmpty()) {
            operationTextView.text = ""
        }
        if (canClear) {
            resultTextView.text = ""
            operationTextView.append(string)
        } else {
            operationTextView.append(resultTextView.text)
            operationTextView.append(string)
            resultTextView.text = ""
        }
    }
}