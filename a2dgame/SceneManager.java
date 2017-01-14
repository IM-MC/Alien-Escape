package matth.a2dgame;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by matth on 1/6/2017.
 */

public class SceneManager {

  private ArrayList<Scene> scenes = new ArrayList<>();
  public static int ACTIVE_SCENES;

  public SceneManager(){
    ACTIVE_SCENES = 0;
    scenes.add(new GameplayScene());

  }

  public void receiveTouch(MotionEvent event){
    scenes.get(ACTIVE_SCENES).receiveTouch(event);
  }

  public void update(){
    scenes.get(ACTIVE_SCENES).update();
  }

  public void draw(Canvas canvas){
    scenes.get(ACTIVE_SCENES).draw(canvas);
  }

}
