package pers.yueer.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Order对象", description="")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    private Integer orderSellerId;

    private Integer orderBuyerId;

    private Integer orderGoodsId;

    private Integer orderNum;

    private Float orderPrice;

    @ApiModelProperty(value = "订单状态 1待发货 2待收货 3已完成")
    private Integer orderStatus;

    private Date orderTime;


}
