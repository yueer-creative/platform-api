package pers.yueer.api.service.impl;

import pers.yueer.api.entity.Category;
import pers.yueer.api.mapper.CategoryMapper;
import pers.yueer.api.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yueer
 * @since 2021-03-21
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
