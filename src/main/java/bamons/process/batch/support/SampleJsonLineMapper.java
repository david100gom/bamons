/*
 *  Copyright 2015 the original author or authors.
 *  @https://github.com/david100gom/Bamons
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package bamons.process.batch.support;

import bamons.process.batch.domain.SampleDomain;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.JsonLineMapper;

import java.util.Map;

public class SampleJsonLineMapper  implements LineMapper<SampleDomain> {

	private JsonLineMapper delegate;

	public SampleDomain mapLine(String line, int lineNumber) throws Exception {
        Map<String,Object> logAsMap = delegate.mapLine(line, lineNumber);
        SampleDomain sampleDomain = new SampleDomain();
        sampleDomain.setCode((String) logAsMap.get("code"));
        sampleDomain.setName((String) logAsMap.get("name"));
        return sampleDomain;
	}

	public void setDelegate(JsonLineMapper delegate) {
        this.delegate = delegate;
	}

}