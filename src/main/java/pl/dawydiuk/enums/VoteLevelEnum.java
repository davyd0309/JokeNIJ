package pl.dawydiuk.enums;

public enum VoteLevelEnum {

    COOL("COOL"),
    NORMAL("NORMAL"),
    BAD("BAD");

    private String level;


    VoteLevelEnum(String level) {
        this.level = level;
    }
}
