package dabami.connect4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class Initial extends Activity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial);
    }

    public boolean onTouchEvent(MotionEvent motionEvent){
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
