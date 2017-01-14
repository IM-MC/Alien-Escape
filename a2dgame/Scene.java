package matth.a2dgame;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by matth on 1/6/2017.
 */

public interface Scene {
  public void update();
  public void draw(Canvas canvas);
  public void terminate();
  public void receiveTouch(MotionEvent event);
}
