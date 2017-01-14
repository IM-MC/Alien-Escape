package matth.a2dgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by matth on 1/6/2017.
 */

public class ObstacleManager {
  private ArrayList<Obstacle> obstacles;
  private int playerGap;
  private int obstacleGap;
  private int obstacleHeight;
  private int colour;

  private int score = 0;

  private long startTime;
  private long initTime;

  public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int colour){
    this.playerGap = playerGap;
    this.obstacleGap = obstacleGap;
    this.obstacleHeight = obstacleHeight;
    this.colour = colour;

    startTime = initTime = System.currentTimeMillis();

    obstacles = new ArrayList<>();

    populateObstacles();
  }

  public boolean playerCollide(RectPlayer player){
    for(Obstacle ob : obstacles){
      if (ob.playerCollide(player)){
        return true;
      }
    }
    return false;
  }

  private void populateObstacles(){
    int currY = -5*Constants.SCREEN_HEIGHT/4;
    while(currY < 0){
      int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH-playerGap));
      obstacles.add(new Obstacle(obstacleHeight,colour,xStart,currY,playerGap));
      currY += obstacleHeight + obstacleGap;
    }
  }

  public void update(){

    if (startTime < Constants.INIT_TIME)
      startTime = Constants.INIT_TIME;
    int elapsedTime =  (int) (System.currentTimeMillis() - startTime);
    startTime = System.currentTimeMillis();
    float speed = (float)(Math.sqrt(1 + (startTime - initTime)/2000.0))*Constants.SCREEN_HEIGHT/10000.0f;
    for(Obstacle ob : obstacles){
      ob.incrementY(speed * elapsedTime);
    }
    if(obstacles.get(obstacles.size() -1).getRectangle().top >= Constants.SCREEN_HEIGHT){
      int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH-playerGap));
      obstacles.add(0, new Obstacle(obstacleHeight,colour, xStart,obstacles.get(0).getRectangle().top-obstacleHeight-obstacleGap,playerGap ));
      obstacles.remove(obstacles.size() - 1);

    }
    if(obstacles.get(obstacles.size() -1).getRectangle().bottom >= 0 )
      score++;
  }

  public void draw(Canvas canvas){
    for(Obstacle ob : obstacles){
      ob.draw(canvas);
    }
    Paint paint = new Paint();
    paint.setTextSize(100);
    paint.setColor(Color.CYAN);
    canvas.drawText("" + score, 50, 50 + paint.descent() - paint.ascent(), paint);
  }

}
