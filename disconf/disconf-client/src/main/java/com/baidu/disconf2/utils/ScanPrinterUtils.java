package com.baidu.disconf2.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.disconf2.client.common.annotations.DisconfFile;
import com.baidu.disconf2.client.common.annotations.DisconfFileItem;
import com.baidu.disconf2.client.common.annotations.DisconfItem;
import com.google.common.collect.Multimap;

/**
 * 扫描打印器
 * 
 * @author liaoqiqi
 * @version 2014-6-9
 */
public class ScanPrinterUtils {

    protected static final Logger LOGGER = LoggerFactory
            .getLogger(ScanPrinterUtils.class);

    /**
     * 打印出StoreMap的数据
     * 
     * @param reflections
     */
    public static void printSotreMap(Reflections reflections) {

        LOGGER.info("Now we will print store map......");

        Store store = reflections.getStore();
        Map<String/* indexName */, Multimap<String, String>> storeMap = store
                .getStoreMap();
        for (String indexName : storeMap.keySet()) {

            LOGGER.info("====================================");
            LOGGER.info("indexName:" + indexName);

            Multimap<String, String> multimap = storeMap.get(indexName);

            for (String firstName : multimap.keySet()) {
                Collection<String> lastNames = multimap.get(firstName);
                LOGGER.info("\t\t" + firstName + ": " + lastNames);
            }

        }
    }

    /**
     * 
     */
    public static void printFileItem(Set<Field> disconfFileItemSet) {

        for (Field item : disconfFileItemSet) {

            LOGGER.info(item.toString());
            DisconfFileItem disconfFileItem = item
                    .getAnnotation(DisconfFileItem.class);
            LOGGER.info("\tkey: " + disconfFileItem.key());
            LOGGER.info("\tvalue: " + disconfFileItem.defaultValue());
        }
    }

    /**
     * 
     */
    public void printFile(Set<Class<?>> classdata) {

        for (Class<?> item : classdata) {

            LOGGER.info(item.toString());
            DisconfFile disconfFile = item.getAnnotation(DisconfFile.class);
            LOGGER.info("\tfile name: " + disconfFile.filename());
            LOGGER.info("\tenv: " + disconfFile.env());
            LOGGER.info("\tversion: " + disconfFile.env());
        }
    }

    /**
     * 
     */
    public static void printItem(Set<Field> af1) {

        for (Field item : af1) {

            LOGGER.info(item.toString());
            DisconfItem disconfItem = item.getAnnotation(DisconfItem.class);
            LOGGER.info("\tkey: " + disconfItem.key());
            LOGGER.info("\tvalue: " + disconfItem.defaultValue());
            LOGGER.info("\tenv: " + disconfItem.env());
            LOGGER.info("\tversion: " + disconfItem.version());

        }
    }

    /**
     * 
     */
    public  static void printActiveBackup(Set<Class<?>> classdata) {

        for (Class<?> item : classdata) {

            LOGGER.info(item.toString());
        }
    }

    /**
     * 
     */
    public static void printUpdateFile(Set<Class<?>> classdata) {

        for (Class<?> item : classdata) {

            LOGGER.info(item.toString());
        }
    }
}