package org.digitalegiz.handler;

import org.junit.jupiter.api.Test;
import org.digitalegiz.config.Config;
import org.digitalegiz.config.Keys;
import org.digitalegiz.model.Device;
import org.digitalegiz.model.Position;
import org.digitalegiz.session.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MotionHandlerTest {

    @Test
    public void testCalculateMotion() {

        var cacheManager = mock(CacheManager.class);
        when(cacheManager.getObject(eq(Device.class), anyLong())).thenReturn(mock(Device.class));
        var config = mock(Config.class);
        when(config.getString(Keys.EVENT_MOTION_SPEED_THRESHOLD.getKey())).thenReturn("0.01");
        when(cacheManager.getConfig()).thenReturn(config);

        MotionHandler motionHandler = new MotionHandler(cacheManager);

        Position position = new Position();
        motionHandler.handlePosition(position, p -> {});

        assertEquals(false, position.getAttributes().get(Position.KEY_MOTION));

    }

}
