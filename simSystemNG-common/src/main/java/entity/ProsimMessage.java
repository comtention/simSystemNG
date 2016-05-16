package entity;

/**
 * Abstract class for telnet and serial message
 */
public class ProsimMessage extends GenericMessage {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
