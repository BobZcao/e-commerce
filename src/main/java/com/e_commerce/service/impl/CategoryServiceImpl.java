package com.e_commerce.service.impl;

import com.e_commerce.common.ServerResponse;
import com.e_commerce.dao.CategoryMapper;
import com.e_commerce.pojo.Category;
import com.e_commerce.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by code on 5/20/18.
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    public ServerResponse addCategory(String categoryName, Integer parentId){
        if(parentId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("add category failed");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);//category valid
        int rowCount = categoryMapper.insert(category);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("add category success");
        }
        return ServerResponse.createByErrorMessage("add category failed");
    }

    public ServerResponse updateCategory(Integer categoryId, String categoryName){
        if(categoryId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("update category parameter error");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("update category name success");
        }
        return ServerResponse.createByErrorMessage("update category name failed");
    }
}
