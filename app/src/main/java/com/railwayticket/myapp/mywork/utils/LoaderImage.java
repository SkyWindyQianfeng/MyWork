package com.railwayticket.myapp.mywork.utils;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.railwayticket.myapp.mywork.R;

/**
 * Created by Administrator on 2015/12/14.
 */
public class LoaderImage {

	private static LoaderImage loaderImage;

	private static ImageLoader imageLoader;

	private static DisplayImageOptions options;

	private static DisplayImageOptions noShowOptions;

	private static DisplayImageOptions roundedOptions;

	public LoaderImage(Context context) {

		options = new DisplayImageOptions.Builder() // 创建配置过得DisplayImageOption对象
				.showStubImage(R.drawable.image_bg) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.image_bg) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.image_bg) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory() // 设置下载的图片是否缓存在内存中
				.cacheOnDisc() // 设置下载的图片是否缓存在SD卡中
				// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build();

		noShowOptions = new DisplayImageOptions.Builder() // 创建配置过得DisplayImageOption对象
//				.showStubImage(R.drawable.line_white) // 设置图片下载期间显示的图片
				.cacheInMemory() // 设置下载的图片是否缓存在内存中
				.cacheOnDisc() // 设置下载的图片是否缓存在SD卡中
				// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build();
		roundedOptions = new DisplayImageOptions.Builder() // 创建配置过得DisplayImageOption对象
				.showStubImage(R.drawable.image_bg) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.image_bg) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.image_bg) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory() // 设置下载的图片是否缓存在内存中
				.cacheOnDisc()
				// 设置下载的图片是否缓存在SD卡中
				.displayer(
						new RoundedBitmapDisplayer(30)) // 设置成圆角图片
				.build();

		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory() // 1.8.6包使用时候，括号里面传入参数true
				.cacheOnDisc() // 1.8.6包使用时候，括号里面传入参数true
				.build();

		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
				context).defaultDisplayImageOptions(defaultOptions)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).enableLogging()
				.build();

		ImageLoader.getInstance().init(configuration);

		imageLoader = ImageLoader.getInstance();
	}

	public static LoaderImage getInstance(Context context) {
		if (loaderImage == null) {
			loaderImage = new LoaderImage(context);
		}
		return loaderImage;
	}

	public static void loaderImage(String url, ImageView view,ImageLoadingListener listener) {
		imageLoader.displayImage(url, view, options, listener);
	}

	public static void loaderImage(String url, ImageView view) {
		imageLoader.displayImage(url, view, options);
	}

	public static void loaderNoshowImage(String url, ImageView view) {
		imageLoader.displayImage(url, view, noShowOptions);
	}

	public static void loaderRoundedImage(String url, ImageView view) {
		imageLoader.displayImage(url, view, roundedOptions);
	}
	public static void loaderNoshowImage(String url, ImageView view,ImageLoadingListener listener) {
		imageLoader.displayImage(url, view, noShowOptions,listener);
	}
}
