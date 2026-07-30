package com.example.aurythmedelaproduction;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.os.Looper;

public class FlashHelper {
    private static final long FLASH_DURATION = 5000;      // 5 secondes
    private static final long FLASH_INTERVAL = 300;       // 300 ms

    private final CameraManager cameraManager;
    private final Handler handler;

    private String cameraId;
    private boolean flashAvailable = false;
    private boolean flashing = false;
    private boolean flashOn = false;

    public FlashHelper(Context context) {

        handler = new Handler(Looper.getMainLooper());

        CameraManager manager = null;

        try {

            PackageManager pm = context.getPackageManager();

            if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {

                manager = (CameraManager)
                        context.getSystemService(Context.CAMERA_SERVICE);

                for (String id : manager.getCameraIdList()) {

                    CameraCharacteristics characteristics =
                            manager.getCameraCharacteristics(id);

                    Boolean hasFlash =
                            characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);

                    if (Boolean.TRUE.equals(hasFlash)) {

                        cameraId = id;
                        flashAvailable = true;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        cameraManager = manager;
    }

    private final Runnable flashRunnable = new Runnable() {

        @Override
        public void run() {

            if (!flashing || !flashAvailable)
                return;

            flashOn = !flashOn;

            try {
                cameraManager.setTorchMode(cameraId, flashOn);
            } catch (Exception ignored) {
            }

            handler.postDelayed(this, FLASH_INTERVAL);
        }
    };

    public void startFlashing() {

        if (!flashAvailable)
            return;

        if (flashing) {
            stopFlashing();
        }

        flashing = true;

        handler.post(flashRunnable);

        handler.postDelayed(this::stopFlashing, FLASH_DURATION);
    }

    public void stopFlashing() {

        flashing = false;

        handler.removeCallbacks(flashRunnable);

        try {

            if (flashAvailable) {
                cameraManager.setTorchMode(cameraId, false);
            }

        } catch (Exception ignored) {
        }

        flashOn = false;
    }

    public boolean isFlashAvailable() {
        return flashAvailable;
    }

}
