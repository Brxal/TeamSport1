package com.alexquispe.ddtielf

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class DiceRollerActivity : AppCompatActivity() {

    private lateinit var diceResultTextView: TextView
    private lateinit var rollDiceButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_roller)

        diceResultTextView = findViewById(R.id.diceResultTextView)
        rollDiceButton = findViewById(R.id.rollDiceButton)

        rollDiceButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val result = Random.nextInt(1, 21)
        diceResultTextView.text = result.toString()
    }
}
