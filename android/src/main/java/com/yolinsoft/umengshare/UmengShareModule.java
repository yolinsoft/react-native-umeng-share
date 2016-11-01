package com.yolinsoft.umengshare;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import com.umeng.socialize.*;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.net.URLEncoder.*;
import javax.annotation.Nullable;

public class UmengShareModule extends ReactContextBaseJavaModule {

	private final Context context;
	public UmengShareModule(ReactApplicationContext reactContext) {
		super(reactContext);
		context = reactContext;
	}

	@Override
	public String getName() {
		return "RCTUmengShare";
	}

	@ReactMethod
	public void setWeixin(String appId, String secret){
		PlatformConfig.setWeixin(appId, secret);
	}

	@ReactMethod
	public void setQQZone(String appId, String secret){
		PlatformConfig.setQQZone(appId, secret);
	}

	@ReactMethod
	public void setSinaWeibo(String appId, String secret){
		PlatformConfig.setSinaWeibo(appId, secret);
	}

	//private @Nullable;
	private int getResourceDrawableId(Context context, @Nullable String name) {
        if (name == null || name.isEmpty()) {
            return 0;
        }
        name = name.toLowerCase().replace("-", "_");

        int id = context.getResources().getIdentifier(
                name,
                "drawable",
                context.getPackageName());
        return id;
    }

	@ReactMethod
	public void openShare(String title, String content, String url, ReadableMap imageSource){
		final SHARE_MEDIA[] displayList = new SHARE_MEDIA[] {
			SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA, SHARE_MEDIA.WEIXIN_FAVORITE
		};
		final UmengShareModule finalThis = this;
		final Activity tempActivity = this.getCurrentActivity();
        final Context tempContext = this.getReactApplicationContext();
        final String finalTitle = title;
        final String finalContent = content;

		try{
			final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
			final String finalurl = Uri.encode(url,ALLOWED_URI_CHARS);
			final String imageUrl = imageSource.getString("uri");

			//UI线程更新
			tempActivity.runOnUiThread(new Runnable(){
				@Override
				public void run(){
					try{
						UMImage image;
						if(imageUrl.contains("http://")||imageUrl.contains("https://")){
							image = new UMImage(tempActivity, Uri.encode(imageUrl,ALLOWED_URI_CHARS));
						}else{
							Uri uri = Uri.parse(imageUrl);
							Bitmap bitmap;
                            if(uri.getScheme() == null)
                            {
                                int resId = finalThis.getResourceDrawableId(tempContext, imageUrl);
                                bitmap = BitmapFactory.decodeResource(tempActivity.getResources(), resId);
                            }
                            else{
                                String tempUrlString = "";
                                tempUrlString = imageUrl.replace("file://", "");
                                bitmap = BitmapFactory.decodeFile(tempUrlString);
                            }
                            image = new UMImage(tempActivity, bitmap);
						}
						new ShareAction(tempActivity).setDisplayList(displayList)
							.withTitle(finalTitle)
							.withText(finalContent)
							.withTargetUrl(finalurl)
							.withMedia(image)
							.open();
					}catch (Exception e){
						Log.e("分享错误", e.toString());
					}
				}
			});
		}catch (Exception e) {
			Log.e("分享错误", e.toString());
		}
	}

}