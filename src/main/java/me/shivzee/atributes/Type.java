package me.shivzee.atributes;

public enum Type {
    MULTIPLE("multiple"),
    BOOLEAN("boolean"),
    RANDOM("");

    private final String type;

    private Type(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }


}
