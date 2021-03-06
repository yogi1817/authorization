package com.spj.authorization.security.pojo;

/**
 * 
 * @author Yogesh Sharma
 *
 */
public enum UserType {
	BARBER("BARBER"), CUSTOMER("CUSTOMER"), SUPERUSER("SUPERUSER"), CLIENT("CLIENT");
    private final String value;

    public String getResponse() {
        return value;
    }

    UserType(String value){
        this.value = value;
    }
}
