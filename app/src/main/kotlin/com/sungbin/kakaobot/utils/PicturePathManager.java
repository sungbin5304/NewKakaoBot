package com.sungbin.kakaobot.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class PicturePathManager{
    private static String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static String PICTURE_PATH = sdcard + "/Android/data/com.kakao.talk/contents/Mg==";

    private static class ModifiedDate implements Comparator<File> {
        public int compare(File f1, File f2) {
            return Long.compare(f2.lastModified(), f1.lastModified());
        }
    }

    private static String getLastPictureFolderPath(){
        File file = new File(PICTURE_PATH);
        File[] list = file.listFiles();
        assert list != null;
        Arrays.sort(list, new ModifiedDate());
        return list[0].toString();
    }

    private static File[] getLastPictureFilePathFromFoldPath(String path){
        File file = new File(path);
        File[] list = file.listFiles();
        assert list != null;
        Arrays.sort(list, new ModifiedDate());
        return list;
    }

    public static String getLastPicture(){
        try{
            File[] path = getLastPictureFilePathFromFoldPath(getLastPictureFolderPath());
            for (File value : path) {
                File file = new File(value.toString());
                if (Objects.requireNonNull(file.listFiles()).length == 0) continue;
                else {
                    String picture = getLastPictureFilePathFromFoldPath(file.getPath())[0].toString();
                    Bitmap bm = BitmapFactory.decodeFile(picture);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] bImage = baos.toByteArray();
                    return Base64.encodeToString(bImage, 0);
                }
            }
            return "이미지 파일을 불러올 수 없습니다.";
        }
        catch(Exception e){
            return e.toString();
        }
    }
}
