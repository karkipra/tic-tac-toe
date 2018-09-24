package com.pratikkarki.tictactoe.model

object TicTacToeModel{  // "Object" automatically makes it a singleton

    public val CIRCLE: Short = 1
    public val CROSS: Short = 2
    public val EMPTY: Short = 0

    private val model =
            arrayOf(                                        // Creating a 2x2 matrix of TicTacToe game
                shortArrayOf(EMPTY, EMPTY, EMPTY),
                shortArrayOf(EMPTY, EMPTY, EMPTY),
                shortArrayOf(EMPTY, EMPTY, EMPTY)

            )

    private var nextPlayer = CIRCLE

    fun setFieldContent(x: Int, y: Int, player: Short){ //sets the value as circle, cross
        model[x][y] = player
    }

    fun getFieldContent(x: Int, y: Int) = model[x][y] // gets the value of the selected value

    fun getNextPlayer() = nextPlayer

    fun changeNextPlayer(){
        nextPlayer = if(nextPlayer == CIRCLE) CROSS else CIRCLE //using if statement to determine whether CROSS or CIRCLE

        /*
            ====== If we did this on Java ======

            if(nextPlayer == CIRCLE)(
                nextPlayer = CROSS
            ) else (
                nextPlayer = CIRCLE
            )
         */
    }

    fun resetModel(){
        for(i in 0..2){
            for(j in 0..2){
                model[i][j] = EMPTY
            }
        }
        nextPlayer = CIRCLE
    }


}