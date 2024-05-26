package qrcodeapi.DTO;



import java.util.Arrays;

public class QRCodeParams {
    private String contents;
    private int size = 250;
    private String type = "PNG";
    private String correction = "L";

    public static int MAX_SIZE = 350;
    public static int MIN_SIZE = 150;

    public boolean isSizeValid() {
        if(size > MAX_SIZE || size < MIN_SIZE)
            return false;
        return true;
    }

    public boolean isCorrectionValid() {
        if(this.correction == null)
            return false;

        String[] l = new String[]{"L", "M", "Q", "H"};
        return Arrays.stream(l).anyMatch(s -> s.equalsIgnoreCase(correction.trim()));
    }

    public boolean isContentsValid() {
        if(contents != null && !contents.trim().isEmpty())
            return true;
        return false;
    }

    public boolean isTypeValid() {
        if(this.type == null)
            return false;

        String[] l = new String[]{"PNG", "JPEG", "GIF"};
        return Arrays.stream(l).anyMatch(s -> s.contains(this.type.toUpperCase()));
    }

    public String getCorrection() {
        return correction;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
