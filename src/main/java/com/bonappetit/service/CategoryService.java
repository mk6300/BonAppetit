package com.bonappetit.service;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryEnum;

public interface CategoryService {

    void initCategory();

    Category findCategory(CategoryEnum style);

}
