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
 * @since 2021-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "以手机号作为登录账号")
    private String userAccount;

    private String userPwd;

    private String userName;

    private Date userDate;

    @ApiModelProperty(value = "账号是否冻结，默认0未冻结")
    private Integer userStatus;

    private String userPic;

    private String userAddress;

    @ApiModelProperty(value = "0为未被删除，1为已删除，方便测试")
    @TableField("user_isDelete")
    @TableLogic // 做逻辑删除
    private Integer userIsdelete;


}
