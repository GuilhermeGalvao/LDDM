package com.example.administrador.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.zxing.Result;

import java.io.IOException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
/**Activity to start OCR vision and barcode scanner
    After read a value from OCR vision the user will click on button called cadastrar and will start another function for start barcodeScanner
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ZXingScannerView scannerView;
    SurfaceView cameraView;
    TextView textView;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;
    String valor ;
    String code ;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case RequestCameraPermissionID:
            {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final Button button = (Button)findViewById(R.id.cadastrar);
        cameraView = (SurfaceView) findViewById(R.id.surface_view);
        textView = (TextView) findViewById(R.id.text_view);
        button.setOnClickListener(this);

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Log.w("MainActivity", "Detector dependencies are not yet available");
        } else {

            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();

            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.CAMERA},
                                        RequestCameraPermissionID);
                            return;
                        }
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    cameraSource.stop();
                }
            });
            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if(items.size() != 0)
                    {
                        textView.post(new Runnable() {
                            @Override
                            public void run() {
                        if(! button.isPressed()) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 0; i < items.size(); i++) {
                                TextBlock item = items.valueAt(i);
                                stringBuilder.append(item.getValue());
                                //achar o valor
                                String valor2 = item.getValue().toString();
                                for(int j = 0; j < valor2.length(); j ++) {
                                    if (valor2.charAt(j) == ',') {
                                        valor = valor2;
                                    }
                                }
                                stringBuilder.append("\n");
                            }
//                            valor = stringBuilder.toString();
                            textView.setText(stringBuilder.toString());
                        }else{
                            Toast.makeText(MainActivity.this, "tentei", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Valores.class);
                            startActivity(intent);
                        }
                            }
                        });
                    }
                }
            });
        }

    }
    @Override
    public void onClick(View view) {

        Toast.makeText(MainActivity.this, valor, Toast.LENGTH_SHORT).show();
        scanCode(view);
//        Intent intent = new Intent(getApplicationContext(), Valores.class);
//        intent.putExtra("Valor", valor);
//        startActivity(intent);

    }
    public void scanCode(View view){
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new MainActivity.ZxingScannerResultHandler());

        setContentView(scannerView);
        scannerView.startCamera();
    }


    @Override
    public void onPause(){
        super.onPause();
        scannerView.startCamera();
    }

    class ZxingScannerResultHandler implements ZXingScannerView.ResultHandler{

        @Override
        public void handleResult(Result result) {
            String resultCode = result.getText();
//            Toast.makeText(MainActivity.this, resultCode, Toast.LENGTH_SHORT).show();
            code = resultCode;
            Intent intent = new Intent(getApplicationContext(), CodeBar.class);
            intent.putExtra("Valor",valor);
            intent.putExtra("Code", code);
//            setContentView(R.layout.activity_valores);
            startActivity(intent);
            scannerView.startCamera();
        }
    }

}

