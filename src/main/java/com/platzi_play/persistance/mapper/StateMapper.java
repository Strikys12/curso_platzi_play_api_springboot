package com.platzi_play.persistance.mapper;

import org.mapstruct.Named;

public class StateMapper {

    @Named("stringToBoolean")
    public static Boolean stringToBoolean(String state){
        if(state == null){
            return null;
        }

        return switch (state.toUpperCase()) {
            case "D" -> true;
            case "N" -> false;
            default -> null;
        };
    }

    @Named("booleanToString")
    public static String booleanToString(Boolean state){
        return state == null ? null : (state ? "D" : "N");
        };

}
