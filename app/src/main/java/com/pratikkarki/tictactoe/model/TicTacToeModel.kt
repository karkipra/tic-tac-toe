package com.pratikkarki.tictactoe.model

import com.pratikkarki.tictactoe.MainActivity
//import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.W
import android.os.Build.VERSION_CODES.O
import android.view.View.X


object TicTacToeModel {  // "Object" automatically makes it a singleton

    public val CIRCLE: Short = 1
    public val CROSS: Short = 2
    public val EMPTY: Short = 0
    public var checker: Boolean = true

    public val model =
            arrayOf(                                        // Creating a 2x2 matrix of TicTacToe game
                    shortArrayOf(EMPTY, EMPTY, EMPTY),
                    shortArrayOf(EMPTY, EMPTY, EMPTY),
                    shortArrayOf(EMPTY, EMPTY, EMPTY)

            )

    private var nextPlayer = CIRCLE

    private var lastX = 0
    private var lastY = 0




    fun setFieldContent(x: Int, y: Int, player: Short) { //sets the value as circle, cross
        lastX = x
        lastY = y
        model[x][y] = player
    }

    fun getFieldContent(x: Int, y: Int) = model[x][y] // gets the value of the selected value

    fun getNextPlayer() = nextPlayer

    fun changeNextPlayer() {
        nextPlayer = if (nextPlayer == CIRCLE) CROSS else CIRCLE //using if statement to determine whether CROSS or CIRCLE

        /*
            ====== If we did this on Java ======

            if(nextPlayer == CIRCLE)(
                nextPlayer = CROSS
            ) else (
                nextPlayer = CIRCLE
            )
         */
    }


    fun win(): Short {
        val cross = TicTacToeModel.CROSS
        val circle = TicTacToeModel.CIRCLE
        val emp = 0.toShort()

        val i = 0
        val j = 0

        var w: Short = 0


        if (model[i][j] == cross && model[i][j + 1] == cross && model[i][j + 2] == cross) { // Crosses
            w = 2
        } else if (model[i + 1][j] == cross && model[i + 1][j + 1] == cross && model[i + 1][j + 2] == cross) {
            w = 2
        } else if (model[i + 2][j] == cross && model[i + 2][j + 1] == cross && model[i + 2][j + 2] == cross) {
            w = 2
        } else if (model[i][j] == cross && model[i + 1][j] == cross && model[i + 2][j] == cross) {
            w = 2
        } else if (model[i][j + 1] == cross && model[i + 1][j + 1] == cross && model[i + 2][j + 1] == cross) {
            w = 2
        } else if (model[i][j + 2] == cross && model[i + 1][j + 2] == cross && model[i + 2][j + 2] == cross) {
            w = 2
        } else if (model[i][j] == cross && model[i + 1][j + 1] == cross && model[i + 2][j + 2] == cross) {
            w = 2
        } else if (model[i][j + 2] == cross && model[i + 1][j + 1] == cross && model[i + 2][j] == cross) {
            w = 2
        } else if (model[i][j + 1] == cross && model[i + 1][j + 1] == cross && model[i + 2][j + 1] == cross) {
            w = 2
        } else if (model[i + 1][j] == cross && model[i + 1][j + 1] == cross && model[i + 1][j + 2] == cross) {
            w = 2
        }


        else if (model[i][j] == circle && model[i][j + 1] == circle && model[i][j + 2] == circle) { // Circles
            w = 1
        } else if (model[i + 1][j] == circle && model[i + 1][j + 1] == circle && model[i + 1][j + 2] == circle) {
            w = 1
        } else if (model[i + 2][j] == circle && model[i + 2][j + 1] == circle && model[i + 2][j + 2] == circle) {
            w = 1
        } else if (model[i][j] == circle && model[i + 1][j] == circle && model[i + 2][j] == circle) {
            w = 1
        } else if (model[i][j + 1] == circle && model[i + 1][j + 1] == circle && model[i + 2][j + 1] == circle) {
            w = 1
        } else if (model[i][j + 2] == circle && model[i + 1][j + 2] == circle && model[i + 2][j + 2] == circle) {
            w = 1
        } else if (model[i][j] == circle && model[i + 1][j + 1] == circle && model[i + 2][j + 2] == circle) {
            w = 1
        } else if (model[i][j + 2] == circle && model[i + 1][j + 1] == circle && model[i + 2][j] == circle) {
            w = 1
        } else if (model[i][j + 1] == circle && model[i + 1][j + 1] == circle && model[i + 2][j + 1] == circle) {
            w = 1
        } else if (model[i + 1][j] == circle && model[i + 1][j + 1] == circle && model[i + 1][j + 2] == circle) {
            w = 1
        }

        // Neither works
        else {
            w = emp
        }

        return w

    }

    fun undo() {
        if (lastX != -1 && lastY != -1) {
            model[lastX][lastY] = EMPTY
            lastX = -1
            lastY = -1
            changeNextPlayer()
        }
    }

    fun resetModel() {
        for (i in 0..2) {
            for (j in 0..2) {
                model[i][j] = EMPTY
            }
        }
        nextPlayer = CIRCLE
    }


}