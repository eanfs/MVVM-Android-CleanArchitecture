package com.fernandocejas.android10.sample.presentation.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.fernandocejas.android10.sample.domain.interactor.DefaultSubscriber;
import com.fernandocejas.android10.sample.domain.interactor.UseCase;
import com.fernandocejas.android10.sample.domain.interactor.mapper.UserModelDataMapper;
import com.fernandocejas.android10.sample.domain.interactor.model.UserModel;
import com.fernandocejas.android10.sample.domain.interactor.repository.UserDataRepository;
import com.fernandocejas.android10.sample.presentation.AndroidApplication;

/**
 * Created by rocko on 15-11-5.
 */
public class UserDetailsViewModel extends LoadingViewModel {

    public final ObservableBoolean showUserDetails = new ObservableBoolean(true);
    public final ObservableField<UserModel> userObs = new ObservableField<>();

    UseCase getUserDetailsUseCase = new UserDataRepository(AndroidApplication.getContext());
    UserModelDataMapper userModelDataMapper = new UserModelDataMapper();

    @BindView
    @Override
    public void showLoading() {
//		super.showLoading(); // show Details
        showRetry.set(false);
        showLoading.set(true);
        showUserDetails.set(true);
    }

    @BindView
    @Override
    public void showRetry() {
        super.showRetry();
        showUserDetails.set(false);
    }

    @BindView
    public void showUserDetails(UserModel userModel) {
        showLoading.set(false);
        showRetry.set(false);
        showUserDetails.set(true);
        userObs.set(userModel);
    }


    @Command
    public void loadUserDetailsCommand(int userId) {
        showLoading();
        getUserDetailsUseCase.createUser(new DefaultSubscriber<UserModel>() {
            @Override
            public void onNext(UserModel user) {
                showUserDetails(user);
            }

            @Override
            public void onError(Throwable e) {
                showRetry();
            }
        }, userId);
    }

    @Override
    public View.OnClickListener onRetryClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUserDetailsCommand(userObs.get().getUserId());
            }
        };
    }
}
