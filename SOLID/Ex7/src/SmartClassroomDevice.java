interface PowerControllable {
    void powerOn();
    void powerOff();
}

interface InputConnectable {
    void connectInput(String port);
}

interface BrightnessControllable {
    void setBrightness(int pct);
}

interface TemperatureControllable {
    void setTemperatureC(int c);
}

interface AttendanceReadable {
    int scanAttendance();
}

interface ProjectorControl extends PowerControllable, InputConnectable {}

interface LightsControl extends PowerControllable, BrightnessControllable {}

interface AirConditioningControl extends PowerControllable, TemperatureControllable {}

interface AttendanceControl extends AttendanceReadable {}
