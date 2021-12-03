package me.shivzee.atributes;

public enum Encoding {
    DEFAULT(""),
    BASE64("base64"),
    URL_ENCODED("url3986");

    private final String encoding;

    private Encoding(String encoding){
        this.encoding = encoding;
    }

    public String getEncoding() {
        return this.encoding;
    }
}
