package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.Burger;

public  class BurgerValidation {

    public static boolean isIdValid(long id){
        return id < 0 ? false : true;
    }

    public static boolean burgerIsValid(Burger burger) {
        return burger != null &&
                burger.getIsVegan() != null &&
                burger.getName() != null && !burger.getName().isEmpty() &&
                burger.getPrice() != null &&
                burger.getContents() != null && !burger.getContents().isEmpty() &&
                burger.getBreadType() != null;
    }

    public static boolean isPriceValid(double price){
        return price < 0 ? false : true;
    }

    public static boolean isContentValid(String content){
        return content != null;
    }


}
