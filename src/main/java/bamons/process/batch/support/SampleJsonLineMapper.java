/*
 *  Copyright 2015 the original author or authors.
 *  @https://github.com/david100gom/Bamons
 * <p/>
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * <p/>
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * <p/>
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
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