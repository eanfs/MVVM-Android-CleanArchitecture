/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.UserDetailsBinding;
import com.fernandocejas.android10.sample.presentation.model.UserModel;
import com.fernandocejas.android10.sample.presentation.view.UserDetailsView;
import com.fernandocejas.android10.sample.presentation.view.component.AutoLoadImageView;
import com.fernandocejas.android10.sample.presentation.viewmodel.UserDetailsViewModel;

/**
 * Fragment that shows details of a certain user.
 */
public class UserDetailsFragment extends BaseFragment<UserDetailsViewModel, UserDetailsBinding> implements UserDetailsView {

	private static final String ARGUMENT_KEY_USER_ID = "org.android10.ARGUMENT_USER_ID";

	private int userId;

	AutoLoadImageView iv_cover;
	TextView tv_fullname;
	TextView tv_email;
	TextView tv_followers;
	TextView tv_description;
	RelativeLayout rl_progress;
	RelativeLayout rl_retry;
	Button bt_retry;

	public UserDetailsFragment() {
		super();

	}

	public static UserDetailsFragment newInstance(int userId) {
		UserDetailsFragment userDetailsFragment = new UserDetailsFragment();

		Bundle argumentsBundle = new Bundle();
		argumentsBundle.putInt(ARGUMENT_KEY_USER_ID, userId);
		userDetailsFragment.setArguments(argumentsBundle);

		return userDetailsFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		setViewModel(new UserDetailsViewModel());
		setBinding(DataBindingUtil.<UserDetailsBinding>inflate(inflater, R.layout.fragment_user_details, container, false));
		getBinding().setViewModel(getViewModel());

		return getBinding().getRoot();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.initialize();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private void initialize() {
		this.userId = getArguments().getInt(ARGUMENT_KEY_USER_ID);

		getViewModel().loadUserDetailsCommand(userId);
	}

	@Override
	public void renderUser(UserModel user) {
		if (user != null) {
			this.iv_cover.setImageUrl(user.getCoverUrl());
			this.tv_fullname.setText(user.getFullName());
			this.tv_email.setText(user.getEmail());
			this.tv_followers.setText(String.valueOf(user.getFollowers()));
			this.tv_description.setText(user.getDescription());
		}
	}

	@Override
	public void showLoading() {
		this.rl_progress.setVisibility(View.VISIBLE);
		this.getActivity().setProgressBarIndeterminateVisibility(true);
	}

	@Override
	public void hideLoading() {
		this.rl_progress.setVisibility(View.GONE);
		this.getActivity().setProgressBarIndeterminateVisibility(false);
	}

	@Override
	public void showRetry() {
		this.rl_retry.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideRetry() {
		this.rl_retry.setVisibility(View.GONE);
	}

	@Override
	public void showError(String message) {
		this.showToastMessage(message);
	}

	@Override
	public Context getContext() {
		return getActivity().getApplicationContext();
	}

	/**
	 * Loads all users.
	 */
	private void loadUserDetails() {
//		if (this.userDetailsPresenter != null) {
//			this.userDetailsPresenter.initialize(this.userId);
//		}
	}

	void onButtonRetryClick() {
		UserDetailsFragment.this.loadUserDetails();
	}
}
