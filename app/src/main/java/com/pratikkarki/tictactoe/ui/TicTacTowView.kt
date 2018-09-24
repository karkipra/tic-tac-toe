package com.pratikkarki.tictactoe.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.pratikkarki.tictactoe.MainActivity
import com.pratikkarki.tictactoe.model.TicTacToeModel
import kotlinx.android.synthetic.main.activity_main.*

class TicTacTowView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paintBackGround: Paint = Paint()
    private val paintLine: Paint = Paint()
    private var myX: Float = 0F
    private var myY: Float = 0F

    private var circs = mutableListOf<PointF>()


    init {
        paintBackGround.color = Color.BLACK
        paintBackGround.strokeWidth = 5F
        paintBackGround.style = Paint.Style.FILL

        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5F

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //Rectangle with border

        canvas?.drawRect(0F, 0F, // ? in canvas as it doesn't let there be a NullPointerException
                width.toFloat(), height.toFloat(), paintBackGround)
        canvas?.drawRect(0F, 0F,
                width.toFloat(), height.toFloat(), paintLine)

        //Four lines

        drawGameArea(canvas)

        drawPlayers(canvas)


    }


    fun drawGameArea(canvas: Canvas) {
        //Horizontal
        canvas?.drawLine(width.toFloat() / 3, 0F,
                width.toFloat() / 3, height.toFloat(), paintLine)

        canvas?.drawLine(width.toFloat() * 2 / 3, 0F,
                width.toFloat() * 2 / 3, height.toFloat(), paintLine)

        //Vertical

        canvas?.drawLine(0F, height.toFloat() / 3,
                width.toFloat(), height.toFloat() / 3, paintLine)

        canvas?.drawLine(0F, height.toFloat() * 2 / 3,
                width.toFloat(), height.toFloat() * 2 / 3, paintLine)
    }

    private fun drawPlayers(canvas: Canvas) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CIRCLE) {
                    val centerX = (i * width / 3 + width / 6).toFloat()
                    val centerY = (j * height / 3 + height / 6).toFloat()
                    val radius = height / 6 - 2

                    canvas.drawCircle(centerX, centerY, radius.toFloat(), paintLine)
                } else if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CROSS) {
                    canvas.drawLine((i * width / 3).toFloat(), (j * height / 3).toFloat(),
                            ((i + 1) * width / 3).toFloat(),
                            ((j + 1) * height / 3).toFloat(), paintLine)

                    canvas.drawLine(((i + 1) * width / 3).toFloat(), (j * height / 3).toFloat(),
                            (i * width / 3).toFloat(), ((j + 1) * height / 3).toFloat(), paintLine)
                }
            }
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val tX = event.x.toInt() / (width / 3)
        val tY = event.y.toInt() / (height / 3)

        if (tX < 3 && tY < 3 &&
                TicTacToeModel.getFieldContent(tX, tY) == TicTacToeModel.EMPTY) {

            TicTacToeModel.setFieldContent(tX, tY, TicTacToeModel.getNextPlayer())
            TicTacToeModel.changeNextPlayer()
            invalidate()

            //(context as MainActivity).showMessage("you have won")
        }

        return super.onTouchEvent(event)
    }

    public fun restart() {
        TicTacToeModel.resetModel()
        invalidate()
    }


}



/*

======= First Iteration =======

//canvas?.drawCircle(myX, myY, 100F, paintLine)



======= Second Iteration =======

// Draws a circle when touched

for (circ in circs) {
canvas?.drawCircle(circ.x, circ.y, 100F, paintLine)
}

*/