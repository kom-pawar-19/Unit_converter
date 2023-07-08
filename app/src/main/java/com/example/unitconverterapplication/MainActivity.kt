package com.example.unitconverterapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var editTextValue: EditText
    private lateinit var textViewResult: TextView
    private lateinit var spinnerFromUnit: Spinner
    private lateinit var spinnerToUnit: Spinner
    private lateinit var buttonConvert: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextValue = findViewById(R.id.editTextValue)
        textViewResult = findViewById(R.id.textViewResult)
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit)
        spinnerToUnit = findViewById(R.id.spinnerToUnit)
        buttonConvert = findViewById(R.id.buttonConvert)

        // Set up the spinner adapters
        ArrayAdapter.createFromResource(
            this,
            R.array.units,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFromUnit.adapter = adapter
            spinnerToUnit.adapter = adapter
        }

        spinnerFromUnit.onItemSelectedListener = this
        spinnerToUnit.onItemSelectedListener = this

        buttonConvert.setOnClickListener { convertUnits() }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // Called when an item is selected in either of the spinners
        // Perform conversion when the selection changes
        convertUnits()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Not used in this example
    }

    private fun convertUnits() {
        val valueText = editTextValue.text.toString().trim()
        if (valueText.isNotEmpty()) {
            val value = valueText.toDouble()
            val fromUnit = spinnerFromUnit.selectedItem.toString()
            val toUnit = spinnerToUnit.selectedItem.toString()
            val result = convert(value, fromUnit, toUnit)
            textViewResult.text = "$value $fromUnit = $result $toUnit"
        } else {
            textViewResult.text = "Please enter a value"
        }
    }

    private fun convert(value: Double, fromUnit: String, toUnit: String): Double {
        // Perform the conversion here
        var result = 0.0

        when {
            fromUnit == "Meter" && toUnit == "Centimeter" -> result = value * 100
            fromUnit == "Centimeter" && toUnit == "Meter" -> result = value / 100
            fromUnit == "Kilogram" && toUnit == "Gram" -> result = value * 1000
            fromUnit == "Gram" && toUnit == "Kilogram" -> result = value / 1000
            fromUnit == "Inch" && toUnit == "Feet" -> result = value / 12
            fromUnit == "Feet" && toUnit == "Inch" -> result = value * 12
            fromUnit == "Liter" && toUnit == "Milliliter" -> result = value * 1000
            fromUnit == "Milliliter" && toUnit == "Liter" -> result = value / 1000
            // Add more conversion cases as needed

            else -> result = value // Default case: no conversion
        }

        return result
    }
}






