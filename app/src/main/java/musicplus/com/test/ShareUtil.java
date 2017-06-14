package musicplus.com.test;

/**
 * Created by chenghuan on 2017/5/24.
 * on phyt company
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;

public class ShareUtil {
    private Context context;

    public ShareUtil(Context context) {
        this.context = context;
    }

    public static final String WEIXIN_PACKAGE_NAME = "";
    public static final String QQ_PACKAGE_NAME = "";
//  public static final String ;


    /**
     * 分享文字
     * @param packageName
     * @param content
     * @param title
     * @param subject
     */
    public void shareText(String packageName,String className,String content,String title,String subject){
        Intent intent =new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        //      if(null != className && null != packageName && !TextUtils.isEmpty(className) && !TextUtils.isEmpty(packageName)){
        //
        //      }else {
        //          if(null != packageName && !TextUtils.isEmpty(packageName)){
        //              intent.setPackage(packageName);
        //          }
        //      }
        if(stringCheck(className) && stringCheck(packageName)){
            ComponentName componentName = new ComponentName(packageName, className);
            intent.setComponent(componentName);
        }else if(stringCheck(packageName)){
            intent.setPackage(packageName);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);
        if(null != title && !TextUtils.isEmpty(title)){
            intent.putExtra(Intent.EXTRA_TITLE, title);
        }
        if(null != subject && !TextUtils.isEmpty(subject)){
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        intent.putExtra(Intent.EXTRA_TITLE, title);
        Intent chooserIntent = Intent.createChooser(intent, "分享到：");
        context.startActivity(chooserIntent);
    }

    /**
     * 分享网页
     */
    public void shareUrl(String packageName,String className,String content,String title,String subject){
        Intent intent =new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
//      if(null != className && null != packageName && !TextUtils.isEmpty(className) && !TextUtils.isEmpty(packageName)){
//
//      }else {
//          if(null != packageName && !TextUtils.isEmpty(packageName)){
//              intent.setPackage(packageName);
//          }
//      }
        if(stringCheck(className) && stringCheck(packageName)){
            ComponentName componentName = new ComponentName(packageName, className);
            intent.setComponent(componentName);
        }else if(stringCheck(packageName)){
            intent.setPackage(packageName);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);
        if(null != title && !TextUtils.isEmpty(title)){
            intent.putExtra(Intent.EXTRA_TITLE, title);
        }
        if(null != subject && !TextUtils.isEmpty(subject)){
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        intent.putExtra(Intent.EXTRA_TITLE, title);
        Intent chooserIntent = Intent.createChooser(intent, "分享到：");
        context.startActivity(chooserIntent);
    }

    /**
     * 分享图片
     */
    public void shareImg(String packageName,String className,File file){
        if(file.exists()){
            Uri uri = Build.VERSION.SDK_INT>=24?FileProvider.getUriForFile(context,
                    context.getApplicationContext().getPackageName() + ".provider", file):Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            if(stringCheck(packageName) && stringCheck(className)){
                intent.setComponent(new ComponentName(packageName, className));
            }else if (stringCheck(packageName)) {
                intent.setPackage(packageName);
            }
            intent.putExtra(Intent.EXTRA_STREAM, uri);
//            Intent chooserIntent = Intent.createChooser(intent, "分享到:");
            context.startActivity(intent);
        }else {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 分享音乐
     */
    public void shareAudio(String packageName,String className,File file){
        if(file.exists()){
            Uri uri = Build.VERSION.SDK_INT>=24?FileProvider.getUriForFile(context,
                    context.getApplicationContext().getPackageName() + ".provider", file):Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("audio/*");
            if(stringCheck(packageName) && stringCheck(className)){
                intent.setComponent(new ComponentName(packageName, className));
            }else if (stringCheck(packageName)) {
                intent.setPackage(packageName);
            }
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            Intent chooserIntent = Intent.createChooser(intent, "分享到:");
            context.startActivity(chooserIntent);
        }else {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 分享视频
     */
    public void shareVideo(String packageName,String className,File file){
        setIntent("video/*", packageName, className, file);
    }

    public void setIntent(String type,String packageName,String className,File file){
        if(file.exists()){
//            Uri uri = Build.VERSION.SDK_INT>=24?FileProvider.getUriForFile(context,
//                    context.getApplicationContext().getPackageName() + ".provider", file):Uri.fromFile(file);
            final Intent intent = new Intent();
            intent.setType(type);
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TITLE", "share");
            intent.putExtra("android.intent.extra.SUBJECT", "share");
            intent.putExtra("android.intent.extra.TEXT", "share");
//            intent.putExtra("android.intent.extra.STREAM", Uri.parse(file.getPath()));
            intent.addFlags(524288);
            if(stringCheck(packageName) && stringCheck(className)){
                intent.setComponent(new ComponentName(packageName, className));
            }else if (stringCheck(packageName)) {
                intent.setPackage(packageName);
            }
            if(type.contains("video")){
                MediaScannerConnection.scanFile(context, new String[]{file.getPath()}, null, new MediaScannerConnection.OnScanCompletedListener(){
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        intent.putExtra("android.intent.extra.STREAM", uri);
                        context.startActivity(Intent.createChooser(intent, "title"));
                    }
                });
            }else{
                Intent chooserIntent = Intent.createChooser(intent, "分享到:");
                context.startActivity(chooserIntent);
            }
//            Intent chooserIntent = Intent.createChooser(intent, "分享到:");
//            context.startActivity(intent);
        }else {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 分享多张图片和文字至朋友圈
     * @param title
     * @param packageName
     * @param className
     * @param file 图片文件
     */
    public void shareImgToWXCircle(String title,String packageName,String className, File file){
        if(file.exists()){
            Uri uri = Build.VERSION.SDK_INT>=24?FileProvider.getUriForFile(context,
                    context.getApplicationContext().getPackageName() + ".provider", file):Uri.fromFile(file);
            Intent intent = new Intent();
            ComponentName comp = new ComponentName(packageName, className);
            intent.setComponent(comp);
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.putExtra("Kdescription", title);
            context.startActivity(intent);
        }else{
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
        }


    }
    /**
     * 是否安装分享app
     * @param packageName
     */
    public boolean checkInstall(String packageName){
        try {
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "请先安装应用app", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 跳转官方安装网址
     */
    public void toInstallWebView(String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    public static boolean stringCheck(String str){
        if(null != str && !TextUtils.isEmpty(str)){
            return true;
        }else {
            return false;
        }
    }
}
