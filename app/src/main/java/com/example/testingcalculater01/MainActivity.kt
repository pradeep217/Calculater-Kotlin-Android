package com.example.testingcalculater01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

//
private lateinit var resultTextView: TextView
private lateinit var operationTextView: TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //textView
        resultTextView = findViewById(R.id.textView1)
        operationTextView = findViewById(R.id.textView2)


        //numbers
        button1.setOnClickListener {
            appendonExpresstion("1", true)
        }
        button2.setOnClickListener {
            appendonExpresstion("2", true)
        }
        button3.setOnClickListener {
            appendonExpresstion("3", true)
        }
        button4.setOnClickListener {
            appendonExpresstion("4", true)
        }
        button5.setOnClickListener {
            appendonExpresstion("5", true)
        }
        button6.setOnClickListener {
            appendonExpresstion("6", true)
        }
        button7.setOnClickListener {
            appendonExpresstion("7", true)
        }
        button8.setOnClickListener {
            appendonExpresstion("8", true)
        }
        button9.setOnClickListener {
            appendonExpresstion("9", true)
        }
        buttonZero.setOnClickListener {
            appendonExpresstion("0", true)
        }
        buttonPoint.setOnClickListener {
            appendonExpresstion(".", true)
        }
        //operation
        buttonPercentage.setOnClickListener {
            appendonExpresstion("%", false)
        }
        buttonDivide.setOnClickListener {
            appendonExpresstion("/", false)
        }
        buttonAdd.setOnClickListener {
            Log.d("add button", "working")
            appendonExpresstion("+", true)
        }
        buttonSubtraction.setOnClickListener {
            appendonExpresstion("-", false)
        }
        buttonMultiply.setOnClickListener {
            appendonExpresstion("*", false)
        }
        buttonEqual.setOnClickListener {
            appendonExpresstion("=", false)
        }

        buttonClearText.setOnClickListener {
            resultTextView.text = ""
            operationTextView.text = ""
        }
        buttonBack.setOnClickListener {
            var string = operationTextView.text.toString()
            if (string.isNotEmpty()) {
                operationTextView.text = string.substring(0, string.length - 1)
            }
            resultTextView.text = ""
        }
        buttonEqual.setOnClickListener {
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