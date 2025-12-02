# Flappy Bird Clone – Android (Kotlin)

A simple, fun, and lightweight Flappy Bird clone built using Kotlin in Android Studio.
This project demonstrates basic game mechanics such as canvas drawing, object movement, touch input, collision detection, animations, and score tracking.

## Features

Tap-to-fly gameplay
  - The bird ascends when the screen is tapped and falls due to gravity.

Smooth animations
The bird, pipes, and background are animated using the Canvas API and game loop.

Pipe obstacles
  - Randomly generated pipes with gaps that the player must navigate through.

Collision detection
  - Game over triggers when the bird hits a pipe or the ground.

Score tracking
  - Score increments each time the player passes through pipes.

Auto-restart
  - Easy game restart after losing.

## Tech Stack

- Kotlin – core game logic
- Android Studio – IDE
- Canvas API – drawing objects on screen
- SurfaceView/Game Loop – animation and movement
- Timers / Handlers – controlling object updates
- XML Layouts – UI setup (start screen, buttons, etc.)

## How to Play

1. Tap the screen to make the bird flap upward.

2. Avoid the pipes.

3. Each set of pipes you pass earns you 1 point.

4. If you hit a pipe or the ground → Game Over.

5. Tap again to play again.

## How It Works (Quick Technical Overview)

A game loop continuously updates positions and redraws the screen.

The bird uses basic physics — gravity pulls down, tap adds upward velocity.

Pipes move from right to left, and new ones are generated at intervals.

Collision detection checks the bird’s bounding box against pipes.

Score increments when the bird passes a pipe safely.

## Setup Instructions

Clone or download the repository
Open with Android Studio
Let Gradle sync

Run the application on:
  - An Android device, or An Android emulator

## Author
Makoma I. Pilusa
