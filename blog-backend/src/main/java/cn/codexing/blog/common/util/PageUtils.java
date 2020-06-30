package cn.codexing.blog.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: guoxing
 * @Date: 2020/4/12 20:07
 * @Email: 2933575030@qq.com
 */
@Data
@NoArgsConstructor
public class PageUtils<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 总记录数 */
    private long totalCount = 0;
    /** 每页记录数*/
    private long pageSize = 10;
    // 总页数
    private long totalPage = 0;
    // 当前页数
    private long currPage = 1;
    // 列表数据
    private List<?> list;
    // 条件参数
    private Map<String, Object> params = new HashMap<>(16);
    // 排序列
    private String sortColumn;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageUtils(List<?> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
     * 分页
     */
    public PageUtils(IPage<?> page) {
        this.list = page.getRecords();
        this.totalCount = (int) page.getTotal();
        this.pageSize = page.getSize();
        this.currPage = page.getCurrent();
        this.totalPage = (int) page.getPages();
    }
}