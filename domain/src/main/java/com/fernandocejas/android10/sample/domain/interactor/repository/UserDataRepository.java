/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.sample.domain.interactor.repository;

import android.content.Context;

import com.fernandocejas.android10.sample.data.datasource.UserDataStore;
import com.fernandocejas.android10.sample.data.datasource.UserDataStoreFactory;
import com.fernandocejas.android10.sample.data.entity.UserEntity;
import com.fernandocejas.android10.sample.domain.interactor.UseCase;
import com.fernandocejas.android10.sample.domain.interactor.mapper.UserModelDataMapper;
import com.fernandocejas.android10.sample.domain.interactor.model.UserModel;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * {@link UserDataRepository} for retrieving user data.
 */
public class UserDataRepository extends UseCase {

    private UserDataStoreFactory userDataStoreFactory;
    private UserModelDataMapper userEntityDataMapper;

    public UserDataRepository(Context appContext) {
        this(new UserDataStoreFactory(appContext), new UserModelDataMapper());
    }

    /**
     * Constructs a {@link UserDataRepository}.
     *
     * @param dataStoreFactory     A factory to construct different data source implementations.
     * @param userEntityDataMapper {@link UserModelDataMapper}.
     */
    public UserDataRepository(UserDataStoreFactory dataStoreFactory,
                              UserModelDataMapper userEntityDataMapper) {
        this.userDataStoreFactory = dataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
    }

    public void setUserDataStoreFactory(UserDataStoreFactory userDataStoreFactory) {
        this.userDataStoreFactory = userDataStoreFactory;
    }

    public void setUserEntityDataMapper(UserModelDataMapper userEntityDataMapper) {
        this.userEntityDataMapper = userEntityDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    protected Observable<List<UserModel>> users() {
        //we always get all users from the cloud
        final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();

        return userDataStore.userEntityList()
                .map(new Func1<List<UserEntity>, List<UserModel>>() {
                    @Override
                    public List<UserModel> call(List<UserEntity> userEntities) {
                        return userEntityDataMapper.transformUsers(userEntities);
                    }
                });
    }

    @SuppressWarnings("Convert2MethodRef")
    protected Observable<UserModel> user(int userId) {
        final UserDataStore userDataStore = this.userDataStoreFactory.create(userId);
        return userDataStore.userEntityDetails(userId)
                .map(new Func1<UserEntity, UserModel>() {
                    @Override
                    public UserModel call(UserEntity userEntity) {
                        return userEntityDataMapper.transformUser(userEntity);
                    }
                });
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.users();

    }

    @Override
    protected Observable buildCreateUseCaseObservable(int userId) {
        return this.user(userId);

    }

}
