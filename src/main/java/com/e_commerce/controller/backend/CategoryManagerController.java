package com.e_commerce.controller.backend;

import com.e_commerce.common.Const;
import com.e_commerce.common.ResponseCode;
import com.e_commerce.common.ServerResponse;
import com.e_commerce.pojo.User;
import com.e_commerce.service.ICategoryService;
import com.e_commerce.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by code on 5/20/18.
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManagerController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value= "parentId",defaultValue = "0") int parentId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"user not loggin, please login");
        }
        //check whether it is manager.
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iCategoryService.addCategory(categoryName,parentId);
        }else{
            return ServerResponse.createByErrorMessage("no right to do, need admin authority");
        }
    }

    @RequestMapping("set_category.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, int categoryId, String categoryName){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"user not loggin, please login");
        }
        //check whether it is manager.
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iCategoryService.updateCategory(categoryId, categoryName);
        }else{
            return ServerResponse.createByErrorMessage("no right to do, need admin authority");
        }
    }


    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"user not loggin, please login");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //search child node category information
            return iCategoryService.getChildrenParallelCategory(categoryId);
        }else{
            return ServerResponse.createByErrorMessage("no right to do, need admin authority");
        }
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session, @RequestParam(value="categoryId", defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"user not loggin, please login");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //查看当前节点的id和递归子节点的id
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        }else{
            return ServerResponse.createByErrorMessage("no right to do, need admin authority");
        }
    }
}
