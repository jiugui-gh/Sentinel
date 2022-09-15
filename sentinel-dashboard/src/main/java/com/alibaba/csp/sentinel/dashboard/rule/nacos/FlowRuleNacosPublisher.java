package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述: 流控发布类
 *
 * @author wanghongwei
 * @create 2022-09-14 23:04
 */
@Service
public class FlowRuleNacosPublisher implements DynamicRulePublisher<List<FlowRuleEntity>> {

    private static Logger logger = LoggerFactory.getLogger(FlowRuleNacosPublisher.class);

    @Autowired
    private NacosPropertiesConfiguration nacosPropertiesConfiguration;

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<List<FlowRuleEntity>, String> converter;


    @Override
    public void publish(String appName, List<FlowRuleEntity> rules) throws Exception {
        logger.info("publish   appName:{}, rules:{}", appName, rules);
        AssertUtil.notEmpty(appName, "appName cannot be empty");
        if (rules == null) {
            return;
        }
        String dataId = new StringBuilder(appName).append(NacosConstants.DATA_ID_POSTFIX).toString();
        System.out.println("dataId:" + dataId);
        configService.publishConfig(dataId, nacosPropertiesConfiguration.getGroupId(), converter.convert(rules));
    }
}