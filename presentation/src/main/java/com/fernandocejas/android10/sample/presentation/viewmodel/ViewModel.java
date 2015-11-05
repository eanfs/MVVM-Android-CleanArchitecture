package com.fernandocejas.android10.sample.presentation.viewmodel;

import android.databinding.BaseObservable;

import com.fernandocejas.android10.sample.presentation.navigation.Navigator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by rocko on 15-11-5.
 */
public abstract class ViewModel extends BaseObservable{

	protected Navigator mNavigator = new Navigator();

	public Navigator getmNavigator() {
		return mNavigator;
	}

	public void setmNavigator(Navigator mNavigator) {
		this.mNavigator = mNavigator;
	}

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.SOURCE)
	protected  @interface Command {
	}

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.SOURCE)
	protected @interface BindView {
	}
}
