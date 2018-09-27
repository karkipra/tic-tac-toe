package com.pratikkarki.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast;
import com.pratikkarki.tictactoe.model.TicTacToeModel
import com.pratikkarki.tictactoe.ui.TicTacTowView


class MainActivity : AppCompatActivity() {

    // First create view class
    // Put layout there

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reset()

        shimmer.startShimmer()
    }

    fun reset() {
        btnReset.setOnClickListener {
            TicTacToeModel.checker = true

            ticTacToeView.restart()
        }
    }


    fun showMessage(msg: String) {
        Snackbar.make(ticTacToeView, msg, Snackbar.LENGTH_LONG).setAction(R.string.Reset){
            TicTacToeModel.checker = true

            ticTacToeView.restart()
        }.show()




        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun undo(msg: String) {

        Snackbar.make(ticTacToeView, msg, Snackbar.LENGTH_LONG).setAction(R.string.Undo){
            TicTacToeModel.undo()
            ticTacToeView.invalidate()
        }.show()

    }
}




