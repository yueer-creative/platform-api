package pers.yueer.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import pers.yueer.api.common.response.ResponseData;
import pers.yueer.api.common.utils.UploadUtils;
import pers.yueer.api.entity.Goods;
import pers.yueer.api.service.GoodsService;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yueer
 * @since 2021-03-21
 */
@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/admin")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods")
    public ResponseData list(@RequestParam(defaultValue = "0") Integer queryType,
                             @RequestParam(required = false) String query,
                             @RequestParam(defaultValue = "1") Integer current,
                             @RequestParam(defaultValue = "10") Integer limit) {

        ResponseData responseData = new ResponseData();

        //1.构建分页查询对象
        Page<Goods> goodsPage = new Page<>(current, limit);
        goodsPage.getPages();
        //2.构建查询参数
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("goods_date");

        System.out.println("type:" + queryType + "\n");
        if (!StringUtils.isEmpty(query)) {
            if (queryType == 0) {
                wrapper.like("goods_name", query);
            } else if (queryType == 1) {
                wrapper.like("goods_category_id", Integer.parseInt(query) - 1);
            } else if (queryType ==2) {
                wrapper.like("goods_user_id", Integer.parseInt(query));
            } else {
                wrapper.like("goods_buyer_id", Integer.parseInt(query));
            }
        }

        goodsService.page(goodsPage, wrapper);
        long total = goodsPage.getTotal();
        List<Goods> records = goodsPage.getRecords();
        System.out.println("records:" + records);

        return responseData.success("获取商品列表成功").data("total", total).data("records", records);
    }

    @PostMapping("/goodsStatusChange/{goodsId}/{goodsStatus}")
    public ResponseData goodsStatusChange(@PathVariable Integer goodsId,
                                          @PathVariable Integer goodsStatus) {

        ResponseData responseData = new ResponseData();
        Goods goods = goodsService.getById(goodsId);
        goods.setGoodsStatus(goodsStatus);

        goodsService.updateById(goods);
        return responseData.success("商品状态修改成功");
    }

    @PostMapping("/goodsDelete/{goodsId}")
    public ResponseData deleteById(@PathVariable Integer goodsId) {
        ResponseData responseData = new ResponseData();
        goodsService.removeById(goodsId);
        return responseData.success("删除成功");
    }

    @PostMapping("/goodsUpdate")
    public ResponseData updateGood(@RequestBody Goods goods) {
        ResponseData responseData = new ResponseData();
        goods.setGoodsDate(new Date());
        goodsService.updateById(goods);
        return responseData.success("修改成功");
    }

    @PostMapping("/imgUpload")
    public ResponseData updateImg(HttpServletRequest request, @RequestParam("file") MultipartFile imgFile) {
        ResponseData responseData = new ResponseData();
        if (imgFile.isEmpty()) {
            return responseData.error("上传失败，请选择文件");
        }

        // 生成唯一文件名
        String uuid = UUID.randomUUID().toString().trim();
        String filename = imgFile.getOriginalFilename();
        int index=filename.indexOf(".");
        String fileNames=uuid+filename.substring(index);

        // 调用UploadUtils工具类将图片存放到服务器上
        File fileDir = UploadUtils.getImgDirFile();

        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + fileNames);
            System.out.println(newFile.getAbsolutePath());
            System.out.println(fileNames);

            imgFile.transferTo(newFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseData.success("上传成功").data("imgName", fileNames);
   }

    @PostMapping("/goodsAdd")
    public ResponseData addGood(@RequestBody Goods goods) {
        ResponseData responseData = new ResponseData();
        goods.setGoodsDate(new Date());
        goodsService.save(goods);
        return responseData.success("修改成功");
    }
}

