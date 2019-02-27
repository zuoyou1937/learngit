package com.example.mytest2;


import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.BaseLoaderCallback;

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

import java.io.File;
import java.text.SimpleDateFormat;

import org.opencv.core.*;




import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.highgui.HighGui;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




public class MainActivity extends Activity implements CvCameraViewListener2{

    private static final String TAG = "OpenCameraActivity";



    static {
        OpenCVLoader.initDebug();

    }

    private Mat mRgba;
    private Mat mFlipRgba;
    private Mat mGray;
    private Mat mGuss;
    private Mat mThreshould;
  //  private Mat inter;
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
        mGray=new Mat();
        mGuss=new Mat();
        mThreshould=new Mat();


    }

    public void onCameraViewStopped() {
        mRgba.release();
    }

    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();//注意
       // Core.flip(mRgba, mFlipRgba, 1);
       // Core.flip(mFlipRgba, mFlipRgba, 1);
        //inter = new Mat(mRgba.width(), mRgba.height(), CvType.CV_8UC4);
        Imgproc.cvtColor(mRgba,mGray,Imgproc.COLOR_RGB2GRAY);
        Imgproc.GaussianBlur(mGray,mGuss, new Size(5,5), 0);
        Imgproc.threshold(mGuss,mThreshould,0,255,Imgproc.THRESH_BINARY+Imgproc.THRESH_OTSU);
        return mThreshould;
   //     return mGuss;
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
