package com.taylorcfrey.workouttracker.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 */
public class FileUtilities {

    private static final String LOGTAG = "FileUtilities";

    /**
     * Given an object that implements Serializable, write it to the specified path.
     * @param object The object to write. Can be a collection that implements Serializable.
     * @param path The path to write the object to.
     */
    public static boolean writeObjectToPath(Object object, String path){
        ObjectOutputStream outputStream = null;
        FileOutputStream fileOutputStream = null;
        boolean success = false;
        try {
            Log.d(LOGTAG, "Write Path: " + path);
            fileOutputStream = new FileOutputStream(path);
            outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(object);
            outputStream.flush();
            success = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (Exception ignore) {
                // Bad practice, but just want to be sure the streams are closed.
            }

            try {
                fileOutputStream.close();
            } catch (Exception ignore) {
                // Bad practice, but just want to be sure the streams are closed.
            }
        }
        return success;
    }

    public static <T extends Object> List<T> readObjectFromPath(String path, Class<? extends T> classToRead) {
        List<T> list = null;
        ObjectInputStream inputStream = null;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(path);
            inputStream = new ObjectInputStream(fileInputStream);
            list = (List<T>)inputStream.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (Exception ignore) {
                // Bad practice, but just want to be sure the streams are closed.
            }

            try {
                fileInputStream.close();
            } catch (Exception ignore) {
                // Bad practice, but just want to be sure the streams are closed.
            }
        }

        return list;
    }

    public static String createWotPath(String fileName) {
        String result = null;

        try {
//            File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File storageDirectory = Environment.getExternalStorageDirectory();
            Log.d(LOGTAG, "storageDirectory: " + storageDirectory.getAbsolutePath());
            // Create / Access folder for app specific files
            File workoutTrackerDirectory = new File(storageDirectory.getAbsolutePath() + "/WorkoutTracker/");

            workoutTrackerDirectory.mkdirs();
            workoutTrackerDirectory.setWritable(true);

            Log.d(LOGTAG, "workoutTrackerDirectory: " + workoutTrackerDirectory.getAbsolutePath());

            File file = new File(workoutTrackerDirectory, fileName);
//            if (!file.exists()) {
//                file.createNewFile();
//                file.setWritable(true);
//            }

            result = file.getAbsolutePath();
            Log.d(LOGTAG, "FilePath: " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
