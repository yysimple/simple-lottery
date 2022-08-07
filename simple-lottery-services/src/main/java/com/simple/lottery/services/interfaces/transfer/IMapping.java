package com.simple.lottery.services.interfaces.transfer;

import com.simple.pagination.util.Page;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Stream;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: MapStruct 对象映射接口
 *
 * @author: WuChengXing
 * @create: 2022-08-07 15:29
 **/
@MapperConfig
public interface IMapping<SOURCE, TARGET> {

    /**
     * 映射同名属性
     *
     * @param var1 源
     * @return 结果
     */
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    TARGET sourceToTarget(SOURCE var1);

    /**
     * 映射同名属性，反向
     *
     * @param var1 源
     * @return 结果
     */
    @InheritInverseConfiguration(name = "sourceToTarget")
    SOURCE targetToSource(TARGET var1);

    /**
     * 映射同名属性，集合形式
     *
     * @param var1 源
     * @return 结果
     */
    @InheritConfiguration(name = "sourceToTarget")
    List<TARGET> sourceToTarget(List<SOURCE> var1);

    /**
     * 映射同名属性，分页形式
     *
     * @param var1 源
     * @return 结果
     */
    @InheritConfiguration(name = "sourceToTarget")
    Page<TARGET> sourceToTargetPage(Page<SOURCE> var1);

    /**
     * 反向，映射同名属性，集合形式
     *
     * @param var1 源
     * @return 结果
     */
    @InheritConfiguration(name = "targetToSource")
    List<SOURCE> targetToSource(List<TARGET> var1);

    /**
     * 映射同名属性，集合流形式
     *
     * @param stream 源
     * @return 结果
     */
    List<TARGET> sourceToTarget(Stream<SOURCE> stream);

    /**
     * 反向，映射同名属性，集合流形式
     *
     * @param stream 源
     * @return 结果
     */
    List<SOURCE> targetToSource(Stream<TARGET> stream);
}
