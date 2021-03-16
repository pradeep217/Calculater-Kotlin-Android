package com.example.testingcalculater01

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*//widget refrence in kotlin
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

private const val TAG = "MainActivity"
private const val Result_Key = "TEXT_RESULT"
private const val CONTENTS_Key = "TEXT_CONTENTS"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //numbers
        button1.setOnClickListener {
            appendExpression("1", true)
        }
        button2.setOnClickListener {
            appendExpression("2", true)
        }
        button3.setOnClickListener {
            appendExpression("3", true)
        }
        button4.setOnClickListener {
            appendExpression("4", true)
        }
        button5.setOnClickListener {
            appendExpression("5", true)
        }
        button6.setOnClickListener {
            appendExpression("6", true)
        }
        button7.setOnClickListener {
            appendExpression("7", true)
        }
        button8.setOnClickListener {
            appendExpression("8", true)
        }
        button9.setOnClickListener {
            appendExpression("9", true)
        }
        buttonZero.setOnClickListener {
            appendExpression("0", true)
        }
        buttonPoint.setOnClickListener {
            appendExpression(".", true)
        }
        //operation
        buttonPercentage.setOnClickListener {
            appendExpression("%", false)
        }
        buttonDivide.setOnClickListener {
            appendExpression("/", false)
        }
        buttonAdd.setOnClickListener {
            Log.d("add button", "working")
            appendExpression("+", true)
        }
        buttonSubtraction.setOnClickListener {
            appendExpression("-", false)
        }
        buttonMultiply.setOnClickListener {
            appendExpression("*", false)
        }
        buttonEqual.setOnClickListener {
            appendExpression("=", false)
        }

        buttonClearText.setOnClickListener {
            resultTextView.text = ""
            operationTextView.text = ""
        }
        buttonBack.setOnClickListener {
            val string = operationTextView.text.toString()
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

    private fun appendExpression(string: String, canClear: Boolean) {

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

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG, "Data Restoring successfully")
        super.onRestoreInstanceState(savedInstanceState)
        operationTextView.text = savedInstanceState.getString(CONTENTS_Key, "")
        resultTextView.text = savedInstanceState.getString(Result_Key, "")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "data in saveInstanceState")
        super.onSaveInstanceState(outState)
        outState.putString(CONTENTS_Key, operationTextView.text.toString())
        outState.putString(Result_Key, resultTextView.text.toString())
    }
}