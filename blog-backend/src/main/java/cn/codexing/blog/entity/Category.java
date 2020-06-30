package cn.codexing.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author guoxing
 * @since 2020-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 父分类id
     */
    private Long parentCategoryId;

    /**
     * 是否启用(0否，1是) 默认1
     */
    private Boolean enable;

    /**
     * 是否删除(0否，1是) 默认0
     */
    private Boolean deleted;

}
