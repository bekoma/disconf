package com.baidu.disconf.client;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

/**
 * 第一次扫描，静态扫描
 * 
 * @author liaoqiqi
 * @version 2014-6-17
 */
public class DisconfMgrBean implements BeanDefinitionRegistryPostProcessor,
        PriorityOrdered {

    private Set<String> fileList = new HashSet<String>();

    /**
     * 
     */
    private String scanPackage = null;

    /**
     * 关闭
     */
    public void destory() {

        DisconfMgr.close();
    }

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * 这个函数无法达到最高优先级，例如PropertyPlaceholderConfigurer
     */
    @Override
    public void postProcessBeanFactory(
            ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    /**
     * 第一次扫描<br/>
     * 在Spring内部的Bean定义初始化后执行，这样是最高优先级的
     */
    @Override
    public void postProcessBeanDefinitionRegistry(
            BeanDefinitionRegistry registry) throws BeansException {
        DisconfMgr.firstScan(scanPackage, fileList);
    }

    public Set<String> getFileList() {
        return fileList;
    }

    public void setFileList(Set<String> fileList) {
        this.fileList = fileList;
    }
}