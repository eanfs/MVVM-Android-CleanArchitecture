/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.UserListBinding;
import com.fernandocejas.android10.sample.presentation.model.UserModel;
import com.fernandocejas.android10.sample.presentation.view.UserListView;
import com.fernandocejas.android10.sample.presentation.view.adapter.UsersAdapter;
import com.fernandocejas.android10.sample.presentation.view.adapter.UsersLayoutManager;
import com.fernandocejas.android10.sample.presentation.viewmodel.UserListViewModel;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Fragment that shows a list of Users.
 */
public class UserListFragment extends BaseFragment<UserListViewModel, UserListBinding> implements UserListView {

	/**
	 * Interface for listening user list events.
	 */
	public interface UserListListener {
		void onUserClicked(final UserModel userModel);
	}

	private UsersAdapter usersAdapter;
	private UsersLayoutManager usersLayoutManager;

	private UserListListener userListListener;

	public UserListFragment() {
		super();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof UserListListener) {
			this.userListListener = (UserListListener) activity;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		setViewModel(new UserListViewModel());
		setBinding(DataBindingUtil.<UserListBinding>inflate(inflater, R.layout.fragment_user_list, container, true));
		getBinding().setViewModel(getViewModel());

		setupUI();

		return getBinding().getRoot();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getViewModel().loadUsersCommand();
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
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}


	private void setupUI() {
		this.usersLayoutManager = new UsersLayoutManager(getActivity());
		getBinding().rvUsers.setLayoutManager(usersLayoutManager);

		this.usersAdapter = new UsersAdapter(getActivity(), new ArrayList<UserModel>());
		this.usersAdapter.setOnItemClickListener(onItemClickListener);
		getBinding().rvUsers.setAdapter(usersAdapter);
	}

	@Override
	public void showLoading() {
	}

	@Override
	public void hideLoading() {
	}

	@Override
	public void showRetry() {
	}

	@Override
	public void hideRetry() {
	}

	@Override
	public void renderUserList(Collection<UserModel> userModelCollection) {
		if (userModelCollection != null) {
			this.usersAdapter.setUsersCollection(userModelCollection);
		}
	}

	@Override
	public void viewUser(UserModel userModel) {
		if (this.userListListener != null) {
			this.userListListener.onUserClicked(userModel);
		}
	}

	@Override
	public void showError(String message) {
		this.showToastMessage(message);
	}

	@Override
	public Context getContext() {
		return this.getActivity().getApplicationContext();
	}

	/**
	 * Loads all users.
	 */
	private void loadUserList() {
	}

	void onButtonRetryClick() {
		UserListFragment.this.loadUserList();
	}

	private UsersAdapter.OnItemClickListener onItemClickListener =
			new UsersAdapter.OnItemClickListener() {
				@Override
				public void onUserItemClicked(UserModel userModel) {
//					if (UserListFragment.this.userListPresenter != null && userModel != null) {
//						UserListFragment.this.userListPresenter.onUserClicked(userModel);
//					}
				}
			};
}
