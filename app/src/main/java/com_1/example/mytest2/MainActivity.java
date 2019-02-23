package com.example.mytest2;


import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.OpenCVLoader;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.WindowManager;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements CvCameraViewListener2,OnClickListener{

    private static final String TAG = "OpenCameraActivity";

    private EditText input;
    private Button enter;





    static {
        OpenCVLoader.initDebug();

    }

    private Mat mRgba;
    private Mat mFlipRgba;

    private CameraBridgeViewBase mOpenCvCameraView;

    public MainActivity() {

        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);


        //initView();
        //enter.setOnClickListener(this);

       mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.fd_activity_surface_view);
        mOpenCvCameraView.enableView();//
        mOpenCvCameraView.setCvCameraViewListener(this);
        mOpenCvCameraView.setCameraIndex(CameraBridgeViewBase.CAMERA_ID_BACK);
        //FRONT 前置摄像头 CAMERA_ID_BACK为后置摄像头

    }
   /* private void initView(){
        input=(EditText)findViewById(R.id.input);
        enter=(Button)findViewById(R.id.enter);
    }*/
    public void onClick(View v){
       /* switch(v.getId()){
            case R.id.enter:
                Toast.makeText(this, input.getText().toString(), Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }*/
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
        mOpenCvCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat();
        mFlipRgba = new Mat();

    }

    public void onCameraViewStopped() {
        mRgba.release();
    }

    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();//注意
        //Core.flip(mRgba, mFlipRgba, -1);
        //return mFlipRgba;
        return mRgba;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }

}

