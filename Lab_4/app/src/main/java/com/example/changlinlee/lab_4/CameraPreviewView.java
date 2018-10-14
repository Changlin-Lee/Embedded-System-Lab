package com.example.changlinlee.lab_4;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.List;

public class CameraPreviewView extends SurfaceView implements SurfaceHolder.Callback{
    public List<Camera.Size> mSupportedPreviewSizes;
    public Camera.Size mPreviewSize;
    public Camera mCamera;

    public CameraPreviewView(Context context, AttributeSet attr){
        super(context, attr);
        getHolder().addCallback(this);
        getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
        try {
            if (mCamera != null)
                mCamera.setPreviewDisplay(holder);
        } catch (Exception expection){}
    }

    public void surfaceCreated(SurfaceHolder holder){
        android.hardware.Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    }

    public void surfaceDestroyed(SurfaceHolder holder){
        if (mCamera != null)
            mCamera.stopPreview();
    }

    public void setCamera(android.hardware.Camera camera){
        mCamera = camera;
        if(mCamera != null){
            mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
            requestLayout();
        }
    }

    private Camera.Size getOpimalPreviewSize(List<Camera.Size> sizes, int w, int h){
        if(sizes == null)
            return null;
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) w / h;
        double minDiff = Double.MAX_VALUE;
        int targetHeight = h;
        Camera.Size optimalSzie = null;
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;
            if (Math.abs(ratio - targetRatio) < minDiff) {
                optimalSzie = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSzie == null){
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size: sizes){
                if (Math.abs(size.height - targetHeight) < minDiff){
                    optimalSzie = size;
                    minDiff = Math.abs(size.height - targetHeight);
                    }
                }
            }



        return optimalSzie;

    }

}