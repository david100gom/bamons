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
package bamons.process.monitoring.web;

import bamons.process.monitoring.domain.BatchJobExecution;
import bamons.process.monitoring.domain.BatchStepExecution;
import bamons.process.monitoring.service.BatchMonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 * 배치 모니터링 컨트롤러
 *
 */
@Controller
public class BatchMonitoringController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BatchMonitoringService batchMonitoringService;

    @Autowired
    private JobOperator jobOperator;

    /**
     *
     * Job 리스트를 보여준다.
     *
     * @param request request 객체
     * @param response response 객체
     * @return job 정보 JSON
     */
    @RequestMapping(value="/job/list.do",  produces={"application/json; charset=UTF-8"})
    public @ResponseBody
    Map<String, Object> jobJson(HttpServletRequest request, HttpServletResponse response) throws Exception{

        Map<String, Object> map = new HashMap<String, Object>();

        // Pageing Toolbar 에서 자동으로 넘어옴.
        String start = ServletRequestUtils.getStringParameter(request, "start", "0");
        String limit = ServletRequestUtils.getStringParameter(request, "limit", "0");
        String page = ServletRequestUtils.getStringParameter(request, "page", "0");
        String startTime = ServletRequestUtils.getStringParameter(request, "startTime", "0");

        List<BatchJobExecution> list = batchMonitoringService.findBatchJobExecutionList(start, limit, startTime);
        int totalCount = batchMonitoringService.getBatchJobExecutionTotalCount(startTime);

        map.put("success", true);
        map.put("totalCount", totalCount);
        map.put("data", list);
        return map;
    }

    /**
    *
    * Job & Step 정보를 보여준다.
    *
    * @param request request 객체
    * @param response response 객체
    * @return job 정보 JSON
    * @throws Exception
    */
    @RequestMapping(value="/step/list.do",  produces={"application/json; charset=UTF-8"})
    public @ResponseBody Map<String, Object> jobStepJson(HttpServletRequest request, HttpServletResponse response) throws Exception{

        int jobExecutionId = ServletRequestUtils.getIntParameter(request, "jobExecutionId", 0);

        Map<String, Object> map = new HashMap<String, Object>();

        List<BatchStepExecution> stepList = batchMonitoringService.findBatchStepExecutionList(jobExecutionId);

        // Job 파라미터.
        String params = jobOperator.getParameters(jobExecutionId);

        map.put("success", true);
        map.put("message", params);
        map.put("data", stepList);
        return map;
    }


    /**
     *
     * Job 재구동
     *
     * @param request request 객체
     * @param response response 객체
     * @return job 정보 JSON
     * @throws Exception
     */
    @RequestMapping(value="/job/restart.do",  produces={"application/json; charset=UTF-8"})
    public @ResponseBody Map<String, Object> jobRestart(HttpServletRequest request, HttpServletResponse response) throws Exception{

        boolean status = true;
        int jobExecutionId = ServletRequestUtils.getIntParameter(request, "jobExecutionId", 0);
        String jobName = ServletRequestUtils.getStringParameter(request, "jobName", "");
        String statusMessage = "";

        Map<String, Object> map = new HashMap<String, Object>();

        if(jobExecutionId > 0 && !StringUtils.isEmpty(jobName)) {
            try {
                batchMonitoringService.jobRestart(jobExecutionId, jobName);
            } catch (Exception e) {
                statusMessage = e.toString();
                status = false;
            }
        }else{
            status = false;
            statusMessage = "jobExecutionId or jobName Error";
        }

        map.put("success", true);
        map.put("status", status);
        map.put("statusMessage", statusMessage);
        return map;
    }

    /**
     *
     * Job 구동하기
     *
     * @param request request 객체
     * @param response response 객체
     * @return job 정보 JSON
     * @throws Exception
     */
    @RequestMapping(value="/job/start.do",  produces={"application/json; charset=UTF-8"})
    public @ResponseBody Map<String, Object> jobStart(HttpServletRequest request, HttpServletResponse response) throws Exception{

        boolean status = true;
        String jobName = ServletRequestUtils.getStringParameter(request, "jobName", "");
        String targetDate = ServletRequestUtils.getStringParameter(request, "targetDate", "");
        String statusMessage = "";

        Map<String, Object> map = new HashMap<String, Object>();

        if(!StringUtils.isEmpty(jobName) && !StringUtils.isEmpty(targetDate)) {

            try {
                batchMonitoringService.jobStart(jobName, targetDate);
            } catch (Exception e) {
                statusMessage = e.toString();
                status = false;
            }

        }else{
            status = false;
            statusMessage = "jobName or targetDate Error";
        }

        map.put("success", true);
        map.put("status", status);
        map.put("statusMessage", statusMessage);
        return map;
    }


    /**
     *
     * Job 을 중지한다.
     *
     * @param request request 객체
     * @param response response 객체
     * @return job 정보 JSON
     * @throws Exception
     */
    @RequestMapping(value="/job/stop.do",  produces={"application/json; charset=UTF-8"})
    public @ResponseBody Map<String, Object> jobStop(HttpServletRequest request, HttpServletResponse response) throws Exception{

        boolean status = true;
        int jobExecutionId = ServletRequestUtils.getIntParameter(request, "jobExecutionId", 0);

        Map<String, Object> map = new HashMap<String, Object>();
        String statusMessage = "";
        try {
            batchMonitoringService.jobStop(jobExecutionId);
        } catch (Exception e) {
            statusMessage = e.toString();
            status = false;
        }

        map.put("success", true);
        map.put("status", status);
        map.put("statusMessage", statusMessage);
        return map;
    }

    /**
     *
     * Job Name 리스트를 가져온다.
     *
     * @param request request 객체
     * @param response response 객체
     * @return 결과 JSON
     * @throws Exception
     */
    @RequestMapping(value="/jobName/list.do",  produces={"application/json; charset=UTF-8"})
    public @ResponseBody Map<String, Object> jobName(HttpServletRequest request, HttpServletResponse response) throws Exception{

        boolean status = true;

        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> jobNameList = new ArrayList<Object>();

        try {

            Set<String> list = jobOperator.getJobNames();
            for(String str : list) {
                Map<String, String> map2 = new HashMap<String, String>();
                map2.put("optionText", str);
                map2.put("optionValue", str);
                jobNameList.add(map2);
            }

        } catch (Exception e) {
            status = false;
        }

        map.put("success", true);
        map.put("status", status);
        map.put("data", jobNameList);
        return map;
    }

    /**
     *
     * rawdata (json file)를 restore 한다.
     *
     * @param request request 객체
     * @param response response 객체
     * @return 결과 JSON
     * @throws Exception
     */
    @RequestMapping(value="/rawdata/restore.do",  produces={"application/json; charset=UTF-8"})
    public @ResponseBody Map<String, Object> restore(HttpServletRequest request, HttpServletResponse response) throws Exception{

        boolean status = true;
        String filePath = ServletRequestUtils.getStringParameter(request, "filePath", "");

        String statusMessage = "";
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            batchMonitoringService.restoreRawData(filePath);
        } catch (Exception e) {
            statusMessage = e.toString();
            status = false;
        }

        map.put("success", true);
        map.put("status", status);
        map.put("statusMessage", statusMessage);
        return map;
    }
}