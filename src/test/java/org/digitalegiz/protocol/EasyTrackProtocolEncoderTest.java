package org.digitalegiz.protocol;

import org.junit.jupiter.api.Test;
import org.digitalegiz.ProtocolTest;
import org.digitalegiz.model.Command;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EasyTrackProtocolEncoderTest extends ProtocolTest {

    @Test
    public void testEncodeEngineStop() throws Exception {

        var encoder = inject(new EasyTrackProtocolEncoder(null));

        Command command = new Command();
        command.setDeviceId(1);
        command.setType(Command.TYPE_ENGINE_STOP);

        assertEquals("*ET,123456789012345,FD,Y1#", encoder.encodeCommand(command));

    }

}
