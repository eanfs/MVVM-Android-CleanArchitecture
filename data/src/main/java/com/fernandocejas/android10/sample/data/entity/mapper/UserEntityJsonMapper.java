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
package com.fernandocejas.android10.sample.data.entity.mapper;

import com.alibaba.fastjson.JSON;
import com.fernandocejas.android10.sample.data.entity.UserEntity;

import java.util.List;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class UserEntityJsonMapper {


    public UserEntityJsonMapper() {
    }

    /**
     * Transform from valid json string to {@link UserEntity}.
     *
     * @param userJsonResponse A json representing a user profile.
     * @return {@link UserEntity}.
     */
    public UserEntity transformUserEntity(String userJsonResponse) {
        UserEntity userEntity = JSON.parseObject(userJsonResponse, UserEntity.class);

        return userEntity;

    }

    /**
     * Transform from valid json string to List of {@link UserEntity}.
     *
     * @param userListJsonResponse A json representing a collection of users.
     * @return List of {@link UserEntity}.
     */
    public List<UserEntity> transformUserEntityCollection(String userListJsonResponse) {

        List<UserEntity> userEntityCollection;
        userEntityCollection = JSON.parseArray(userListJsonResponse, UserEntity.class);

        return userEntityCollection;

    }
}
