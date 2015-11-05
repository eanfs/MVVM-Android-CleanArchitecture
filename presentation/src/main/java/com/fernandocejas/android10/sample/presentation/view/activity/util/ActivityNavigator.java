package com.fernandocejas.android10.sample.presentation.view.activity.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fernandocejas.android10.sample.presentation.AndroidApplication;

/**
 * Created by rocko on 15-11-3.
 * Activity 跳转控制
 */
public class ActivityNavigator {

	/**
	 * 在 currentActivity 上进行跳转，跳转后 currentActivity finish。在 onCreate 之中不要使用，currentActivity 可能为空
	 *
	 * @param targetActivity
	 */
	public static void navigateTo(@NonNull Class<? extends Activity> targetActivity) {
		navigateTo(targetActivity, null);
	}

	/**
	 * @param targetActivity
	 * @param extras
	 */
	public static void navigateTo(@NonNull Class<? extends Activity> targetActivity, @Nullable Bundle extras) {
		Activity currentActivity = AndroidApplication.getInstance().getCurrentActivity();
		navigateTo(currentActivity, targetActivity, extras);
	}

	/**
	 * Activity 跳转后 finish
	 *
	 * @param context
	 * @param targetActivity
	 * @param extras
	 */
	public static void navigateTo(@NonNull Context context, @NonNull Class<? extends Activity> targetActivity, @Nullable Bundle extras) {
		Intent intent = new Intent(context, targetActivity);
		if (extras != null) {
			intent.putExtras(extras);
		}
		context.startActivity(intent);
		((Activity) context).finish();
	}

	public static void finish() {
		AndroidApplication.getInstance().getCurrentActivity().finish();
	}
}
