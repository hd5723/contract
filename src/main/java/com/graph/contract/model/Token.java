package com.graph.contract.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author kelvin
 * @since 2022-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Token extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private transient Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private Double price;

    /**
     * 总量
     */
    @TableField("totalSupply")
    private String totalSupply;

    /**
     * 精度
     */
    private Integer decimals;

    /**
     * 简称
     */
    private String symbol;

    /**
     * 创建时间(时间戳)
     */
    @TableField("createTime")
    private Long createTime;

    /**
     * 修改时间(时间戳)
     */
    @TableField("updateTime")
    private Long updateTime;

    /**
     * 所属链
     */
    private String chain;

    /**
     * 地址
     */
    private String address;

    /**
     * 手续费地址
     */
    private String feeReceiver;

}
