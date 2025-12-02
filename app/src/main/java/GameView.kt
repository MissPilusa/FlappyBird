package com.example.icetask4_flappybird

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.random.Random

class GameView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val bird = BitmapFactory.decodeResource(resources, R.drawable.bird)
    private var birdX = 200f
    private var birdY = 500f
    private var birdVelocity = 0f
    private val gravity = 2f

    private val pipeWidth = 200
    private val pipeGap = 500
    private val pipeSpeed = 12f
    private var pipeX = 1000f
    private var pipeTopHeight = 400

    private val pipePaint = Paint().apply { color = Color.GREEN }
    private val scorePaint = Paint().apply {
        color = Color.WHITE
        textSize = 80f
        typeface = Typeface.DEFAULT_BOLD
    }

    private var score = 0
    private var isGameOver = false
    private lateinit var gameTimer: Timer

    init {
        startGame()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw background
        canvas.drawColor(Color.CYAN)

        // Draw bird
        canvas.drawBitmap(bird, birdX, birdY, null)

        // Draw pipes
        canvas.drawRect(pipeX, 0f, pipeX + pipeWidth, pipeTopHeight.toFloat(), pipePaint)
        canvas.drawRect(pipeX, pipeTopHeight + pipeGap.toFloat(), pipeX + pipeWidth, height.toFloat(), pipePaint)

        // Draw score
        canvas.drawText("Score: $score", 50f, 100f, scorePaint)

        // Draw Game Over
        if (isGameOver) {
            val gameOverPaint = Paint().apply {
                color = Color.RED
                textSize = 100f
                typeface = Typeface.DEFAULT_BOLD
                textAlign = Paint.Align.CENTER
            }
            canvas.drawText("Game Over", width / 2f, height / 2f, gameOverPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && !isGameOver) {
            birdVelocity = -30f
        } else if (event.action == MotionEvent.ACTION_DOWN && isGameOver) {
            restartGame()
        }
        return true
    }

    private fun startGame() {
        isGameOver = false
        gameTimer = fixedRateTimer(initialDelay = 0, period = 30) {
            updateGame()
            postInvalidate()
        }
    }

    private fun restartGame() {
        birdY = 500f
        birdVelocity = 0f
        pipeX = width.toFloat()
        pipeTopHeight = Random.nextInt(200, height - pipeGap - 200)
        score = 0
        startGame()
    }

    private fun updateGame() {
        if (isGameOver) return

        birdVelocity += gravity
        birdY += birdVelocity

        // Move pipe
        pipeX -= pipeSpeed
        if (pipeX + pipeWidth < 0) {
            pipeX = width.toFloat()
            pipeTopHeight = Random.nextInt(200, height - pipeGap - 200)
            score++
        }

        // Collision detection
        val birdRect = Rect(
            birdX.toInt(),
            birdY.toInt(),
            (birdX + bird.width).toInt(),
            (birdY + bird.height).toInt()
        )

        val topPipeRect = Rect(
            pipeX.toInt(),
            0,
            (pipeX + pipeWidth).toInt(),
            pipeTopHeight
        )

        val bottomPipeRect = Rect(
            pipeX.toInt(),
            pipeTopHeight + pipeGap,
            (pipeX + pipeWidth).toInt(),
            height
        )

        if (birdY + bird.height > height || Rect.intersects(birdRect, topPipeRect) || Rect.intersects(birdRect, bottomPipeRect)) {
            gameOver()
        }
    }

    private fun gameOver() {
        isGameOver = true
        gameTimer.cancel()
    }
}
