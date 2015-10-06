package com.paar.ch3marker;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Camera;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;
import edu.dhbw.andar.ARObject;
import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.AndARActivity;
import edu.dhbw.andar.exceptions.AndARException;


public class Activity extends AndARActivity implements OnFrameAvailableListener  {
	static int cnt=0;
	static int cnt1=0;
	private ARObject someObject;
	private ARToolkit artoolkit;
	 MediaPlayer mp ;
	 AndARActivity a;
	 Resources res;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		Context context=getBaseContext();
		 res=context.getResources();
		super.onCreate(savedInstanceState);
		CustomRenderer renderer = new CustomRenderer();
		setNonARRenderer(renderer);
		getSurfaceView().setOnTouchListener(new TouchEventHandler());
		getSurfaceView().getHolder().addCallback(this);
		
		try {
			artoolkit = getArtoolkit();
			 mp = MediaPlayer.create(this,R.raw.aot);
			
			
			
			someObject = new CustomObject2
			("test", "marker_peace16.patt", 80.0, new double[]{0,0});
			artoolkit.registerARObject(someObject);
			
			someObject = new CustomObject3
			("test", "marker_rupee16.patt", 80.0, new double[]{0,0});
			artoolkit.registerARObject(someObject);
			
			someObject = new CustomObject4
			("test", "marker_hand16.patt", 80.0, new double[]{0,0});
			artoolkit.registerARObject(someObject);
			
			someObject = new CustomObject1
					("test", "marker_at16.patt", 80.0, new double[]{0,0});
					artoolkit.registerARObject(someObject);
			
		} catch (AndARException ex){
			System.out.println("");
		}
		if(cnt1==0)
		{
		startPreview();
		//surfaceCreated=false;
		//stopService();
		if(someObject.isVisible())
			Log.d("tag", "visibleeeeeeeeeeeeeeee");
		cnt1++;
		}
		}
	
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e("AndAR EXCEPTION", ex.getMessage());
		/*VideoActivity v1=new VideoActivity();
		v1.play();*/
		//finish();
	}
class TouchEventHandler implements OnTouchListener {
    	
    	private float lastX=0;
    	private float lastY=0;
    	
		//artoolkit.registerARObject(someObject);

		public boolean onTouch(View v, MotionEvent event) {
			Log.d("Ontouch","success");
			if(someObject.isVisible())
				Log.d("tag", "visibleeeeeeeeeeeeeeee");
			else
				Log.d("tag","visiblenot"+someObject.isVisible());
			//mp.start();
			if(cnt==0&&someObject.isVisible())
			{
			Intent i=new Intent(Activity.this,Videoplay.class);
			startActivity(i);
			cnt++;
				Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
			}
			
			return true;
		}
    	
    }
@Override
public void onFrameAvailable(SurfaceTexture surfaceTexture) {
	// TODO Auto-generated method stub
	Log.d("Frame","Availible");
}
}