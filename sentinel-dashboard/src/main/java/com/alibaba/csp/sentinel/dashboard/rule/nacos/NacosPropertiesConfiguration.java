package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述: 加载外部配置
 *
 * @author wanghongwei
 * @create 2022-09-14 22:36
 */
@ConfigurationProperties(prefix = "sentinel.nacos", ignoreInvalidFields = true)
public class NacosPropertiesConfiguration {
    private String serverAddr;
    private String dataId;
    private String groupId = "DEFAULT_GROUP";
    private String namespace;

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String toString() {
        return "NacosPropertiesConfiguration{" +
                "serverAddr='" + serverAddr + '\'' +
                ", dataId='" + dataId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", namespace='" + namespace + '\'' +
                '}';
    }
}