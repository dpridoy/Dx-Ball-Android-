package nia.dxball.android;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class DX_BallActivity extends Activity implements OnClickListener {
    
	//Intent i,i2,i3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    	
    }
    
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newGame:
            	Intent i=new Intent(this,GameCanvas.class);
            	startActivity(i);
            	break;
            case R.id.exit:
                finish();
                System.exit(0);
                break;
        }

    }

}


