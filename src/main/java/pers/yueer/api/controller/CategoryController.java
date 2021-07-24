package pers.yueer.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import pers.yueer.api.common.response.ResponseData;
import pers.yueer.api.entity.Category;
import pers.yueer.api.entity.Goods;
import pers.yueer.api.entity.User;
import pers.yueer.api.service.CategoryService;
import pers.yueer.api.service.GoodsService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yueer
 * @since 2021-03-21
 */
@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseData list(@RequestParam(required = false) String query,
                             @RequestParam(defaultValue = "1") Integer current,
                             @RequestParam(defaultValue = "10") Integer limit) {

        ResponseData responseData = new ResponseData();

        //1.构建分页查询对象
        Page<Category> goodsPage = new Page<>(current, limit);
        goodsPage.getPages();
        //2.构建查询参数
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("category_id");

        if (!StringUtils.isEmpty(query)) {
            wrapper.like("category_name", query);
        }

        categoryService.page(goodsPage, wrapper);
        long total = goodsPage.getTotal();
        List<Category> records = goodsPage.getRecords();
        System.out.println("records:" + records);

        return responseData.success("获取种类列表成功").data("total", total).data("records", records);
    }

    @PostMapping("/categoryAdd")
    public ResponseData addCategory(@RequestBody Category category){
        ResponseData responseData = new ResponseData();
        categoryService.save(category);
        return responseData.success("添加成功");
    }

    @PostMapping("/categoryUpdate")
    public ResponseData updateCategory(@RequestBody Category category){
        ResponseData responseData = new ResponseData();
        categoryService.updateById(category);
        return responseData.success("修改成功");
    }

    @PostMapping("/categoryDelete/{categoryId}")
    public ResponseData deleteById(@PathVariable Integer categoryId){
        ResponseData responseData = new ResponseData();
        categoryService.removeById(categoryId);
        return responseData.success("删除成功");
    }
}

