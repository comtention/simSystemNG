package message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import starter.Starter;
import types.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.HashMap;
import java.util.Map;

public class MessageMapper {
    //This map contains <ARDUINO_NUMBER><ARDUINO_MESSAGE_SEPARATOR><ARDUINO_MESSAGE> -> <PROSIM_MSG>
    private static Map<String, String> arduinoProsimMap = new HashMap<String, String>();
    //This map contains <PROSIM_MSG> -> <ARDUINO_NUMBER><ARDUINO_MESSAGE_SEPARATOR><ARDUINO_MESSAGE>
    private static Map<String, String> prosimArduinoMap = new HashMap<String, String>();
    public static final String ARDUINO_MESSAGE_SEPARATOR = "##";

    private static Logger logger = LoggerFactory.getLogger(MessageMapper.class);

    public static void fillArduinoProsimMap() {
        logger.debug("Start fillArduinoProsimMap()");
        ConfigurationType config = null;
        try {
            JAXBContext jc = JAXBContext.newInstance("entity");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            config = (ConfigurationType) ((JAXBElement) unmarshaller.unmarshal(Starter.configurationXMLFile)).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }


        for (ArduinoType arduino : config.getArduinos().getArduino()) {
            for (PanelType panel : arduino.getPanels().getPanel()) {
                //proSimMessage indicator
                IndicatorsType indicators = panel.getIndicators();
                if (indicators != null) {
                    for (IndicatorType indicator : indicators.getIndicator()) {
                        IndicatorStatesType indicatorStatesType = indicator.getIndicatorStates();
                        for (IndicatorStateType state : indicatorStatesType.getIndicatorState()) {
                            String arduinoMsg = state.getArduinoMessage();
                            String proSimMsg = state.getProSimMessage();
                            String arduinoName = arduino.getName();
                            arduinoProsimMap.put(arduinoName + ARDUINO_MESSAGE_SEPARATOR + arduinoMsg, proSimMsg);
                        }
                    }

                    //proSimMessage switch
                    SwitchesType switches = panel.getSwitches();
                    if (switches != null) {
                        for (SwitchType sw : switches.getSwitch()) {
                            SwitchStatesType switchStatesType = sw.getSwitchStates();
                            for (SwitchStateType state : switchStatesType.getSwitchState()) {
                                String arduinoMsg = state.getArduinoMessage();
                                String proSimMsg = state.getProSimMessage();
                                String arduinoName = arduino.getName();
                                arduinoProsimMap.put(arduinoName + ARDUINO_MESSAGE_SEPARATOR + arduinoMsg, proSimMsg);
                            }
                        }
                    }

                    //proSimMessage rotary
                    RotaryEncodersType rotaries = panel.getRotaryEncoders();
                    if (rotaries != null) {
                        for (RotaryType rotary : rotaries.getRotary()) {
                            RotaryStatesType rotaryStatesType = rotary.getRotaryStates();
                            for (RotaryStateType state : rotaryStatesType.getRotaryState()) {
                                String arduinoMsg = state.getArduinoMessage();
                                String proSimMsg = state.getProSimMessage();
                                String arduinoName = arduino.getName();
                                arduinoProsimMap.put(arduinoName + ARDUINO_MESSAGE_SEPARATOR + arduinoMsg, proSimMsg);
                            }
                        }
                    }
                }
            }
        }
        logger.info("ArduinoProsimMap just filled with {} messages.", arduinoProsimMap.size());
        logger.debug("ArduinoProsimMap elements :{}", arduinoProsimMap.toString());
        logger.debug("Stop fillArduinoProsimMap()");
    }

    public static void fillProsimArduinoMap(){
        logger.debug("Start fillProsimArduinoMap()");
        if(arduinoProsimMap.size() == 0){
            fillArduinoProsimMap();
        }

        for (Map.Entry<String, String> entry : arduinoProsimMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            prosimArduinoMap.put(value, key);
        }
        logger.info("ProsimArduinoMap just filled with {} messages.", prosimArduinoMap.size());
        logger.debug("ProsimArduinoMap elements :{}", prosimArduinoMap.toString());
        logger.debug("Stop fillProsimArduinoMap()");
    }
}