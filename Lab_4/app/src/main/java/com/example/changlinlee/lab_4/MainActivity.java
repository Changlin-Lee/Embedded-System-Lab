package com.example.changlinlee.lab_4;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private PowerManager mPowerManager;
    private PowerManager.WakeLock mWakeLock;
    private CameraPreviewView mcameraPreviewView;
    private Camera mcamera;
    private int mCamerald;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this,"NO Camera On the Phone Leaving", Toast.LENGTH_SHORT).show();
            finish();
        }
        int numberOfCameras = Camera.getNumberOfCameras();
        boolean hvBackCamera = false;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++ ){
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                mCamerald = i;
                hvBackCamera = true;
                break;
            }
        }
        mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
        if(!hvBackCamera){
            Toast.makeText(this, "The Apps only support the back Camera, Leaving...", Toast.LENGTH_SHORT).show();
            finish();
        }
        mcameraPreviewView = (CameraPreviewView) findViewById(R.id.mCameraPreviewView);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    protected synchronized void onResume() {
        super.onResume();
        mWakeLock.acquire();
        mcamera = Camera.open(mCamerald);
        mcameraPreviewView.setCamera(mcamera);
    }

    protected  synchronized void onPause() {
        mWakeLock.release();
        if(mcamera != null){
            mcameraPreviewView.setCamera(null);
            mcamera.stopPreview();
            mcamera.release();
            mcamera = null;
        }
        super.onPause();
    }

    public boolean onCreatOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public  boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuTakePicture :
                return true;
            case R.id.menuRecordVideo :
                return true;
            case R.id.menuStopRecord :
                return true;
        }
        return false;
    }

}