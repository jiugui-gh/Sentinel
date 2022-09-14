package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.nacos.api.config.ConfigService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 描述: 实现动态从nacos配置中心获取流控规则
 *
 * @author wanghongwei
 * @create 2022-09-14 22:58
 */
@Service
public class FlowRuleNacosProvider implements DynamicRuleProvider<List<FlowRuleEntity>> {

    private static Logger logger = LoggerFactory.getLogger(FlowRuleNacosProvider.class);

    @Autowired
    private NacosPropertiesConfiguration nacosPropertiesConfiguration;

    @Autowired
    private ConfigService configService;

    @Autowired
    private Converter<String, List<FlowRuleEntity>> converter;

    @Override
    public List<FlowRuleEntity> getRules(String appName) throws Exception {
        String dataId = new StringBuilder(appName).append(NacosConstants.DATA_ID_POSTFIX).toString();
        String rules = configService.getConfig(dataId, nacosPropertiesConfiguration.getGroupId(), 3000);
        logger.info("pull FlowRule from Nacos Config:{}",rules);
        if (StringUtils.isEmpty(rules)) {
            return Collections.EMPTY_LIST;
        }
        return converter.convert(rules);
    }
}