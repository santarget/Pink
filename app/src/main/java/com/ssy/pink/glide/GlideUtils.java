package com.ssy.pink.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ssy.pink.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author ssy
 * @date 2018/1/24
 */
public class GlideUtils {

    public static void loadImage(Context context, ImageView iv, String url) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        Glide.with(context).load(url)
                .dontAnimate().placeholder(R.drawable.img_empty)
                .error(R.drawable.img_empty)
                .into(iv);
    }

    public static void loadImage(Context context, ImageView iv, String url, int errorPicRes) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        Glide.with(context).load(url)
                .dontAnimate().placeholder(errorPicRes)
                .error(errorPicRes)
                .into(iv);
    }

    public static void loadImage(Context context, ImageView iv, File file) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        Glide.with(context).load(file)
                .dontAnimate()
                .placeholder(R.drawable.img_empty)
                .error(R.drawable.img_empty)
                .into(iv);
    }

    public static void loadImageWithoutCache(Context context, ImageView iv, File file) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        Glide.with(context).load(file)
                .dontAnimate()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.img_empty)
                .into(iv);
    }

    public static void loadImageWithoutCache(Context context, ImageView iv, File file, Drawable errorDrawable) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        Glide.with(context).load(file)
                .dontAnimate()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(errorDrawable)
                .into(iv);
    }

    public static void loadImage(Context context, ImageView iv, Uri uri) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        Glide.with(context).load(uri)
                .dontAnimate().placeholder(R.drawable.img_empty)
                .error(R.drawable.img_empty)
                .into(iv);
    }

    public static void loadVideoIcon(Context context, ImageView iv, File file) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        Glide.with(context)
                .load(file)
                .centerCrop()
                .placeholder(R.drawable.img_empty)
                .crossFade()
                .into(iv);
    }


    /*

    public static File saveImage(FileFolderInfo data, Bitmap bmp) {
        if (bmp == null) {
            return null;
        }
        // 首先保存图片
        File currentFile = new File(Constants.THUMBNAIL_PATH, PublicTools.getThumbnailNameByFileSize(data.getOwnedBy(), data.getId(), data.getSize()));
        if (!currentFile.getParentFile().exists()) {
            currentFile.getParentFile().mkdir();
        }
        FileOutputStream fos = null;
        try {
            if (!currentFile.exists()) {
                currentFile.createNewFile();
            }
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return currentFile;
    }

    public static File saveImage(RecycleInfo data, Bitmap bmp) {
        if (bmp == null) {
            return null;
        }
        // 首先保存图片
        File currentFile = new File(Constants.THUMBNAIL_PATH, PublicTools.getThumbnailNameByFileSize(data.getOwnedBy(), data.getId(), data.getSize()));
        if (!currentFile.getParentFile().exists()) {
            currentFile.getParentFile().mkdir();
        }
        FileOutputStream fos = null;
        try {
            if (!currentFile.exists()) {
                currentFile.createNewFile();
            }
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return currentFile;
    }
*/

    /**
     * 保存图片
     *
     * @param path
     * @param name
     * @param bitmap
     */
    public static File saveBitmap(String path, String name, Bitmap bitmap) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(path + name);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            if (name != null && !"".equals(name)) {
                int index = name.lastIndexOf(".");
                if (index != -1 && (index + 1) < name.length()) {
                    String extension = name.substring(index + 1).toLowerCase();
                    if ("png".equals(extension)) {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    } else if ("jpg".equals(extension)
                            || "jpeg".equals(extension)) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

}
