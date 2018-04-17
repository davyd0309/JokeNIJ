package pl.dawydiuk.enums;

public enum  JokeCategoryEnum {

    CATEGORY_1("Kategoria 1","Opis 1"),
    CATEGORY_2("Kategoria 2","Opis 2");



    private String categoryName;
    private String description;

    JokeCategoryEnum(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }
}
