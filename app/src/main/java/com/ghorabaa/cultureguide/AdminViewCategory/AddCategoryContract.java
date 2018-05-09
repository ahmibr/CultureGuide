package com.ghorabaa.cultureguide.AdminViewCategory;

public interface AddCategoryContract {
    interface View{

        void onSuccess();
        void onFail(String errorMesage);
    }


    interface Presenter{

        void onInsertion();
        void onFail(String errorMessage);
        void addCategory(String name);
    }
}
