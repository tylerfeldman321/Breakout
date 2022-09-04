# breakout

## Tyler Feldman

This project implements the game of Breakout.

### Timeline

**Start Date:** 9/1/2022

**Finish Date:** 9/4/2022

**Hours Spent:** 15

### Resources Used

No external files were used.

### Running the Program

**Main class:** `src/main/java/Breakout.java`

**Data files needed:** No data files are required.

**Key/Mouse inputs: **

- Left/right arrows control the player movement
- Space bar will spawn a ball at the player's position if a ball is available

**Cheat keys:** No cheat keys are available.

### Notes/Assumptions

**Assumptions or Simplifications:** During collisions, I assumed each
shape to be rectangular and that the ball will only bounce horizontally
or vertically. This means that if the ball hits the corner of a block,
the ball will only bounce in one direction rather than at an angle.

**Known Bugs:** No known bugs.

**Extra features or interesting things we should not miss:**

- Score multiplier based on how many lives are remaining.
- The ball bounces off the paddle at an angle based on where the ball hits the paddle.
- Can specify how many balls can be in play via the `NUM_BASIC_BALLS_ALLOWED_IN_PLAY` variable in
  GameWorldManager

### Impressions

I enjoyed the assignment. I didn't think it was too much work,
but I have used JavaFX before. I think because the JavaFX resources
and tutorials provided don't really talk about creating a game with
JavaFX, it might be helpful to included resources that could help
someone who doesn't know how to get started with a JavaFX game.
