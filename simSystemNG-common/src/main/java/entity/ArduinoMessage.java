package entity;

/**
 * Abstract class for telnet and serial message
 */
public class ArduinoMessage extends GenericMessage{
    private int[] content;

    public int[] getContent() {
        return content;
    }

    public void setContent(int[] content) {
        this.content = content;
    }

    public void setContent(String content) {
        if(content.contains(",")){
            String[] splitTab =  content.split(",");
            byte[] byteArray = new byte[splitTab.length];
            for(int i = 0; i < splitTab.length; i++){
                if(splitTab[i].trim() != "") {
                    byteArray[i] = Byte.decode(splitTab[i].trim());
                }
            }
        }else{
            this.content = new int[] {Byte.decode(content.trim())};
        }
    }

    public String toString() {
        return java.util.Arrays.toString(content);
    }
}
