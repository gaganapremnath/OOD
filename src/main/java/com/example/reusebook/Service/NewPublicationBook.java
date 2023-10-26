package com.example.reusebook.Service;

import com.example.reusebook.Constants.AppConstants;
import com.example.reusebook.Interface.DiscountStrategy;

public class NewPublicationBook implements DiscountStrategy {

    private String getDiscountPrice(Integer price, double discount){
        return String.valueOf(Math.round(price * discount));
    }
    @Override
    public String getDiscountPrint(int price, int noOfTransaction) {
        if(noOfTransaction <= 5){
            return getDiscountPrice(price, AppConstants.DISCOUNT_10);
        }
        return getDiscountPrice(price,AppConstants.DISCOUNT_20);
    }
}
