# ThinAër Protocol Specification
ThinAër is a protocol specification that defines a Bluetooth low energy (BLE) message format for proximity beacon messages. ThinAër proximity beacon advertisements are transmitted by devices for the purpose of signaling their proximity to nearby receivers. The contents of the emitted message contain information that the receiving device can use to identify the beacon and to compute its relative distance to the beacon. The receiving device may use this information as a contextual trigger to execute procedures and implement behaviors that are relevant to being in proximity to the transmitting beacon.


## What is the purpose?
The purpose of creating this specification overcome the constraints current iBeacon specification from Apple Inc. ThinAër has reduced the size of the overall package to only a required 10 bytes, consisting of the company identifier and beacon identification. This specification and initial package size offers 4,294,967,296 unique beacon identifications per company identifier.


### Advertising Packet
The Advertising packet consists of three sections of data: Company Identifier, Beacon Identifier and Options Bytes. The first two sections -- Company Identifier and Beacon Identifier -- are of a fixed byte position and length. The third section -- the Options Bytes -- is optional, of variable length and consists of one or more Key-Value pairs, each seperated by a Stop Byte. 

![packet_format]

----

# Primary advertising packet
The Primary Advertising Packet or PAP hold the most critical information, allowing you to identify the company the beacon is from and the beacon itself. This packet is a total of ten bytes starting with the first two bytes as the company ID. The company ID should be obtained from the [Bluetooth Special Interest Group (Bluetooth SIG)](https://www.bluetooth.org/en-us/specification/assigned-numbers/company-identifiers). They will provide you with a two byte long company identification that can be used in most every other specification. After the company ID, comes the beacon ID which is an eight bytes long and should increment starting from zero per company ID.

#### Example
The example below will show our test company building two beacons using the company ID then the beacon ID incremented.


__Example Company Identification from [Bluetooth SIG](https://www.bluetooth.org/en-us/specification/assigned-numbers/company-identifiers): 0x1234__

Beacon | Format
--- | ---
Beacon 1 | 0x1234, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
Beacon 2 | 0x1234, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01


# Option Bytes
ThinAër’s option bytes have been modeled after the Key-Value Store concept common to many modern software programming languages. Option bytes are predefined in this specification, allowing for quick identification on the peripheral side. The first option (or the key) byte defines what the data will be, using a standard hex value from 0 to F. After the initial byte the data will follow using as many bytes as needed (the value). A stop byte is used at the end of the data letting the peripheral know all data of this type has been sent. More keys and values may be sent until the advertising package limit is reached on the specific chipset.


ThinAër’s option bytes are an extremely easy to use and flexible option to the specification for sending data over the advertising package. Options can be in any order and can change positions within the options data set itself. Option bytes are predefined in this specification and can be added with a request to the authors.

![options_format]

## Predefined bytes
These predefined byte keys are used in the Option byte section of the advertising package. You may use your own option keys on the fly but it may clash with other ThinAër beacon users. You may request new option keys by contacting us with the option key you would like to use.

Option Hex | Description
--- | ---
0x01 | Battery (percent left) reading from power source.
0x02 | Temperature reading from primary chipset.
0x03 | Temperature reading from a secondary source external to the primary chipset.
0x04 | Humidity reading from a secondary source external to the primary chipset.
0x05 | Boolean


## Value Byte(s)
Value bytes are the value of the predefined bytes and can use more than one hex value. An example of this would be using the predefined byte of `0x01` signaling to the peripheral device that it will receive the battery reading. The value byte could be `0x51`, which translates to "81%". The value byte(s) should be split into hex values and sent only after a predefined byte (key byte). To signal the end of the data a stop byte is required at the end as described below.



## Stop byte
A stop byte is used to signal that data has ended. A stop byte is required after each piece of data to show that we are moving on to a new predefined byte or we are done. The peripheral will be looking for the predefined option “start” byte then the data byte(s), then finally the stop byte.

Byte | Description
--- | ---
0xFF | Stop byte.


#### Example
In this example we will be sending temperature and a boolean from the same beacons we created earlier in the “[Primary Advertising Packet](https://github.com/Kloudnation/thinaer#primary-advertising-packet)” section.


__Example Company Identification from [Bluetooth SIG](https://www.bluetooth.org/en-us/specification/assigned-numbers/company-identifiers): 0x1234__

Beacon | Format
--- | ---
Beacon 1 | 0x1234, [...8 byte beacon ID...], 0x02, 0x4A, 0xFF, 0x05, 0x00, 0xFF
Beacon 2 | 0x1234, [...8 byte beacon ID...], 0x05, 0x01, 0xFF, 0x02, 0x4A, 0xFF


[packet_format]: https://raw.githubusercontent.com/Kloudnation/thinaer/master/figures/packet_format.png "packet format"
[options_format]: https://raw.githubusercontent.com/Kloudnation/thinaer/master/figures/options_format.png "options format"
