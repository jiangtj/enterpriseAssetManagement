package com.jtj.web;

import org.apache.shiro.codec.Base64;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/7/26.
 */
public class AssetTest {
    @Test
    public void contextLoads() {
        // shiro cache key
        byte[] aa = Base64.encode("mine-asset-key:A".getBytes());
        System.out.println(new String(aa));
        Assert.assertTrue(true);
    }
}
