/*
 * Copyright 2017 - 2024 Anton Tananaev (anton@digitalegiz.org)
 * Copyright 2017 Andrey Kunitsyn (andrey@digitalegiz.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.digitalegiz.handler;

import jakarta.inject.Inject;
import org.digitalegiz.config.Keys;
import org.digitalegiz.helper.model.AttributeUtil;
import org.digitalegiz.model.Position;
import org.digitalegiz.session.cache.CacheManager;

public class MotionHandler extends BasePositionHandler {

    private final CacheManager cacheManager;

    @Inject
    public MotionHandler(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void onPosition(Position position, Callback callback) {
        if (!position.hasAttribute(Position.KEY_MOTION)) {
            double threshold = AttributeUtil.lookup(
                    cacheManager, Keys.EVENT_MOTION_SPEED_THRESHOLD, position.getDeviceId());
            position.set(Position.KEY_MOTION, position.getSpeed() > threshold);
        }
        callback.processed(false);
    }

}
