package com.rotabuilder.integtests.modules;

import java.util.Map;

import com.google.common.collect.Maps;

import org.apache.isis.core.integtestsupport.IsisSystemForTest;

import com.rotabuilder.app.DomainAppAppManifest;

public class DomainAppSystemInitializer {

    public static void initIsft() {
        IsisSystemForTest isft = IsisSystemForTest.getElseNull();
        if(isft == null) {
            isft = new IsisSystemForTest.Builder()
                    .withLoggingAt(org.apache.log4j.Level.INFO)
                    .with(new DomainAppAppManifest() {
                        @Override
                        public Map<String, String> getConfigurationProperties() {
                            final Map<String, String> map = Maps.newHashMap();
                            Util.withJavaxJdoRunInMemoryProperties(map);
                            Util.withDataNucleusProperties(map);
                            Util.withIsisIntegTestProperties(map);
                            return map;
                        }
                    })
                    .build();
            isft.setUpSystem();
            IsisSystemForTest.set(isft);
        }
    }

}
