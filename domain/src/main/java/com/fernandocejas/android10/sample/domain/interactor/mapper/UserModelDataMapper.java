/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.sample.domain.interactor.mapper;

import com.fernandocejas.android10.sample.data.entity.UserEntity;
import com.fernandocejas.android10.sample.domain.interactor.model.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Mapper class used to transform {@link UserEntity} (in the domain layer) to {@link UserModel} in the
 * presentation layer.
 */
public class UserModelDataMapper {

  public UserModelDataMapper() {}

  /**
   * Transform a {@link UserEntity} into an {@link UserModel}.
   *
   * @param user Object to be transformed.
   * @return {@link UserModel}.
   */
  public UserModel transformUser(UserEntity user) {
    if (user == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    UserModel userModel = new UserModel(user.getId());
    userModel.setCoverUrl(user.getCover_url());
    userModel.setFullName(user.getFull_name());
    userModel.setEmail(user.getEmail());
    userModel.setDescription(user.getDescription());
    userModel.setFollowers(user.getFollowers());


    return userModel;
  }

  /**
   * Transform a Collection of {@link UserEntity} into a Collection of {@link UserModel}.
   *
   * @param usersCollection Objects to be transformed.
   * @return List of {@link UserModel}.
   */
  public List<UserModel> transformUsers(Collection<UserEntity> usersCollection) {
    List<UserModel> userModelsCollection;

    if (usersCollection != null && !usersCollection.isEmpty()) {
      userModelsCollection = new ArrayList<>();
      for (UserEntity user : usersCollection) {
        userModelsCollection.add(transformUser(user));
      }
    } else {
      userModelsCollection = Collections.emptyList();
    }

    return userModelsCollection;
  }
}
