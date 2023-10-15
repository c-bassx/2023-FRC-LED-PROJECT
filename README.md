# 2023-FRC-LED-PROJECT

**Code to control WS2812B LED Strip for FRC**

**Note: This code is simply a copy of code from Team4159/FRC-2023, refer to that repository for relevant constants and implementation**

<ins>Features:</ins>
- Colors: White, Purple, Yellow, Black, Red, Blue
- Rainbow and Rainbow cycle features, pride flags, blinking

<ins>Usage:</ins>

Create a Constants.java file under java/frc/robot. An example resides below:
```
package frc.robot;

public final class Constants {
    public static final class Fun {
        // Example constants utilized in the FRC 2023 competition

        public static final int ledPort = 9;
        public static final int ledLength = 229;
        public static final double ledReductionFactor = 0.9;

        public static final double ledBlinkDelay = 0.5d;
        public static final double blinkLEDDelayDecrease = 1.5;
        public static final int blinkLEDDelayThreshold = 5;
    }
}
```

You can control the LEDs through `led.setState`.

<ins>Credits:</ins>
- FRC Team 4159
