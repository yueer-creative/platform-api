package pers.yueer.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author yueer
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Goods对象", description="")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    private String goodsName;

    @ApiModelProperty(value = "商品分类，外键")
    private Integer goodsCategoryId;

    private Date goodsDate;

    @TableField("goods_originalPrice")
    private Float goodsOriginalprice;

    private Float goodsPrice;

    private String goodsDesc;

    @ApiModelProperty(value = "卖家，外键")
    private String goodsUserId;

    @ApiModelProperty(value = "买家，外键")
    private String goodsBuyerId;

    private String goodsImg;

    @ApiModelProperty(value = "0: 还未通过审核; 1: 已通过审核")
    private Integer goodsStatus;

    @ApiModelProperty(value = "0: 正常; 1: 删除")
    @TableField("goods_isDelete")
    @TableLogic // 做逻辑删除
    private Integer goodsIsdelete;


}
