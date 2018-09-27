package com.pratikkarki.tictactoe.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.pratikkarki.tictactoe.MainActivity
import com.pratikkarki.tictactoe.R
import com.pratikkarki.tictactoe.model.TicTacToeModel
import kotlinx.android.synthetic.main.activity_main.*

class TicTacTowView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paintBackGround: Paint = Paint()
    private val paintLine: Paint = Paint()
    private val paintLine1: Paint = Paint()
    private val paintLine2: Paint = Paint()
    private val paintText: Paint = Paint()


    private var bitmapBg = BitmapFactory.decodeResource(resources, R.drawable.donny)


    private var circs = mutableListOf<PointF>()


    init {
        paintBackGround.color = Color.BLACK
        paintBackGround.strokeWidth = 10F
        paintBackGround.style = Paint.Style.FILL

        paintLine.color = Color.BLUE
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 10F

        paintLine1.color = Color.RED
        paintLine1.style = Paint.Style.STROKE
        paintLine1.strokeWidth = 10F

        paintLine2.color = Color.GREEN
        paintLine2.style = Paint.Style.STROKE
        paintLine2.strokeWidth = 10F

        paintText.color = Color.MAGENTA
        paintText.textSize = 100F

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //Rectangle with border

        canvas?.drawRect(0F, 0F, // ? in canvas as it doesn't let there be a NullPointerException
                width.toFloat(), height.toFloat(), paintBackGround)
        canvas?.drawRect(0F, 0F,
                width.toFloat(), height.toFloat(), paintLine)

        //canvas?.drawText("Hello", 10F, height.toFloat(), paintText)

        //Four lines

        drawGameArea(canvas)

        drawPlayers(canvas)

        if (TicTacToeModel.checker == false) {
            (context as MainActivity).player.text = "The Game is Over!"
        } else {

            if (TicTacToeModel.getNextPlayer() == TicTacToeModel.CIRCLE) {
                (context as MainActivity).player.text = "Circle is Playing!"
            } else {
                (context as MainActivity).player.text = "Cross is Playing!"
            }
        }




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
                    val radius = (height / 6 - 2)

                    canvas.drawCircle(centerX, centerY, radius.toFloat()-15, paintLine1)
                } else if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CROSS) {
                    canvas.drawLine((i * width / 3).toFloat(), (j * height / 3).toFloat(),
                            ((i + 1) * width / 3).toFloat(),
                            ((j + 1) * height / 3).toFloat(), paintLine2)

                    canvas.drawLine(((i + 1) * width / 3).toFloat(), (j * height / 3).toFloat(),
                            (i * width / 3).toFloat(), ((j + 1) * height / 3).toFloat(), paintLine2)
                }
            }
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {

        val tX = event.x.toInt() / (width / 3)
        val tY = event.y.toInt() / (height / 3)

        if (tX < 3 && tY < 3 && TicTacToeModel.getFieldContent(tX, tY) == TicTacToeModel.EMPTY){
            if(TicTacToeModel.checker == true) {
                TicTacToeModel.setFieldContent(tX, tY, TicTacToeModel.getNextPlayer())
                TicTacToeModel.changeNextPlayer()

                val winner = TicTacToeModel.win()

                if(TicTacToeModel.getNextPlayer() == TicTacToeModel.CROSS){
                    (context as MainActivity).undo("Circle Made a Move")
                } else {
                    (context as MainActivity).undo("Cross Made a Move")
                }



                if (winner == TicTacToeModel.CROSS) {
                    (context as MainActivity).showMessage("Cross has won!")
                    TicTacToeModel.checker = false
                } else if (winner == TicTacToeModel.CIRCLE) {
                    (context as MainActivity).showMessage("Circle has won!")
                    TicTacToeModel.checker = false
                }



                invalidate()
            }

        }


        return super.onTouchEvent(event)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paintText.textSize = height.toFloat() / 3

        bitmapBg = Bitmap.createScaledBitmap(bitmapBg, width, height, false)
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