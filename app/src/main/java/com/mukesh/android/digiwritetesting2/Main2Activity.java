package com.mukesh.android.digiwritetesting2;

// This is the camera screen (used Anyline library ) . It gives document view to the screen. Passes the intent to MainActivity 4.

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scanlibrary.PickImageFragment;
import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;

import java.io.File;
import java.io.IOException;
import java.util.List;

import at.nineyards.anyline.camera.CameraController;
import at.nineyards.anyline.models.AnylineImage;
import at.nineyards.anyline.modules.document.DocumentResult;
import at.nineyards.anyline.modules.document.DocumentResultListener;
import at.nineyards.anyline.modules.document.DocumentScanView;


public class Main2Activity extends AppCompatActivity {

    private DocumentScanView documentScanView;
    private Dialog progressDialog;
    private ImageView imageviewresult;
    private Toast notificationToast;
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<PointF> lastOutline;
    private ObjectAnimator errorMessageAnimator;
    private FrameLayout errorMessageLayout;
    private TextView errorMessage;
    private android.os.Handler handler = new android.os.Handler();
    private long lastErrorRecieved = 0;
    final int RequestCameraPermissionID = 1001;
    final  int Requestcode = 100;



    private Runnable errorMessageCleanup = new Runnable(){
        @Override
        public void run() {
            if( System.currentTimeMillis() > lastErrorRecieved + getApplication().getResources().getInteger(R.integer.error_message_delay)) {
                if(errorMessage == null || errorMessageAnimator == null){
                    return;
                }
                if(errorMessage.getAlpha() == 0f){
                    errorMessage.setText("");
                } else if(!errorMessageAnimator.isRunning()){
                    errorMessageAnimator = ObjectAnimator.ofFloat(errorMessage, "alpha", errorMessage.getAlpha(), 0f);
                    errorMessageAnimator.setDuration(getResources().getInteger(R.integer.error_message_delay));
                    errorMessageAnimator.setInterpolator(new AccelerateInterpolator());
                    errorMessageAnimator.start();
                }
            }
            handler.postDelayed(errorMessageCleanup, getResources().getInteger(R.integer.error_message_delay));

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case  RequestCameraPermissionID : {
                if(grantResults[0] ==  PackageManager.PERMISSION_GRANTED){

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }

                }
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String licenseKey = getString(R.string.anyline_license_key);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        documentScanView = (DocumentScanView) findViewById(R.id.document_scan_view);
        documentScanView.setConfigFromAsset("document_view_config.json");
        imageviewresult = (ImageView)findViewById(R.id.image_view);
        errorMessageLayout = (FrameLayout) findViewById(R.id.error_message_layout);
        errorMessage = (TextView) findViewById(R.id.error_message);

        ///* documentScanView.setCameraOpenListener(this);
        // Optional: Set a ratio you want the documents to be restricted to. default is set to DIN_AX
        //documentScanView.setDocumentRatios(DocumentScanView.DocumentRatio.DIN_AX_PORTRAIT.getRatio());

        // Optional: Set a maximum deviation for the ratio. 0.15 is the default
        //documentScanView.setMaxDocumentRatioDeviation(0.15);



        documentScanView.initAnyline(licenseKey, new DocumentResultListener() {
            @Override
            public void onPreviewProcessingSuccess(AnylineImage anylineImage) {

            }

            @Override
            public void onPreviewProcessingFailure(DocumentScanView.DocumentError documentError) {
                showErrorMessageFor(documentError);
            }

            @Override
            public void onPictureProcessingFailure(DocumentScanView.DocumentError documentError) {
                showErrorMessageFor(documentError,true);
                if(progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

                // if there is a problem, here is how images could be saved in the error case
                // this will be a full, not cropped, not transformed image
                AnylineImage image = documentScanView.getCurrentFullImage();

                if (image != null) {
                    File outDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "error");
                    outDir.mkdir();
                    File outFile = new File(outDir, "" + System.currentTimeMillis() + documentError.name() + ".jpg");
                    try {
                        image.save(outFile, 100);
                        Log.d(TAG, "error image saved to " + outFile.getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    image.release();
                }
            }

            @Override
            public boolean onDocumentOutlineDetected(List<PointF> list, boolean b) {
                lastOutline = list;
                return false;
            }

            @Override
            public void onTakePictureSuccess() {

                // this is called after the image has been captured from the camera and is about to be processed
                progressDialog = ProgressDialog.show(Main2Activity.this, getString(R.string
                                .document_processing_picture_header), getString(R
                                .string
                                .document_processing_picture),
                        true);

                if (errorMessageAnimator != null && errorMessageAnimator.isRunning()) {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            errorMessageAnimator.cancel();
                            errorMessageLayout.setVisibility(View.GONE);
                        }
                    });

                }
            }



            @Override
            public void onTakePictureError(Throwable throwable) {

                throw new RuntimeException(throwable);

            }




            @Override
            public void onResult(DocumentResult documentResult) {

                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                AnylineImage transformedImage = documentResult.getResult();
                AnylineImage fullFrame = documentResult.getFullImage();



                //imageviewresult.setImageBitmap(Bitmap.createScaledBitmap(transformedImage.getBitmap(), 100, 160, false));
                File outDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ok");
                outDir.mkdir();
                // change the file ending to png if you want a png
                File outFile = new File(outDir, "" + System.currentTimeMillis() + ".jpg");
                try {
                    // convert the transformed image into a gray scaled image internally
                    // transformedImage.getGrayCvMat(false);
                    // get the transformed image as bitmap
                    // Bitmap bmp = transformedImage.getBitmap();
                    // save the image with quality 100 (only used for jpeg, ignored for png)
                    transformedImage.save(outFile, 100);
                    //Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                    String filedestination = outFile.getAbsolutePath();
                    //Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                    //intent.putExtra("Imageadress",filedestination);
                    int preference = ScanConstants.OPEN_CAMERA;
                    Intent intent = new Intent(Main2Activity.this, ScanActivity.class);
                    intent.putExtra("uri", filedestination);
                    intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
                    startActivityForResult(intent,Requestcode);

                    //showToast(getString(R.string.document_image_saved_to) +" " + outFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // release the images
                transformedImage.release();
                fullFrame.release();

            }
        });



    }

    private void showErrorMessageFor(DocumentScanView.DocumentError documentError){
        showErrorMessageFor(documentError,false);
    }

    private void showErrorMessageFor(DocumentScanView.DocumentError documentError, boolean highlight) {
        String text = getString(R.string.document_picture_error);
        switch (documentError) {
            case DOCUMENT_NOT_SHARP:
                text += getString(R.string.document_error_not_sharp);
                break;
            case DOCUMENT_SKEW_TOO_HIGH:
                text += getString(R.string.document_error_skew_too_high);
                break;
            case DOCUMENT_OUTLINE_NOT_FOUND:
                //text += getString(R.string.document_error_outline_not_found);
                return; // exit and show no error message for now!
            case IMAGE_TOO_DARK:
                text += getString(R.string.document_error_too_dark);
                break;
            case SHAKE_DETECTED:
                text += getString(R.string.document_error_shake);
                break;
            case DOCUMENT_BOUNDS_OUTSIDE_OF_TOLERANCE:
                text += getString(R.string.document_error_closer);
                break;
            case DOCUMENT_RATIO_OUTSIDE_OF_TOLERANCE:
                text += getString(R.string.document_error_format);
                break;
            case UNKNOWN:
                break;
            default:
                text += getString(R.string.document_error_unknown);
                return; // exit and show no error message for now!
        }

        if(highlight) {
            showHighlightErrorMessageUiAnimated(text);
        }
        else {
            showErrorMessageUiAnimated(text);
        }
    }
    private void showErrorMessageUiAnimated(String message) {
        if(lastErrorRecieved == 0) {
            // the cleanup takes care of removing the message after some time if the error did not show up again
            handler.post(errorMessageCleanup);
        }
        lastErrorRecieved = System.currentTimeMillis();
        if (errorMessageAnimator != null && (errorMessageAnimator.isRunning() || errorMessage.getText().equals
                (message))) {
            return;
        }

        errorMessageLayout.setVisibility(View.VISIBLE);
        errorMessage.setBackgroundColor(ContextCompat.getColor(this, R.color.anyline_blue_darker));
        errorMessage.setAlpha(0f);
        errorMessage.setText(message);
        errorMessageAnimator = ObjectAnimator.ofFloat(errorMessage, "alpha", 0f, 1f);
        errorMessageAnimator.setDuration(getResources().getInteger(R.integer.error_message_delay));
        errorMessageAnimator.setInterpolator(new DecelerateInterpolator());
        errorMessageAnimator.start();
    }
    private void showHighlightErrorMessageUiAnimated(String message){
        lastErrorRecieved = System.currentTimeMillis();
        errorMessageLayout.setVisibility(View.VISIBLE);
        errorMessage.setBackgroundColor(ContextCompat.getColor(this,R.color.anyline_red));
        errorMessage.setAlpha(0f);
        errorMessage.setText(message);

        if(errorMessageAnimator != null && errorMessageAnimator.isRunning()){
            errorMessageAnimator.cancel();
        }

        errorMessageAnimator = ObjectAnimator.ofFloat(errorMessage, "alpha", 0f, 1f);
        errorMessageAnimator.setDuration(getResources().getInteger(R.integer.error_message_delay));
        errorMessageAnimator.setInterpolator(new DecelerateInterpolator());
        errorMessageAnimator.setRepeatMode(ValueAnimator.REVERSE);
        errorMessageAnimator.setRepeatCount(1);
        errorMessageAnimator.start();
    }

    private void showToast(String text) {
        try {
            notificationToast.setText(text);
        } catch (Exception e) {
            notificationToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        }
        notificationToast.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //start the actual scanning
        documentScanView.startScanning();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //stop the scanning
        documentScanView.cancelScanning();
        //release the camera (must be called in onPause, because there are situations where
        // it cannot be auto-detected that the camera should be released)
        //documentScanView.releaseCameraInBackground();
    }
   /* @Override
    public void onCameraOpened(CameraController cameraController, int width, int height) {
        //the camera is opened async and this is called when the opening is finished
        Log.d(TAG, "Camera opened successfully. Frame resolution " + width + " x " + height);
    }

    @Override
    public void onCameraError(Exception e) {
        //This is called if the camera could not be opened.
        // (e.g. If there is no camera or the permission is denied)
        // This is useful to present an alternative way to enter the required data if no camera exists.
        throw new RuntimeException(e);
    }*/


   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data){
       super.onActivityResult(requestCode, resultCode, data);
       //Toast.makeText(this, "This is has come till here", Toast.LENGTH_SHORT).show();


       if (requestCode == Requestcode && resultCode == Activity.RESULT_OK) {
           Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
           String stringuri = uri.toString();
           Intent intents = new Intent(Main2Activity.this, Main4Activity.class);
           intents.putExtra("Imageadress",stringuri);
           startActivity(intents);


       }

   }

}