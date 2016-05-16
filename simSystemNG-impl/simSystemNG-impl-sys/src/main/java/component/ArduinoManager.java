package component;

import exception.ArduinoNotFoundInArduinoManagerException;
import starter.Starter;
import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ArduinoManager {
    public static List<Arduino> arduinoList = new ArrayList<Arduino>();

    public static Arduino getArduinoByIndentifier(int arduinoIdentifier) throws ArduinoNotFoundInArduinoManagerException {
        for(Arduino arduino: arduinoList){
            if(arduino.getIdentifier() == arduinoIdentifier){
                return arduino;
            }
        }
        throw new ArduinoNotFoundInArduinoManagerException();
    }

    private static ArrayList<String> listSerialPorts() {
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        ArrayList portList = new ArrayList();
        while (ports.hasMoreElements()) {
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portList.add(port.getName());
            }
        }
        return portList;
    }

    public static void connectAllArduino(){
        ArrayList<String> listSerialPort = listSerialPorts();
        for(String serialPort: listSerialPort){
            Arduino arduino = new Arduino(serialPort);
            arduino.connect();
            Thread arduinoThread = new Thread(arduino);
            arduinoThread.start();
        }
    }
}
