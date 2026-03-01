public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        ProjectorControl pj = reg.getFirst(ProjectorControl.class);
        pj.powerOn();
        pj.connectInput("HDMI-1");

        LightsControl lights = reg.getFirst(LightsControl.class);
        lights.setBrightness(60);

        AirConditioningControl ac = reg.getFirst(AirConditioningControl.class);
        ac.setTemperatureC(24);

        AttendanceControl scan = reg.getFirst(AttendanceControl.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getFirst(ProjectorControl.class).powerOff();
        reg.getFirst(LightsControl.class).powerOff();
        reg.getFirst(AirConditioningControl.class).powerOff();
    }
}
