//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.07 at 11:29:50 PM CEST 
//


package types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for auxShiftRegistersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="auxShiftRegistersType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="auxShiftRegister" type="{}auxShiftRegisterType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "auxShiftRegistersType", propOrder = {
    "auxShiftRegister"
})
public class AuxShiftRegistersType {

    protected List<AuxShiftRegisterType> auxShiftRegister;

    /**
     * Gets the value of the auxShiftRegister property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the auxShiftRegister property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuxShiftRegister().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AuxShiftRegisterType }
     * 
     * 
     */
    public List<AuxShiftRegisterType> getAuxShiftRegister() {
        if (auxShiftRegister == null) {
            auxShiftRegister = new ArrayList<AuxShiftRegisterType>();
        }
        return this.auxShiftRegister;
    }

}
