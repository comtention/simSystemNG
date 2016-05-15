//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.07 at 11:29:50 PM CEST 
//


package types;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for rotaryStateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rotaryStateType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arduinoMessage" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="proSimMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rotaryPin" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="minThreshold"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="900"/&gt;
 *               &lt;enumeration value="800"/&gt;
 *               &lt;enumeration value="650"/&gt;
 *               &lt;enumeration value="500"/&gt;
 *               &lt;enumeration value="400"/&gt;
 *               &lt;enumeration value="200"/&gt;
 *               &lt;enumeration value="100"/&gt;
 *               &lt;enumeration value=""/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="maxThreshold"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="1024"/&gt;
 *               &lt;enumeration value="900"/&gt;
 *               &lt;enumeration value="800"/&gt;
 *               &lt;enumeration value="650"/&gt;
 *               &lt;enumeration value="500"/&gt;
 *               &lt;enumeration value="400"/&gt;
 *               &lt;enumeration value="200"/&gt;
 *               &lt;enumeration value=""/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rotaryStateType", propOrder = {
    "arduinoMessage",
    "proSimMessage",
    "rotaryPin",
    "minThreshold",
    "maxThreshold"
})
public class RotaryStateType {

    @XmlElement(required = true)
    protected String arduinoMessage;
    protected String proSimMessage;
    @XmlElement(required = true)
    protected String rotaryPin;
    @XmlElement(required = true)
    protected String minThreshold;
    @XmlElement(required = true)
    protected String maxThreshold;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the arduinoMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArduinoMessage() {
        return arduinoMessage;
    }

    /**
     * Sets the value of the arduinoMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArduinoMessage(String value) {
        this.arduinoMessage = value;
    }

    /**
     * Gets the value of the proSimMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProSimMessage() {
        return proSimMessage;
    }

    /**
     * Sets the value of the proSimMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProSimMessage(String value) {
        this.proSimMessage = value;
    }

    /**
     * Gets the value of the rotaryPin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRotaryPin() {
        return rotaryPin;
    }

    /**
     * Sets the value of the rotaryPin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRotaryPin(String value) {
        this.rotaryPin = value;
    }

    /**
     * Gets the value of the minThreshold property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinThreshold() {
        return minThreshold;
    }

    /**
     * Sets the value of the minThreshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinThreshold(String value) {
        this.minThreshold = value;
    }

    /**
     * Gets the value of the maxThreshold property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxThreshold() {
        return maxThreshold;
    }

    /**
     * Sets the value of the maxThreshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxThreshold(String value) {
        this.maxThreshold = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
