package com.fernandocejas.android10.sample.presentation.viewmodel;

import android.view.View;

import com.fernandocejas.android10.sample.presentation.AndroidApplication;

/**
 * Created by rocko on 15-11-5.
 */
public class HomeViewModel extends ViewModel {

	@Command
	public View.OnClickListener onClickLoadData() {
		return new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				mNavigator.navigateToUserList(AndroidApplication.getInstance().getCurrentActivity());
			}
		};
	}
}
