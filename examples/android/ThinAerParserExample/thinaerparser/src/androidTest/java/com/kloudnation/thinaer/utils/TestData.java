package com.kloudnation.thinaer.utils;

public class TestData {

    private TestData(){

    }

    public static byte[] SCANRECORD_NOOPTIONS = new byte[]
            {
                    0x02,                   // length of first structure

                    0x01, 0x06,             // standard ble stuff -> general discoverable, br/edr

                    0x0D,                   // advertising packet length
                    (byte) 0xFF,            // type 0xFF = mfr specific data
                    (byte) 0xF0, 0x01,      // bluekloud identifier
                    0x12, 0x34,             // company/customer identifier

                    0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,     // beacon id

                    0x03,                   // manufacturer specific data length
                    (byte) 0xFF,            // type 0xFF = mfr specific data
                    (byte) 0xF0, 0x01,      // manufacturer identifier

                    // unused elements
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    0x00
            };

    public static byte[] SCANRECORD_OPTIONS = new byte[]
            {
                    0x02,                   // length of first structure

                    0x01, 0x06,             // standard ble stuff -> general discoverable, br/edr

                    0x16,                   // advertising packet length
                    (byte) 0xFF,            // type 0xFF = mfr specific data
                    (byte) 0xF0, 0x01,      // bluekloud identifier
                    0x12, 0x34,             // company/customer identifier

                    0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,     // beacon id

                                                // options
                    0x01, 0x64, (byte) 0xFF,    // battery
                    0x02, 0x1B, (byte) 0xFF,    // onboard temp
                    0x05, 0x01, (byte) 0xFF,    // generic boolean

                    0x03,                   // manufacturer specific data length
                    (byte) 0xFF,            // type 0xFF = mfr specific data
                    (byte) 0xF0, 0x01,      // manufacturer identifier

                    // unused elements
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
            };
}
