package com.graph.contract.model;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-07-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("contractSign")
public class ContractSign extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 方法名称
     */
    private String name;

    /**
     * 签名加密数据
     */
    private String sign;

    /**
     * 签名数据
     */
    @TableField("signText")
    private String signText;

    /**
     * 签名数据，用于浏览器展示
     */
    @TableField("signTextView")
    private String signTextView;

    /**
     * 所属链
     */
    private String chain;

    /**
     * 方法类型：function , event
     */
    private String type;

    /**
     * 合约地址
     */
    private String address;

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


}
