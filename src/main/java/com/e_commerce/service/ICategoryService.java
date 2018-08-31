package com.e_commerce.service;

import com.e_commerce.common.ServerResponse;
import com.e_commerce.pojo.Category;

import java.util.List;

/**
 * Created by code on 5/20/18.
 */
public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse updateCategory(Integer categoryId, String categoryName);
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
